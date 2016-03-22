package phoenics.wms.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import phoenics.wms.vos.OperationsInfo;

@RestController
@RequestMapping(value = "/home")
public class LoginController {
	@Autowired
	private AuthenticationManager myAuthenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public OperationsInfo login(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password, HttpServletRequest request) {
		/*if (!checkValidateCode(request)) {
			return new LoginInfo().failed().msg("验证码错误！");
		}*/

		username = username.trim();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		try {
			Authentication authentication = myAuthenticationManager.authenticate(authRequest); // 调用loadUserByUsername
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
			return new OperationsInfo(authentication.getName(),"成功登陆系统",true);
		}catch (AuthenticationException  ex) {
			return new OperationsInfo(username,"用户名或密码错误",false);
		}
	}
	@RequestMapping(value = "/getme", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public OperationsInfo getme(HttpServletRequest request){
		SecurityContext sc=(SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(sc==null){
			return new OperationsInfo("","",false);
		}
		String user_=sc.getAuthentication().getName();
		return new OperationsInfo(user_,"yes",true);
	}

	protected boolean checkValidateCode(HttpServletRequest request) {
		String result_verifyCode = request.getSession().getAttribute("verifyResult").toString(); // 获取存于session的验证值
		String user_verifyCode = request.getParameter("verifyCode");// 获取用户输入验证码
		if (null == user_verifyCode || !result_verifyCode.equalsIgnoreCase(user_verifyCode)) {
			return false;
		}
		return true;
	}

}
