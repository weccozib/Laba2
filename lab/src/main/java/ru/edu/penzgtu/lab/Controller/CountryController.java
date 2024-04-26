package ru.edu.penzgtu.lab.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.edu.penzgtu.lab.Baseresponce.BaseResponseService;
import ru.edu.penzgtu.lab.Baseresponce.ResponseWrapper;
import ru.edu.penzgtu.lab.Dto.CountryDto;
import ru.edu.penzgtu.lab.Entity.Country;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Repo.CountryRepository;
import ru.edu.penzgtu.lab.Service.CountryService;

import java.util.List;
@Validated
@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
@Tag(name = "Страны производителей",description = "Операции над странами производителей")
public class CountryController {
    private final CountryService countryService;
    private final BaseResponseService baseResponseService;
    private final CountryRepository countryRepository;

    @Operation(
            summary = "Получение всех стран производителей", description = "Позволяет выгрузить всех стран производителей из БД"
    )

    @GetMapping
    public ResponseWrapper<List<CountryDto>> findAll(){
        return baseResponseService.wrapSuccessResponse(countryService.findAllCountries());
    }

    @Operation(
            summary = "Получение страны производителя по ID", description = "Позволяет выгрузить одну страну производителя по ID из БД"
    )

    @GetMapping("/country/{id}")
    public ResponseWrapper<CountryDto> getById(@PathVariable @Min(0) Long id)  {
        return baseResponseService.wrapSuccessResponse(countryService.findCountryById(id));

    }

    @Operation(
            summary = "Создать страну производителя", description = "Позволяет создать новую запись о стране производителя в БД"
    )


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCountry(@RequestBody @Valid CountryDto country){
        countryService.saveCountry(country);
    }

    @Operation(
            summary = "Обновить данные о стране производителя", description = "Позволяет обновить информацию о стране производителя в БД"
    )

    @PutMapping("/country/")
    public void updateCountry(@RequestBody @Valid CountryDto country) {
        countryService.updateCountry(country);
    }

    @Operation(
            summary = "Удалить страну производителя по ID", description = "Позволяеть удалить страну производителя по ID из БД"
    )

    @DeleteMapping("/country/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountry(@PathVariable @Min(0) Long id) {
        countryService.deleteCountryById(id);
    }
}