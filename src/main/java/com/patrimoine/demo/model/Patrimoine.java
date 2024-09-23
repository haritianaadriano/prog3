package com.patrimoine.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Patrimoine {
    private String id;
    private String possesseur;
    private Instant derniereModification;

    @Override
    public String toString() {
        return "id: " + id + " possesseur: " + possesseur + " derniereModification: " + derniereModification;
    }
}
