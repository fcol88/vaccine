package app.iwf.vaccine.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

public interface AbstractController {
	
	default void addPageObject(ModelAndView model, Page<?> page) {
		
		model.addObject("currentPage", page);
		int totalPages = page.getTotalPages();
		
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed().collect(Collectors.toList());
			model.addObject("pageNumbers", pageNumbers);
		}
		
	}

}
