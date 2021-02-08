package com.nagarro.HIRegApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HIRegApp.models.UserSession;

@Repository("userSessionRepo")
public interface UserSessionRepo extends CrudRepository<UserSession, Long> {
	UserSession findByUserEmail(String userEmail);
	UserSession findBySessionToken(String sessionToken);
}
