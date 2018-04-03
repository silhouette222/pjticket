package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.QNADAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.QNAVO;
import com.ticket.domain.SearchCriteria;

public class QNADAOImpl implements QNADAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE = "QNAMapper";
	
	@Override
	public void insertQNA(QNAVO qna) throws SQLException {
		sqlSession.update(NAMESPACE+".insertQNA", qna);
	}

	@Override
	public void updateQNA(QNAVO qna) throws SQLException {
		sqlSession.update(NAMESPACE+".updateQNA", qna);
	}

	@Override
	public void deleteQNA(int qna_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteQNA", qna_no);
	}

	@Override
	public List<QNAVO> selectQNAList() throws SQLException {
		List<QNAVO> qnaList = sqlSession.selectList(NAMESPACE+".selectQNAList", null);
		return qnaList;
	}

	@Override
	public QNAVO selectQNAByNo(int qna_no) throws SQLException {
		QNAVO qna = (QNAVO)sqlSession.selectOne(NAMESPACE+".selectQNAByNo", qna_no);
		return qna;
	}

	@Override
	public List<QNAVO> selectQNAListCriteria(Criteria cri) throws SQLException {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<QNAVO> qnaList = sqlSession.selectList(NAMESPACE+".selectQNAList", null, rowBounds);
		return qnaList;
	}

	@Override
	public List<QNAVO> selectSearchList(SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		System.out.println(cri);
		List<QNAVO> qnaList = sqlSession.selectList(NAMESPACE+".selectSearchQNAList", cri, rowBounds);
		System.out.println(qnaList);
		return qnaList;
	}

	@Override
	public int selectSearchListCount(SearchCriteria cri) throws SQLException {
		int listCount=(Integer)sqlSession.selectOne(NAMESPACE+".selectSearchQNACount", cri);
		return listCount;
	}

	@Override
	public void qnareply(QNAVO qna) throws SQLException {
		sqlSession.update(NAMESPACE+".addStep",qna);
		sqlSession.update(NAMESPACE+".qnareply",qna);
	}

}
