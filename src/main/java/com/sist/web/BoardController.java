package com.sist.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.service.BoardService;
import com.sist.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("board/list.do")
	public String board_list(String page, Model model) {
		
		if(page == null) {
			page = "1";
		}
		
		int curpage = Integer.parseInt(page);
		
		final int ROW_SIZE = 10;
		int start = (curpage-1)*ROW_SIZE;
		
		List<BoardVO> list = boardService.boardListData(start);
		int count = boardService.boardRowCount();
		int totalpage = (int)(Math.ceil(count/10.0)); // 총 페이지
		count = count - ((curpage*ROW_SIZE)-ROW_SIZE);
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("count", count);
		model.addAttribute("today", new SimpleDateFormat("yyyy-mm-dd").format(new Date()));	
		
		model.addAttribute("main_jsp", "../board/list.jsp");
		return "main/main";
	}
	
	@GetMapping("board/insert.do")
	public String board_insert(String page, Model model) {
		
		model.addAttribute("main_jsp", "../board/insert.jsp");
		return "main/main";
	}
	
	@PostMapping("board/insert_ok.do")
	public String board_insert_ok(BoardVO vo) {
		
		return "redirect:../board/list.do";
	}
	
	
}
