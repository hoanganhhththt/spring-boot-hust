package com.example.project.service;

import com.example.project.dao.RoleRepository;
import com.example.project.dao.UserRepository;
import com.example.project.entity.RoleDTO;
import com.example.project.entity.UserDTO;
import com.example.project.request.AddUserReq;
import com.example.project.request.DetailReq;
import com.example.project.request.ListUserReq;
import com.example.project.request.UpdateUserReq;
import com.example.project.response.ApiResponse;
import com.example.project.response.DetailUser;
import com.example.project.response.ListUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, RoleRepository theRoleRepository) {
        this.userRepository = theUserRepository;
        this.roleRepository = theRoleRepository;
    }

    @Override
    public List<UserDTO> findAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void save(UserDTO theUserDTO) {
        userRepository.save(theUserDTO);
    }

    @Override
    public boolean isPhoneNumberTaken(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }


    @Override
    public UserDTO findUserById(int theId) {
        Optional<UserDTO> result = userRepository.findById(theId);
        UserDTO theUserDTO = null;
        if(result.isPresent()) {
            theUserDTO = result.get();
        } else{
            throw new RuntimeException("Did not find user id -" + theId);
        }
        return theUserDTO;
    }

    @Override
    public Optional<UserDTO> findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ApiResponse<DetailUser> detailUserById(DetailReq req) {
        Optional<UserDTO> user = userRepository.findById(req.getId());
        List<Integer> roles = new ArrayList<>();
        for(RoleDTO roleDTO : user.get().getRoles()) {
            roles.add(roleDTO.getId());
        }
        DetailUser result = new DetailUser(user.get().getId(),user.get().getUserName(), user.get().getFullName(), user.get().getEmail(), user.get().getPhoneNumber(), user.get().getBankName(), user.get().getAccountNumber(), user.get().getStatus(), roles);
        ApiResponse<DetailUser> response = new ApiResponse<>();
        response.setCode("00");
        response.setData(result);
        response.setMessage("");
        return response;
    }

    @Transactional
    @Override
    public ApiResponse<String> addNewUser(AddUserReq request) {
        if(userRepository.existsByUsername(request.getUserName())) {
            ApiResponse<String> response = new ApiResponse<String>("11", "", "Tên đăng nhập đã được đăng kí");
            return response;
        }
        else if(userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            ApiResponse<String> response = new ApiResponse<String>("11", "", "Số điện thoại đã được đăng kí");
            return response;
        }
        else if(userRepository.existsByEmail(request.getEmail())) {
            ApiResponse<String> response = new ApiResponse<String>("12", "", "Email đã được đăng kí");
            return response;
        } else {
            UserDTO userDTOReq = new UserDTO(request.getUserName(), request.getFullName(),request.getEmail(), request.getPhoneNumber(), request.getPassword(), request.getAccountNumber(), request.getBankName(), request.getIsAdmin(), request.getStatus());
            for(Integer theId: request.getRoles()) {
                Optional<RoleDTO> role = roleRepository.findById(theId);
                userDTOReq.addRole(role.get());
            }
            userRepository.save(userDTOReq);
            ApiResponse<String> response = new ApiResponse<String>();
            response.setCode("00");
            response.setMessage("Thêm mới User thành công");
            return response;
        }
    }

    @Transactional
    @Override
    public ApiResponse<String> updateUser(UpdateUserReq request) {
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            ApiResponse<String> response = new ApiResponse<String>("11", "", "Số điện thoại đã được đăng kí");
            return response;
        }
        else if(userRepository.existsByEmail(request.getEmail())) {
            ApiResponse<String> response = new ApiResponse<String>("12", "", "Email đã được đăng kí");
            return response;
        } else {
            Optional<UserDTO> user = userRepository.findById(request.getId());
            user.get().setFullName(request.getFullName());
            user.get().setEmail(request.getEmail());
            user.get().setAccountNumber(request.getAccountNumber());
            user.get().setBankName(request.getBankName());
            for(Integer theId: request.getRoles()) {
                Optional<RoleDTO> role = roleRepository.findById(theId);
                user.get().addRole(role.get());
            }
            userRepository.save(user.get());
            ApiResponse<String> response = new ApiResponse<String>();
            response.setCode("00");
            response.setMessage("Cập nhật User thành công");
            return response;
        }
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public ApiResponse<String> changeStatusUser(DetailReq request) {
        Optional<UserDTO> user = userRepository.findById(request.getId());
        if(user.get().getStatus() == 1) {
            user.get().setStatus(2);
        } else {
            user.get().setStatus(1);
        }
        userRepository.save(user.get());
        ApiResponse<String> response = new ApiResponse<String>();
        response.setCode("00");
        response.setMessage("Cập nhật trạng thái User thành công");
        return response;
    }

    @Override
    public ApiResponse<?> findByFullNameContaining(ListUserReq request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<UserDTO> users = userRepository.findByFullNameContaining(request.getFullName(), pageable);
        List<UserDTO> data = users.getContent();
        if(data.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>("00", "", "Thành công");
            return response;
        } else {
            int totalRows = (int) users.getTotalElements();
            List<DetailUser> result = new ArrayList<>();
            for(UserDTO userDTO : data) {
                List<Integer> roles = new ArrayList<>();
                for(RoleDTO roleDTO : userDTO.getRoles()) {
                    roles.add(roleDTO.getId());
                }
                DetailUser detailUser = new DetailUser(userDTO.getId(), userDTO.getUserName(), userDTO.getFullName(), userDTO.getEmail(), userDTO.getPhoneNumber(), userDTO.getBankName(), userDTO.getAccountNumber(), userDTO.getStatus(), roles);
                result.add(detailUser);
            }
            ListUserRes list = new ListUserRes(result, totalRows);
            System.out.println(list);
            ApiResponse<ListUserRes> response = new ApiResponse<>("00", list, "Thành công");
            return response;
        }
    }

}
