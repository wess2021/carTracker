package com.carTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carTracker.Models.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
