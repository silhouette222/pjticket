package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.dao.MusicalDAO;
import com.ticket.domain.MusicalVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.MusicalService;


public class MusicalServiceImpl implements MusicalService{

	private MusicalDAO musicalDAO;
	public void setMusicalDAO(MusicalDAO musicalDAO){
		this.musicalDAO=musicalDAO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	
	@Override
	public void createMc(MusicalVO mc) throws SQLException {

		musicalDAO.insertMc(mc);
		String[]files=mc.getFiles();
				
	}
	@Transactional(propagation=Propagation.REQUIRED,
			   rollbackFor={Exception.class})
	@Override
	public void updateMc(MusicalVO mc) throws SQLException {

		musicalDAO.updateMc(mc);
		
		int ttr_no=mc.getTtr_no();
		/*musicalDAO.deleteAttach(ttr_no);*/
		
		String[] files=mc.getFiles();
		System.out.println(mc);
		if(files==null){return;}
		for(String file_name:files){
		/*	musicalDAO.replaceAttach(file_name, ttr_no);*/
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,
		       rollbackFor={Exception.class})
	@Override
	public void deleteMc(int ttr_no) throws SQLException {

		musicalDAO.deleteMc(ttr_no);
/*		musicalDAO.deleteAttach(ttr_no);*/
	}
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly=true)
	@Override
	public List<MusicalVO> readMcList() throws SQLException {
		List<MusicalVO> mcList=musicalDAO.selectMcList();
		return mcList;
	}
	@Override
	public MusicalVO readMusicalBymcno(int ttr_no) throws SQLException {
		MusicalVO mc=musicalDAO.selectMusicalBymcno(ttr_no);
		return mc;
	}
	@Override
	public List<MusicalVO> readSearchMusicalList(SearchCriteria cri)
			throws SQLException {
		List<MusicalVO> mcList=musicalDAO.selectSearchList(cri);
		return mcList;
	}
	@Override
	public int readSearchMcListCount(SearchCriteria cri) throws SQLException {
		int countList=musicalDAO.selectSearchListCount(cri);
		return countList;
	}
	@Override
	public List<String> getAttach(int ttr_no) throws SQLException {
		List<String> file_names=musicalDAO.selectAttach(ttr_no);
		return file_names;
	}
	
	
	
	
}
