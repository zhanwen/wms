package phoenics.wms.service;

import phoenics.wms.entities.User;

public interface UserService {

	User findUser(String name);
	User findEmail(String email);
	void saveUser(User user);
}