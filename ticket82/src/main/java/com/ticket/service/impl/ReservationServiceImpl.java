package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.dao.ReservationDAO;
import com.ticket.domain.ReservationVO;
import com.ticket.service.ReservationService;
public class ReservationServiceImpl implements ReservationService{

	private ReservationDAO resDAO;
	public void setReservationDAO(ReservationDAO resDAO) {
		this.resDAO = resDAO;
	}

	@Override
	public int countresbyseat_id(String seat_id) throws SQLException {
		List<ReservationVO> res=resDAO.selectresbyseat_id(seat_id);
		int rescnt=res.size();
		return rescnt;
	}
	@Override
	public ReservationVO selectresbyres_id(String res_id) throws SQLException {
		ReservationVO res=resDAO.selectresbyres_id(res_id);
		return res;
	}
	@Override
	public ReservationVO selectresbyres_nom(int res_nom, String seat_id)
			throws SQLException {
		ReservationVO res=resDAO.selectresbyres_nom(res_nom,seat_id);
		return res;
	}
	@Override
	public List<ReservationVO> selectresbyseat_id(String seat_id)
			throws SQLException {
		List<ReservationVO> res=resDAO.selectresbyseat_id(seat_id);
		return res;
	}
	@Override
	public List<ReservationVO> selectresbymem_id(String mem_id)
			throws SQLException {
		List<ReservationVO> res=resDAO.selectresbymem_id(mem_id);
		return res;
	}
	@Override
	public List<ReservationVO> selectresbyttr_no(int ttr_no)
			throws SQLException {
		List<ReservationVO> res=resDAO.selectresbyttr_no(ttr_no);
		return res;
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void insertres(ReservationVO res) throws SQLException {
		resDAO.insertRes(res);
		
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deleteResid(String res_id) throws SQLException {
		resDAO.deleteResid(res_id);
		
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deleteResnom(String res_nom) throws SQLException {
		resDAO.deleteResnom(res_nom);
		
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deletettrno(int ttr_no) throws SQLException {
		resDAO.deletettrno(ttr_no);
		
	}

	@Override
	public List<ReservationVO> readResList() throws SQLException {
		List<ReservationVO> resList=resDAO.selectResList();
		
		for (int i = 0; i < resList.size(); i++) {
			ReservationVO bef = resList.get(i);
			String thumb = resDAO.selectThumb(bef.getTtr_no());
			/*bef.setThumb_name(thumb);*/
			resList.set(i, bef);
		};
		return null;
	}
	
	
}
