package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.MainDAO;
import com.ticket.domain.MainVO;
import com.ticket.service.MainService;

public class MainServiceImpl implements MainService {

	private MainDAO mainDAO;

	public void setMainDAO(MainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}
	
	@Override
	public List<MainVO> readMainList() throws SQLException {
		return mainDAO.selectMainList();
	}

	@Override
	public void insertMain(MainVO main) throws SQLException {
		mainDAO.insertMain(main);
	}

	@Override
	public void updateMain(MainVO main) throws SQLException {
		mainDAO.updateMain(main);
	}

	@Override
	public void deleteMain(int ttr_no) throws SQLException {
		mainDAO.deleteMain(ttr_no);
	}

}
