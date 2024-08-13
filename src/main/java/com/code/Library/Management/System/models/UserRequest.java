package com.code.Library.Management.System.models;

import com.code.Library.Management.System.enums.Status;
import lombok.*;

import java.util.Date;
@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Status status;
    private String emailAddress;
    private String phoneNumber;
}
