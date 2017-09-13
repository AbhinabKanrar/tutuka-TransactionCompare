/**
 * 
 */
package com.tutuka.transactioncompare.web.router;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tutuka.transactioncompare.util.domain.FileLoader;

/**
 * Default controller
 * 
 * @author abhinab
 *
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("fileLoader", new FileLoader());
		// based on this showCouter the result panel in the page will be
		// displayed
		model.addAttribute("showCounter", false);
		return "index";
	}

}
