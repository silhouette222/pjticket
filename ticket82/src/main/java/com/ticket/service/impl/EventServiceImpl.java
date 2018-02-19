package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.EventDAO;
import com.ticket.domain.EventVO;
import com.ticket.service.EventService;

public class EventServiceImpl implements EventService {
	
	private EventDAO eventDAO;
	public void setEventDAO(EventDAO eventDAO){
		this.eventDAO=eventDAO;
	}

	@Override
	public void insertEvent(EventVO event) throws SQLException {
		System.out.println(event);
		eventDAO.insertEvent(event);
	}

	@Override
	public void updateEvent(EventVO event) throws SQLException {
		eventDAO.updateEvent(event);
	}

	@Override
	public void deleteEvent(int et_no) throws SQLException {
		eventDAO.deleteEvent(et_no);
	}

	@Override
	public List<EventVO> readEventList() throws SQLException {
		List<EventVO> eventList=eventDAO.selectEventList();
		return eventList;
	}

	@Override
	public EventVO readEventByNo(int et_no) throws SQLException {
		EventVO event=eventDAO.selectEventByNo(et_no);
		return event;
	}

}
