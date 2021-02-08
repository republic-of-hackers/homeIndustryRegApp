package com.nagarro.HIRegApp.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.HIRegApp.models.Attachment;

public interface FileStorageService {
	public void init();

	public Attachment save(MultipartFile file);
	
	public Resource load(String filename);
	
	public void deleteAll();
	
	public Stream<Path> loadAll();

}
