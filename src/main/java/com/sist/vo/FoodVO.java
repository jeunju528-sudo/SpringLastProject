package com.sist.vo;

import lombok.Data;

@Data
public class FoodVO {
	private int no, hit;
	private String name, type, phone, address, price, theme, time, reserve, parking, content, poster;
	private double score;
}
