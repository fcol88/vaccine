package app.iwf.vaccine.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import app.iwf.vaccine.dto.FriendDTO;
import app.iwf.vaccine.entity.Friend;

public interface IFriendService {
	
	Friend saveOrUpdate(FriendDTO friend);
	Friend findByCode(UUID code);
	List<Friend> findAll();
	Page<Friend> findAll(int page, int size);
	List<Friend> findAllByVaccinated(boolean firstDosed, boolean secondDosed);

}
