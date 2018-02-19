package com.ticket.service;

import java.sql.SQLException;

import com.ticket.domain.AdminVO;

public interface AdminService {
	
	AdminVO getAdminById(String am_id)throws SQLException;
	AdminVO login(String am_id)throws SQLException;
}
