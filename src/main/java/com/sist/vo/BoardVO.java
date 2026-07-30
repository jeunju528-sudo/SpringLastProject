package com.sist.vo;

import java.util.Date;

import lombok.Data;
/*
 * group_id : 글 그룹 식별자 
 * group_step : 그룹 내 출력 순서 
 * group_tab : 들여쓰기 깊이
 * root : 누가 이 답변의 부모냐
 * depth : 답변 몇개 달렸냐, 지울수 있냐없냐 (0만 지울 수 있음)
 *             DESC  ASC 
 *          no   gi   gs   gt  root  depth
 * AAAAA    1    1    0    0    0     2    << 원글
 *  => bb   2    1    1    1    1     2
 *   => dd  4    1    3    2    2     0
 *   => cc  3    1    2    2    2     0
 *  => ee   5    1    4    1    1     0
 * */
@Data
public class BoardVO {
	private int no, hit, group_id, group_step, group_tab, root, depth;
	private String name, subject, content, pwd, dbday;
	private Date regdate;
}
