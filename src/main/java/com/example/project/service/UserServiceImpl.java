package com.example.project.service;

import com.example.project.dao.UserDAO;
import com.example.project.entity.Transaction;
import com.example.project.entity.User;
import com.example.project.request.AddUserReq;
import com.example.project.request.DetailReq;
import com.example.project.request.ListUserReq;
import com.example.project.request.UpdateUserReq;
import com.example.project.response.ApiResponse;
import com.example.project.response.DetailUser;
import com.example.project.response.ListUserRes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserSerive{
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO) {
        userDAO = theUserDAO;
    }

    @Override
    public List<User> findAllUser() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public void save(User theUser) {
        userDAO.save(theUser);
    }

    @Override
    public boolean isPhoneNumberTaken(String phoneNumber) {
        return userDAO.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userDAO.existsByEmail(email);
    }

    @Override
    public User findUserById(int theId) {
        Optional<User> result = userDAO.findById(theId);
        User theUser = null;
        if(result.isPresent()) {
            theUser = result.get();
        } else{
            throw new RuntimeException("Did not find user id -" + theId);
        }
        return theUser;
    }

    @Override
    public ApiResponse<DetailUser> detailUserById(DetailReq req) {
        Optional<User> user = userDAO.findById(req.getId());
        DetailUser result = new DetailUser(user.get().getId(), user.get().getFullName(), user.get().getEmail(), user.get().getPhoneNumber(), user.get().getBankName(), user.get().getAccountNumber(), user.get().getStatus());
        ApiResponse<DetailUser> response = new ApiResponse<>();
        response.setCode("00");
        response.setData(result);
        response.setMessage("");
        return response;
    }

    @Transactional
    @Override
    public ApiResponse<String> addNewUser(AddUserReq request) {
        if(userDAO.existsByPhoneNumber(request.getPhoneNumber())) {
            ApiResponse<String> response = new ApiResponse<String>("11", "", "Số điện thoại đã được đăng kí");
            return response;
        }
        else if(userDAO.existsByEmail(request.getEmail())) {
            ApiResponse<String> response = new ApiResponse<String>("12", "", "Email đã được đăng kí");
            return response;
        } else {
            User userReq = new User(request.getFullName(),request.getEmail(), request.getPhoneNumber(), request.getPassword(), request.getAccountNumber(), request.getBankName(), request.getIsAdmin(), request.getStatus());
            userDAO.save(userReq);
            ApiResponse<String> response = new ApiResponse<String>();
            response.setCode("00");
            response.setMessage("Thêm mới User thành công");
            return response;
        }
    }

    @Transactional
    @Override
    public ApiResponse<String> updateUser(UpdateUserReq request) {
        if(userDAO.existsByPhoneNumber(request.getPhoneNumber())) {
            ApiResponse<String> response = new ApiResponse<String>("11", "", "Số điện thoại đã được đăng kí");
            return response;
        }
        else if(userDAO.existsByEmail(request.getEmail())) {
            ApiResponse<String> response = new ApiResponse<String>("12", "", "Email đã được đăng kí");
            return response;
        } else {
            Optional<User> user = userDAO.findById(request.getId());
            user.get().setFullName(request.getFullName());
            user.get().setEmail(request.getEmail());
            user.get().setAccountNumber(request.getAccountNumber());
            user.get().setBankName(request.getBankName());
            userDAO.save(user.get());
            ApiResponse<String> response = new ApiResponse<String>();
            response.setCode("00");
            response.setMessage("Cập nhật User thành công");
            return response;
        }
    }

    @Transactional
    @Override
    public ApiResponse<String> changeStatusUser(DetailReq request) {
        Optional<User> user = userDAO.findById(request.getId());
        if(user.get().getStatus() == 1) {
            user.get().setStatus(2);
        } else {
            user.get().setStatus(1);
        }
        userDAO.save(user.get());
        ApiResponse<String> response = new ApiResponse<String>();
        response.setCode("00");
        response.setMessage("Cập nhật trạng thái User thành công");
        return response;
    }

    @Override
    public ApiResponse<?> findByFullNameContaining(ListUserReq request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<User> users = userDAO.findByFullNameContaining(request.getFullName(), pageable);
        List<User> data = users.getContent();
        if(data.isEmpty()) {
            ApiResponse<String> response = new ApiResponse<>("00", "", "Thành công");
            return response;
        } else {
            int totalRows = (int) users.getTotalElements();
            List<DetailUser> result = new ArrayList<>();
            for(User user: data) {
                DetailUser detailUser = new DetailUser(user.getId(),user.getFullName(),user.getEmail(),user.getPhoneNumber(), user.getBankName(), user.getAccountNumber(), user.getStatus());
                result.add(detailUser);
            }
            ListUserRes list = new ListUserRes(result, totalRows);
            System.out.println(list);
            ApiResponse<ListUserRes> response = new ApiResponse<>("00", list, "Thành công");
            return response;
        }
    }
}
