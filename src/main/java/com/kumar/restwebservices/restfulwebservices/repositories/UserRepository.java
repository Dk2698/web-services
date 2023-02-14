package com.kumar.restwebservices.restfulwebservices.repositories;

import com.kumar.restwebservices.restfulwebservices.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
