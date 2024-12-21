package com.ecom.demo.eecom.repository;

import com.ecom.demo.eecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Connecting to SQL db <tableNAme, Unique Colum Datatype>

    //Add dependencies, entity class for the cable, repository for the db


}
