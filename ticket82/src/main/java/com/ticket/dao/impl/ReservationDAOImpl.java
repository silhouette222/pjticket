package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.ReservationDAO;
import com.ticket.domain.ReservationVO;

public class ReservationDAOImpl implements ReservationDAO{

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession= sqlSession;
	}
	
	private static final String NAMESPACE="ResMapper";

	@Override
	public void insertRes(ReservationVO res) throws SQLException {
		sqlSession.insert(NAMESPACE+".insertres",res);		
	}

	@Override
	public void deletettrno(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteresbyttr_no",ttr_no);

	}

	@Override
	public void deleteResid(String res_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteresbyres_id",res_id);		
	}

	@Override
	public void deleteResnom(String res_nom) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteresbyres_nom",res_nom);
		
	}

	@Override
	public ReservationVO selectresbyres_id(String res_id) throws SQLException {
		ReservationVO res=(ReservationVO) sqlSession.selectOne(NAMESPACE+".selectresbyres_id",res_id);
		return res;
	}

	@Override
	public ReservationVO selectresbyres_nom(int res_nom, String seat_id)
			throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		
		paramMap.put("res_nom",res_nom);
		paramMap.put("seat_id",seat_id);
		
		ReservationVO res=(ReservationVO) sqlSession.selectOne(NAMESPACE+".selectresbyres_nom",paramMap);
		return res;
	}

	@Override
	public List<ReservationVO> selectresbyseat_id(String seat_id)
			throws SQLException {
		List<ReservationVO> res=sqlSession.selectList(NAMESPACE+".selectresbyseat_id",seat_id);
		return res;
	}

	@Override
	public List<ReservationVO> selectresbymem_id(String mem_id)
			throws SQLException {
		List<ReservationVO> res=sqlSession.selectList(NAMESPACE+".selectresbymem_id",mem_id);
		return res;
	}

	@Override
	public List<ReservationVO> selectresbyttr_no(int ttr_no)
			throws SQLException {
		List<ReservationVO> res=sqlSession.selectList(NAMESPACE+".selectresbyttr_no",ttr_no);
		return res;
	}

	@Override
	public List<ReservationVO> selectResList() throws SQLException {
		
		List<ReservationVO> resList=sqlSession.selectList(NAMESPACE+".selectResList",null);
		return resList;
	}

	@Override
	public String selectThumb(int ttr_no) throws SQLException {
		String thumb=(String) sqlSession.selectOne(NAMESPACE+".selectThumb",ttr_no);
		return thumb;
	}
	
	

}
