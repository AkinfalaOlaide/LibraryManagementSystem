package com.code.Library.Management.System.entity;


import com.code.Library.Management.System.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user id")
    private Long id;

    @Column(name = "first name")
    private String firstName;

    @Column(name = "last name")
    private String  lastName;

    @Column(name = "dob")
    private Date  dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "email address")
    private String emailAddress;

     @Column(name = "phone number")
    private String phoneNumber;

}
