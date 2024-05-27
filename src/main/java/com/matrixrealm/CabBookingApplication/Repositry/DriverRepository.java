package com.matrixrealm.CabBookingApplication.Repositry;

import com.matrixrealm.CabBookingApplication.Model.Driver;
import com.matrixrealm.CabBookingApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer>{
    Optional<Driver> findByEmail(String email);
}
