package com.edumans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edumans.model.SystemUser;
/**
 * System users Repository
 * @author mohamed.elmasry
 *
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, String> {

	
}
