package app.iwf.vaccine.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.entity.Friend;
import app.iwf.vaccine.service.IFriendService;

@Controller
@RequestMapping("/friends")
public class FriendController implements AbstractController {
	
	@Autowired
	private IFriendService friendService;
	
	@GetMapping({"/","/list"})
	public ModelAndView getList(
			@RequestParam(name="size", defaultValue="10") int size,
			@RequestParam(name="page", defaultValue="1") int page, 
			@RequestParam(name="unfriend", defaultValue="false") boolean unfriend,
			ModelAndView model) {
		
		Page<Friend> friends = friendService.findAll(page, size);
		
		model.addObject("unfriend", unfriend);
		model.setViewName("friends/list");
		addPageObject(model, friends);
		
		return model;
		
	}
	
	@GetMapping("/form")
	public ModelAndView getForm(@RequestParam(name="id", required=false) UUID code,
			ModelAndView model) {
		
		if(code == null) {
			model.addObject("friend", new FriendDTO());
		} else {
			Friend friend = friendService.findByCode(code);
			model.addObject("friend", new FriendDTO(friend));
		}
		
		model.setViewName("friends/form");
		
		return model;
		
	}
	
	@PostMapping("/form")
	public ModelAndView postForm(@Valid @ModelAttribute("friend") FriendDTO friend, 
			BindingResult result, ModelAndView model) {
		
		if(!result.hasErrors() && 
				friend.getFirstDose().equals(Boolean.FALSE) &&
					friend.getSecondDose().equals(Boolean.TRUE)) {
			result.rejectValue("secondDose", "error.friend", 
					"You trying to break something? How exactly can "
					+ "you have your second dose first, hmm?");
		}
		
		if(result.hasErrors()) { 
			model.setViewName("friends/form");
			return model;
		} else {
			friendService.saveOrUpdate(friend);
			model.setViewName("redirect:list");
			return model;
		}
		
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam("id") Long id, ModelAndView model) {
		
		friendService.unfriend(id);
		model.setViewName("redirect:list");
		return model;
		
	}
	

}
