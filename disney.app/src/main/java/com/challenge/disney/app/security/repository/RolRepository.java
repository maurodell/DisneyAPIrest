package com.challenge.disney.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.disney.app.security.entity.Rol;
import com.challenge.disney.app.security.enums.RolName;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	Optional<Rol> findByRolName(RolName rolName);
}
