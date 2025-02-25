package wesp.company.feirafacilapi.exception;

import lombok.Getter;

@Getter
public enum EnumErrorMessages {

    EMAIL_ALREADY_EXISTS("A user with this email already exists. Please choose a different email.", "Email already exists"),
    USER_CREATION_FAILED("Error occurred while creating the user.", "User creation failed"),
    INVALID_INPUT("Invalid input data.", "Invalid input"),
    USER_NOT_FOUND("The user with email %s does not exist.", "User not found"),
    USER_EMAIL_NOT_FOUND("The user with email does not found.", "User email not found"),
    USER_NAME_NOT_FOUND("The user with name does not found.", "User name not found"),
    USER_FETCH_FAILED("Error occurred while fetching the user with name %s.", "User fetch failed"),
    USER_DELETION_FAILED("Error occurred while deleting the user with email %s.", "User deletion failed"),
    USER_UPDATE_FAILED("Error occurred while updating the user with email %s.", "User update failed"),
    FETCHING_USERS_FAILED("Error occurred while fetching all users.", "Fetching users failed");

    private final String message;
    private final String errorCode;

    EnumErrorMessages(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
