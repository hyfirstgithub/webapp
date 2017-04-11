package com.netease.course.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netease.course.Service.CommentService;
import com.netease.course.pojo.User;

@Controller
public class BeforeAdmin {

	@Autowired
	@Qualifier("buyerService")
	CommentService commentService;

	@RequestMapping("/show")
	public ModelAndView showp(@RequestParam("id") int productId) {
		return commentService.showProduct(productId);
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/logout")
	public String logou(HttpSession session) {
		session.removeValue("user");
		return "login";
	}

	@RequestMapping(value = "/")
	public ModelAndView index(HttpSession session) {
		User user = null;
		user=(User)session.getAttribute("user");
		return commentService.index(user);
	}

}
