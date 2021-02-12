package com.example.accountoperationservice.repositories;

import com.example.accountoperationservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository<Operation, Long> {
    public Collection<Operation> findByCompteId(Long compteID);
}
