package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.CompanyVO;
import com.ticket.domain.Criteria;
import com.ticket.domain.SearchCriteria;

;

public interface CompanyService {

	List<CompanyVO> getCompanyList()throws SQLException;
	CompanyVO getCompanyById(String com_id)throws SQLException;
	CompanyVO login(String com_id)throws SQLException;
	
	void insertCompany(CompanyVO company)throws SQLException;
	void updateCompany(CompanyVO company)throws SQLException;
	void deleteCompany(String com_id)throws SQLException;
	int checkId(String com_id)throws SQLException;
	void insertCompanyAuthority(CompanyVO company)throws SQLException;
	
	List<CompanyVO> readCompanyListCriteria(Criteria cri)throws SQLException;
	List<CompanyVO> readSearchMemberList(SearchCriteria cri)throws SQLException;
	int readSearchCompanyListCount(SearchCriteria cri)throws SQLException;
}
