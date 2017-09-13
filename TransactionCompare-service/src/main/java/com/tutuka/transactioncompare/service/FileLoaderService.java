/**
 * 
 */
package com.tutuka.transactioncompare.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.tutuka.transactioncompare.util.domain.FileDetail;
import com.tutuka.transactioncompare.util.domain.Transaction;
import com.tutuka.transactioncompare.util.domain.TransactionHolder;

/**
 * Service layer to fetch/parse/process the uploaded contents 
 * 
 * @author abhinab
 *
 */
public interface FileLoaderService {

	/**
	 * patch the computed result. very fast since, the end result is computed in
	 * parallel threads
	 * 
	 * @param fileOne
	 * @param fileTwo
	 * @return map of the result of the files
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	Map<String, FileDetail> compare(File fileOne, File fileTwo)
			throws FileNotFoundException, InterruptedException, ExecutionException, TimeoutException;

	/**
	 * parse the csv and fetch the data in async mode
	 * 
	 * @param fileOne
	 * @return future of of the transaction holder
	 * @throws FileNotFoundException
	 */
	CompletableFuture<TransactionHolder> process(File fileOne) throws FileNotFoundException;

	/**
	 * process different transaction sets and prepare result set accordingly in
	 * async mode
	 * 
	 * @param file
	 * @param transactionsFromOwnFile
	 * @param transactionsFromOtherFile
	 * @return futurre of filedetail
	 */
	CompletableFuture<FileDetail> process(File file, List<Transaction> transactionsFromOwnFile,
			List<Transaction> transactionsFromOtherFile);

}
