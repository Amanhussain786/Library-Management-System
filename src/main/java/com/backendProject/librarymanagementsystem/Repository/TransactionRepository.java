package com.backendProject.librarymanagementsystem.Repository;

import com.backendProject.librarymanagementsystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
