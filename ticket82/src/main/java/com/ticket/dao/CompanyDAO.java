package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.CompanyVO;
import com.ticket.domain.Criteria;
import com.ticket.domain.SearchCriteria;

public interface CompanyDAO {

	CompanyVO selectCompanyById(String com_id)throws SQLException;
	List<CompanyVO> selectCompanyList()throws SQLException;
	
	void insertCompany(CompanyVO company)throws SQLException;
	void updateCompany(CompanyVO company)throws SQLException;
	void deleteCompany(String com_id)throws SQLException;
	int registerCheck(String com_id)throws SQLException;
	void insertCompanyAuthority(CompanyVO company)throws SQLException;
	
	List<CompanyVO> selectCompanyListCriteria(Criteria cri)throws SQLException;
	
	List<CompanyVO> selectSearchList(SearchCriteria cri)throws SQLException;
	
	int selectSearchListCount(SearchCriteria cri)throws SQLException;
	
	
	
}
