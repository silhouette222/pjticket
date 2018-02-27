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
		return sqlSession.selectList(NAMESPACE+".selectmainall");
	}

	@Override
	public void insertMain(MainVO main) throws SQLException {
		sqlSession.update(NAMESPACE+".insertmain",main);
	}

	@Override
	public void deleteMain(String main_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deletemain",main_id);
	}

	@Override
	public MainVO selectMain(String main_id) throws SQLException {
		return (MainVO) sqlSession.selectOne(NAMESPACE+".selectmain",main_id);
	}

	@Override
	public void updateMain(MainVO main) throws SQLException {
		sqlSession.update(NAMESPACE+".updatemain",main);
	}

	@Override
	public String selectthumb(int ttr_no) throws SQLException {
		String thumb = (String) sqlSession.selectOne(NAMESPACE+".selectThumb",ttr_no);
		return thumb;
	}
}
