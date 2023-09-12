package com.project.weather.repository;

import com.project.weather.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository; // returns List not iterable: For using Stream API not CrudRepository
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, String>{
        Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);

}
