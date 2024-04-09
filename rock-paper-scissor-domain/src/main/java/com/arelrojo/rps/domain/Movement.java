package com.arelrojo.rps.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movement {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Match match;

    private Integer moveOrder;

    @Enumerated(EnumType.STRING)
    private RPSMove robotType;

    @Enumerated(EnumType.STRING)
    private RPSMove humanType;

    private Boolean isFinal;
}
