package com.giovannigalatioto.FrydayW5.EsFridayW5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovannigalatioto.FrydayW5.EsFridayW5.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
