package com.example.demo.service;

import com.example.demo.dto.request.SearchDto;
import com.example.demo.dto.response.SearchResponseDto;
import com.example.demo.model.ContactModel;

import java.util.List;

public interface ContactService {
    void validateByEmail(String email);
    void validateByPhone(String email);
    ContactModel findById(Long id);
    void save(ContactModel data);
    void delete(Long id);
    List<SearchResponseDto> search(SearchDto requestDto);
}
