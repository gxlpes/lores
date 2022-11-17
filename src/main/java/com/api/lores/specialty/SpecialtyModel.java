package com.api.lores.specialty;

import lombok.Data;

import javax.persistence.*;

@Table(name = "specialties")
@Entity
@Data
public class SpecialtyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String title;

}