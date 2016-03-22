package phoenics.wms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import phoenics.wms.entities.User;


public interface UserRepository  extends JpaRepository<User, Integer> {
	User findByName(String name);
	User findByLogin(String email);
}
