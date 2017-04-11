package com.netease.course.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.course.pojo.Content;
import com.netease.course.pojo.Product;

public interface ContentDao {
	
	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Select("SELECT * FROM content ")
	List<Product> selelecAll();
	

	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Select("SELECT * FROM content WHERE id=#{contentId}")
	Content selectOne(@Param("contentId")int contentId);
	
	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Select("SELECT * FROM content WHERE title=#{title}")
	Content selectOneBytitle(@Param("title")String title);

	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Select("SELECT * FROM content WHERE id=#{contentId}")
	Product selectProduct(@Param("contentId")int contentId);
	
	@Update("UPDATE content set price=#{price},title=#{title},Abstract=#{summary},icon=#{image},text=#{detail} WHERE id=#{id} ")
	void Update(Content content);
	
	@Insert("INSERT INTO content(price,title,icon,Abstract,text) VALUES(#{price},#{title},#{image},#{summary},#{detail})")
	void insertContent(Content content);
	

	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Delete("Delet FROM Content WHERE id=#{contentId}")
	void deletecontent(@Param("contentId")int contentId);
}
