package app.iwf.vaccine.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import app.iwf.vaccine.service.IFriendService;

@WebMvcTest(MainController.class)
class MainControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IFriendService friendService;
	
	@Test
	void getHomeUsesHomeViewAllZeros() throws Exception {
		
		Mockito.when(friendService.countAllByVaccinated(false, false)).thenReturn(0L);
		Mockito.when(friendService.countAllByVaccinated(true, false)).thenReturn(0L);
		Mockito.when(friendService.countAllByVaccinated(true, true)).thenReturn(0L);
		Mockito.when(friendService.countAll()).thenReturn(0L);
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
		
	}
	
	@Test
	void getHomeUsesHomeViewOneUnVax() throws Exception {
		
		Mockito.when(friendService.countAllByVaccinated(false, false)).thenReturn(1L);
		Mockito.when(friendService.countAllByVaccinated(true, false)).thenReturn(0L);
		Mockito.when(friendService.countAllByVaccinated(true, true)).thenReturn(0L);
		Mockito.when(friendService.countAll()).thenReturn(1L);
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
		
	}
	
	@Test
	void getHomeUsesHomeViewOneVax() throws Exception {
		
		Mockito.when(friendService.countAllByVaccinated(false, false)).thenReturn(0L);
		Mockito.when(friendService.countAllByVaccinated(true, false)).thenReturn(1L);
		Mockito.when(friendService.countAllByVaccinated(true, true)).thenReturn(0L);
		Mockito.when(friendService.countAll()).thenReturn(1L);
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
		
	}
	
	@Test
	void getHomeUsesHomeViewOneSecondVax() throws Exception {
		
		Mockito.when(friendService.countAllByVaccinated(false, false)).thenReturn(0L);
		Mockito.when(friendService.countAllByVaccinated(true, false)).thenReturn(0L);
		Mockito.when(friendService.countAllByVaccinated(true, true)).thenReturn(1L);
		Mockito.when(friendService.countAll()).thenReturn(1L);
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
		
	}
	
	@Test
	void getHomeUsesHomeViewSeveral() throws Exception {
		
		Mockito.when(friendService.countAllByVaccinated(false, false)).thenReturn(2L);
		Mockito.when(friendService.countAllByVaccinated(true, false)).thenReturn(2L);
		Mockito.when(friendService.countAllByVaccinated(true, true)).thenReturn(2L);
		Mockito.when(friendService.countAll()).thenReturn(6L);
		mockMvc.perform(get("/"))
			.andExpect(view().name("home"));
		
	}

}
