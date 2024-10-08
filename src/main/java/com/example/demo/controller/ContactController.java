/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dto.request.RequestDto;
import com.example.demo.dto.request.SearchDto;
import com.example.demo.dto.response.SearchResponseDto;
import com.example.demo.dto.response.SuccessResponseDto;
import com.example.demo.exception.ExceptionHandling;
import com.example.demo.helper.AppIdHelper;
import com.example.demo.model.ContactModel;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"/", "/api/v1/contact"})
public class ContactController extends ExceptionHandling {
    public static final String SUCCESS_MSG = "Success";
    private final ContactService contactService;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<SuccessResponseDto> add(
            @Valid @RequestBody RequestDto requestDto, HttpServletRequest request) {
        AppIdHelper.verifyAppId(request.getHeader("APPID"));
        contactService.validateByEmail(requestDto.getEmail());
        contactService.validateByPhone(requestDto.getPhone());
        ContactModel data = modelMapper.map(requestDto, ContactModel.class);
        contactService.save(data);
        return ResponseEntity.ok(new SuccessResponseDto<>(HttpStatus.OK,SUCCESS_MSG));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDto<String>> delete(@PathVariable Long id,
                                                             HttpServletRequest request) {
        AppIdHelper.verifyAppId(request.getHeader("APPID"));
        contactService.delete(id);
        return ResponseEntity.ok(new SuccessResponseDto<>(HttpStatus.OK, SUCCESS_MSG));
    }

    @GetMapping("/{id}") //select
    public ResponseEntity<SuccessResponseDto> select(@PathVariable Long id,
                                                     HttpServletRequest request) {
        AppIdHelper.verifyAppId(request.getHeader("APPID"));
        ContactModel data = contactService.findById(id);
        return ResponseEntity.ok(new SuccessResponseDto<>(HttpStatus.OK, data));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SuccessResponseDto> update(@Valid @RequestBody RequestDto requestDto,
                                                     @PathVariable Long id,
                                                     HttpServletRequest request) {
        AppIdHelper.verifyAppId(request.getHeader("APPID"));
        ContactModel data = contactService.findById(id);
        data.setName(requestDto.getName());
        data.setSecondname(requestDto.getSecondname());
        data.setEmail(requestDto.getEmail());
        data.setPhone(requestDto.getPhone());
        data.setDescription(requestDto.getDescription());
        contactService.save(data);
        return ResponseEntity.ok(new SuccessResponseDto<>(HttpStatus.OK, data));
    }

    //search with native query
    @PostMapping("/search")
    public ResponseEntity<SuccessResponseDto<List<SearchResponseDto>>> search(@Valid @RequestBody SearchDto requestDto,
                                                                              HttpServletRequest request) {
        AppIdHelper.verifyAppId(request.getHeader("APPID"));
        List<SearchResponseDto> data = contactService.search(requestDto);
        return ResponseEntity.ok(new SuccessResponseDto<>(HttpStatus.OK, data));
    }
}
