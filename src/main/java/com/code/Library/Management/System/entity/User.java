package com.code.Library.Management.System.entity;


import com.code.Library.Management.System.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String  lastName;

    @Column(name = "dob")
    private Date  dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "emailAddress")
    private String emailAddress;

     @Column(name = "phoneNumber")
    private String phoneNumber;

}
