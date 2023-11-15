package com.dev.gdmainservice.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "note")
@Getter
@Setter
@RequiredArgsConstructor
public class GdNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String section;

    private String advice;

    private LocalDate dateOfCreation;

    private String whoCreated;

    private String status;

    @OneToMany(mappedBy = "note", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<GdNoteComment> comments;

    public GdNote(BuilderGdNote builderGdNote) {
        this.id = builderGdNote.id;
        this.section = builderGdNote.section;
        this.advice = builderGdNote.advice;
        this.dateOfCreation = builderGdNote.dateOfCreation;
        this.whoCreated = builderGdNote.whoCreated;
        this.comments = builderGdNote.comments;
        this.status = builderGdNote.status;
    }

    public static BuilderGdNote newBuilder() {
        return new BuilderGdNote();
    }

    public static class BuilderGdNote {
        private Integer id;
        private String section;
        private String advice;
        private LocalDate dateOfCreation;
        private String whoCreated;
        private List<GdNoteComment> comments;
        private String status;

        public BuilderGdNote id(Integer id) {
            this.id = id;
            return this;
        }

        public BuilderGdNote section(String section) {
            this.section = section;
            return this;
        }

        public BuilderGdNote advice(String advice) {
            this.advice = advice;
            return this;
        }

        public BuilderGdNote dateOfCreation(LocalDate dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
            return this;
        }

        public BuilderGdNote whoCreated(String whoCreated) {
            this.whoCreated = whoCreated;
            return this;
        }
        public BuilderGdNote comments(List<GdNoteComment> comments){
            this.comments = comments;
            return this;
        }

        public BuilderGdNote status(String status) {
            this.status = status;
            return this;
        }

        public GdNote build() {
            return new GdNote(this);
        }
    }
}
