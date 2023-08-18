package com.example.project.service;

import com.example.project.entity.UserDTO;
import com.example.project.request.AddUserReq;
import com.example.project.request.DetailReq;
import com.example.project.request.ListUserReq;
import com.example.project.request.UpdateUserReq;
import com.example.project.response.ApiResponse;
import com.example.project.response.DetailUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTO> findAllUser();

    void save(UserDTO theUserDTO);

    boolean isPhoneNumberTaken(String phoneNumber);

    boolean isEmailTaken(String email);

    boolean isUsernameTaken(String username);

    UserDTO findUserById(int theId);

    Optional<UserDTO> findUserByUserName(String username);

    ApiResponse<DetailUser> detailUserById(DetailReq req);

    ApiResponse<String> addNewUser(AddUserReq request);

    ApiResponse<String> updateUser(UpdateUserReq request);

    Optional<UserDTO> findByUsername(String username);

    ApiResponse<String> changeStatusUser(DetailReq request);

    ApiResponse<?> findByFullNameContaining(ListUserReq request);

}



