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
import ru.edu.penzgtu.lab.Dto.CarDto;
import ru.edu.penzgtu.lab.Dto.DriverDto;
import ru.edu.penzgtu.lab.Entity.Car;
import ru.edu.penzgtu.lab.Entity.Driver;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Service.CarService;
import ru.edu.penzgtu.lab.Service.DriverService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Tag(name = "Водители",description = "Операции над водителями")
public class DriverController {
    private final DriverService driverService;
    private final BaseResponseService baseResponseService;

    @Operation(
            summary = "Получение всех водителей", description = "Позволяет выгрузить всех водителей из БД"
    )

    @GetMapping
    public ResponseWrapper<List<DriverDto>> findAllDriver() {
        return baseResponseService.wrapSuccessResponse(driverService.findAllDrivers());
    }

    @Operation(
            summary = "Получение водителей по ID", description = "Позволяет выгрузить одного водителя по ID из БД"
    )

    @GetMapping("/driver/{id}")
    public ResponseWrapper<DriverDto> getDriverById(@PathVariable @Min(0) Long id) throws PenzGtuException {
        return baseResponseService.wrapSuccessResponse(driverService.findDriverById(id));
    }


    @Operation(
            summary = "Создать водителя", description = "Позволяет создать новую запись о водителя  в БД"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDriver(@RequestBody @Valid DriverDto driver){
        driverService.saveDriver(driver);
    }

    @Operation(
            summary = "Обновить данные о водителя ", description = "Позволяет обновить информацию о водителя в БД"
    )

    @PutMapping("/driver/")
    public void updateDriver(@RequestBody @Valid Driver driver) {
        driverService.updateDriver(driver);
    }

    @Operation(
            summary = "Удалить водителя по ID ", description = "Позволяет удалить водителя по ID из БД"
    )

    @DeleteMapping("/driver/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable @Min(0) Long id) {
        driverService.deleteDriverById(id);
    }
}
