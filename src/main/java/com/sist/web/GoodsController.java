package com.sist.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.service.GoodsService;
import com.sist.vo.GoodsVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoodsController {
	
	private final GoodsService goodsService;

	@GetMapping("goods/list.do")
	public String goods_list(String page, Model model, HttpServletRequest request) {
		
		if(page == null) {
			page = "1";
		}
		int curpage = Integer.parseInt(page);
		final int ROW_SIZE = 12;
		int start = (curpage-1)*ROW_SIZE;
		
		List<GoodsVO> list = goodsService.goodsListData(start);
		int totalpage = goodsService.goodsTotalPage();
		
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
		
		Cookie[] cookies = request.getCookies();
		List<GoodsVO> cList = new ArrayList<GoodsVO>();
		
		if(cookies != null) {
			for(int i=cookies.length-1; i>=0; i--) {
				if(cookies[i].getName().startsWith("goods_")) {
					if(cookies[i].getName().equals("goods_null"))
						continue;
					String cno = cookies[i].getValue();
					GoodsVO gvo = goodsService.goodsDetailData(Integer.parseInt(cno));
					cList.add(gvo);
				}
			}
		}
		
		model.addAttribute("cList", cList);
		model.addAttribute("size", cList.size());
		
		model.addAttribute("main_jsp", "../goods/list.jsp");
		return "main/main";
	}
	
	@GetMapping("goods/detail_before.do")
	public String goods_detail_before(int no, HttpServletResponse response, RedirectAttributes ra) {
		
		Cookie cookie = new Cookie("goods_"+no, String.valueOf(no));
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		
		response.addCookie(cookie);
		
		ra.addAttribute("no",no);
		
		return "redirect:../goods/detail.do";
	}
	
	@GetMapping("goods/detail.do")
	public String goods_detail(int no, Model model) {
		
		GoodsVO vo = goodsService.goodsDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../goods/detail.jsp");
		
		return "main/main";
	}
}
