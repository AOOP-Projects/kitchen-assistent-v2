package com.kitchenassistant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitchenassistant.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void updateUser(Long id, String username, String email, String password);
    void deleteUser(Long id);
    void addUser(String username, String email, String password);
    User getById(Long id);
    List<User> getAllUsers();
}