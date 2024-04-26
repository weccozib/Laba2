package ru.edu.penzgtu.lab.Service.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.CarBrandDto;
import ru.edu.penzgtu.lab.Dto.CarDto;
import ru.edu.penzgtu.lab.Entity.Car;
import ru.edu.penzgtu.lab.Entity.CarBrand;
import ru.edu.penzgtu.lab.Repo.DriverRepository;

import java.util.List;



@Service
@RequiredArgsConstructor
public class CarMapper {
    private final DriverRepository driverRepository;


    public List<CarDto> toListDto(List<Car> cars) {
        return cars.stream().map(this::toDto).toList();
}

    public CarDto toDto(Car car){
        String driverName = car.getDriver() != null ? car.getDriver().getName() : null;

        return CarDto.builder()
                .id(car.getId())
                .name(car.getName())
                .localDateTime(car.getLocalDateTime())
                .yearOfManufacture(car.getYearOfManufacture())
                .color(car.getColor())
                .driverName(driverName)
                .build();
    }
    public Car toEntity(CarDto carDto){
        Car car = new Car();

        car.setId(carDto.getId());
        car.setName(carDto.getName());
        car.setLocalDateTime(carDto.getLocalDateTime());
        car.setYearOfManufacture(carDto.getYearOfManufacture());
        car.setColor(carDto.getColor());
        car.setDriver(driverRepository.findByName(carDto.getDriverName()));

        return car;
    }
}


