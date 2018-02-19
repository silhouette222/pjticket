package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.ZzimVO;

public interface ZzimDAO {
	
	public List<ZzimVO> listZzim(String mem_id)throws SQLException;
	public void insertZzim(ZzimVO zzim)throws SQLException;
	public void deleteZzim(int zzim_no)throws SQLException;
	public int countZzim(int ttr_no, String mem_id)throws SQLException;
	
	
}
