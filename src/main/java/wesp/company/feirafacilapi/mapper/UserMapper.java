package wesp.company.feirafacilapi.mapper;

import wesp.company.feirafacilapi.domain.dtos.user.request.UserRequest;
import wesp.company.feirafacilapi.domain.dtos.user.response.UserResponse;
import wesp.company.feirafacilapi.domain.entities.User;
import wesp.company.feirafacilapi.domain.enums.EnumUserType;

public class UserMapper {

    /**
     * Converte uma entidade User em um DTO UserResponse.
     *
     * @param user a entidade User
     * @return um UserResponse contendo os dados necessários
     */
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getImageUrl()
        );
    }

    /**
     * Converte um DTO UserRequest em uma entidade User.
     *
     * @param dto o DTO UserRequest
     * @return uma entidade User pronta para ser persistida
     */
    public static User toEntity(UserRequest dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPhoneNumber(dto.phoneNumber());
        user.setPassword(dto.password());
        user.setImageUrl(dto.imageUrl());
        user.setRole(EnumUserType.COSTUMER);
        user.setEnabled(true);
        user.update();
        return user;
    }
}