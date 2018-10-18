package com.naurtki.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naurtki.userservice.models.NaurtkiUser;

@Repository
public interface NaurtkiUserRepository extends JpaRepository<NaurtkiUser, Long>{

}
