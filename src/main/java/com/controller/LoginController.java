package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request,Model mv){
		String e = (String) request.getAttribute("shiroLoginFailure");
        if (e != null) {
            if (e.contains("org.apache.shiro.authc.UnknownAccountException")) {
                mv.addAttribute("msg", "账号不存在");
            } else if (e.contains("org.apache.shiro.authc.IncorrectCredentialsException")) {
                mv.addAttribute("msg", "密码错误");
            }
        }
//        return "/login.jsp";

		return "manager";
	}
	
	/*@SuppressWarnings("static-access")
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request,HttpServletResponse response,String username,String password){
		WebUser user = new WebUser();
		CookieUtil cu = new CookieUtil();
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			user = cu.getLoginUser(request);
			if(null==user){
				return "login";
			}
			return "manager";
		}
		user.setUserName(username);
		user.setLoginStatus(1);
		cu.setLoginUser(response, user);
		return "manager";
	}*/
}
