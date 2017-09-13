/**
 * 
 */
package com.tutuka.transactioncompare.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tutuka.transactioncompare.util.domain.TransactionHolder;

/**
 * Test runner to check application health. Testcase names is/are in BDD style
 * 
 * @author abhinab
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class })
public class AppTest {

	@Autowired
	private FileLoaderService fileLoaderService;

	@Test
	public void givenFile_processResult_matchCount()
			throws FileNotFoundException, InterruptedException, ExecutionException {
		CompletableFuture<TransactionHolder> txnHolder = fileLoaderService
				.process(new File(getClass().getClassLoader().getResource("TutukaMarkoffFile20140113.csv").getFile()));
		assertEquals(305, txnHolder.get().getTransactions().size());
	}

}
