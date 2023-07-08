package com.backendProject.librarymanagementsystem.DTO;

import com.backendProject.librarymanagementsystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueBookResponseDto {
    private String transactionID;
    private String bookName;
    private TransactionStatus transactionStatus;
}
