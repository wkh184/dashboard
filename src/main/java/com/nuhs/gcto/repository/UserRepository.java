package com.nuhs.gcto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuhs.gcto.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findByAdid(String adid);
}
