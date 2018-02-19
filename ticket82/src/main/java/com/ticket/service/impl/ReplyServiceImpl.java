package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticket.dao.ReplyDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;
import com.ticket.service.ReplyService;

public class ReplyServiceImpl implements ReplyService{
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO){
		this.replyDAO=replyDAO;
	}

	@Override
	public List<ReplyVO> getReplyList(int bno) throws SQLException {		
		return replyDAO.selectReplyList(bno);
	}

	@Override
	public void addReply(ReplyVO reply) throws SQLException {		
		replyDAO.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		replyDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int rno) throws SQLException {
		replyDAO.deleteReply(rno);
	}

	@Override
	public List<ReplyVO> getReplyListPage(int bno, Criteria cri)
			throws SQLException {
		return replyDAO.selectReplyListPage(bno, cri);
	}

	@Override
	public int countReply(int bno) throws SQLException {
		return replyDAO.countReply(bno);
	}

}
