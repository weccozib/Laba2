package ru.edu.penzgtu.lab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.edu.penzgtu.lab.Dto.CarBrandDto;
import ru.edu.penzgtu.lab.Dto.CarDto;
import ru.edu.penzgtu.lab.Entity.Car;
import ru.edu.penzgtu.lab.Entity.CarBrand;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByName(String name);


}
