package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.AnswerDAO;
import com.ticket.domain.AnswerVO;
import com.ticket.domain.Criteria;

public class AnswerDAOImpl implements AnswerDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE = "AnswerMapper";
	
	@Override
	public List<AnswerVO> selectAnswerList(int qna_no) throws SQLException {
		List<AnswerVO> ansList=sqlSession.selectList(NAMESPACE+".selectAnswerList",qna_no);
		return ansList;
	}

	@Override
	public void insertAnswer(AnswerVO answer) throws SQLException {
		sqlSession.update(NAMESPACE+".insertAnswer",answer);
	}

	@Override
	public void updateAnswer(AnswerVO answer) throws SQLException {
		sqlSession.update(NAMESPACE+".updateAnswer",answer);
	}

	@Override
	public void deleteAnswer(int ano) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteAnswer",ano);
	}

	@Override
	public List<AnswerVO> selectAnswerListPage(int qna_no, Criteria cri)
			throws SQLException {
		int offset=cri.getPageStart();
		int limit=cri.getPerPageNum();
		RowBounds rowbounds=new RowBounds(offset,limit);
		
		List<AnswerVO> ansList=sqlSession.selectList(NAMESPACE+".selectAnswerListPage",qna_no,rowbounds);
		return ansList;
	}

	@Override
	public int countAnswer(int qna_no) throws SQLException {
		int count=(Integer)sqlSession.selectOne(NAMESPACE+".countAnswer",qna_no);
		return count;
	}

}
