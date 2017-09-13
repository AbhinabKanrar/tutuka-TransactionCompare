/**
 * 
 */
package com.tutuka.transactioncompare.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tutuka.transactioncompare.service.Validator;
import com.tutuka.transactioncompare.util.domain.FileLoader;

import static com.tutuka.transactioncompare.util.CommonUtil.isNull;

/**
 * Implementation of the Validator interface
 * 
 * @author abhinab
 *
 */
public class DataValidator extends Validator {

	private List<String> errorList = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tutuka.transactioncompare.service.Validator#validate(com.tutuka.
	 * transactioncompare.util.domain.FileLoader)
	 */
	@Override
	public List<String> validate(FileLoader fileLoader) {
		if (isNull(fileLoader)) {
			errorList.add("No data found to be updated");
		} else {
			if (isNull(fileLoader.getFileOne())) {
				errorList.add("No data found for file 1");
			}
			if (isNull(fileLoader.getFileTwo())) {
				errorList.add("No data found for file 2");
			}
			if (!isNull(fileLoader.getFileOne()) && !isNull(fileLoader.getFileTwo())) {
				if (!isValidData(fileLoader.getFileOne())) {
					errorList.add("File 1 is not a valid csv");
				}
				if (!isValidData(fileLoader.getFileTwo())) {
					errorList.add("File 2 is not a valid csv");
				}
			}
		}
		return errorList;
	}

}
