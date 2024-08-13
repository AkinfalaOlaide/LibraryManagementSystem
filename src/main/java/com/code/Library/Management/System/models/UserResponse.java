package com.code.Library.Management.System.models;

import com.code.Library.Management.System.enums.Status;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
   private String firstName;
   private String lastName;
    private Date dateOfBirth;
    private Status status;
    private String emailAddress;
    private String phoneNumber;

}
