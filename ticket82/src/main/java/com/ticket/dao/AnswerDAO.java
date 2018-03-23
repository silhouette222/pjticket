package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.AnswerVO;
import com.ticket.domain.Criteria;

public interface AnswerDAO {

	List<AnswerVO> selectAnswerList(int qna_no) throws SQLException;
	void insertAnswer(AnswerVO answer)throws SQLException;
	void updateAnswer(AnswerVO answer)throws SQLException;
	void deleteAnswer(int ano)throws SQLException;
	
	List<AnswerVO> selectAnswerListPage(int qna_no,Criteria cri)throws SQLException;
	int countAnswer(int qna_no)throws SQLException;
	
}
