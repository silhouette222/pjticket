package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.MusicalDAO;
import com.ticket.domain.MusicalVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.ZzimVO;


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
	public int selectSearchListCount() throws SQLException {
		int listCount = (Integer)sqlSession.selectOne(NAMESPACE+".selectSearchListCount",null);
		return listCount;
	}

	@Override
	public void insertAttach(String fileName, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("file_name",fileName);
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
		
		sqlSession.update(NAMESPACE+".insertThumb",paramMap);
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
		
		sqlSession.update(NAMESPACE+".insertSeatMap",paramMap);
	}

	@Override
	public void deleteseatmap(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteSeatMap",ttr_no);
		
	}

	@Override
	public String selectseatmap(int ttr_no) throws SQLException {
		String seatmap=(String)sqlSession.selectOne(NAMESPACE+".selectSeatMap",ttr_no);
		return seatmap;
	}

	@Override
	public void insertseat(Seatinfo seat) throws SQLException {
		sqlSession.update(NAMESPACE+".insertSeat",seat);
		
	}

	@Override
	public void deleteseatbyseat_id(String seat_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteSeatBySeat_id",seat_id);		
	}

	@Override
	public void deleteseatbyttr_no(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteSeatByTtr_no",ttr_no);
		
	}

	@Override
	public void updateseatbyseat_id(Seatinfo seat) throws SQLException {
		sqlSession.update(NAMESPACE+".updateSeatBySeat_id",seat);
		
	}

	@Override
	public void updateseatbyttr_no(Seatinfo seat) throws SQLException {
		sqlSession.update(NAMESPACE+".updateSeatByTtr_no",seat);
		
	}

	@Override
	public List<Seatinfo> selectseatbyttr_no(int ttr_no) throws SQLException {
		List<Seatinfo> seat=sqlSession.selectList(NAMESPACE+".selectSeatByTtr_no",ttr_no);
		return seat;
	}

	@Override
	public Seatinfo selectseatbyseat_id(String seat_id) throws SQLException {
		Seatinfo seat=(Seatinfo) sqlSession.selectOne(NAMESPACE+".selectSeatBySeat_id",seat_id);
		return seat;
	}

	@Override
	public List<Seatinfo> selectseat() throws SQLException {
		List<Seatinfo> seat=sqlSession.selectList(NAMESPACE+".selectSeat",null);
		return seat;
	}

	@Override
	public Double scoreavg(int ttr_no) throws SQLException {
		double avg=(double) sqlSession.selectOne(NAMESPACE+".scoreavg",ttr_no);
		return avg;
	}

	@Override
	public void insertzzim(String mem_id, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mem_id",mem_id);
		paramMap.put("ttr_no",ttr_no);
		sqlSession.update(NAMESPACE+".insertzzim",paramMap);
	}

	@Override
	public void deletezzim(String mem_id, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mem_id",mem_id);
		paramMap.put("ttr_no",ttr_no);
		sqlSession.update(NAMESPACE+".deletezzim",paramMap);	
	}

	@Override
	public ZzimVO selectzzin(String mem_id, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mem_id",mem_id);
		paramMap.put("ttr_no",ttr_no);
		ZzimVO zzim=(ZzimVO) sqlSession.selectOne(NAMESPACE+".selectzzim",paramMap);
		return zzim;
	}

	@Override
	public List<MusicalVO> selectsearchmusicallist(SearchCriteria cri)
			throws SQLException {
		List<MusicalVO> mclist=sqlSession.selectList(NAMESPACE+".selectSearchMusicalList",cri);
		return mclist;
	}

	

}
