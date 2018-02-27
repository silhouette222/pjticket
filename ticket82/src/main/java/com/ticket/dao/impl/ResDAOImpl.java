package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.ResDAO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;

public class ResDAOImpl implements ResDAO {

private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="BoardMapper";
	
	@Override
	public ResVO selectresbyres_id(String res_id) throws SQLException {
		ResVO res=(ResVO) sqlSession.selectOne(NAMESPACE+".selectresbyres_id",res_id);
		return res;
	}

	@Override
	public ResVO selectresbyres_nom(int res_nom, String seat_id)
			throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		
		paramMap.put("res_nom",res_nom);
		paramMap.put("seat_id",seat_id);
		
		ResVO res=(ResVO) sqlSession.selectOne(NAMESPACE+".selectresbyres_nom",paramMap);
		return res;
	}

	@Override
	public List<ResVO> selectresbyseat_id(String seat_id) throws SQLException {
		List<ResVO> res=sqlSession.selectList(NAMESPACE+".selectresbyseat_id",seat_id);
		return res;
	}

	@Override
	public List<ResVO> selectresbymem_id(String mem_id) throws SQLException {
		List<ResVO> res=sqlSession.selectList(NAMESPACE+".selectresbymem_id",mem_id);
		return res;
	}

	@Override
	public List<ResVO> selectresbyttr_no(int ttr_no) throws SQLException {
		List<ResVO> res=sqlSession.selectList(NAMESPACE+".selectresbyttr_no",ttr_no);
		return res;
	}

	@Override
	public void insertres(ResVO res) throws SQLException {
		sqlSession.insert(NAMESPACE+".insertres",res);
	}

	@Override
	public void deleteresbyres_id(String res_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteresbyres_id",res_id);
	}

	@Override
	public void deleteresbyres_nom(String res_nom) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteresbyres_nom",res_nom);
	}

	@Override
	public void deleteresbyttr_no(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteresbyttr_no",ttr_no);
	}

	@Override
	public void updateres(ResVO res) throws SQLException {
		sqlSession.update("ResMapper.updateRes",res);
	}
}
