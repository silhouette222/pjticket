package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.QNADAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.QNAVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.QNAService;

public class QNAServiceImpl implements QNAService {

	private QNADAO qnaDAO;
	public void setQNADAO(QNADAO qnaDAO){
		this.qnaDAO=qnaDAO;
	}
	
	@Override
	public void createQNA(QNAVO qna) throws SQLException {
		qnaDAO.insertQNA(qna);
	}

	@Override
	public void updateQNA(QNAVO qna) throws SQLException {
		qnaDAO.updateQNA(qna);
	}

	@Override
	public void deleteQNA(int qna_no) throws SQLException {
		qnaDAO.deleteQNA(qna_no);
	}

	@Override
	public List<QNAVO> readQNAList() throws SQLException {
		List<QNAVO> qnaList=qnaDAO.selectQNAList();
		return qnaList;
	}

	@Override
	public QNAVO readQNAByNo(int qna_no) throws SQLException {
		QNAVO qna=qnaDAO.selectQNAByNo(qna_no);
		return qna;
	}

	@Override
	public List<QNAVO> readQNAListCriteria(Criteria cri) throws SQLException {
		List<QNAVO> qnaList=qnaDAO.selectQNAListCriteria(cri);
		return qnaList;
	}

	@Override
	public List<QNAVO> readSearchQNAList(SearchCriteria cri)
			throws SQLException {
		List<QNAVO> qnaList=qnaDAO.selectSearchList(cri);
		return qnaList;
	}

	@Override
	public int readSearchQNAListCount(SearchCriteria cri) throws SQLException {
		int countList=qnaDAO.selectSearchListCount(cri);
		return countList;
	}

	@Override
	public void qnareply(QNAVO qna) throws SQLException {
		qnaDAO.qnareply(qna);
	}

}
