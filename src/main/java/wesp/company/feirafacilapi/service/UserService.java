package wesp.company.feirafacilapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import wesp.company.feirafacilapi.domain.dtos.user.request.UserRequest;
import wesp.company.feirafacilapi.domain.dtos.user.response.UserResponse;
import wesp.company.feirafacilapi.domain.entities.User;
import wesp.company.feirafacilapi.exception.ApiException;
import wesp.company.feirafacilapi.exception.EnumErrorMessages;
import wesp.company.feirafacilapi.mapper.UserMapper;
import wesp.company.feirafacilapi.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        try {
            User newUser = UserMapper.toEntity(userRequest);
            return UserMapper.toResponse(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException(HttpStatus.CONFLICT.value(),
                    EnumErrorMessages.EMAIL_ALREADY_EXISTS.getErrorCode(),
                    EnumErrorMessages.EMAIL_ALREADY_EXISTS.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(),
                    EnumErrorMessages.INVALID_INPUT.getErrorCode(),
                    EnumErrorMessages.INVALID_INPUT.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "USER_CREATION_FAILED",
                    EnumErrorMessages.USER_CREATION_FAILED.getMessage());
        }
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {

        try {
            Page<UserResponse> users = userRepository.findAllUsersByOrderByNameAsc(pageable);
            return users;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    EnumErrorMessages.FETCHING_USERS_FAILED.getErrorCode(),
                    EnumErrorMessages.FETCHING_USERS_FAILED.getMessage());
        }
    }

    public UserResponse updateUser(String email, UserRequest dto) {

        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND.value(),
                            EnumErrorMessages.USER_NOT_FOUND.getErrorCode(),
                            String.format(EnumErrorMessages.USER_NOT_FOUND.getMessage(), email)));

            if (dto.name() != null)
                user.setName(dto.name());
            if (dto.email() != null)
                user.setEmail(dto.email());
            if (dto.phoneNumber() != null)
                user.setPhoneNumber(dto.phoneNumber());
            if (dto.imageUrl() != null)
                user.setImageUrl(dto.imageUrl());

            user.update();
            userRepository.save(user);

            return UserMapper.toResponse(user);
        } catch (ApiException e) {
            throw new ApiException(HttpStatus.NOT_FOUND.value(),
                    EnumErrorMessages.USER_EMAIL_NOT_FOUND.getErrorCode(),
                    EnumErrorMessages.USER_EMAIL_NOT_FOUND.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    EnumErrorMessages.USER_UPDATE_FAILED.getErrorCode(),
                    EnumErrorMessages.USER_UPDATE_FAILED.getMessage());
        }
    }

    public void deleteUser(String email) {

        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND.value(),
                            EnumErrorMessages.USER_EMAIL_NOT_FOUND.getErrorCode(),
                            String.format(EnumErrorMessages.USER_EMAIL_NOT_FOUND.getMessage(), email)));
            userRepository.delete(user);
        } catch (ApiException e) {
            throw new ApiException(HttpStatus.NOT_FOUND.value(),
                    EnumErrorMessages.USER_EMAIL_NOT_FOUND.getErrorCode(),
                    EnumErrorMessages.USER_EMAIL_NOT_FOUND.getMessage());
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error deleting user", e.getMessage());
        }
    }

    public User findUserByName(String name) {
        try {
            User user = userRepository.findByName(name)
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND.value(),
                            EnumErrorMessages.USER_NAME_NOT_FOUND.getErrorCode(),
                            EnumErrorMessages.USER_NAME_NOT_FOUND.getMessage()));

            return user;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error fetching user by name",
                    e.getMessage());
        }
    }

    public User getUserByName(User userDTO) {

        try {
            User user = userRepository.findByName(userDTO.getName())
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND.value(),
                            EnumErrorMessages.USER_NAME_NOT_FOUND.getErrorCode(),
                            EnumErrorMessages.USER_NAME_NOT_FOUND.getMessage()));

            return user;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error fetching user by name",
                    e.getMessage());
        }
    }
}
