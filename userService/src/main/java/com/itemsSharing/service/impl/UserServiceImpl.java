package com.itemsSharing.service.impl;

import com.itemsSharing.config.ApiParameters;
import com.itemsSharing.dto.*;
import com.itemsSharing.jwt.JwtGenerator;
import com.itemsSharing.model.ResponseModel;
import com.itemsSharing.model.User;
import com.itemsSharing.model.UserRole;
import com.itemsSharing.repository.UserRepository;
import com.itemsSharing.repository.UserRoleRepository;
import com.itemsSharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AmazonClient amazonClient;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;

    @Override
    public ResponseEntity<?> saveNewUser(UserDTO userDTO) throws Exception {
        ResponseModel responseModel = new ResponseModel();
        Optional<User> userOpt = userRepository.getUserByUsernameForSignUp(userDTO.getUsername());
        if(userOpt.isPresent()){
            responseModel.setMessage("Username Already Exists!");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        if(userRepository.getUserByEmail(userDTO.getEmail()).isPresent()){
            responseModel.setMessage("Email Already Exists!");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }

        Optional<UserRole> optionalUserRole = userRoleRepository.findById(userDTO.getRoleId());

        if(!optionalUserRole.isPresent()){
            responseModel.setMessage("Invalid User Role");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }

        if(userDTO.getProfilePic().isEmpty()){
            responseModel.setMessage("Please Upload a profile pic");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
        }

        String profilePic = amazonClient.uploadFile(userDTO.getProfilePic(),true);

        String encryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setUserRole(optionalUserRole.get());
        newUser.setPassword(encryptedPassword);
        newUser.setProfilePic(profilePic);



        try {
            userRepository.save(newUser);
            responseModel.setMessage("User Added Successfully!");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public ResponseEntity<?> getUserFromToken(Principal principal){
        Optional<User> optionalUser = userRepository.findById(Integer.parseInt(principal.getName()));
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalied user id",false),HttpStatus.BAD_REQUEST);
        } else {
            String profileUrl = amazonClient.getUrlFromFileName(optionalUser.get().getProfilePic());
            User user = optionalUser.get();

            AuthToken authToken = new AuthToken();
            authToken.setAccessToken(principal.getName());
            authToken.setRefreshToken(user.getRefeshToken());

            DisplayUserDTO displayUserDTO = new DisplayUserDTO();
            displayUserDTO.setName(user.getName());
            displayUserDTO.setUserName(user.getUsername());
            displayUserDTO.setProfileUrl(profileUrl);
            displayUserDTO.setEmail(user.getEmail());
            displayUserDTO.setUserId(user.getUserId());
            displayUserDTO.setAuthToken(authToken);
            displayUserDTO.setUserRole(user.getUserRole());

            return new ResponseEntity<>(displayUserDTO,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> userLogin(LoginDTO loginDTO) {
        ResponseModel responseModel = new ResponseModel();
        Optional<User> optionalUser = userRepository.getUserByUsername(loginDTO.getUsername());
        if(!optionalUser.isPresent()){
            responseModel.setMessage("Invalid Login Credentials OR User Is Not Active");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.UNAUTHORIZED);
        }

        if(bCryptPasswordEncoder.matches(loginDTO.getPassword(), optionalUser.get().getPassword())) {
            String accessToken = createJWtWithOutPrefix(optionalUser.get());
            if (accessToken == null) {
                responseModel.setMessage("Something went Wrong");
                responseModel.setStatus(false);
                return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String refreshToken = createRefreshToken(optionalUser.get());
            if (refreshToken == null) {
                responseModel.setMessage("Something went Wrong");
                responseModel.setStatus(false);
                return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            AuthToken authToken = new AuthToken();
            authToken.setAccessToken(accessToken);
            authToken.setRefreshToken(refreshToken);

            String profileUrl = amazonClient.getUrlFromFileName(optionalUser.get().getProfilePic());

            DisplayUserDTO displayUserDTO = new DisplayUserDTO();
            displayUserDTO.setAuthToken(authToken);
            displayUserDTO.setEmail(optionalUser.get().getEmail());
            displayUserDTO.setUserId(optionalUser.get().getUserId());
            displayUserDTO.setName(optionalUser.get().getName());
            displayUserDTO.setUserName(optionalUser.get().getUsername());
            displayUserDTO.setUserRole(optionalUser.get().getUserRole());
            displayUserDTO.setProfileUrl(profileUrl);

            return new ResponseEntity<>(displayUserDTO, HttpStatus.OK);
        } else {
            responseModel.setMessage("Invalid Password Entered");
            responseModel.setStatus(false);
            return new ResponseEntity<>(responseModel,HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<DisplayOneUserDTO> userDataDTOList = new ArrayList<>();
            for(User user : userList){
                DisplayOneUserDTO displayUserDataDTO = new DisplayOneUserDTO();
                displayUserDataDTO.setUserId(user.getUserId());
                displayUserDataDTO.setName(user.getName());
                displayUserDataDTO.setEmail(user.getEmail());
                displayUserDataDTO.setUsername(user.getUsername());

                String profilePic = amazonClient.getUrlFromFileName(user.getProfilePic());

                displayUserDataDTO.setProfilePic(profilePic);

                userDataDTOList.add(displayUserDataDTO);
            }

            return new ResponseEntity<>(userDataDTOList,HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> getOneUser(int userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User user = optionalUser.get();
        DisplayOneUserDTO displayUserDataDTO = new DisplayOneUserDTO();
        displayUserDataDTO.setUserId(user.getUserId());
        displayUserDataDTO.setName(user.getName());
        displayUserDataDTO.setEmail(user.getEmail());
        displayUserDataDTO.setUserRole(user.getUserRole().getUserRoleType());
        displayUserDataDTO.setUsername(user.getUsername());

        String profilePic = amazonClient.getUrlFromFileName(user.getProfilePic());

        displayUserDataDTO.setProfilePic(profilePic);

        return new ResponseEntity<>(displayUserDataDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> activateAUser(int userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(new ResponseModel("Invalid User Id",false), HttpStatus.BAD_REQUEST);
        } else{
            User user = optionalUser.get();
            user.setActive(true);
            userRepository.save(user);
            return new ResponseEntity<>(new ResponseModel("User Activated successfully",true),HttpStatus.OK);
        }
    }

    private String createJWtWithOutPrefix(User user) {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getUserRole().getUserRoleType()));
        String accessToken = null;
        try {
            accessToken = JwtGenerator.genarateAccessJWT( user.getUsername(),Integer.toString(user.getUserId()), grantedAuthorities, ApiParameters.JWT_EXPIRATION, ApiParameters.JWT_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    private String createRefreshToken(User user) {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().getUserRoleType()));
        String refreshToken = JwtGenerator.generateRefreshToken(user.getUsername(),Integer.toString(user.getUserId()),grantedAuthorityList,ApiParameters.REFRESH_TOKEN_EXPIRATION,ApiParameters.JWT_SECRET);
        try {
            int i = userRepository.updateRefreshToken(user.getUsername(), refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refreshToken;
    }

}
