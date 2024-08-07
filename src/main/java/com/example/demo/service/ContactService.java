package com.example.demo.service;

import com.example.demo.dto.request.RequestDto;
import com.example.demo.model.ContactModel;

import java.util.Optional;

public interface ContactService {
    ContactModel findByEmail(String email);
    ContactModel findById(Long id);
    void save(ContactModel data);
    void delete(Long id);
}
