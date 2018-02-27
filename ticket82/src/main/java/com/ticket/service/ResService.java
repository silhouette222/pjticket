package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;

public interface ResService {
	int countresbyseat_id(String seat_id) throws SQLException;

	ResVO selectresbyres_id(String res_id) throws SQLException;
	ResVO selectresbyres_nom(int res_nom,String seat_id) throws SQLException;
	List<ResVO> selectresbyseat_id(String seat_id) throws SQLException;
	List<ResVO> selectresbymem_id(String mem_id) throws SQLException;
	List<ResVO> selectresbyttr_no(int ttr_no) throws SQLException;
	
	void insertres(ResVO res) throws SQLException;
	void updateres(ResVO res) throws SQLException;
	void deleteresbyres_id(String res_id) throws SQLException;
	void deleteresbyres_nom(String res_nom) throws SQLException;
	void deleteresbyttr_no(int ttr_no) throws SQLException;
}
