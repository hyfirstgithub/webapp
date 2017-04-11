package com.netease.course.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.netease.course.pojo.Product;
import com.netease.course.pojo.Trx;
import com.netease.course.pojo.TrxJoinContent;

public interface TrxDao {
	
	@Select("SELECT * FROM trx where id=#{trxId}")
	Trx selectTrx(@Param("trxId")int trxId);
	
	@Insert("INSERT INTO trx(contentId,personId,buyPrice,time,buyNum) VALUES(#{contentId},#{personId},#{buyPrice},#{time},#{buyNum})")
	void insertTrx(Trx trx);
	
	@Select("SELECT * FROM trx WHERE personId=#{personId}")
	List<Trx> getPsersonBuylistId(@Param("personId")int personId);
	
	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Select("SELECT content.id,content.price,title,icon,Abstract,text FROM trx,content where content.id=trx.contentId")
	List<Product> getBuyList();
	
	@Results({
		@Result(property="image",column="icon"),
		@Result(property="summary",column="Abstract"),
		@Result(property="detail",column="text")
	})
	@Select("SELECT * FROM content where id NOT in(SELECT contentId FROM trx)")
	List<Product> getUnSellList();
	
	@Select("SELECT COUNT(*) FROM Trx WHERE contentId=#{contentId}")
	int getSellNUm(@Param("contentId")int contentId);
	
	@Results({
		@Result(property="id",column="content.id"),
		@Result(property="image",column="icon")
	})
	@Select("SELECT content.id,title,price,buyPrice,time,buyNum,icon FROM trx,content WHERE content.id=contentId")
	List<TrxJoinContent> getAccount();
}	
