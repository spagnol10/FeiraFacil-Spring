package wesp.company.feirafacilapi.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wesp.company.feirafacilapi.domain.dtos.user.request.UserRequest;
import wesp.company.feirafacilapi.domain.dtos.user.response.UserResponse;
import wesp.company.feirafacilapi.domain.entities.User;
import wesp.company.feirafacilapi.service.UserService;
import wesp.company.feirafacilapi.utils.PaginationUtils;

@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user in the system")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid user data")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        logger.info("Received request to create user with email: {}", userRequest.email());

        try {
            UserResponse response = userService.createUser(userRequest);
            logger.info("User created successfully with email: {}", response.email());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error occurred while creating user with email: {}", userRequest.email(), e);
            throw e;
        }
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Fetches a paginated list of all users")
    @ApiResponse(responseCode = "200", description = "Successfully fetched users", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        logger.info("Received request to fetch users with page: {}, size: {}, sort: {}", page, size, sort);

        Pageable pageable = PaginationUtils.createPageableRequest(page, size, sort);

        try {
            Page<UserResponse> users = userService.getAllUsers(pageable);
            logger.info("Fetched {} users", users.getTotalElements());
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error occurred while fetching users", e);
            throw e;
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        try {
            User users = userService.findUserByName(name);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            logger.error("Error occurred while fetching users", e);
            throw e;
        }
    }

    @PatchMapping("/{email}")
    @Operation(summary = "Update user", description = "Updates an existing user's details")
    @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String email,
            @RequestBody @Valid UserRequest dto) {

        logger.info("Received request to update user with email: {}", email);

        try {
            UserResponse response = userService.updateUser(email, dto);
            logger.info("User updated successfully with email: {}", email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred while updating user with email: {}", email, e);
            throw e;
        }
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user", description = "Deletes a user by their email")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        logger.info("Received request to delete user with email: {}", email);

        try {
            userService.deleteUser(email);
            logger.info("User deleted successfully with email: {}", email);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting user with email: {}", email, e);
            throw e;
        }
    }
}
