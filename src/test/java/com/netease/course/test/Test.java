package com.netease.course.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.netease.course.Mapper.ContentDao;
import com.netease.course.pojo.Content;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
	
		
		
		ContentDao contentDao = context.getBean("contentDao",ContentDao.class);
		Content content =  contentDao.selectOne(3);
		System.out.println(content.getDetail());
		Content content2 = contentDao.selectOne(1);
		System.out.println(content2.getTitle());
	
		
//		ContentDao dao=context.getBean("contentDao", ContentDao.class);
//		Product product;
//		product = dao.selectOne(1);
//		System.out.println(product.getDetail());
//		System.out.println(product.getPrice());
//		System.out.println(product.getSummary());
//		List<Product> productList = new ArrayList<Product>();
//		productList = dao.showallproduct();
//		
//		for(Product e:productList){
//			System.out.println(e.getPrice()+" "+e.isBuy());
//		}
//		
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
