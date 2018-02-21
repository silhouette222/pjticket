package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.MainDAO;
import com.ticket.domain.MainVO;

public class MainDAOImpl implements MainDAO {

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="MainMapper";

	@Override
	public List<MainVO> selectMainList() throws SQLException {
		return sqlSession.selectList(NAMESPACE+".listMain");
	}

	@Override
	public void insertMain(MainVO main) throws SQLException {
		sqlSession.insert(NAMESPACE+".insertMain",main);
	}

	@Override
	public void deleteMain(int ttr_no) throws SQLException {
		sqlSession.delete(NAMESPACE+".deleteMain", ttr_no);
	}

	@Override
	public void updateMain(MainVO main) throws SQLException {
		sqlSession.update(NAMESPACE+".updateMain",main);
	}

}
