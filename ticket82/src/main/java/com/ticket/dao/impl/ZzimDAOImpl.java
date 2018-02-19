package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.ZzimDAO;
import com.ticket.domain.ZzimVO;

public class ZzimDAOImpl implements ZzimDAO {

	private SqlSession session;
	public void setSqlSession(SqlSession session){
		this.session=session;
	}
	
	private static final String NAMESPACE="ZzimMapper";
	
	@Override
	public List<ZzimVO> listZzim(String mem_id) throws SQLException {
		return session.selectList(NAMESPACE+".listZzim", mem_id);
	}

	@Override
	public void insertZzim(ZzimVO zzim) throws SQLException {
		session.insert(NAMESPACE+".insertZzim", zzim);
	}

	@Override
	public void deleteZzim(int zzim_no) throws SQLException {
		session.delete(NAMESPACE+".deleteZzim", zzim_no);
	}

	@Override
	public int countZzim(int ttr_no, String mem_id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ttr_no", ttr_no);
		map.put("mem_id", mem_id);
		return (Integer)session.selectOne(NAMESPACE+".countZzim", map);
	}

}
