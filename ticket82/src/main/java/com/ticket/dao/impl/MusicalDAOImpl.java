package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.MusicalDAO;
import com.ticket.domain.MusicalVO;
import com.ticket.domain.SearchCriteria;


public class MusicalDAOImpl implements MusicalDAO{
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession= sqlSession;
	}
	private static final String NAMESPACE="MusicalMapper";
	
	@Override
	public void insertMc(MusicalVO mc) throws SQLException {
		
		sqlSession.update(NAMESPACE+".insertMusical",mc);
		
	}

	@Override
	public void updateMc(MusicalVO mc) throws SQLException {
		
		sqlSession.update(NAMESPACE+".updateMc",mc);
	}

	@Override
	public void deleteMc(int ttr_no) throws SQLException {
		
		sqlSession.update(NAMESPACE+".deleteMc",ttr_no);
	}

	@Override
	public List<MusicalVO> selectMcList() throws SQLException {

		List<MusicalVO> McList=sqlSession.selectList(NAMESPACE+".selectMcList",null);
		return McList;
	}

	@Override
	public MusicalVO selectMusicalBymcno(int ttr_no) throws SQLException {
		MusicalVO mc = (MusicalVO)sqlSession.selectOne(NAMESPACE+".selectMusicalBymcno",ttr_no);
		return mc;
	}

	@Override
	public List<MusicalVO> selectSearchList(SearchCriteria cri)
			throws SQLException {
		
		List<MusicalVO> McList=sqlSession.selectList(NAMESPACE+".selectSearchMcList",cri);
		return McList;
	}

	@Override
	public int selectSearchListCount(SearchCriteria cri) throws SQLException {
		int listCount = (Integer)sqlSession.selectOne(NAMESPACE+".selectSearchListCount",cri);
		return listCount;
	}

	@Override
	public void insertAttach(String file_name, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("file_name",file_name);
		paramMap.put("ttr_no",ttr_no);
		
		sqlSession.update(NAMESPACE+".insertAttach",paramMap);
		
	}
	
	@Override
	public List<String> selectAttach(int ttr_no) throws SQLException {
		List<String> file=sqlSession.selectList(NAMESPACE+".selectAttach",ttr_no);
		return file;
	}

	@Override
	public void deleteAttach(int ttr_no) throws SQLException {

		sqlSession.update(NAMESPACE+".deleteAttach",ttr_no);
	}

	@Override
	public void insertThumb(String thumb_name, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("thumb_name",thumb_name);
		paramMap.put("ttr_no",ttr_no);
		
		sqlSession.update(NAMESPACE+".insertAttach",paramMap);
	}

	@Override
	public void deleteThumb(int ttr_no) throws SQLException {

		sqlSession.update(NAMESPACE+".deleteThumb",ttr_no);
	}

	@Override
	public String selectThumb(int ttr_no) throws SQLException {
		String thumb=(String) sqlSession.selectOne(NAMESPACE+".selectThumb",ttr_no);
		return thumb;
	}

	@Override
	public void insertseatmap(String seatmap_name, int ttr_no)
			throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("ttr_no",ttr_no);
		paramMap.put("seatmap_name",seatmap_name);
		sqlSession.update(NAMESPACE+".insertseatmap",paramMap);
	}

	@Override
	public void deleteseatmap(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteseatmap",ttr_no);
		
	}

	@Override
	public String selectseatmap(int ttr_no) throws SQLException {
		String seatmap=(String)sqlSession.selectOne(NAMESPACE+".selectseatmap",ttr_no);
		return seatmap;
	}

	

}
