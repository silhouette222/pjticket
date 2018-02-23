package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.MemberDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.service.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO){
		this.memberDAO=memberDAO;
	}
	
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> memberList=memberDAO.selectMemberList();
		return memberList;
	}

	@Override
	public MemberVO getMemberById(String mem_id) throws SQLException {
		MemberVO member=memberDAO.selectMemberById(mem_id);
		return member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		memberDAO.insertMember(member);
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		memberDAO.updateMember(member);
	}

	@Override
	public void deleteMember(String mem_id) throws SQLException {
		memberDAO.deleteMember(mem_id);
	}

	@Override
	public List<MemberVO> readMemberListCriteria(Criteria cri)
			throws SQLException {
		List<MemberVO> memberList=memberDAO.selectMemberListCriteria(cri);
		return memberList;
	}

	@Override
	public List<MemberVO> readSearchMemberList(SearchCriteria cri)
			throws SQLException {
		List<MemberVO> memberList=memberDAO.selectSearchList(cri);
		return memberList;
	}

	@Override
	public int readSearchMemberListCount(SearchCriteria cri)
			throws SQLException {
		int countList=memberDAO.selectSearchListCount(cri);
		return countList;
	}

	@Override
	public int checkId(String mem_id) throws SQLException {
		int result = memberDAO.registerCheck(mem_id);
		return result;
	}

	@Override
	public MemberVO login(String mem_id) throws SQLException {
		MemberVO member=memberDAO.selectMemberById(mem_id);
		return member;
	}

	@Override
	public void insertMemberAuthority(MemberVO member) throws SQLException {
		memberDAO.insertMemberAuthority(member);
	}

	@Override
	public MemberVO getMemberByEmail(String mem_mail) throws SQLException {
		MemberVO member=memberDAO.selectMemberByEmail(mem_mail);
		if(member == null){
			throw new SQLException();
		}
			
		return member;
	}

	@Override
	public void deleteMemberAuthority(String mem_id) throws SQLException {
		memberDAO.deleteMemberAuthority(mem_id);
	}



}
