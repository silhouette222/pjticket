package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.Criteria;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;

public interface MemberDAO {
	
	MemberVO selectMemberById(String mem_id)throws SQLException;
	MemberVO selectMemberByEmail(String mem_mail)throws SQLException;
	List<MemberVO> selectMemberList()throws SQLException;
	
	void insertMember(MemberVO member)throws SQLException;
	void updateMember(MemberVO member)throws SQLException;
	void deleteMember(String mem_id)throws SQLException;
	int registerCheck(String mem_id)throws SQLException;
	void insertMemberAuthority(MemberVO member)throws SQLException;
	void deleteMemberAuthority(String mem_id)throws SQLException;
	
	List<MemberVO> selectMemberListCriteria(Criteria cri)throws SQLException;
	List<MemberVO> selectSearchList(SearchCriteria cri)throws SQLException;
	
	int selectSearchListCount(SearchCriteria cri)throws SQLException;
	

}
