package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.BoardDAO;
import com.ticket.domain.BoardVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.ZzimVO;

public class BoardDAOImpl implements BoardDAO {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="BoardMapper";

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		sqlSession.update(NAMESPACE+".insertBoard",board);
	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		sqlSession.update(NAMESPACE+".updateBoard",board);
	}

	@Override
	public void deleteBoard(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteBoard",ttr_no);
	}

	@Override
	public List<BoardVO> selectBoardList() throws SQLException {
		List<BoardVO> boardList=sqlSession.selectList(NAMESPACE+".selectBoardList",null);
		return boardList;
	}

	@Override
	public List<BoardVO> selectBoardListBycat(String ttr_cat)
			throws SQLException {
		List<BoardVO> boardList=sqlSession.selectList(NAMESPACE+".selectBoardListByCat",ttr_cat);
		return boardList;
	}

	@Override
	public BoardVO selectBoatdByNo(int ttr_no) throws SQLException {
		BoardVO board=(BoardVO)sqlSession.selectOne(NAMESPACE+".selectBoardByNo",ttr_no);
		return board;
	}

	@Override
	public List<BoardVO> selectSearchList(SearchCriteria cri)
			throws SQLException {
		List<BoardVO> boardList=sqlSession.selectList(NAMESPACE+".selectSearchBoardList",cri);
		return boardList;
	}

	@Override
	public int selectmaxttr_no() throws SQLException {
		return (Integer)sqlSession.selectOne(NAMESPACE+".selectmaxttr_no",null);
	}

	@Override
	public void insertThumb(String thumb_name, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("ttr_no",ttr_no);
		paramMap.put("thumb_name",thumb_name);
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

	@Override
	public void insertfile(String file_name, int ttr_no) throws SQLException {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("file_name",file_name);
		paramMap.put("ttr_no",ttr_no);
		sqlSession.update(NAMESPACE+".insertfile",paramMap);
	}

	@Override
	public void deletefile(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deletefile",ttr_no);
	}

	@Override
	public List<String> selectfile(int ttr_no) throws SQLException {
		List<String> file=sqlSession.selectList(NAMESPACE+".selectfile",ttr_no);
		return file;
	}

	@Override
	public void insertseat(Seatinfo seat) throws SQLException {
		System.out.println(seat);
		sqlSession.update(NAMESPACE+".insertseat",seat);
	}

	@Override
	public void deleteseatbyseat_id(String seat_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteseatbyseat_id",seat_id);
	}

	@Override
	public void deleteseatbyttr_no(int ttr_no) throws SQLException {
		sqlSession.update(NAMESPACE+".deleteseatbyttr_no",ttr_no);
	}

	@Override
	public void updateseatbyseat_id(Seatinfo seat) throws SQLException {
		sqlSession.update(NAMESPACE+".updateseatbyseat_id",seat);
	}

	@Override
	public void updateseatbyttr_no(Seatinfo seat) throws SQLException {
		sqlSession.update(NAMESPACE+".updateseatbyttr_no",seat);
	}

	@Override
	public List<Seatinfo> selectseatbyttr_no(int ttr_no) throws SQLException {
		List<Seatinfo> seat=sqlSession.selectList(NAMESPACE+".selectseatbyttr_no",ttr_no);
		return seat;
	}

	@Override
	public Seatinfo selectseatbyseat_id(String seat_id) throws SQLException {
		Seatinfo seat=(Seatinfo) sqlSession.selectOne(NAMESPACE+".selectseatbyseat_id",seat_id);
		return seat;
	}

	@Override
	public List<Seatinfo> selectseat() throws SQLException {
		List<Seatinfo> seat=sqlSession.selectList(NAMESPACE+".selectseat",null);
		return seat;
	}

	@Override
	public List<BoardVO> selectsearchboardlist(SearchCriteria cri)
			throws SQLException {
		List<BoardVO> boardlist=sqlSession.selectList(NAMESPACE+".selectSearchBoardList",cri);
		return boardlist;
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
	public Double scoreavg(int ttr_no) throws SQLException {
		double avg=(double) sqlSession.selectOne(NAMESPACE+".scoreavg",ttr_no);
		return avg;
	}

	@Override
	public List<BoardVO> selectBoardListByComId(String com_id)
			throws SQLException {
		List<BoardVO> boardList=sqlSession.selectList(NAMESPACE+".selectBoardListByComId",com_id);
		return boardList;
	}
}
