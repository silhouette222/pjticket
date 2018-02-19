package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.ZzimDAO;
import com.ticket.domain.ZzimVO;
import com.ticket.service.ZzimService;

public class ZzimServiceImpl implements ZzimService {
	
	public ZzimDAO dao;
	public void setZzimDAO(ZzimDAO dao){
		this.dao=dao;
	}
	
	@Override
	public List<ZzimVO> listZzim(String mem_id) throws SQLException {
		return dao.listZzim(mem_id);
	}

	@Override
	public void insertZzim(ZzimVO zzim) throws SQLException {
		dao.insertZzim(zzim);
	}

	@Override
	public void deleteZzzim(int zzim_no) throws SQLException {
		dao.deleteZzim(zzim_no);
	}

	@Override
	public int countZzim(int ttr_no, String mem_id) throws SQLException {
		return dao.countZzim(ttr_no, mem_id);
	}

}
