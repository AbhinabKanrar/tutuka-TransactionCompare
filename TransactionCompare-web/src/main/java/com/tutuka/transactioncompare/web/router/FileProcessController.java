/**
 * 
 */
package com.tutuka.transactioncompare.web.router;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tutuka.transactioncompare.service.FileLoaderService;
import com.tutuka.transactioncompare.service.impl.DataValidator;
import com.tutuka.transactioncompare.util.CommonUtil;
import com.tutuka.transactioncompare.util.domain.FileDetail;
import com.tutuka.transactioncompare.util.domain.FileLoader;

/**
 * This controller class is used to handle file compare trigger. It's designed
 * in such a way that no impact would be introduced due to heavy use of parallel
 * computing
 * 
 * @author abhinab
 *
 */
@Controller
public class FileProcessController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private FileLoaderService fileLoaderService;

	@PostMapping(value = "/", params = "action=Compare")
	public String compare(@ModelAttribute("fileLoader") FileLoader fileLoader, Model model) {
		try {
			List<String> errors = new DataValidator().validate(fileLoader);
			if (errors != null && !errors.isEmpty()) {
				model.addAttribute("errors", errors);
			} else {
				File fileOne = CommonUtil.multipartToFile(fileLoader.getFileOne());
				File fileTwo = CommonUtil.multipartToFile(fileLoader.getFileTwo());

				Map<String, FileDetail> results = fileLoaderService.compare(fileOne, fileTwo);

				model.addAttribute(CommonUtil.KEY_FILE_ONE, results.get(CommonUtil.KEY_FILE_ONE));
				model.addAttribute(CommonUtil.KEY_FILE_TWO, results.get(CommonUtil.KEY_FILE_TWO));

				// based on this showCouter the result panel in the page will be
				// displayed
				model.addAttribute("showCounter", true);
			}
		} catch (Exception e) {
			log.error("Illegal file/s detected", e);
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("fileLoader", new FileLoader());
		return "index";
	}

}
