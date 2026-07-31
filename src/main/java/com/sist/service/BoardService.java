package com.sist.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sist.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardListData(int start);

	public int boardRowCount();

	public void boardInsert(BoardVO vo);

	public BoardVO boardDetailData(int no);
	
	public void boardReplyInsert(int parent_no, BoardVO vo);

	public String boardUpdate(BoardVO vo);

	public boolean boardDelete(int no, String pwd);

}
