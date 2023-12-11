package com.sintad.emaintenance.domain.service;

import com.sintad.emaintenance.domain.model.User;

import com.sintad.emaintenance.dto.SaveUserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User create(User user);

    User update(Long id, SaveUserDto user);

    ResponseEntity<?> delete(Long id);

    User findByEmail(String email);
}
