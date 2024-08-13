package com.code.Library.Management.System.models;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class BookRequest {
    private String name;
    private String authorName;
    private String dateOfRelease;
    private int copies;
}
