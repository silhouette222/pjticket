package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.AnswerVO;
import com.ticket.domain.Criteria;

public interface AnswerService {
	
	List<AnswerVO> getAnswerList(int qna_no)throws SQLException;
	
	void addAnswer(AnswerVO answer)throws SQLException;
	void modifyAnswer(AnswerVO answer)throws SQLException;
	void removeAnswer(int ano)throws SQLException;
	
	List<AnswerVO> getAnswerListPage(int qna_no,Criteria cri)throws SQLException;
	int countAnswer(int qna_no)throws SQLException;
}
