package com.backendProject.librarymanagementsystem.Service;

import com.backendProject.librarymanagementsystem.DTO.IssueBookRequestDto;
import com.backendProject.librarymanagementsystem.DTO.IssueBookResponseDto;
import com.backendProject.librarymanagementsystem.Entity.Book;
import com.backendProject.librarymanagementsystem.Entity.LibraryCard;
import com.backendProject.librarymanagementsystem.Entity.Transaction;
import com.backendProject.librarymanagementsystem.Enum.CardStatus;
import com.backendProject.librarymanagementsystem.Enum.TransactionStatus;
import com.backendProject.librarymanagementsystem.Repository.BookRepository;
import com.backendProject.librarymanagementsystem.Repository.LibraryCardRepository;
import com.backendProject.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    LibraryCardRepository libraryCardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private JavaMailSender emailSender;
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssuedOperation(true);
        //step1- get the information of book and the card
        LibraryCard card;
        try {
            card = libraryCardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Invalid Card ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Card ID");
        }

        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Invalid Book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book ID");
        }
        transaction.setBook(book);
        transaction.setCard(card);

        //both cardID and bookID are valid
        //checking book is activated or not
        if(!card.getStatus().equals(CardStatus.ACTIVATED))
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Your Card is not Activated");
            transactionRepository.save(transaction);
            throw new Exception("Your Card is not Activated");
        }
        //checking book is not already issued so, if issued then we cannot issue again
        if(book.isIssued()==true)
        {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionMessage("Sorry!, Book is Already Issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry!, Book is Already Issued");
        }
        //I can Issue book now

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionMessage("Transaction Successfull");

        book.setIssued(true);
        book.setCard(card);
        book.getTransaction().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        libraryCardRepository.save(card); //will save book and transaction also

        //prepare responsDto
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionID(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookResponseDto.setBookName(book.getTitle());

        //send email
        String text = "Congratualtions !! "+ card.getStudent().getName()+" You Have Been Issued "+book.getTitle()+" Book";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendaman123@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book Notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;
    }
}
