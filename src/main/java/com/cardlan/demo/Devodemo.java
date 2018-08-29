package com.cardlan.demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.annotations.Until;

@RestController
public class Devodemo {
	@Autowired
	private SqlSessionTemplate template;
	//开发登录接口
	@RequestMapping(value="/login3",method=RequestMethod.POST)
	public String getlogin(@RequestBody User user,HttpServletResponse response) {
		int selectOne = template.selectOne("loginjiekou", user);
		//返回cookie
		Cookie cookie=new Cookie("login", "true");
		response.addCookie(cookie);
		if(selectOne==1) {
			return "true";
		}
		return "false";
	}
	
	/*
	 * 开发insert接口，不带cookie验证，看是否是cookie导致的问题
	 *  经过验证，不带cookie的没问题，说明cookie验证出了问题
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String  insert(@RequestBody User user,HttpServletRequest request) {
		for (Cookie cookie : request.getCookies()) {
			if(cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
				int insert = template.insert("insert", user);
				if(insert==1) {
					return "添加成功";
				}
			}
		}
		return "添加失败";
	}
}
