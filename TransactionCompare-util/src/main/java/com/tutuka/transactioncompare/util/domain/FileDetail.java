/**
 * 
 */
package com.tutuka.transactioncompare.util.domain;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * POJO for holding file specific transaction matching results
 * 
 * @author abhinab
 *
 */
@Data
public class FileDetail {

	private String fileName;
	private int totalRecordsCount;
	private int totalMatchedRecordsCount;
	private int totalUnmatchedRecordsCount;
	private List<Transaction> transactions;
	private List<Transaction> matchedTransactions;
	private List<Transaction> unMatchedTransactions;

	/**
	 * Used to perform quick search operation on both files
	 * 
	 * @param file
	 * @param transactionsFromOwnFile
	 * @param transactionsFromOtherFile
	 */
	public FileDetail(File file, List<Transaction> transactionsFromOwnFile,
			List<Transaction> transactionsFromOtherFile) {
		this.fileName = file.getName();
		this.totalRecordsCount = transactionsFromOwnFile.size();
		this.matchedTransactions = transactionsFromOtherFile.stream()
				.filter(transaction -> transactionsFromOwnFile.contains(transaction)).collect(Collectors.toList());
		this.unMatchedTransactions = transactionsFromOtherFile.stream()
				.filter(transaction -> !transactionsFromOwnFile.contains(transaction)).collect(Collectors.toList());
		this.totalMatchedRecordsCount = this.matchedTransactions.size();
		this.totalUnmatchedRecordsCount = this.unMatchedTransactions.size();
	}

}
