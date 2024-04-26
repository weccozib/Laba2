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
import ru.edu.penzgtu.lab.Dto.CarDto;
import ru.edu.penzgtu.lab.Entity.Car;
import ru.edu.penzgtu.lab.Entity.CarBrand;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Service.CarBrandService;
import ru.edu.penzgtu.lab.Service.CarService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Tag(name = "Машины",description = "Операции над машинами")
public class CarController {
    private final CarService carService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех машин", description = "Позволяет выгрузить все машины из БД"
    )

    @GetMapping
    public ResponseWrapper<List<CarDto>> findAllCar() {
        return baseResponseService.wrapSuccessResponse(carService.findAllCars());
    }

    @Operation(
            summary = "Получение машины по ID", description = "Позволяет выгрузить одну машину по ID из БД"
    )

    @GetMapping("/car/{id}")
    public ResponseWrapper<CarDto> getCarById(@PathVariable @Min(0) Long id) throws PenzGtuException {
        return baseResponseService.wrapSuccessResponse(carService.findCarById(id));
    }



    @Operation(
            summary = "Создать машину", description = "Позволяет создать новую запись о машине  в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody @Valid CarDto car){
        carService.saveCar(car);
    }

    @Operation(
            summary = "Обновить данные о машине ", description = "Позволяет обновить информацию о машине в БД"
    )

    @PutMapping("/car/")
    public void updateCar(@RequestBody @Valid Car car) {
        carService.updateCar(car);
    }

    @Operation(
            summary = "Удалить машину по ID ", description = "Позволяет удалить машину по ID из БД"
    )

    @DeleteMapping("/car/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable @Min(0) Long id) {
        carService.deleteCarById(id);
    }
}

