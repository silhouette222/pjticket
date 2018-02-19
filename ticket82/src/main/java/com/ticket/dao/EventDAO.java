package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.EventVO;

public interface EventDAO {
	
	void insertEvent(EventVO event)throws SQLException;
	void updateEvent(EventVO event)throws SQLException;
	void deleteEvent(int et_no)throws SQLException;
	
	EventVO selectEventByNo(int et_no)throws SQLException;
	List<EventVO> selectEventList()throws SQLException;

}
