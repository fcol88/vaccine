package app.iwf.vaccine.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.entity.Friend;
import app.iwf.vaccine.service.IFriendService;

@WebMvcTest(FriendController.class)
class FriendControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IFriendService friendService;
	
	static Page<Friend> friends;
	static Friend friend;
	
	@BeforeAll
	static void setup() {
		
		friend = new Friend();
		friend.setId(1L);
		friend.setName("Test");
		friend.setFirstDosed(true);
		friend.setSecondDosed(true);
		List<Friend> friendList = new ArrayList<>();
		friendList.add(friend);
		friends = new PageImpl<Friend>(friendList);
		
	}
	
	@Test
	void listGetsListPage() throws Exception {
		
		Mockito.when(friendService.findAll(1, 10)).thenReturn(friends);
		mockMvc.perform(get("/friends/list"))
			.andExpect(view().name("friends/list"));
		
	}
	
	@Test
	void formPageUsesFormPage() throws Exception {
		
		mockMvc.perform(get("/friends/form"))
			.andExpect(view().name("friends/form"));
		
	}
	
	@Test
	void formPageGetsExistingFriend() throws Exception {
		
		Mockito.when(friendService.findByCode(friend.getCode())).thenReturn(friend);		
		mockMvc.perform(get("/friends/form?id="
				.concat(friend.getCode().toString())))
			.andExpect(view().name("friends/form"));
		
	}
	
	@Test
	void postFormEmptyErrors() throws Exception {
		
		FriendDTO dto = new FriendDTO();
		mockMvc.perform(post("/friends/form")
				.flashAttr("friend", dto))
			.andExpect(view().name("friends/form"));
		
	}
	
	@Test
	void postFirstDoseFalseSecondDoseTrue() throws Exception {
		
		FriendDTO dto = new FriendDTO(friend);
		dto.setFirstDose(false);
		dto.setSecondDose(true);
		
		mockMvc.perform(post("/friends/form")
				.flashAttr("friend", dto))
			.andExpect(view().name("friends/form"));
		
	}
	
	@Test
	void postFirstDoseFalseSecondDoseFalse() throws Exception {
		
		FriendDTO dto = new FriendDTO(friend);
		dto.setFirstDose(false);
		dto.setSecondDose(false);
		
		mockMvc.perform(post("/friends/form")
				.flashAttr("friend", dto))
			.andExpect(redirectedUrl("list"));
		
	}
	
	@Test
	void postValid() throws Exception {
		
		FriendDTO dto = new FriendDTO(friend);
		
		mockMvc.perform(post("/friends/form")
				.flashAttr("friend", dto))
			.andExpect(redirectedUrl("list"));
		
	}
	
	@Test
	void deleteRedirectsToList() throws Exception {
		
		mockMvc.perform(get("/friends/delete?id="
				.concat(friend.getId().toString())))
			.andExpect(redirectedUrl("list"));		
		
	}

}
