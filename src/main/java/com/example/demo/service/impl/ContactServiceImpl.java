package com.example.demo.service.impl;

import com.example.demo.dto.request.SearchDto;
import com.example.demo.dto.response.SearchResponseDto;
import com.example.demo.exception.domain.BadRequestException;
import com.example.demo.exception.domain.ResourceNotFoundException;
import com.example.demo.model.ContactModel;
import com.example.demo.repo.ContactRepo;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    private final Clock clock;

    @Override
    public void validateByEmail(String email) {
        ContactModel data = contactRepo.findByEmail(email);
        if(data != null){
            throw new BadRequestException("email already registered.");
        }
    }
    @Override
    public void validateByPhone(String phone) {
        ContactModel data = contactRepo.findByPhone(phone);
        if(data != null){
            throw new BadRequestException("phone number already registered.");
        }
    }
    @Override
    public ContactModel findById(Long id) {
        return contactRepo.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new BadRequestException("Data is inactive"));
    }
    @Override
    @SneakyThrows
    @Transactional
    public void save(ContactModel data) {
        data.setActive(true);
        if(data.getId() == null) {
            data.setCreatedAt(LocalDateTime.now(clock));
        }else {
            data.setUpdatedAt(LocalDateTime.now(clock));
        }
        contactRepo.save(data);
    }

    @Override
    public void delete(Long id) {
        ContactModel data = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not Found."));
        data.setActive(false);
        contactRepo.save(data);
    }

    @Override
    public List<SearchResponseDto> search(SearchDto requestDto) {
        List<Map<String, Object>> mapList = contactRepo.searchByParamStartDateAndEndDate(requestDto.getStartDate(), requestDto.getEndDate());
        List<SearchResponseDto> dtoResponseList = new ArrayList<>();
        for (Object object : mapList) {
            Map row = (Map) object;
            SearchResponseDto dtoResponse = new SearchResponseDto();
            dtoResponse.setName(row.get("name") != null ? (String) row.get("name") : null);
            dtoResponse.setSecondname(row.get("secondname") != null ? (String) row.get("secondname") : null);
            dtoResponse.setWork(row.get("work") != null ? (String) row.get("work") : null);
            dtoResponse.setEmail(row.get("email") != null ? (String) row.get("email") : null);
            dtoResponse.setPhone(row.get("phone") != null ? (String) row.get("phone") : null);
            dtoResponse.setDescription(row.get("description") != null ? (String) row.get("description") : null);
            dtoResponseList.add(dtoResponse);
        }
        return dtoResponseList;
    }
}
