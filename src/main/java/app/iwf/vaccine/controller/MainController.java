package app.iwf.vaccine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import app.iwf.vaccine.service.IFriendService;

@Controller
public class MainController {
	
	@Autowired
	private IFriendService friendService;
	
	@GetMapping("/")
	public ModelAndView getHome(ModelAndView model) {
		
		long unVax = friendService.countAllByVaccinated(false, false);
		long firstVax = friendService.countAllByVaccinated(true, false);
		long secondVax = friendService.countAllByVaccinated(true, true);
		long all = friendService.countAll();
		
		model.addObject("unVax", (double)unVax);
		model.addObject("firstVax", (double)firstVax);
		model.addObject("secondVax", (double)secondVax);
		model.addObject("all", (double)all);
		
		model.setViewName("home");
		
		return model;
		
	}

}
