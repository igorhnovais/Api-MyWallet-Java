package com.mywallet.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mywallet.Api.model.Transaction;


@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long>{
   
}
