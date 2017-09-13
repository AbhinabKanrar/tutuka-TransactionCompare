/**
 * 
 */
package com.tutuka.transactioncompare.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * This class contains necessary util params and functions used more often
 * 
 * @author abhinab
 *
 */
public class CommonUtil {

	public static final String SEPARATOR = System.getProperty("csv.separator", ",");
	public static final String HEAP_LOCATION = System.getProperty("heap.location", "/tmp/");
	public static final int TIME_OUT = Integer.parseInt(System.getProperty("time.out", "1"));
	public static final int MIN_THREAD_POOL = Integer.parseInt(System.getProperty("min.thread.pool", "2"));
	public static final int MAX_THREAD_POOL = Integer.parseInt(System.getProperty("max.thread.pool", "5"));
	public static final int MAX_QUEUE_CAPACITY = Integer.parseInt(System.getProperty("max.queue.capacity", "500"));
	public static final String KEY_FILE_ONE = "FILE_ONE";
	public static final String KEY_FILE_TWO = "FILE_TWO";

	/**
	 * used to check null value
	 * 
	 * @param object
	 * @return booean
	 */
	public static boolean isNull(Object object) {
		return object == null ? true : object.toString().length() == 0 ? true : false;
	}

	/**
	 * Convert Spring's multipart file to plain java.io.File Note that, here
	 * heap location is configuration based on the environment
	 * 
	 * @param multipart
	 * @return File
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		String destination = HEAP_LOCATION + multipart.getOriginalFilename();
		File convFile = new File(destination);
		multipart.transferTo(convFile);
		return convFile;
	}

}
