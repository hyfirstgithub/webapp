package com.netease.course.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.netease.course.Mapper.ContentDao;
import com.netease.course.Service.CommentService;
import com.netease.course.pojo.BuyContent;
import com.netease.course.pojo.Content;
import com.netease.course.pojo.User;

@Controller
public class AfterAdmin {
	
	@Autowired
	ContentDao contentDao;
	
	@Autowired
	@Qualifier("buyerService")
	CommentService commentService;

	@RequestMapping(value = "/api/login")
	@ResponseBody
	public Map<String, Object> login(@RequestParam("userName") String userName,
			@RequestParam("password") String password, HttpSession session) {

		Map<String, Object> modelmap = new HashMap<String, Object>();
		User user = commentService.login(userName, password);
		if (user != null) {
			modelmap.put("code", "200");
			modelmap.put("message", "登陆成功");
			modelmap.put("result", true);
			session.setAttribute("user", user);
		} else {
			modelmap.put("code", "250");
			modelmap.put("message", "用户名密码错误");
			modelmap.put("result", false);
		}

		return modelmap;
	}

	@RequestMapping(value = "/api/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam("id") int productId) {
		
		return	commentService.delete(productId);
	}

	@RequestMapping(value = "/account")
	public ModelAndView account() {
		
		return commentService.account();
	}

	@RequestMapping(value = "/public")
	public String publicProduct() {
		return "public";
	}
	
	@RequestMapping(value="/publicSubmit")
	public ModelAndView publicSubmit(Content content){
		ModelAndView modelAndView = new ModelAndView();
		try {
			contentDao.insertContent(content);
			Content content2 = contentDao.selectOneBytitle(content.getTitle());
			System.out.println(content2.getDetail());
			modelAndView.addObject("product", content2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.setViewName("publicSubmit");
		return modelAndView;
	}

	@RequestMapping(value = "/settleAccount")
	public String settleAccount() {
		return "settleAccount";
	}

	@RequestMapping(value = "/api/buy")
	@ResponseBody
	public Map<String, Object> buy(@RequestBody List<BuyContent> buyList, HttpSession session) {
		
		return commentService.buy(buyList, session);
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(String id){
		ModelAndView modelAndView = new ModelAndView();
		int contentId = Integer.parseInt(id);
		Content content = contentDao.selectOne(contentId);
		modelAndView.addObject("product", content);
		modelAndView.setViewName("edit");
		return modelAndView;
	}
	
	@RequestMapping("/api/upload")
	@ResponseBody
	public Map<String,Object> upload(@RequestParam("file")MultipartFile file,HttpServletRequest request){
		Map<String, Object> model = new HashMap<String,Object>();
		String filePath ="image/";
		String path =null;
		 if (!file.isEmpty()) {  
	            try {  
	                // 文件保存路径  
	                 filePath = request.getSession().getServletContext().getRealPath("/")+filePath +file.getOriginalFilename();
	                file.transferTo(new File(filePath)); 
	                path ="image/"+file.getOriginalFilename();
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        } 
		 model.put("code", "200");
		 model.put("message", "上传成功");
		 model.put("result", path);
		 
		 return model;
	}
	
	@RequestMapping(value="/editSubmit")
	
	public ModelAndView publicSumbit(Content content){
		ModelAndView modelAndView = new ModelAndView();
		try{
		contentDao.Update(content);;
		
		modelAndView.addObject("product", content);
		}catch(Exception e){
			e.printStackTrace();
		}
		modelAndView.setViewName("editSubmit");
		
		return modelAndView;
	}
}
