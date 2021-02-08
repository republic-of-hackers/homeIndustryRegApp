package com.nagarro.HIRegApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HIRegApp.models.UserCredential;

@Repository("userCredentialRepo")
public interface UserCredentialRepo extends CrudRepository<UserCredential, Long> {
	UserCredential findByuserEmail(final String userEmail);
}
