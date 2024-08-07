package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "contact")
@NoArgsConstructor
public class ContactModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 100 )
    private String name;
    @Column(name = "secondname", nullable = false, length = 100)
    private String secondname;
    @Column(name = "work", nullable = false, length = 100 )
    private String work;
    @Column(name = "email",  nullable = false, length = 70,unique = true)
    private String email;
    @Column(name = "phone", nullable = false, length = 20,unique = true)
    private String phone;
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;


    @JsonProperty("isActive")
    public boolean getIsActive() {
        return isActive;
    }
}
