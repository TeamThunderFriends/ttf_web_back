package ttf.lost.infrastructure.repository.avatar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttf.lost.domain.avatar.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}
