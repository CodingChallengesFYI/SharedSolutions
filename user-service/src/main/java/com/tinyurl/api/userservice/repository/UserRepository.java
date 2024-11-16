package com.tinyurl.api.userservice.repository;


import com.tinyurl.api.userservice.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    //find by username
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email );
}
