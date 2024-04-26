package ru.edu.penzgtu.lab.Service.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.DriverDto;
import ru.edu.penzgtu.lab.Entity.Driver;
import ru.edu.penzgtu.lab.Repo.CarBrandRepository;
import ru.edu.penzgtu.lab.Repo.CarRepository;
import ru.edu.penzgtu.lab.Entity.Car;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverMapper {
    private final CarRepository carRepository;

    public List<DriverDto> toListDto(List<Driver> drivers) {
        return drivers.stream().map(this::toDto).toList();
    }

    public DriverDto toDto(Driver driver) {
        return DriverDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .localDateTime(driver.getLocalDateTime())
                .age(driver.getAge())
                .drivingExperience(driver.getDrivingExperience())
                .cars(driver.getCars().stream()
                        .map(Car::getName)
                        .toList())
                .build();
    }

    public Driver toEntity(DriverDto driverDto) {
        Driver driver = new Driver();

        driver.setId( driverDto.getId());
        driver.setName( driverDto.getName());
        driver.setLocalDateTime(driverDto.getLocalDateTime());
        driver.setAge(driverDto.getAge());
        driver.setDrivingExperience(driverDto.getDrivingExperience());
        driver.setCars((Collections.singletonList
                (carRepository.findByName(String.valueOf(driverDto.getCars().stream().toList())))));

        return  driver;
    }
}

