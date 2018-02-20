package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ticket.dao.UserDAO;
import com.ticket.domain.AdminCri;
import com.ticket.domain.AdminVO;
import com.ticket.domain.BoardVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;

public class UserDAOImpl implements UserDAO {

private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="UserMapper";
	
	@Override
	public void insertmem(MemberVO mem) throws SQLException {
		sqlSession.update(NAMESPACE+".insertmem",mem);
	}

	@Override
	public MemberVO selectmembyid(String mem_id) throws SQLException {
		MemberVO mem=(MemberVO) sqlSession.selectOne(NAMESPACE+".selectmembyid",mem_id);
		return mem;
	}

	@Override
	public List<MemberVO> selectmembyname(String mem_name) throws SQLException {
		List<MemberVO> memlist=sqlSession.selectList(NAMESPACE+".selectmembyname",mem_name);
		return memlist;
	}

	@Override
	public List<MemberVO> selectmem() throws SQLException {
		List<MemberVO> memlist=sqlSession.selectList(NAMESPACE+".selectmem");
		return memlist;
	}

	@Override
	public void updatemem(MemberVO mem) throws SQLException {
		sqlSession.update(NAMESPACE+".updatemem",mem);
	}

	@Override
	public void deletemem(String mem_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deletemen",mem_id);
	}

	@Override
	public void insertcom(CompanyVO com) throws SQLException {
		sqlSession.update(NAMESPACE+".insertcom",com);
	}

	@Override
	public CompanyVO selectcombyid(String com_id) throws SQLException {
		CompanyVO mem=(CompanyVO)sqlSession.selectOne(NAMESPACE+".selectcombyid",com_id);
		return mem;
	}

	@Override
	public List<CompanyVO> selectcombyname(String com_name) throws SQLException {
		List<CompanyVO> comlist=sqlSession.selectList(NAMESPACE+".selectcombyname",com_name);
		return comlist;
	}

	@Override
	public List<CompanyVO> selectcom() throws SQLException {
		List<CompanyVO> comlist=sqlSession.selectList(NAMESPACE+".selectcom");
		return comlist;
	}

	@Override
	public void updatecom(CompanyVO com) throws SQLException {
		sqlSession.update(NAMESPACE+".updatecom",com);
	}

	@Override
	public void deletecom(String com_id) throws SQLException {
		sqlSession.update(NAMESPACE+".deletecom",com_id);
	}

	@Override
	public AdminVO selectadmin(String am_id) throws SQLException {
		AdminVO admin=(AdminVO)sqlSession.selectOne(NAMESPACE+".selectadmin",am_id);
		return admin;
	}

	@Override
	public List<BoardVO> searchsortboard(AdminCri cri) throws SQLException {
		List<BoardVO> list=sqlSession.selectList(NAMESPACE+".searchsortboard",cri);
		return list;
	}

	@Override
	public List<ResVO> searchsortres(AdminCri cri) throws SQLException {
		List<ResVO> list=sqlSession.selectList(NAMESPACE+".searchsortres",cri);
		return list;
	}

	@Override
	public List<MemberVO> searchsortmem(AdminCri cri) throws SQLException {
		List<MemberVO> list=sqlSession.selectList(NAMESPACE+".searchsortmem",cri);
		return list;
	}

	@Override
	public List<CompanyVO> searchsortcom(AdminCri cri) throws SQLException {
		List<CompanyVO> list=sqlSession.selectList(NAMESPACE+".searchsortcom",cri);
		return list;
	}

	@Override
	public int countboard() throws SQLException {
		int count=(Integer)sqlSession.selectOne(NAMESPACE+".countboard");
		return count;
	}

	@Override
	public int countcom() throws SQLException {
		int count=(Integer)sqlSession.selectOne(NAMESPACE+".countcom");
		return count;
	}

	@Override
	public int countmem() throws SQLException {
		int count=(Integer)sqlSession.selectOne(NAMESPACE+".countmem");
		return count;
	}

	@Override
	public int countres() throws SQLException {
		int count=(Integer)sqlSession.selectOne(NAMESPACE+".countres");
		return count;
	}

	@Override
	public BoardVO selectpkboard(int ttr_no) throws SQLException {
		return (BoardVO)sqlSession.selectOne(NAMESPACE+".selectpkboard",ttr_no);
	}

	@Override
	public MemberVO selectpkmem(String mem_id) throws SQLException {
		return (MemberVO)sqlSession.selectOne(NAMESPACE+".selectpkmem",mem_id);
	}

	@Override
	public CompanyVO selectpkcom(String com_id) throws SQLException {
		return (CompanyVO)sqlSession.selectOne(NAMESPACE+".selectpkcom",com_id);
	}

	@Override
	public ResVO selectpkres(String res_id) throws SQLException {
		return (ResVO)sqlSession.selectOne(NAMESPACE+".selectpkres",res_id);
	}

	@Override
	public void deleteres(String res_id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateboard(BoardVO board) throws SQLException {
		sqlSession.update(NAMESPACE+".updateBoard",board);
	}

}
