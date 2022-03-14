package com.example.easylife.repository;

import com.example.easylife.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserRepository extends GenericRepository {

  public UserRepository(EntityManager em) {
    super(em, User.class);
  }

}
