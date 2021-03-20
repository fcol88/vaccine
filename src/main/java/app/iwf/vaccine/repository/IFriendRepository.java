package app.iwf.vaccine.repository;

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
	long countByFirstDosedAndSecondDosed(boolean firstDosed, boolean secondDosed);
	Page<Friend> findAllByOrderByName(Pageable pageable);
	
}
