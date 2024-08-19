package com.code.Library.Management.System.models;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {
    @NotEmpty(message = " Name cannot be empty")
    @Size(min = 6,max=20,message = "Name must be between 6 to 20 characters")
    private String name;

    @NotEmpty(message = " Author's name cannot be empty")
    @Size(min = 10,max=30,message = "Name must be between 10 to 30 characters")
    private String authorName;

    @NotNull(message = " Date cannot be null")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateOfRelease;

    @NotNull(message = "Copies cannot be null")
    @Max(value = 50 ,message = "Excess book in Library")
    @Min(value = 30 , message = "Shortage of Book in Library")
    private int copies;


}
