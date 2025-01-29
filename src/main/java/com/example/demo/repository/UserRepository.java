package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//JPARepository has all the crud functions
public interface UserRepository extends JpaRepository<User,Long> {
}
