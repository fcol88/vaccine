package app.iwf.vaccine.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.iwf.vaccine.entity.Friend;

@Repository
public interface IFriendRepository extends JpaRepository<Friend, Long> {

	Optional<Friend> findByCode(UUID code);
	List<Friend> findByFirstDosedAndSecondDosed(boolean firstDosed, boolean secondDosed);
	Page<Friend> findAll(Pageable pageable);
	
}
