package com.dev.gdmainservice.models.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class GdPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private Integer age;

    private String country;

    private LocalDate birthDate;

    @Convert(converter = ExtraInfoConverter.class)
    @Column(columnDefinition = "jsonb")
    private ExtraInfo extraInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class ExtraInfo implements Serializable {
        private String github;
        private String instagram;
        private String vk;
        private String telegram;
    }

    @Converter(autoApply = true)
    public static class ExtraInfoConverter implements AttributeConverter<ExtraInfo, String> {

        private final static Gson GSON = new Gson();

        @Override
        public String convertToDatabaseColumn(ExtraInfo mjo) {
            return GSON.toJson(mjo);
        }

        @Override
        public ExtraInfo convertToEntityAttribute(String dbData) {
            return GSON.fromJson(dbData, ExtraInfo.class);
        }
    }
}
