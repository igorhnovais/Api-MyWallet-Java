package com.mywallet.Api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mywallet.Api.dto.TransactionDTO;
import com.mywallet.Api.model.Transaction;

import com.mywallet.Api.service.TransactionsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transactions")
public class TransactionsController {
    
    @Autowired
    private TransactionsService service;
    

    @PersistenceContext
    private EntityManager entityManager;
    

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllByUserId(){
        var obj = service.findByUserId(1);
        if(obj != null) {
            return ResponseEntity.ok().body(obj);
        } else {
           return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping("/new-entry")
    public ResponseEntity<String> saveNewEntry(@RequestBody @Valid TransactionDTO transactionDTO){
        service.saveNewEntry(transactionDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body("criado com sucesso!");
    } 

    @PostMapping("/new-exit")
    public ResponseEntity<String> saveNewExit(@RequestBody @Valid TransactionDTO transactionDTO){
        service.saveNewExit(transactionDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body("criado com sucesso!");
    } 

    @PutMapping("/{id}")
    public String updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO req){
        service.update(id, req);
        return "Atualizado";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public String deleteTransaction(@PathVariable Long id){
        service.delete(id);
        return "Deletado!";
    }
}
