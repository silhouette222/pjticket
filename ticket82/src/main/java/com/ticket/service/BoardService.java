package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.BoardVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.ZzimVO;

public interface BoardService {
	void insertBoard(BoardVO board) throws SQLException;
	void updateBoard(BoardVO board) throws SQLException;
	void deleteBoard(int ttr_no) throws SQLException;
	List<BoardVO> readBoardList() throws SQLException;
	BoardVO readBoardByNo(int ttr_no) throws SQLException;
	List<BoardVO> readBoardListByCat(String ttr_cat) throws SQLException;
	List<BoardVO> readSearchBoardList(SearchCriteria cri) throws SQLException;
	List<String> getFiles(int ttr_no) throws SQLException;
	
	Seatinfo readseatbyseat_id(String seat_id) throws SQLException;
	List<Seatinfo> readseatbyttr_no(int ttr_no) throws SQLException;
	List<Seatinfo> readseat() throws SQLException;
	
	List<BoardVO> searchBoardList(SearchCriteria cri) throws SQLException;
	
	void insertzzim(String mem_id,int ttr_no) throws SQLException;
	void deletezzim(String mem_id,int ttr_no) throws SQLException;
	ZzimVO selectzzin(String mem_id,int ttr_no) throws SQLException;
	
	Double scoreavg(int ttr_no) throws SQLException;
	
	List<BoardVO> readBoardListByComId(String com_id) throws SQLException;
}
