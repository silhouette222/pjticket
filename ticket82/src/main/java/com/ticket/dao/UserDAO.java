package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.AdminCri;
import com.ticket.domain.AdminVO;
import com.ticket.domain.BoardVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;

public interface UserDAO {
	void insertmem(MemberVO mem) throws SQLException; 
	MemberVO selectmembyid(String mem_id) throws SQLException;
	List<MemberVO> selectmembyname(String mem_name) throws SQLException;
	List<MemberVO> selectmem() throws SQLException;
	void updatemem(MemberVO mem) throws SQLException;
	void deletemem(String mem_id) throws SQLException;
	
	void insertcom(CompanyVO com) throws SQLException; 
	CompanyVO selectcombyid(String com_id) throws SQLException;
	List<CompanyVO> selectcombyname(String com_name) throws SQLException;
	List<CompanyVO> selectcom() throws SQLException;
	void updatecom(CompanyVO com) throws SQLException;
	void deletecom(String com_id) throws SQLException;
	
	AdminVO selectadmin(String am_id) throws SQLException;
	
	List<BoardVO> searchsortboard(AdminCri cri) throws SQLException;
	List<ResVO> searchsortres(AdminCri cri) throws SQLException;
	List<MemberVO> searchsortmem(AdminCri cri) throws SQLException;
	List<CompanyVO> searchsortcom(AdminCri cri) throws SQLException;
	
	int countboard()throws SQLException;
	int countcom()throws SQLException;
	int countmem()throws SQLException;
	int countres()throws SQLException;
	
	BoardVO selectpkboard(int ttr_no) throws SQLException;
	MemberVO selectpkmem(String mem_id) throws SQLException;
	CompanyVO selectpkcom(String com_id) throws SQLException;
	ResVO selectpkres(String res_id) throws SQLException;
	
	void deleteres(String res_id) throws SQLException;
	
	void updateboard(BoardVO board) throws SQLException;
}
