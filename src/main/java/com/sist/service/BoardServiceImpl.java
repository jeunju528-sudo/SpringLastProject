package com.sist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sist.mapper.BoardMapper;
import com.sist.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper boardMapper;

	@Override
	public List<BoardVO> boardListData(int start) {
		return boardMapper.boardListData(start);
	}

	@Override
	public int boardRowCount() {
		return boardMapper.boardRowCount();
	}

	@Override
	public void boardInsert(BoardVO vo) {
		boardMapper.boardInsert(vo);
	}

	@Override
	public BoardVO boardDetailData(int no) {
		// hit + 1
		boardMapper.boardHitIncrement(no);
		return boardMapper.boardDetailData(no);
	}

	/*
	 * Mybatis는 쓰기지연 없음!
	 * @Transactional
	 * public void boardReplyInsert(int pno, BoardVO vo){
	 * 
	 * 	@Before => session.openSession();
	 * 	try {
	 * 		conn.setAutoCommit(false); =>@Around
	 * 		boardMapper.boardInsert();
	 * 		conn.commit();
	 * 	}
	 * 	catch(){
	 * 		conn.rollback();	=>@AfterThrowing
	 * 	}
	 * 	finally {
	 * 		conn.setAutoCommit(true) => @After 
	 * 	}
	 * 
	 * }
	 * */
	@Override
	@Transactional // AOP 적용
	public void boardReplyInsert(int parent_no, BoardVO vo) {
		// 1. 부모글 정보조회
		BoardVO parent = boardMapper.boardDetailData(parent_no);
		// 2. 부모글의 다른 댓글들 출력순서 밀기 group_step + 1
		boardMapper.boardStepIncrement(parent.getGroup_id(), parent.getGroup_step());
		// 3. 내 글 넣기
		vo.setGroup_id(parent.getGroup_id());
		vo.setGroup_step(parent.getGroup_step()+1); // 출력 순서, 부모보다 하나 아래-맨 위에 
		vo.setGroup_tab(parent.getGroup_step()+1); // 댓글 깊이, 부모거보다 한칸 밑에
		vo.setRoot(parent_no);
		boardMapper.boardReplyInsert(vo);
		// 4. 부모글 depth 올리기
		boardMapper.boardParentDepth(parent_no);
	}

}
