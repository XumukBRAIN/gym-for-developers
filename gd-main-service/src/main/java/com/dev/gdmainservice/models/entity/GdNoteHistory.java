package com.dev.gdmainservice.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "note_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GdNoteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String section;

    private String advice;

    private LocalDate dateOfCreation;

    private String whoCreated;

    private String status;
}
