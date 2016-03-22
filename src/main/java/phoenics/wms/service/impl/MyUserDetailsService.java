package phoenics.wms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import phoenics.wms.entities.User;
import phoenics.wms.manager.SecurityUser;
import phoenics.wms.service.UserService;



@Component
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findEmail(email);
		if(user == null){
			throw new UsernameNotFoundException("UserName "+email+" not found");
		}
		return new SecurityUser(user);
	}


}
