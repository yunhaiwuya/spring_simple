package com.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * cookie工具类
 * @author cjm
 *
 */
public class CookieUtil {

	//默认cookie过期时间（秒）
	public static final int max_time = 1*30;
	
	//用登录信息cookie名字 
	public static final String user_info = "user";
	
	public static void setLoginUser(HttpServletResponse response,WebUser user){
		
		if(null==response || null==user){
			return ;
		}
		long userId = user.getUserId();
		String userName = user.getUserName();
		try {
			userName = URLEncoder.encode(userName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder cookieVal = new StringBuilder();
		//FIXME 用户ID此处写入是用于调试,上线需要去掉
		cookieVal.append(userId).append("|").append(userName).append("|").append(user.getLoginStatus());
		addCookie(response,user_info,cookieVal.toString());
	}
	 /*将构造好的信息放入coolie中*/
	public static void addCookie(HttpServletResponse response,String name,String value){
		Cookie ck = new Cookie(name,value);
		ck.setPath("/");
		ck.setMaxAge(max_time);
		response.addCookie(ck);
	}
	
	/*从cookie取出用户登陆信息并且构造webuser对象返回*/
	public static WebUser getLoginUser(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
 
		//从cookie里取出用户信息(三个字段)
		String value = getCookieValue(user_info, request);
		if (StringUtils.isEmpty(value)) {
			return null;
		}
 
		String[] array = value.split("\\|");
 
		WebUser user = new WebUser();
		user.setUserId(Long.parseLong(array[0]));
		try {
			user.setUserName(URLDecoder.decode(array[1], "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			user.setUserName(array[1]);
		}
		user.setLoginStatus(Integer.parseInt(array[2]));
		return user;
	}
	
	/*从cookie中取出用户的登陆信息*/
	public static String getCookieValue(String name, HttpServletRequest request) {
		if (null == request || StringUtils.isEmpty(name)) {
			return null;
		}
 
		Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 == cookies.length) {
			return null;
		}
 
		for (Cookie cookie : cookies) {
			if (Objects.equals(cookie.getName(), name)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	/**
	 * 删除Cookie
	 * @param response HttpServletResponse
	 * @param name Cookie名
	 * @param path Cookie Path
     */
	public static void removeCookie(HttpServletResponse response, String name, String path) {
		if (null == response || StringUtils.isEmpty(name) || StringUtils.isEmpty(path)) {
			return;
		}
		Cookie cookie = new Cookie(name, "");
		cookie.setPath(path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}
