package com.arelrojo.rps.domain;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Movement {

    @Id
    private Long id;

    @ManyToOne
    private Match match;

    private Integer order;

    private String robotType;

    private String humanType;

    private Boolean isFinal;
}
