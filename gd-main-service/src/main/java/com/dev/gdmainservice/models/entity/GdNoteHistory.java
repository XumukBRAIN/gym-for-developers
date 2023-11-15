package com.dev.gdmainservice.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "noteHistory", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<GdNoteHistoryComment> historyComments;
}
