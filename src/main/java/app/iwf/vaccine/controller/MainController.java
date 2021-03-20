package app.iwf.vaccine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import app.iwf.vaccine.entity.Friend;
import app.iwf.vaccine.service.IFriendService;

@Controller
public class MainController {
	
	@Autowired
	private IFriendService friendService;
	
	@GetMapping("/")
	public ModelAndView getHome(ModelAndView model) {
		
		List<Friend> unVax = friendService.findAllByVaccinated(false);
		List<Friend> vax = friendService.findAllByVaccinated(true);
		
		model.addObject("unVax", unVax.size());
		model.addObject("vax", vax.size());
		
		model.setViewName("home");
		
		return model;
		
	}

}
