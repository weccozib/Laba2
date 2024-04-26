package ru.edu.penzgtu.lab.Entity;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_and_time")
    @NotNull(message = "Дата и время не должны быть пустыми")
    private LocalDateTime localDateTime;

    @Column(name = "capital",nullable = false,length = 44)
    private String capital;

    @Column(name = "language",nullable = false,length = 44)
    private String language;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "countries_carBrands",
            joinColumns = @JoinColumn(name = "carBrand_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id",referencedColumnName = "id"))
    private List<CarBrand> carBrands;
}
