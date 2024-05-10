// PasswordController.java
package com.example.securepasswordmanager.controller;

import com.example.securepasswordmanager.model.Password;
import com.example.securepasswordmanager.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {
    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public List<Password> getAllPasswords() {
        return passwordService.getAllPasswords();
    }

    @GetMapping("/{id}")
    public Password getPasswordById(@PathVariable Long id) {
        return passwordService.getPasswordById(id);
    }

    @PostMapping
    public Password createPassword(@RequestBody Password password) throws Exception {
        return passwordService.createPassword(password);
    }

    @PutMapping("/{id}")
    public Password updatePassword(@PathVariable Long id, @RequestBody Password password) throws Exception {
        return passwordService.updatePassword(password);
    }

    @DeleteMapping("/{id}")
    public void deletePassword(@PathVariable Long id) {
        passwordService.deletePassword(id);
    }
}