package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.ReservationVO;


public interface ReservationDAO {

	void insertRes(ReservationVO res)throws SQLException;
	void deletettrno(int ttr_no)throws SQLException;
	void deleteResid(String res_id)throws SQLException;
	void deleteResnom(String res_nom)throws SQLException;
	
	ReservationVO selectresbyres_id(String res_id) throws SQLException;
	ReservationVO selectresbyres_nom(int res_nom,String seat_id) throws SQLException;
	List<ReservationVO> selectresbyseat_id(String seat_id) throws SQLException;
	List<ReservationVO> selectresbymem_id(String mem_id) throws SQLException;
	List<ReservationVO> selectresbyttr_no(int ttr_no) throws SQLException;
	
	List<ReservationVO> selectResList()throws SQLException;
	String selectThumb(int ttr_no) throws SQLException;
}
