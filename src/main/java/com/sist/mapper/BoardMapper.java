package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.BoardVO;

public interface BoardMapper {

	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'yyyy-mm-dd') as dbday, hit, group_tab "
			+ "FROM springreplyboard "
			+ "ORDER BY group_id DESC, group_step ASC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT COUNT(*) FROM springreplyboard ")
	public int boardRowCount();
	
	@Insert("INSERT INTO springreplyboard(no, name, subject, content, pwd, group_id "
			+ "VALUES(srb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}, (SELECT NVL(MAX(group_id)+1,1) FROM springreplyboard))")
	public void boardInsert(BoardVO vo);
	
	@Update("UPDATE springreplyboard "
			+ "SET hit=hit+1 "
			+ "WHERE no=#{no} ")
	public void boardHitIncrement(int no);
	
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'yyyy-mm-dd') as dbday, hit "
			+ "FROM springreplyboard "
			+ "WHERE no = #{no}")
	public BoardVO boardDetailData(int no);
	
	// 답변하기
	// 1. 상위 데이터 가지고 오기
	@Select("SELECT group_id, group_step, group_tab "
			+ "FROM springreplyboard "
			+ "WHERE no = #{no}")
	public BoardVO boardParentInfoData(int no);

	@Update("UPDATE springreplyboard "
			+ "SET group_step = group_step+1 "
			+ "WHERE group_id = #{group_id} AND group_step > #{group_step} ")
	public void boardStepIncrement(@Param("group_id") int group_id, @Param("group_step") int group_step );	
	
	// 3. 내 데이터 입력하기
	@Insert("INSERT INTO springreplyboard(no, name, subject, content, pwd, group_id, group_step, group_tab, root "
			+ "VALUES(srb_no_seq.nextval, #{name}, #{subject}, #{content}, #{pwd}, #{group_id}, #{group_step}, #{group_tab}, #{root}")
	public void boardReplyInsert(BoardVO vo);
	
	// 4. 상위 데이터에 갯수 update 하기
	@Update("UPDATE springreplyboard "
			+ "SET depth = depth + 1 "
			+ "WHERE no = #{no}")
	public void boardParentDepth(int no);
	
	
	// 수정
	
	// 삭제

}
