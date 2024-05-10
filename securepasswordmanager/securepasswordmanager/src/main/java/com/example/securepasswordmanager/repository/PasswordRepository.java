// PasswordRepository.java
package com.example.securepasswordmanager.repository;

import com.example.securepasswordmanager.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password, Long> {
}