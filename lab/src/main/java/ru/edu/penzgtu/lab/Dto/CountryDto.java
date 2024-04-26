package ru.edu.penzgtu.lab.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Информация о стране производителя")
public class CountryDto {
    @JsonProperty("id")
    @Schema(description = "ID страны производителя в БД", example = "123")
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Schema(description = "страны производителя", example = "Германия")
    private String name;

    @JsonProperty("dateAndTime")
    @Schema(description = "Дата и время добавления страны в БД")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @JsonProperty("capital")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Столица может содержать только буквы и пробелы")
    @Schema(description = "Столица",example = "Берлин")
    private String capital;

    @JsonProperty("language")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-Я\\s]+$",
            message = "Язык может содержать только буквы и пробелы")
    @Schema(description = "Язык",example = "Немецкий")
    private String language;

    @JsonProperty("carBrands")
    @Schema(description = "Названия марок стран производителя")
    private List<String> carBrands;


}
