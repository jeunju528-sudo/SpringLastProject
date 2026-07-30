package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sist.vo.BoardVO;
import com.sist.vo.FoodVO;

public interface FoodService {

	public List<FoodVO> foodListData(int start, int end);
	
	public int foodTotalPage();
	
	public FoodVO foodDetail(int no);
}
