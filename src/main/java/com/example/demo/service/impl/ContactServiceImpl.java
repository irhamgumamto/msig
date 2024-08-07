package com.example.demo.service.impl;

import com.example.demo.dto.request.RequestDto;
import com.example.demo.exception.domain.ResourceNotFoundException;
import com.example.demo.model.ContactModel;
import com.example.demo.repo.ContactRepo;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;

    private final Clock clock;

    @Override
    public ContactModel findByEmail(String email) {
        return contactRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found"));
    }
    @Override
    public ContactModel findById(Long id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found"));
    }
    @Override
    @SneakyThrows
    @Transactional
    public void save(ContactModel data) {
        data.setActive(true);
        data.setCreatedAt(LocalDateTime.now(clock));
        data.setUpdatedAt(LocalDateTime.now(clock));
        contactRepo.save(data);
    }

    @Override
    public void delete(Long id) {
        ContactModel data = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mohon maaf data tidak ditemukan"));
        data.setActive(false);
        contactRepo.save(data);
    }
}
