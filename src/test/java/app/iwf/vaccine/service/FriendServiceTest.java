package app.iwf.vaccine.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.entity.Friend;
import app.iwf.vaccine.repository.IFriendRepository;

@ExtendWith(MockitoExtension.class)
class FriendServiceTest {
	
	@Mock
	private IFriendRepository friendRepository;
	
	@Autowired
	@InjectMocks
	private FriendService friendService;
	
	private FriendDTO dto;
	private Friend entity;
	
	@BeforeEach
	void setup() {
		dto = new FriendDTO();
		dto.setName("Test");
		dto.setFirstDose(false);
		dto.setSecondDose(false);	
		entity = new Friend();
		entity.setName("Test");
		entity.setFirstDosed(false);
		entity.setSecondDosed(false);
		entity.setCode(UUID.randomUUID());
		entity.setId(1L);
	}
	
	@Test
	void saveNewSavesNew() {
		
		friendService.saveOrUpdate(dto);
		
		ArgumentCaptor<Friend> argument = ArgumentCaptor.forClass(Friend.class);
		
		verify(friendRepository).save(argument.capture());
		assertEquals(dto.getName(), argument.getValue().getName());
		
	}
	
	@Test
	void saveExistingSomehowMissing() {
		
		dto.setCode(entity.getCode());
		
		friendService.saveOrUpdate(dto);
		
		ArgumentCaptor<Friend> argument = ArgumentCaptor.forClass(Friend.class);
		
		verify(friendRepository).save(argument.capture());
		assertNotEquals(dto.getCode(), argument.getValue().getCode());
		
	}
	
	@Test
	void saveExistingSavesExisting() {
		
		dto.setCode(entity.getCode());
		Mockito.when(friendRepository.findByCode(dto.getCode())).thenReturn(Optional.of(entity));
		
		friendService.saveOrUpdate(dto);
		
		ArgumentCaptor<Friend> argument = ArgumentCaptor.forClass(Friend.class);
		
		verify(friendRepository).save(argument.capture());
		assertEquals(dto.getCode(), argument.getValue().getCode());
		
	}
	
	@Test
	void findByCodeFindsByCode() {
		
		Mockito.when(friendRepository.findByCode(entity.getCode())).thenReturn(Optional.of(entity));
		Friend result = friendService.findByCode(entity.getCode());
		assertEquals(entity.getCode(), result.getCode());		
		
	}
	
	@Test
	void findByCodeReturnsNull() {
		
		Friend result = friendService.findByCode(entity.getCode());
		assertNull(result);
		
	}
	
	@Test
	void countAllCountsAll() {
		
		Mockito.when(friendRepository.count()).thenReturn(1L);
		assertEquals(1L, friendService.countAll());
		
	}
	
	@Test
	void getPageReturnsPage() {
		
		List<Friend> frands = new ArrayList<>();
		frands.add(entity);
		
		Page<Friend> friends = new PageImpl<Friend>(frands);
		
		Mockito.when(friendRepository.findAllByOrderByName(any(Pageable.class))).thenReturn(friends);
		
		Page<Friend> result = friendService.findAll(1, 10);
		
		ArgumentCaptor<Pageable> argument = ArgumentCaptor.forClass(Pageable.class);
		
		verify(friendRepository).findAllByOrderByName(argument.capture());
		
		assertEquals(1, result.getTotalElements());
		assertEquals(0, argument.getValue().getPageNumber());
		
	}
	
	@Test
	void countAllByVaccinatedCounts() {
		
		Mockito.when(friendRepository.countByFirstDosedAndSecondDosed(false, false)).thenReturn(1L);
		
		assertEquals(1L, friendService.countAllByVaccinated(false, false));
		
	}
	
	@Test
	void unfriendDeletes() {
		
		friendService.unfriend(1L);
		
		ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
		
		verify(friendRepository).deleteById(argument.capture());
		
		assertEquals(1L, argument.getValue());
		
	}

}
