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
@Schema(description = "Информация о машине")
public class CarDto {
    @JsonProperty("id")
    @Schema(description = "ID машины в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @Schema(description = "Название машины в БД", example = "Logan")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Название машины может содержать только буквы")
    private String name;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления машины в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("year_of_manufacture")
    @Positive(message = "Год производства должен быть положительным числом")
    @Schema(description = "Год производства машины", example = "33")
    private Long yearOfManufacture;

    @JsonProperty("color")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Цвет машины может содержать только буквы")
    @Schema(description = "Цвет машины", example = "Черный")
    private String color;

    @JsonProperty("driver")
    @Schema(description = "Имя водителя")
    private String driverName;

}
