package ru.edu.penzgtu.lab.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.CarDto;
import ru.edu.penzgtu.lab.Entity.Car;
import ru.edu.penzgtu.lab.Exception.ErrorType;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Repo.CarBrandRepository;
import ru.edu.penzgtu.lab.Repo.CarRepository;
import ru.edu.penzgtu.lab.Service.Mapper.CarMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    public List<CarDto> findAllCars() {
        log.info("Найдены все существующие машины в БД");
        return carMapper.toListDto(carRepository.findAll());
    }
    public CarDto findCarById(Long id)throws PenzGtuException {
        log.info("Найдена машина по id: " + id);
        Car car = carRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Машина"+ id +"Не найдено"));
        return carMapper.toDto(car);
    }


    public void saveCar(CarDto carDto) {
        log.info("Машина сохранена");
        Car car = carMapper.toEntity(carDto);
        car.setLocalDateTime(LocalDateTime.now());
        carRepository.save(car);
    }
    public void updateCar(Car newCar) {
        log.info("Данные о машине были обновлены");
        Car oldCar = carRepository.findById(newCar.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Машина не найдена "));
        oldCar.setName(newCar.getName());
        oldCar.setYearOfManufacture(newCar.getYearOfManufacture());
        oldCar.setColor(newCar.getColor());
        carRepository.save(oldCar);
    }
    public void deleteCarById(Long id ){
        log.info("Удалена машина с id: " + id);
        carRepository.deleteById(id);
    }
}
