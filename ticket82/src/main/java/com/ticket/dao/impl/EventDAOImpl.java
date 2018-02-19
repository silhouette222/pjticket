package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.EventDAO;
import com.ticket.domain.EventVO;

public class EventDAOImpl implements EventDAO {
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="EventMapper";

	@Override
	public void insertEvent(EventVO event) throws SQLException {
		sqlSession.update(NAMESPACE+".insertEvent",event);
	}

	@Override
	public void updateEvent(EventVO event) throws SQLException {
		sqlSession.update(NAMESPACE+".updateEvent",event);
	}

	@Override
	public void deleteEvent(int et_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteEvent",et_no);
	}

	@Override
	public EventVO selectEventByNo(int et_no) throws SQLException {
		EventVO event=(EventVO)sqlSession.selectOne(NAMESPACE+".selectEventByNo", et_no);
		return event;
	}

	@Override
	public List<EventVO> selectEventList() throws SQLException {
		List<EventVO> eventList=sqlSession.selectList(NAMESPACE+".selectEventList",null);
		return eventList;
	}

}
