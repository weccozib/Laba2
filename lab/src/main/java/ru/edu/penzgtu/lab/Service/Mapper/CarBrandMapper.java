package ru.edu.penzgtu.lab.Service.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.CarBrandDto;
import ru.edu.penzgtu.lab.Entity.CarBrand;
import ru.edu.penzgtu.lab.Entity.Country;
import ru.edu.penzgtu.lab.Repo.CountryRepository;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CarBrandMapper {
    private final CountryRepository countryRepository;

    public List<CarBrandDto> toListDto(List<CarBrand> carBrands) {
        return carBrands.stream().map(this::toDto).toList();
    }

    public CarBrandDto toDto(CarBrand carBrand) {
        String countryName = carBrand.getCountry() != null ? carBrand.getCountry().getName() : null;


        return CarBrandDto.builder()
                .id(carBrand.getId())
                .name(carBrand.getName())
                .localDateTime(carBrand.getLocalDateTime())
                .founder(carBrand.getFounder())
                .yearOfFound(carBrand.getYearOfFound())
                .countryName(countryName)
                .build();
    }
    public CarBrand toEntity(CarBrandDto carBrandDto) {
        CarBrand carBrand = new CarBrand();

        carBrand.setId(carBrandDto.getId());
        carBrand.setName(carBrandDto.getName());
        carBrand.setLocalDateTime(carBrandDto.getLocalDateTime());
        carBrand.setFounder(carBrandDto.getFounder());
        carBrand.setYearOfFound(carBrandDto.getYearOfFound());
        carBrand.setCountry(countryRepository.findByName(carBrandDto.getCountryName()));

        return carBrand;
    }
}
