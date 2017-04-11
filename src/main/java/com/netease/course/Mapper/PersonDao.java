package com.netease.course.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.netease.course.pojo.Person;

public interface PersonDao {

	@Select("SELECT * From person WHERE userName=#{personId}")
	Person getPerson(@Param("personId")String personId);
	
	@Select("SELECT * From person WHERE userName=#{userName}")
	Person getPersonInfo(@Param("userName")String userName);
}
