package com.netease.course.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.netease.course.pojo.BuyContent;
import com.netease.course.pojo.User;

public interface CommentService {
	ModelAndView showProduct(int productId);
	
	ModelAndView index(User user);
	
	User login(String userName,String password );
	
	Map<String, Object> delete(int productId);
	
	ModelAndView account();
	
	Map<String, Object> buy(List<BuyContent> buyList,HttpSession session);
}
