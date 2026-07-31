package com.sist.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController // 문자열, json 전송만 가능, router기능 없음(sendredirect, forward)
@RequiredArgsConstructor
public class BoardRestController {
	
	private final BoardService boardService;
	
	@PostMapping(value="board/delete_ok.do", produces="text/html; charset=UTF-8")
	public String board_delete(int no, String pwd) {
		
		String result = "";
		
		boolean bCheck = boardService.boardDelete(no, pwd);
		if(bCheck) {
			result = "<script>"
					+ "location.href=\"../board/list.do\""
					+ "</script>";
		}
		else {
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back()"
					+ "</script>";
		}
		
		
		return result;
	}
	
}
