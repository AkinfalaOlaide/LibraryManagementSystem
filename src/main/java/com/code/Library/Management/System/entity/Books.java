package com.code.Library.Management.System.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "date of release")
    private Date dateOfRelease;

    @Column(name = "author's name")
    private String authorName;

    @Column(name = "copies")
    private int copies;


}
