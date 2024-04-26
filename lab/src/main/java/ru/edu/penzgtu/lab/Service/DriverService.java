package ru.edu.penzgtu.lab.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.DriverDto;
import ru.edu.penzgtu.lab.Entity.Driver;
import ru.edu.penzgtu.lab.Exception.ErrorType;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Repo.DriverRepository;
import ru.edu.penzgtu.lab.Service.Mapper.DriverMapper;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    public List<DriverDto> findAllDrivers() {
        log.info("Найдены все существующие водители в БД");
        return driverMapper.toListDto(driverRepository.findAll());
    }
    public DriverDto findDriverById(Long id)throws PenzGtuException {
        log.info("Найден водитель по id: " + id);
        Driver driver = driverRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Водитель"+ id +"Не найдено"));
        return driverMapper.toDto(driver);
    }


    public void saveDriver(DriverDto driverDto) {
        log.info("Водитель сохранен");
        Driver driver = driverMapper.toEntity(driverDto);
        driver.setLocalDateTime(LocalDateTime.now());
        driverRepository.save(driver);
    }
    public void updateDriver(Driver newDriver) {
        log.info("Данные о водителе были обновлены");
        Driver oldDriver = driverRepository.findById(newDriver.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Водитель не найден "));
        oldDriver.setName(newDriver.getName());
        oldDriver.setAge(newDriver.getAge());
        oldDriver.setDrivingExperience(newDriver.getDrivingExperience());
        driverRepository.save(oldDriver);
    }
    public void deleteDriverById(Long id ){
        log.info("Удален водитель с id: " + id);
        driverRepository.deleteById(id);
    }
}
