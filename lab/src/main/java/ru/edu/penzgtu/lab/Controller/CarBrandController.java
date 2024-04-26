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
import ru.edu.penzgtu.lab.Dto.CarBrandDto;
import ru.edu.penzgtu.lab.Entity.CarBrand;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Service.CarBrandService;

import java.util.List;
@Validated
@RestController
@RequestMapping("/carBrands")
@RequiredArgsConstructor
@Tag(name = "Марки",description = "Операции над марками")
public class CarBrandController {
    private final CarBrandService carBrandService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех марок", description = "Позволяет выгрузить все марки из БД"
    )

    @GetMapping
    public ResponseWrapper<List<CarBrandDto>> findAllCarBrand() {
        return baseResponseService.wrapSuccessResponse(carBrandService.findAllCarBrands());
    }

    @Operation(
            summary = "Получение марки по ID", description = "Позволяет выгрузить одну марку по ID из БД"
    )

    @GetMapping("/carBrand/{id}")
    public ResponseWrapper<CarBrandDto> getCarBrandById(@PathVariable @Min(0) Long id) throws PenzGtuException {
        return baseResponseService.wrapSuccessResponse(carBrandService.findCarBrandById(id));
    }


    @Operation(
            summary = "Создать марку", description = "Позволяет создать новую запись о марке  в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCarBrand(@RequestBody @Valid CarBrandDto carBrand){
        carBrandService.saveCarBrand(carBrand);
    }

    @Operation(
            summary = "Обновить данные о марке ", description = "Позволяет обновить информацию о марке в БД"
    )

    @PutMapping("/carBrand/")
    public void updateCarBrand(@RequestBody @Valid CarBrand carBrand) {
        carBrandService.updateCarBrand(carBrand);
    }

    @Operation(
            summary = "Удалить марку по ID ", description = "Позволяет удалить марку по ID из БД"
    )

    @DeleteMapping("/carBrand/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarBrand(@PathVariable @Min(0) Long id) {
        carBrandService.deleteCarBrandById(id);
    }
}