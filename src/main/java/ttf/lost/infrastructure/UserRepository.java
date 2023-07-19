package ttf.lost.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ttf.lost.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserId(String userId);
}
