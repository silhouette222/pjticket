package com.ticket.dao;

import java.sql.SQLException;

import com.ticket.domain.AdminVO;

public interface AdminDAO {

	AdminVO selectAdminById(String am_id)throws SQLException;
}
