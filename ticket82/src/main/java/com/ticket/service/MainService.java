package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.MainVO;

public interface MainService {
	public List<MainVO> readMainList()throws SQLException;
	public void insertMain(MainVO main)throws SQLException;
	public void updateMain(MainVO main)throws SQLException;
	public void deleteMain(int ttr_no)throws SQLException;
}
