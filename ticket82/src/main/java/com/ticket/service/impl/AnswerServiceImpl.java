package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.AnswerDAO;
import com.ticket.domain.AnswerVO;
import com.ticket.domain.Criteria;
import com.ticket.service.AnswerService;

public class AnswerServiceImpl implements AnswerService {

	private AnswerDAO answerDAO;
	public void setAnswerDAO(AnswerDAO answerDAO){
		this.answerDAO=answerDAO;
	}
	
	@Override
	public List<AnswerVO> getAnswerList(int qna_no) throws SQLException {
		return answerDAO.selectAnswerList(qna_no);				
	}

	@Override
	public void addAnswer(AnswerVO answer) throws SQLException {
		answerDAO.insertAnswer(answer);
	}

	@Override
	public void modifyAnswer(AnswerVO answer) throws SQLException {
		answerDAO.updateAnswer(answer);
	}

	@Override
	public void removeAnswer(int ano) throws SQLException {
		answerDAO.deleteAnswer(ano);
	}

	@Override
	public List<AnswerVO> getAnswerListPage(int qna_no, Criteria cri)
			throws SQLException {
		return answerDAO.selectAnswerListPage(qna_no, cri);
	}

	@Override
	public int countAnswer(int qna_no) throws SQLException {
		return answerDAO.countAnswer(qna_no);
	}

}
