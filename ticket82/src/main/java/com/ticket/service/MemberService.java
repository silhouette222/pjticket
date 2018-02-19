package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;

public interface MemberService {
	List<MemberVO> getMemberList()throws SQLException;
	MemberVO getMemberById(String mem_id)throws SQLException;
	MemberVO getMemberByEmail(String mem_mail)throws SQLException;
	MemberVO login(String mem_id)throws SQLException;
	
	void insertMember(MemberVO member)throws SQLException;
	void updateMember(MemberVO member)throws SQLException;
	void deleteMember(String mem_id)throws SQLException;
	int checkId(String mem_id)throws SQLException;
	void insertMemberAuthority(MemberVO member)throws SQLException;
	
	List<MemberVO> readMemberListCriteria(Criteria cri)throws SQLException;
	List<MemberVO> readSearchMemberList(SearchCriteria cri)throws SQLException;
	int readSearchMemberListCount(SearchCriteria cri)throws SQLException;
}
