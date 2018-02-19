package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;

public interface ReplyService {
	
	List<ReplyVO> getReplyList(int bno) throws SQLException;
	
	void addReply(ReplyVO reply) throws SQLException;
	void modifyReply(ReplyVO reply)throws SQLException;
	void removeReply(int rno) throws SQLException;
	
	List<ReplyVO> getReplyListPage(int bno, Criteria cri) 
	 				throws SQLException;
	int countReply(int bno) throws SQLException;
}








