package com.sist.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.FoodService;
import com.sist.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final FoodService foodService;
	
	@GetMapping("main/main.do")
	public String main_main(String page, Model model, HttpServletRequest request) {
		
		if(page == null) {
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		final int ROW_SIZE = 12;
		int start = (curpage-1)*ROW_SIZE+1;
		int end = curpage*ROW_SIZE;
		
		List<FoodVO> list = foodService.foodListData(start, end);
		int totalpage = foodService.foodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage > totalpage)
			endPage = totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		List<FoodVO> cList = new ArrayList<FoodVO>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=cookies.length-1; i>=0; i--) {
				if(cookies[i].getName().startsWith("food_")) {
					if(cookies[i].getName().equals("food_null"))
						continue;
					int no = Integer.parseInt(cookies[i].getValue());
					FoodVO fvo = foodService.foodDetail(no);
					cList.add(fvo);
				}
			}
		}
		
		model.addAttribute("cList", cList);
		model.addAttribute("size", cList.size());
		
		model.addAttribute("main_jsp", "../main/home.jsp");
		return "main/main";
	}
	
}
