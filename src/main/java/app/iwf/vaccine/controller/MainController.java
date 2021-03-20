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
		
		List<Friend> unVax = friendService.findAllByVaccinated(false, false);
		List<Friend> firstVax = friendService.findAllByVaccinated(true, false);
		List<Friend> secondVax = friendService.findAllByVaccinated(true, true);
		List<Friend> all = friendService.findAll();
		
		model.addObject("unVax", (double)unVax.size());
		model.addObject("firstVax", (double)firstVax.size());
		model.addObject("secondVax", (double)secondVax.size());
		model.addObject("all", (double)all.size());
		
		model.setViewName("home");
		
		return model;
		
	}

}
