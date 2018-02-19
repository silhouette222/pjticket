package com.ticket.service;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.EventVO;

public interface EventService {
	void insertEvent(EventVO event)throws SQLException;
	void updateEvent(EventVO event)throws SQLException;
	void deleteEvent(int et_no)throws SQLException;
	List<EventVO> readEventList()throws SQLException;
	EventVO readEventByNo(int et_no)throws SQLException;
	
}
