package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.CompanyDAO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.Criteria;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;

public class CompanyDAOImpl implements CompanyDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="CompanyMapper";

	@Override
	public CompanyVO selectCompanyById(String com_id) throws SQLException {
		CompanyVO company =(CompanyVO)sqlSession.selectOne(NAMESPACE+".selectCompanyById",com_id);
		System.out.println(company);
		return company;
	}

	@Override
	public List<CompanyVO> selectCompanyList() throws SQLException {
		List<CompanyVO> companyList = (List<CompanyVO>)sqlSession.selectList(NAMESPACE+".selectCompanyList", null);
		return companyList;
	}

	@Override
	public void insertCompany(CompanyVO company) throws SQLException {
		sqlSession.update(NAMESPACE+".insertCompany", company);
	}

	@Override
	public void updateCompany(CompanyVO company) throws SQLException {
		sqlSession.update(NAMESPACE+".updateCompany", company);
	}

	@Override
	public void deleteCompany(String com_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteCompany", com_id);
	}

	@Override
	public List<CompanyVO> selectCompanyListCriteria(Criteria cri)
			throws SQLException {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<CompanyVO> companyList = sqlSession.selectList(NAMESPACE+".selectCompanyList",null,rowBounds);
		
		return companyList;
	}

	@Override
	public List<CompanyVO> selectSearchList(SearchCriteria cri)
			throws SQLException {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<CompanyVO> companyList = sqlSession.selectList(NAMESPACE+".selectSearchCompanyList",null,rowBounds);
		
		return companyList;
	}

	@Override
	public int selectSearchListCount(SearchCriteria cri) throws SQLException {
		int listCount = (Integer)sqlSession.selectOne(NAMESPACE+".selectSearchCompanyCount",cri);
		return listCount;
	}

	@Override
	public int registerCheck(String com_id) throws SQLException {
		int result=(Integer)sqlSession.selectOne(NAMESPACE+".checkId", com_id);
		return result;
	}

	@Override
	public void insertCompanyAuthority(CompanyVO company) throws SQLException {
		sqlSession.insert(NAMESPACE+".insertCompanyAuthority", company);
	}

}
