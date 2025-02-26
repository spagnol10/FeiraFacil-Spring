package wesp.company.feirafacilapi.dto;

import wesp.company.feirafacilapi.domain.enums.EnumUserType;

public record RegisterAuthDTO(String email, String password, EnumUserType role) {
}
