package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.MainVO;

public interface MainService {
	public List<MainVO> selectMainList()throws SQLException;
	public MainVO selectMain(String main_id)throws SQLException;
	public void insertMain(MainVO main)throws SQLException;
	public void insertMain2(MainVO main)throws SQLException;
	public void deleteMain(String main_id)throws SQLException;
	public void updateMain(MainVO main)throws SQLException;
	public void updateMain2(MainVO main)throws SQLException;
}
