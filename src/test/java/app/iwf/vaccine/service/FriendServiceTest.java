package app.iwf.vaccine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.repository.IFriendRepository;

@ExtendWith(MockitoExtension.class)
class FriendServiceTest {
	
	@Mock
	private IFriendRepository friendRepository;
	
	@Autowired
	@InjectMocks
	private FriendService friendService;
	
	private static FriendDTO dto;
	
	@BeforeEach
	static void setup() {
		dto = new FriendDTO();
		dto.setName("Test");
		dto.setFirstDose(false);
		dto.setSecondDose(false);		
	}
	
	@Test
	void saveNewSavesNew() {
		
		
		
	}
	

}
