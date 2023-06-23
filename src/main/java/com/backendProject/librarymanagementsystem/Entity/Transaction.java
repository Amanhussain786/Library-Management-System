package com.backendProject.librarymanagementsystem.Entity;

import com.backendProject.librarymanagementsystem.Enum.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    private String transactionNumber;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    private String transactionMessage;

    private boolean isIssuedOperation;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Book book;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    LibraryCard card;

}
