package app.iwf.vaccine.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.entity.Friend;

public interface IFriendService {
	
	Friend saveOrUpdate(FriendDTO friend);
	Friend findByCode(UUID code);
	long countAll();
	Page<Friend> findAll(int page, int size);
	long countAllByVaccinated(boolean firstDosed, boolean secondDosed);
	void unfriend(Long id);

}
