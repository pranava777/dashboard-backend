package com.dashboard.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.dashboard.api.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
// for CRUD operations on manager
public interface UserRepository extends CrudRepository<User, Integer> {

}
