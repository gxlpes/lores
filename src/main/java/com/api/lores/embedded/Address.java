package com.api.lores.embedded;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    private String mainAddress;

    private String numberAddress;

    private String neighborHood;

    private String complementInfo;

    private String zipCode;

}