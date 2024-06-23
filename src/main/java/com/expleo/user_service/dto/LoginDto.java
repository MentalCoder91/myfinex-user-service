package com.expleo.user_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Login Dto",
        description = "Schema to hold User login data"
)
public class LoginDto {

    @Schema(
            description = "Name of the user", example = "Anish Alwekar"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the user name should be between 5 and 30")
    private String userName;


    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the user name should be between 5 and 30")
    private String password;
}
