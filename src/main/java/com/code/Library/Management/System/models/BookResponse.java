package com.code.Library.Management.System.models;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookResponse {
    private Long id;

    private String name;
    private Date dateOfRelease;
    private String authorName;
    private int copies;


}
