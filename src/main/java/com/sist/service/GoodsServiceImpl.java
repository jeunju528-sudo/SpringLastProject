package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.mapper.GoodsMapper;
import com.sist.vo.GoodsVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {

	private final GoodsMapper goodsMapper;
	
	@Override
	public List<GoodsVO> goodsListData(int start) {
		return goodsMapper.goodsListData(start);
	}

	@Override
	public int goodsTotalPage() {
		return goodsMapper.goodsTotalPage();
	}

	@Override
	public GoodsVO goodsDetailData(int no) {
		return goodsMapper.goodsDetailData(no);
	}

}
