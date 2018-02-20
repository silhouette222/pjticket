package com.ticket.service;
import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.MusicalVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.ZzimVO;

public interface MusicalService {

	void createMc(MusicalVO mc) throws SQLException;
	void updateMc(MusicalVO mc) throws SQLException;
	void deleteMc(int ttr_no) throws SQLException;
	
	List<MusicalVO> readMcList() throws SQLException;
	MusicalVO readMusicalBymcno(int ttr_no)throws SQLException;	
	
	List<MusicalVO> readSearchMusicalList(SearchCriteria cri)throws SQLException;
	int readSearchMcListCount(SearchCriteria cri)throws SQLException;
	
	List<String> getAttach(int ttr_no)throws SQLException;
	
	Seatinfo readseatbyseat_id(String seat_id) throws SQLException;
	List<Seatinfo> readseatbyttr_no(int ttr_no) throws SQLException;
	List<Seatinfo> readseat() throws SQLException;
	
	List<MusicalVO> searchMusicalList(SearchCriteria cri) throws SQLException;
	
	void insertzzim(String mem_id,int ttr_no) throws SQLException;
	void deletezzim(String mem_id,int ttr_no) throws SQLException;
	ZzimVO selectzzin(String mem_id,int ttr_no) throws SQLException;
	
	Double scoreavg(int ttr_no) throws SQLException;
}
