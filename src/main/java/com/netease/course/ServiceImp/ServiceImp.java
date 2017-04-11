package com.netease.course.ServiceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.netease.course.Mapper.ContentDao;
import com.netease.course.Mapper.PersonDao;
import com.netease.course.Mapper.TrxDao;
import com.netease.course.Service.CommentService;
import com.netease.course.pojo.BuyContent;
import com.netease.course.pojo.Content;
import com.netease.course.pojo.Person;
import com.netease.course.pojo.Product;
import com.netease.course.pojo.Trx;
import com.netease.course.pojo.TrxJoinContent;
import com.netease.course.pojo.User;

@Service("buyerService")
public class ServiceImp implements CommentService  {
	@Autowired
	ContentDao contentDao;
	
	@Autowired
	TrxDao trxdao;
	
	@Autowired
	PersonDao persondao;


	@Override
	public ModelAndView showProduct(int productId) {
		ModelAndView model = new ModelAndView();
		model.setViewName("show");
		Product product;
		product = contentDao.selectProduct(productId);
		model.addObject(product);
		return model;
	}

	@Override
	public ModelAndView index(User user) {
		ModelAndView model = new ModelAndView();
		List<Product> productList = new ArrayList<Product>();
		if (user == null) {
			productList =  contentDao.selelecAll();
			model.addObject(productList);
			model.setViewName("index");
		} else {
			List<Product> unsellList = new ArrayList<Product>();
			productList = trxdao.getBuyList();
			unsellList = trxdao.getUnSellList();
			int num;
			for(Product e:productList){
				e.setBuy(true);
				e.setSell(true);
				num=trxdao.getSellNUm(e.getId());
				e.setBuyNum(num);
				e.setSaleNum(num);
			}
			for(Product e:unsellList){
				productList.add(e);
			}
			
			model.addObject(productList);
			model.addObject(user);
			model.setViewName("index");
		}
		
		return model;
	}

	@Override
	public User login(String userName, String password) {
		User user = null ;
		try{
			Person person = persondao.getPerson(userName);
			if (password.equals(person.getPassword())) {
				user = new User(person.getUsername(), person.getUserType());
			}
		}catch (Exception e) {
			user=null;
		}
		return user;
	}

	@Override
	public Map<String, Object> delete(int productId) {
		Map<String, Object> modelmap = new HashMap<String, Object>();
		try {
			contentDao.deletecontent(productId);
			modelmap.put("code", "200");
			modelmap.put("message", "删除成功");
			modelmap.put("result", true);
		} catch (Exception e) {
			modelmap.put("code", "250");
			modelmap.put("message", "没有该产品");
			modelmap.put("result", false);
		}
		return modelmap;
	}

	@Override
	public ModelAndView account() {
		ModelAndView model = new ModelAndView();
		List<TrxJoinContent> buyList = new ArrayList<TrxJoinContent>();
		buyList = trxdao.getAccount();
		model.addObject("buyList", buyList);
		model.setViewName("account");
		return model;
	}

	@Override
	public Map<String, Object> buy(List<BuyContent> buyList,HttpSession session) {
		Map<String, Object> modleMap = new HashMap<String, Object>();
		Long time = System.currentTimeMillis();
		User user = (User) session.getAttribute("user");
		Person person = persondao.getPersonInfo(user.getUsername());
		Trx trx = new Trx();
		Content content;
		try {
			for (BuyContent b : buyList) {
				content = contentDao.selectOne(b.getId());
				trx.setTime(time);
				trx.setBuyNum(b.getNumber());
				trx.setBuyPrice(b.getNumber() * content.getPrice());
				trx.setContentId(b.getId());
				trx.setPersonId(person.getId());
				trxdao.insertTrx(trx);
			}
			modleMap.put("code", "200");
			modleMap.put("message", "购买成功");
			modleMap.put("result", true);

		} catch (Exception e) {
			modleMap.put("code", "250");
			modleMap.put("message", "数量有误");
			modleMap.put("result", false);
		}
		return modleMap;
	}

	
	
}
