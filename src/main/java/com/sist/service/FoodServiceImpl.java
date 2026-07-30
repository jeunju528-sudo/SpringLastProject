package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.mapper.FoodMapper;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
	
	private final FoodMapper foodMapper;

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		return foodMapper.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		return foodMapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetail(int no) {
		return foodMapper.foodDetail(no);
	}

}
