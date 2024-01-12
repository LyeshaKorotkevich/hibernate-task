package ru.clevertec.house.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.house.entity.listener.HouseListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(HouseListener.class)
public class House {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    private String area;

    private String country;

    private String city;

    private String street;

    private int number;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}

