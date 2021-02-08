package com.nagarro.HIRegApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HIRegApp.models.VerificationToken;

@Repository("verficationTokenRepo")
public interface VerificationTokenRepo extends CrudRepository<VerificationToken, Long> {
	VerificationToken findByToken(String token);
}
