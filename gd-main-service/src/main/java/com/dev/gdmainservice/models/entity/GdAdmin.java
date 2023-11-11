package com.dev.gdmainservice.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GdAdmin {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private Integer age;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;
}
