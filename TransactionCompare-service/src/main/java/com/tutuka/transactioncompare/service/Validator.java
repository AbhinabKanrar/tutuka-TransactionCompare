/**
 * 
 */
package com.tutuka.transactioncompare.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tutuka.transactioncompare.util.domain.FileLoader;

/**
 * @author abhinab
 *
 */
public abstract class Validator {

	private static final String VALID_FILE_EXTENSION = ".csv";

	/**
	 * Used to validate the uploaded content. Based on the validation return the
	 * list of error message/s
	 * 
	 * @param fileLoader
	 * @return list of error
	 */
	public abstract List<String> validate(FileLoader fileLoader);

	/**
	 * Double check to validate file extension
	 * 
	 * @param extension
	 * @return boolean
	 */
	private static boolean isValidExtension(String extension) {
		if (VALID_FILE_EXTENSION.equalsIgnoreCase(extension)) {
			return true;
		}
		return false;
	}

	/**
	 * validating file uploaded file content
	 * 
	 * @param file
	 * @return boolean
	 */
	public static boolean isValidData(MultipartFile file) {
		if (isValidExtension(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
				file.getOriginalFilename().length()))) {
			return true;
		}
		return false;
	}

}
