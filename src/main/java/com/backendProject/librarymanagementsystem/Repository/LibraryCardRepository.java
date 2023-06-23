package com.backendProject.librarymanagementsystem.Repository;

import com.backendProject.librarymanagementsystem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard,Integer> {

}
