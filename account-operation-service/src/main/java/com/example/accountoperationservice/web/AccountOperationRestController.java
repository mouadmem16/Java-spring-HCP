package com.example.accountoperationservice.web;

import com.example.accountoperationservice.entities.Client;
import com.example.accountoperationservice.entities.Compte;
import com.example.accountoperationservice.entities.Operation;
import com.example.accountoperationservice.repositories.CompteRepository;
import com.example.accountoperationservice.repositories.OperationRepository;
import com.example.accountoperationservice.service.ClientServiceClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
public class AccountOperationRestController {
    @Autowired private ClientServiceClient clientServiceClient;
    @Autowired private CompteRepository compteRepository;
    @Autowired private OperationRepository operationRepository;

    @PostMapping("/creerCompte")
    @Transactional
    public Compte creerCompte(@RequestBody CompteForm compteForm){
        Compte compte = new Compte();
        compte.setClient(clientServiceClient.findClientById(compteForm.getIdClient()));
        compte.setDateCreation(new Date());
        compte.setSolde(compteForm.getSolde());
        compte.setEtat("ACTIVE");
        compte.setType(compteForm.getType());
        compte.setClientID(compteForm.getIdClient());
        compteRepository.save(compte);
        return compte;
    }

    @PostMapping("/versement")
    @Transactional
    public Compte versement(@RequestBody VersementForm versementForm){
        Operation operation = new Operation();
        operation.setDateOperation(new Date());
        operation.setType("CREDIT");
        operation.setMontant(versementForm.getMontant());

        Compte compte = compteRepository.findById(versementForm.getIdCompte()).get();
        compte.setSolde(compte.getSolde() + versementForm.getMontant());
        compte.setClient(clientServiceClient.findClientById(compte.getClientID()));

        operation.setCompte(compte);
        compteRepository.save(compte);
        operationRepository.save(operation);
        return compte;
    }

    @PostMapping("/retrait")
    @Transactional
    public Compte retrait(@RequestBody RetraitForm retraitForm){
        Operation operation = new Operation();
        operation.setDateOperation(new Date());
        operation.setType("DEBIT");
        operation.setMontant(retraitForm.getMontant());

        Compte compte = compteRepository.findById(retraitForm.getIdCompte()).get();
        compte.setSolde(compte.getSolde() - retraitForm.getMontant());
        compte.setClient(clientServiceClient.findClientById(compte.getClientID()));

        operation.setCompte(compte);
        compteRepository.save(compte);
        operationRepository.save(operation);
        return compte;
    }

    @PostMapping("/virement")
    @Transactional
    public String virement(@RequestBody VirementForm virementForm){
        Operation operation1 = new Operation();
        operation1.setDateOperation(new Date());
        operation1.setType("DEBIT");
        operation1.setMontant(virementForm.getMontant());

        Compte compte_sender = compteRepository.findById(virementForm.getIdCompte_sender()).get();
        compte_sender.setSolde(compte_sender.getSolde() - virementForm.getMontant());
        compte_sender.setClient(clientServiceClient.findClientById(compte_sender.getClientID()));

        operation1.setCompte(compte_sender);



        compteRepository.save(compte_sender);
        operationRepository.save(operation1);

        Operation operation2 = new Operation();
        operation2.setDateOperation(new Date());
        operation2.setType("CREDIT");
        operation2.setMontant(virementForm.getMontant());

        Compte compte_receiver = compteRepository.findById(virementForm.getIdCompte_receiver()).get();
        compte_receiver.setSolde(compte_receiver.getSolde() + virementForm.getMontant());
        compte_receiver.setClient(clientServiceClient.findClientById(compte_receiver.getClientID()));

        operation2.setCompte(compte_receiver);



        compteRepository.save(compte_receiver);
        operationRepository.save(operation2);

        return "Virement fait avec success!!";
    }

    /*@GetMapping("/compte/{id}/operations")
    public List<Operation> getCompteOperations(@PathVariable(name="id") Long id){
        compteRepository
        return clientServiceClient;
    }*/

    @GetMapping("/user/full/{id}")
    public Client testGetUser(@PathVariable(name="id") Long id){
        return clientServiceClient.findClientById(id);
    }

    @GetMapping("/consulterCompte/{id}")
    public Compte consulterCompte(@PathVariable(name="id") Long id){
        Compte compte = compteRepository.findById(id).get();
        compte.setClient(clientServiceClient.findClientById(compte.getClientID()));
        return compte;
    }
}

@Data
class CompteForm{
    private Long idClient;
    private double solde;
    private String type;
}

@Data
class VersementForm{
    private Long idCompte;
    private double montant;
}

@Data
class RetraitForm{
    private Long idCompte;
    private double montant;
}

@Data
class VirementForm{
    private Long idCompte_sender;
    private Long idCompte_receiver;
    private double montant;
}