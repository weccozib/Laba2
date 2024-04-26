package ru.edu.penzgtu.lab.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.edu.penzgtu.lab.Dto.CountryDto;
import ru.edu.penzgtu.lab.Entity.Country;
import ru.edu.penzgtu.lab.Exception.ErrorType;
import ru.edu.penzgtu.lab.Exception.PenzGtuException;
import ru.edu.penzgtu.lab.Repo.CountryRepository;
import ru.edu.penzgtu.lab.Service.Mapper.CountryMapper;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    @Transactional
    public List<CountryDto> findAllCountries(){
        log.info("Найдены все существующие страны в БД");
        return countryMapper.toListDto(countryRepository.findAll());
    }
    public CountryDto findCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() ->new PenzGtuException(ErrorType.NOT_FOUND,"Страна с id " + id + " не найден"));
        return countryMapper.toDto(country);
    }


    public void saveCountry(CountryDto countryDto) {
        log.info("Страна сохранена");
        Country country = countryMapper.toEntity(countryDto);
        country.setLocalDateTime(LocalDateTime.now());
        countryRepository.save(country);
    }
    public void updateCountry(CountryDto newCountry) {
        log.info("Данные о стране были обновлены");
        Country oldCountry = countryRepository.findById(newCountry.getId())
                .orElseThrow(() -> new PenzGtuException(ErrorType.NOT_FOUND,"Страна не найдена"));
        oldCountry.setName(newCountry.getName());
        oldCountry.setCapital(newCountry.getCapital());
        oldCountry.setLanguage(newCountry.getLanguage());
        countryRepository.save(oldCountry);
    }
    public void deleteCountryById(Long id) {
        log.info("Удалена страна с id: " + id);
        countryRepository.deleteById(id);
    }
}