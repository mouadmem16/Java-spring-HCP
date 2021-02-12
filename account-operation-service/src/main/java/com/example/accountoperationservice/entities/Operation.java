package com.example.accountoperationservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double montant;
    private Date dateOperation;
    private String type;
    @ManyToOne
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Compte compte;

}
