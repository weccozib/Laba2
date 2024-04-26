package ru.edu.penzgtu.lab.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.CarBrandDto;
import ru.edu.penzgtu.lab.Dto.CountryDto;
import ru.edu.penzgtu.lab.Entity.Country;
import ru.edu.penzgtu.lab.Exception.ErrorType;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Repo.CarBrandRepository;

import java.time.LocalDateTime;
import java.util.List;
import ru.edu.penzgtu.lab.Entity.CarBrand;
import ru.edu.penzgtu.lab.Service.Mapper.CarBrandMapper;
import ru.edu.penzgtu.lab.Service.Mapper.CountryMapper;
@Service
@Slf4j
@RequiredArgsConstructor
public class CarBrandService {
    private final CarBrandRepository carBrandRepository;
    private final CarBrandMapper carBrandMapper;
    public List<CarBrandDto> findAllCarBrands() {
        log.info("Найдены все существующие марки в БД");
        return carBrandMapper.toListDto(carBrandRepository.findAll());
    }
    public CarBrandDto findCarBrandById(Long id)throws PenzGtuException {
        log.info("Найдена марка по id: " + id);
        CarBrand carBrand = carBrandRepository.findById(id)
                .orElseThrow(()-> new PenzGtuException(ErrorType.NOT_FOUND,"Марка машины"+ id +"Не найдено"));
        return carBrandMapper.toDto(carBrand);
    }


    public void saveCarBrand(CarBrandDto carBrandDto) {
        log.info("Марка сохранена");
        CarBrand carBrand = carBrandMapper.toEntity(carBrandDto);
        carBrand.setLocalDateTime(LocalDateTime.now());
        carBrandRepository.save(carBrand);
    }
    public void updateCarBrand(CarBrand newCarBrand) {
        log.info("Данные о марке были обновлены");
        CarBrand oldCarBrand = carBrandRepository.findById(newCarBrand.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND, "Марка не найдена "));
        oldCarBrand.setName(newCarBrand.getName());
        oldCarBrand.setFounder(newCarBrand.getFounder());
        oldCarBrand.setYearOfFound(newCarBrand.getYearOfFound());
        carBrandRepository.save(oldCarBrand);
    }
    public void deleteCarBrandById(Long id ){
        log.info("Удалена марка с id: " + id);
        carBrandRepository.deleteById(id);
    }
}