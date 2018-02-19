package com.ticket.dao.impl;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.AdminDAO;
import com.ticket.domain.AdminVO;

public class AdminDAOImpl implements AdminDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	@Override
	public AdminVO selectAdminById(String am_id) throws SQLException {
		AdminVO admin = (AdminVO)sqlSession.selectOne("AdminMapper.selectAdminById", am_id);
		return admin;
	}

}
