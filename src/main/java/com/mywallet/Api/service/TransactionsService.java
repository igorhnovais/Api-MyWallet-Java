package com.mywallet.Api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mywallet.Api.dto.TransactionDTO;
import com.mywallet.Api.model.Transaction;
import com.mywallet.Api.repository.TransactionsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Transaction> findByUserId(int idUser){
       Query query = entityManager.createNativeQuery("SELECT * FROM testeeee WHERE user_id=:idUser");
        query.setParameter("idUser", idUser);

        return query.getResultList();
    }

    public List<Transaction> findAll(){
        return repository.findAll();
    }

    public void saveNewEntry(TransactionDTO transactionDTO){

        var transaction = new Transaction();
        BeanUtils.copyProperties(transactionDTO, transaction);
        ResponseEntity.status(HttpStatus.CREATED).body(repository.save(transaction));
    }
}
