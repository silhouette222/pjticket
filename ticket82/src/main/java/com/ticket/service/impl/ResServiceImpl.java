package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.dao.ResDAO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;
import com.ticket.service.ResService;

public class ResServiceImpl implements ResService {
	
	private ResDAO resDAO;

	public void setResDAO(ResDAO resDAO) {
		this.resDAO = resDAO;
	}


	@Override
	public int countresbyseat_id(String seat_id) throws SQLException {
		List<ResVO> res=resDAO.selectresbyseat_id(seat_id);
		int rescnt=res.size();
		return rescnt;
	}

	@Override
	public ResVO selectresbyres_id(String res_id) throws SQLException {
		ResVO res=resDAO.selectresbyres_id(res_id);
		return res;
	}

	@Override
	public ResVO selectresbyres_nom(int res_nom, String seat_id)
			throws SQLException {
		ResVO res=resDAO.selectresbyres_nom(res_nom,seat_id);
		return res;
	}

	@Override
	public List<ResVO> selectresbyseat_id(String seat_id) throws SQLException {
		List<ResVO> res=resDAO.selectresbyseat_id(seat_id);
		return res;
	}

	@Override
	public List<ResVO> selectresbymem_id(String mem_id) throws SQLException {
		List<ResVO> res=resDAO.selectresbymem_id(mem_id);
		return res;
	}

	@Override
	public List<ResVO> selectresbyttr_no(int ttr_no) throws SQLException {
		List<ResVO> res=resDAO.selectresbyttr_no(ttr_no);
		return res;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void insertres(ResVO res) throws SQLException {
		resDAO.insertres(res);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deleteresbyres_id(String res_id) throws SQLException {
		resDAO.deleteresbyres_id(res_id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deleteresbyres_nom(String res_nom) throws SQLException {
		resDAO.deleteresbyres_nom(res_nom);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deleteresbyttr_no(int ttr_no) throws SQLException {
		resDAO.deleteresbyttr_no(ttr_no);
	}
}
