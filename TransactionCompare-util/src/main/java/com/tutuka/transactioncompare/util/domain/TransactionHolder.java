/**
 * 
 */
package com.tutuka.transactioncompare.util.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tutuka.transactioncompare.util.CommonUtil;

/**
 * POJO to hold all transactions fetched from the csv
 * 
 * @author abhinab
 *
 */
public class TransactionHolder {

	private List<Transaction> transactions;

	/**
	 * Used to fetch and create transaction instances in stream mode(using lambda). Very
	 * efficient in processing very large file since whole file is not loaded at
	 * once into the JVM
	 * 
	 * @param file
	 */
	public TransactionHolder(File file) {
		this.transactions = new ArrayList<Transaction>();

		try (InputStream is = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			transactions = br.lines().skip(1).map((row) -> {
				String[] columns = row.split(CommonUtil.SEPARATOR);
				Transaction transaction = new Transaction();
				if (columns.length >= 1) {
					transaction.setProfileName(columns[0]);
				}
				if (columns.length >= 2) {
					transaction.setTransactionDate(columns[1]);
				}
				if (columns.length >= 3) {
					try {
						transaction.setTransactionAmount(Long.valueOf(columns[2]));
					} catch (Exception e) {
						transaction.setTransactionAmount(0l);
					}
				}
				if (columns.length >= 4) {
					transaction.setTransactionNarrative(columns[3]);
				}
				if (columns.length >= 5) {
					transaction.setTransactionDescription(columns[4]);
				}
				if (columns.length >= 6) {
					transaction.setTransactionId(columns[5]);
					if (columns.length >= 7) {
					}
					try {
						transaction.setTransactionType(Integer.parseInt(columns[6]));
					} catch (Exception e) {
						transaction.setTransactionType(0);
					}
				}
				if (columns.length >= 8) {
					transaction.setWalletReference(columns[7]);
				}
				return transaction;
			}).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
