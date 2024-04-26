package ru.edu.penzgtu.lab.Service.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.CountryDto;
import ru.edu.penzgtu.lab.Entity.Country;
import ru.edu.penzgtu.lab.Entity.CarBrand;
import ru.edu.penzgtu.lab.Repo.CarBrandRepository;
import ru.edu.penzgtu.lab.Repo.CountryRepository;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CountryMapper {
    private final CarBrandRepository carBrandRepository;
    private final CountryRepository countryRepository;
    public List<CountryDto> toListDto(List<Country> countries) {
        return countries.stream().map(this::toDto).toList();
}
    public CountryDto toDto(Country country) {
        return CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .localDateTime(country.getLocalDateTime())
                .capital(country.getCapital())
                .language(country.getLanguage())
                .carBrands(country.getCarBrands().stream()
                        .map(CarBrand::getName)
                        .toList())
                .build();
    }
    public Country toEntity(CountryDto countryDto) {
        Country country = new Country();

        country.setId( countryDto.getId());
        country.setName( countryDto.getName());
        country.setLocalDateTime(countryDto.getLocalDateTime());
        country.setCapital(countryDto.getCapital());
        country.setLanguage(countryDto.getLanguage());
        country.setCarBrands((Collections.singletonList
                (carBrandRepository.findByName(String.valueOf(countryDto.getCarBrands().stream().toList())))));

        return  country;
    }
}
