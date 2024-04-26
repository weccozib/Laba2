package ru.edu.penzgtu.lab.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@Schema(description = "Информация о водителя")
public class DriverDto {
    @JsonProperty("id")
    @Schema(description = "ID водителя в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @Schema(description = "Название водителя в БД", example = "Nikita")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя водителя может содержать только буквы")
    private String name;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления водителя в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("age")
    @Positive(message = "Возраст должен быть положительным числом")
    @Schema(description = "Возраст водителя", example = "33")
    private Long age;

    @JsonProperty("driving_experience")
    @Positive(message = "Стаж вождения должен быть положительным числом")
    @Schema(description = "Стаж вождения водителя", example = "33")
    private Long drivingExperience;

    @JsonProperty("cars")
    @Schema(description = "Названия машин")
    private List<String> cars;

}
