package com.nagarro.HIRegApp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.HIRegApp.models.Attachment;

public interface AttachmentRepo extends CrudRepository<Attachment, Long> {

}
