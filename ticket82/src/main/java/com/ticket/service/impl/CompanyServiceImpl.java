package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.CompanyDAO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.Criteria;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	
	private CompanyDAO companyDAO;
	public void setCompanyDAO(CompanyDAO companyDAO){
		this.companyDAO=companyDAO;
	}

	@Override
	public List<CompanyVO> getCompanyList() throws SQLException {
		List<CompanyVO> companyList=companyDAO.selectCompanyList();
		return companyList;
	}

	@Override
	public CompanyVO getCompanyById(String com_id) throws SQLException {
		CompanyVO company=companyDAO.selectCompanyById(com_id);
		return company;
	}

	@Override
	public void insertCompany(CompanyVO company) throws SQLException {
		companyDAO.insertCompany(company);
	}

	@Override
	public void updateCompany(CompanyVO company) throws SQLException {
		companyDAO.updateCompany(company);
	}

	@Override
	public void deleteCompany(String com_id) throws SQLException {
		companyDAO.deleteCompany(com_id);
	}

	@Override
	public List<CompanyVO> readCompanyListCriteria(Criteria cri)
			throws SQLException {
		List<CompanyVO> companyList=companyDAO.selectCompanyListCriteria(cri);
		return companyList;
	}

	@Override
	public List<CompanyVO> readSearchMemberList(SearchCriteria cri)
			throws SQLException {
		List<CompanyVO> companyList=companyDAO.selectSearchList(cri);
		return companyList;
	}

	@Override
	public int readSearchCompanyListCount(SearchCriteria cri) throws SQLException {
		int countList = companyDAO.selectSearchListCount(cri);
		return countList;
	}

	@Override
	public int checkId(String com_id) throws SQLException {
		int result=companyDAO.registerCheck(com_id);
		return result;
	}

	@Override
	public CompanyVO login(String com_id) throws SQLException {
		CompanyVO company=companyDAO.selectCompanyById(com_id);
		return company;
	}

	@Override
	public void insertCompanyAuthority(CompanyVO company) throws SQLException {
		companyDAO.insertCompanyAuthority(company);
	}

}
