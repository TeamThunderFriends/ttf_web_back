package ttf.lost.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import ttf.lost.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
