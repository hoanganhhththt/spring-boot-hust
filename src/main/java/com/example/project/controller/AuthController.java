package com.example.project.controller;

import com.example.project.dao.RoleRepository;
import com.example.project.entity.RoleDTO;
import com.example.project.entity.UserDTO;
import com.example.project.request.AddUserReq;
import com.example.project.request.LoginReq;
import com.example.project.response.ApiResponse;
import com.example.project.response.AuthResponseDTO;
import com.example.project.security.JWTGenerator;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private JWTGenerator jwtGenerator;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ApiResponse<String> register(@RequestBody @Valid AddUserReq request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            ApiResponse<String> response = new ApiResponse<String>("11", "", errorMessage);
            return response;
        } else {
            if(userService.isUsernameTaken(request.getUserName())) {
                return new ApiResponse<String>("10", "Tên đăng nhập đã được đăng kí", "Tên đăng nhập đã được đăng kí");
            } else{
                UserDTO user = new UserDTO();
                user.setUserName(request.getUserName());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setFullName(request.getFullName());
                user.setEmail(request.getEmail());
                user.setPhoneNumber(request.getPhoneNumber());
                user.setAccountNumber(request.getAccountNumber());
                user.setBankName(request.getBankName());
                user.setStatus(request.getStatus());
                user.setIsAdmin(request.getIsAdmin());
//                for(Integer roleId: request.getRoles()) {
//                    RoleDTO role = roleRepository.findById(roleId).get();
//                    user.addRole(role);
//                }
                RoleDTO roles = roleRepository.findByName("USER").get();
                user.setRoles(Collections.singletonList(roles));
                userService.save(user);
                return new ApiResponse<String>("00", "Thêm mới User thành công", "Thêm mới User thành công");

            }
        }
    }

    @PostMapping("login")
    public ApiResponse<?> login(@RequestBody LoginReq loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        AuthResponseDTO res = new AuthResponseDTO(token);
        return new ApiResponse<AuthResponseDTO>("00", res, "Đăng nhập thành công");
    }
}
