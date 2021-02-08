package com.nagarro.HIRegApp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.HIRegApp.models.AccountInformation;

public interface AccountInformationRepo extends CrudRepository<AccountInformation, Long> {

}
