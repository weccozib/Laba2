package ru.edu.penzgtu.lab.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация о марке")
public class CarBrandDto {
    @JsonProperty("id")
    @Schema(description = "ID марки в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "Название марки", example = "Мерседес")
    private String name;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления машины в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("founder")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Имя основателя марки может содержать только буквы и пробелы")
    @Schema(description = "Имя основателя марки",example = "Карл Бенц Готтлиб")
    private String founder;

    @JsonProperty("year_of_found")
    @Positive(message = "Год основания марки должен быть положительным числом")
    @Schema(description = "Год основания марки", example = "33")
    private Long yearOfFound;

    @JsonProperty("countries")
    @Schema(description = "Имена стран производителя марок")
    private String countryName;
}
