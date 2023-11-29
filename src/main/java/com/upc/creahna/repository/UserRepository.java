package com.upc.creahna.repository;

import com.upc.creahna.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
