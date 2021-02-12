package com.example.accountoperationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double solde;
    private Date dateCreation;
    private String type;
    private String etat;
    @Transient
    @OneToMany(mappedBy= "operation")
    private Collection<Operation> operations;
    @Transient
    private Client client;
    private long clientID;
}
