// PasswordService.java
package com.example.securepasswordmanager.service;

import com.example.securepasswordmanager.config.EncryptionUtil;
import com.example.securepasswordmanager.model.Password;
import com.example.securepasswordmanager.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private EncryptionUtil encryptionUtil;

    public List<Password> getAllPasswords() {
        return passwordRepository.findAll();
    }

    public Password getPasswordById(Long id) {
        return passwordRepository.findById(id).orElse(null);
    }

    public Password createPassword(Password password) throws Exception {
        String encryptedPassword = encryptionUtil.encrypt(password.getPassword(), "password");
        password.setPassword(encryptedPassword);
        return passwordRepository.save(password);
    }
    public void savePassword(String password) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        Password passwordEntity = new Password(hashedPassword);
        passwordRepository.save(passwordEntity);
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return PasswordHasher.verifyPassword(password, hashedPassword);
    }

    public Password updatePassword(Password password) throws Exception {
        String encryptedPassword = encryptionUtil.encrypt(password.getPassword(), "password");
        password.setPassword(encryptedPassword);
        return passwordRepository.save(password);
    }

    public void deletePassword(Long id) {
        passwordRepository.deleteById(id);
    }
}