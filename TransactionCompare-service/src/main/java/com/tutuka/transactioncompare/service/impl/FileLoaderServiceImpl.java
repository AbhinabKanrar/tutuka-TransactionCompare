/**
 * 
 */
package com.tutuka.transactioncompare.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tutuka.transactioncompare.service.FileLoaderService;
import com.tutuka.transactioncompare.util.CommonUtil;
import com.tutuka.transactioncompare.util.domain.FileDetail;
import com.tutuka.transactioncompare.util.domain.Transaction;
import com.tutuka.transactioncompare.util.domain.TransactionHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Implementation of the FileLoaderService interface
 * 
 * @author abhinab
 *
 */
@Service
public class FileLoaderServiceImpl implements FileLoaderService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tutuka.transactioncompare.service.FileLoaderService#process(java.io.
	 * File, java.io.File)
	 */
	@Override
	@Async
	public CompletableFuture<TransactionHolder> process(File file) throws FileNotFoundException {
		TransactionHolder transactionHolder = new TransactionHolder(file);
		return CompletableFuture.completedFuture(transactionHolder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tutuka.transactioncompare.service.FileLoaderService#process(java.io.
	 * File, java.util.List, java.util.List)
	 */
	@Override
	@Async
	public CompletableFuture<FileDetail> process(File file, List<Transaction> transactionsFromOwnFile,
			List<Transaction> transactionsFromOtherFile) {
		FileDetail fileDetail = new FileDetail(file, transactionsFromOwnFile, transactionsFromOtherFile);
		return CompletableFuture.completedFuture(fileDetail);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tutuka.transactioncompare.service.FileLoaderService#compare(java.io.
	 * File, java.io.File)
	 */
	@Override
	public Map<String, FileDetail> compare(File fileOne, File fileTwo)
			throws FileNotFoundException, InterruptedException, ExecutionException, TimeoutException {

		CompletableFuture<TransactionHolder> transactionHolderOne = process(fileOne);
		CompletableFuture<TransactionHolder> transactionHolderTwo = process(fileTwo);

		CompletableFuture.allOf(transactionHolderOne, transactionHolderTwo).join();

		// intentionally using timeout functionality to eliminate parallel
		// zombie-thread/s scenario
		List<Transaction> transactionsFromFileOne = transactionHolderOne.get(CommonUtil.TIME_OUT, TimeUnit.MINUTES)
				.getTransactions();
		List<Transaction> transactionsFromFileTwo = transactionHolderTwo.get(CommonUtil.TIME_OUT, TimeUnit.MINUTES)
				.getTransactions();

		CompletableFuture<FileDetail> fileDetailOne = process(fileOne, transactionsFromFileOne,
				transactionsFromFileTwo);
		CompletableFuture<FileDetail> fileDetailTwo = process(fileTwo, transactionsFromFileTwo,
				transactionsFromFileOne);

		CompletableFuture.allOf(fileDetailOne, fileDetailTwo).join();

		FileDetail fileDetailFromFileOne = fileDetailOne.get(CommonUtil.TIME_OUT, TimeUnit.MINUTES);
		FileDetail fileDetailFromFileTwo = fileDetailTwo.get(CommonUtil.TIME_OUT, TimeUnit.MINUTES);

		Map<String, FileDetail> result = new HashMap<String, FileDetail>(2);

		result.put(CommonUtil.KEY_FILE_ONE, fileDetailFromFileOne);
		result.put(CommonUtil.KEY_FILE_TWO, fileDetailFromFileTwo);

		return result;
	}

}
