package ttf.lost.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttf.lost.domain.recommendation.AvatarInquiry;

@Repository
public interface AvatarInquiryRepository extends JpaRepository<AvatarInquiry, Long> {
	List<AvatarInquiry> findByUserNickname(String nickname);
}