package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.QNAVO;
import com.ticket.domain.SearchCriteria;

public interface QNAService {

	void createQNA(QNAVO qna) throws SQLException;
	void updateQNA(QNAVO qna) throws SQLException;
	void deleteQNA(int qna_no) throws SQLException;
	
	List<QNAVO> readQNAList() throws SQLException;
	QNAVO readQNAByNo(int qna_no) throws SQLException;
	List<QNAVO> readQNAListCriteria(Criteria cri) throws SQLException;
	List<QNAVO> readSearchQNAList(SearchCriteria cri) throws SQLException;
	int readSearchQNAListCount(SearchCriteria cri)throws SQLException;
	
}
