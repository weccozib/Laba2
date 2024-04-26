package ru.edu.penzgtu.lab.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "carBrands")
public class CarBrand {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_and_time")

    private LocalDateTime localDateTime;

    @Column(name = "founder",nullable = false,length = 44)
    private String founder;

    @Column(name = "year_of_found")
    @Positive(message = "Год основания должен быть положительным числом")
    private Long yearOfFound;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},optional = true)
    @JoinTable(name = "countries_carBrands",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "carBrand_id", referencedColumnName = "id"))
    private Country country;
}
