package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ticket.controller.MemberController;
import com.ticket.dao.MemberDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="MemberMapper";

	@Override
	public MemberVO selectMemberById(String mem_id) throws SQLException {
		MemberVO member=(MemberVO)sqlSession.selectOne(NAMESPACE+".selectMemberById",mem_id);
		System.out.println(member);
		return member;
	}

	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		List<MemberVO> memberList=(List<MemberVO>)sqlSession.selectList(NAMESPACE+".selectMemberList",null);
		return memberList;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		sqlSession.update(NAMESPACE+".insertMember",member);
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		sqlSession.update(NAMESPACE+".updateMember",member);
	}

	@Override
	public void deleteMember(String mem_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteMember",mem_id);
	}

	@Override
	public List<MemberVO> selectMemberListCriteria(Criteria cri) throws SQLException {
		
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<MemberVO> memberList = sqlSession.selectList(NAMESPACE+".selectMemberList",null,rowBounds);
		
		return memberList;
	}

	@Override
	public List<MemberVO> selectSearchList(SearchCriteria cri)
			throws SQLException {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<MemberVO> memberList= sqlSession.selectList(NAMESPACE+".selectSearchMemberList",cri,rowBounds);
		return memberList;
	}

	@Override
	public int selectSearchListCount(SearchCriteria cri) throws SQLException {
		int listCount=(Integer)sqlSession.selectOne(NAMESPACE+".selectSearchMemberCount",cri);
		return listCount;
	}

	@Override
	public int registerCheck(String mem_id) throws SQLException {
		int result=(Integer)sqlSession.selectOne(NAMESPACE+".checkId", mem_id);
		return result;
	}

	@Override
	public void insertMemberAuthority(MemberVO member) throws SQLException {
		sqlSession.insert(NAMESPACE+".insertMemberAuthority", member);
	}

	@Override
	public MemberVO selectMemberByEmail(String mem_mail) throws SQLException {
		MemberVO member=(MemberVO)sqlSession.selectOne(NAMESPACE+".selectMemberByEmail",mem_mail);
		return member;
	}

	@Override
	public void deleteMemberAuthority(String mem_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteMemberAuthority",mem_id);
	}
	

}
