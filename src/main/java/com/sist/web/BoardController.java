package com.sist.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		boardService.boardInsert(vo);
		return "redirect:../board/list.do";
	}
	
	@GetMapping("board/detail.do")
	public String board_detail(int no, Model model) {
		System.out.println("no :: " + no);
		BoardVO vo = boardService.boardDetailData(no);
		System.out.println("detail :: " + vo.toString());
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../board/detail.jsp");
		return "main/main";
	}
	
	@GetMapping("board/reply.do")
	public String board_reply(int no, Model model) {
		
		model.addAttribute("no", no);
		
		model.addAttribute("main_jsp", "../board/reply.jsp");
		return "main/main";
	}
	
	@PostMapping("board/reply_ok.do")
	public String board_reply_ok(int parent_no, BoardVO vo) {
		boardService.boardReplyInsert(parent_no, vo);
		return "redirect:../board/list.do";
	}
	
	@GetMapping("board/update.do")
	public String board_update(int no, Model model) {
		
		BoardVO vo = boardService.boardDetailData(no);
		model.addAttribute("vo", vo);
		
		model.addAttribute("main_jsp", "../board/update.jsp");
		return "main/main";
	}
	
	@PostMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo, Model model) {
		System.out.println(vo.toString());
		String msg = boardService.boardUpdate(vo);
		
		if ("FAIL".equals(msg)) {
	        model.addAttribute("vo", vo); // 작성 중이던 데이터 유지
	        model.addAttribute("msg", "FAIL"); // 실패 메시지 전달
	        model.addAttribute("main_jsp", "../board/update.jsp");
	        return "main/main";
	    }
		
		return "redirect:../board/list.do";
	}
	/*
	@GetMapping("board/delete.do")
	public String board_delete(int no) {
		boardService.boardDelete(no);
				
		return "redirect:../board/list.do";
	}
	*/
	@GetMapping("board/delete.do")
	public String board_delete(int no, Model model) {
		
		model.addAttribute("no", no);
		model.addAttribute("main_jsp", "../board/delete.jsp");
		
		return "main/main";
	}
	
}
