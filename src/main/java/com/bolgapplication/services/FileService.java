package com.bolgapplication.services;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String uplaodImage(String path,MultipartFile file);
	public InputStream getResource(String path,String fileName) throws FileNotFoundException;
}
