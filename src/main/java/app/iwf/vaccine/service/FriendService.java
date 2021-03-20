package app.iwf.vaccine.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.entity.Friend;
import app.iwf.vaccine.repository.IFriendRepository;

@Service
public class FriendService implements IFriendService {
	
	@Autowired
	private IFriendRepository friendRepository;

	@Override
	public Friend saveOrUpdate(FriendDTO friend) {
		
		return friendRepository.save(dtoToEntity(friend));
		
	}

	@Override
	public Friend findByCode(UUID code) {
		
		Optional<Friend> friend = friendRepository.findByCode(code);
		
		return friend.isPresent() ? friend.get() : null;
		
	}

	@Override
	public List<Friend> findAll() {
		
		return friendRepository.findAll();
		
	}
	
	@Override
	public Page<Friend> findAll(int page, int size) { 
		
		Pageable pageable = PageRequest.of(page - 1, size);
		
		return friendRepository.findAllByOrderByName(pageable);
		
	}

	@Override
	public List<Friend> findAllByVaccinated(boolean firstDosed, boolean secondDosed) {
		
		return friendRepository.findByFirstDosedAndSecondDosed(firstDosed, secondDosed);
		
	}
	
	private Friend dtoToEntity(FriendDTO dto) {
		
		Friend friend;
		
		if(dto.getCode() != null) {
			Optional<Friend> friendOpt = friendRepository.findByCode(dto.getCode());
			friend = friendOpt.isPresent() ? friendOpt.get() : new Friend();
		} else {
			friend = new Friend();
		}
		
		friend.setName(dto.getName());
		friend.setFirstDosed(dto.getFirstDose());
		friend.setSecondDosed(dto.getSecondDose());
		
		return friend;
		
	}

}
