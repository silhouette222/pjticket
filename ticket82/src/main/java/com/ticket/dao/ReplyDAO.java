package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;

public interface ReplyDAO {
	
	List<ReplyVO> selectReplyList(int bno) throws SQLException;
	void insertReply(ReplyVO reply)throws SQLException;
	void updateReply(ReplyVO reply)throws SQLException;
	void deleteReply(int rno)throws SQLException;
	
	List<ReplyVO> selectReplyListPage(int bno,Criteria cri)
					throws SQLException;
	int countReply(int bno)throws SQLException;
}








