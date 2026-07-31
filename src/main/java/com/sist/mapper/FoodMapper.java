package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FoodVO;

public interface FoodMapper {
	/*
	@Select("SELECT no, poster, address, name "
			+ "FROM food "
			+ "ORDER BY no ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY")
	public List<FoodVO> foodListData(int start);
	*/
	@Select("SELECT no, poster, address, name, num "
			+ "FROM (SELECT no, poster, address, name, rownum as num "
			+ "FROM (SELECT no, poster, address, name "
			+ "FROM food "
			+ "ORDER BY no ASC) "
			+ ") "
			+ "WHERE num between #{start} and #{end} ")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) "
			+ "FROM food ")
	public int foodTotalPage();
	
	@Select("SELECT no, poster, name, address, time, price, score, theme, content, parking "
			+ "FROM food "
			+ "WHERE no = #{no}")
	public FoodVO foodDetail(int no);
	
	@Update("UPDATE food "
			+ "SET hit = hit+1 "
			+ "WHERE no=#{no}")
	public void foodHitIncrement(int no);
	
	@Select("SELECT no, name, hit, rownum "
			+ "FROM (SELECT no, name, hit "
			+ "FROM food "
			+ "ORDER BY hit DESC) "
			+ "WHERE rownum <= 7")
	public List<FoodVO> foodHit7Data();
	
}
