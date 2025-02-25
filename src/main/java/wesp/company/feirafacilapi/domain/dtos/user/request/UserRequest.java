package wesp.company.feirafacilapi.domain.dtos.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank(message = "Name is null or empty")
        @NotNull(message = "Name is required")
        String name,

        @NotBlank(message = "Email is null or empty")
        @NotNull(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotNull(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Password is mandatory")
        @NotNull(message = "Name is required")
        String password,
        String imageUrl
) {}