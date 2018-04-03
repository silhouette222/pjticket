package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.QNAVO;
import com.ticket.domain.SearchCriteria;

public interface QNADAO {
	
	void insertQNA(QNAVO qna) throws SQLException;
	
	void updateQNA(QNAVO qna) throws SQLException;
	
	void deleteQNA(int qna_no) throws SQLException;
	
	List<QNAVO> selectQNAList() throws SQLException;
	
	QNAVO selectQNAByNo(int qna_no) throws SQLException;
	
	List<QNAVO> selectQNAListCriteria(Criteria cri) throws SQLException;
	
	List<QNAVO> selectSearchList(SearchCriteria cri) throws SQLException;
	
	int selectSearchListCount(SearchCriteria cri) throws SQLException;
	
	void qnareply(QNAVO qna) throws SQLException;
	
	
}
