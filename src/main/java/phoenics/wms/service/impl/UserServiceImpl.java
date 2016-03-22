package phoenics.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phoenics.wms.entities.User;
import phoenics.wms.repositories.UserRepository;
import phoenics.wms.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository UserDao;
	/* (non-Javadoc)
	 * @see phoenics.easyui.service.impl.UserService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String name) {
		if(name ==null){
			return null;
		}
		 return UserDao.findByName(name);

	}
	@Override
	public User findEmail(String email) {
		if(email ==null){
			return null;
		}
		 return UserDao.findByLogin(email);
	}
	@Override
	public void saveUser(User user) {
		UserDao.save(user);
		UserDao.flush();
	}
}
