package com.code.Library.Management.System.models;

import com.code.Library.Management.System.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class UserRequest {
    @NotBlank(message = " Firstname cannot be empty")
    @Size(min = 2,max= 17,message = "Firstname must be between 2 to 17 characters")
    private String firstName;

    @NotBlank(message = " Lastname cannot be empty")
    @Size(min = 2,max= 25,message = "LastName must be between 2 to 25 characters")
    private String lastName;

    @NotNull(message = " Date cannot be blank")
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private Date dateOfBirth;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    private Status status;

    @Email(message = "The email is invalid, please enter a valid email")
    @NotBlank(message = "Email cannot be blank")
    private String emailAddress;

    @NotBlank(message = "PhoneNumber cannot be blank")
    @Size(min = 11, max = 11, message = "Invalid PhoneNumber")
    private String phoneNumber;
}
