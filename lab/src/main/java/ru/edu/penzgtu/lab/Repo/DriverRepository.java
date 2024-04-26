package ru.edu.penzgtu.lab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.edu.penzgtu.lab.Dto.CarDto;
import ru.edu.penzgtu.lab.Dto.DriverDto;
import ru.edu.penzgtu.lab.Entity.Car;
import ru.edu.penzgtu.lab.Entity.Driver;

import java.util.List;
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByName(String name);
}
