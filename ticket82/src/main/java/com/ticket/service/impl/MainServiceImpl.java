package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.BoardDAO;
import com.ticket.dao.MainDAO;
import com.ticket.domain.MainVO;
import com.ticket.service.MainService;

public class MainServiceImpl implements MainService {

	private MainDAO mainDAO;

	public void setMainDAO(MainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}

	@Override
	public List<MainVO> selectMainList() throws SQLException {
		return mainDAO.selectMainList();
	}

	@Override
	public MainVO selectMain(String main_id) throws SQLException {
		return mainDAO.selectMain(main_id);
	}

	@Override
	public void insertMain(MainVO main) throws SQLException {
		mainDAO.insertMain(main);
	}

	@Override
	public void deleteMain(String main_id) throws SQLException {
		mainDAO.deleteMain(main_id);
	}

	@Override
	public void insertMain2(MainVO main) throws SQLException {
		String path=mainDAO.selectthumb(main.getTtr_no());
		main.setMain_path(path);
		mainDAO.insertMain(main);
	}

	@Override
	public void updateMain(MainVO main) throws SQLException {
		mainDAO.updateMain(main);
	}

	@Override
	public void updateMain2(MainVO main) throws SQLException {
		System.out.println(main.getMain_id());
		System.out.println(main.getTtr_no());
		String path=mainDAO.selectthumb(main.getTtr_no());
		System.out.println(path);
		main.setMain_path(path);
		mainDAO.updateMain(main);
	}
}
