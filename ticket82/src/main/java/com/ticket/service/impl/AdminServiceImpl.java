package com.ticket.service.impl;

import java.sql.SQLException;

import com.ticket.dao.AdminDAO;
import com.ticket.domain.AdminVO;
import com.ticket.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private AdminDAO adminDAO;
	public void setAdminDAO(AdminDAO adminDAO){
		this.adminDAO=adminDAO;
	}
	
	@Override
	public AdminVO getAdminById(String am_id) throws SQLException {
		AdminVO admin=adminDAO.selectAdminById(am_id);
		return admin;
	}

	@Override
	public AdminVO login(String am_id) throws SQLException {
		AdminVO admin=adminDAO.selectAdminById(am_id);
		return admin;
	}

}
