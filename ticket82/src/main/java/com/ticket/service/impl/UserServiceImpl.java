package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.ticket.dao.UserDAO;
import com.ticket.domain.AdminCri;
import com.ticket.domain.AdminVO;
import com.ticket.domain.BoardVO;
import com.ticket.domain.CompanyVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.ResVO;
import com.ticket.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public void insertmem(MemberVO mem) throws SQLException {
		userDAO.insertmem(mem);
	}

	@Override
	public MemberVO selectmembyid(String mem_id) throws SQLException {
		MemberVO mem=userDAO.selectmembyid(mem_id);
		return mem;
	}

	@Override
	public List<MemberVO> selectmembyname(String mem_name) throws SQLException {
		List<MemberVO> memlist=userDAO.selectmembyname(mem_name);
		return memlist;
	}

	@Override
	public List<MemberVO> selectmem() throws SQLException {
		List<MemberVO> memlist=userDAO.selectmem();
		return memlist;
	}

	@Override
	public void updatemem(MemberVO mem) throws SQLException {
		userDAO.updatemem(mem);
	}

	@Override
	public void deletemem(String mem_id) throws SQLException {
		userDAO.deletemem(mem_id);
	}

	@Override
	public void insertcom(CompanyVO com) throws SQLException {
		userDAO.insertcom(com);
	}

	@Override
	public CompanyVO selectcombyid(String com_id) throws SQLException {
		CompanyVO com=userDAO.selectcombyid(com_id);
		return com;
	}

	@Override
	public List<CompanyVO> selectcombyname(String com_name) throws SQLException {
		List<CompanyVO> comlist=userDAO.selectcombyname(com_name);
		return comlist;
	}

	@Override
	public List<CompanyVO> selectcom() throws SQLException {
		List<CompanyVO> comlist=userDAO.selectcom();
		return comlist;
	}

	@Override
	public void updatecom(CompanyVO com) throws SQLException {
		userDAO.updatecom(com);
	}

	@Override
	public void deletecom(String com_id) throws SQLException {
		userDAO.deletecom(com_id);
	}

	@Override
	public AdminVO selectadmin(String am_id) throws SQLException {
		AdminVO admin=userDAO.selectadmin(am_id);
		return admin;
	}

	@Override
	public List<BoardVO> searchsortboard(AdminCri cri) throws SQLException {
		List<BoardVO> list=userDAO.searchsortboard(cri);
		return list;
	}

	@Override
	public List<ResVO> searchsortres(AdminCri cri) throws SQLException {
		List<ResVO> list=userDAO.searchsortres(cri);
		return list;
	}

	@Override
	public List<MemberVO> searchsortmem(AdminCri cri) throws SQLException {
		List<MemberVO> list=userDAO.searchsortmem(cri);
		return list;
	}

	@Override
	public List<CompanyVO> searchsortcom(AdminCri cri) throws SQLException {
		List<CompanyVO> list=userDAO.searchsortcom(cri);
		return list;
	}

	@Override
	public int countboard() throws SQLException {
		return userDAO.countboard();
	}

	@Override
	public int countcom() throws SQLException {
		return userDAO.countcom();
	}

	@Override
	public int countmem() throws SQLException {
		return userDAO.countmem();
	}

	@Override
	public int countres() throws SQLException {
		return userDAO.countres();
	}

	@Override
	public BoardVO selectpkboard(int ttr_no) throws SQLException {
		return userDAO.selectpkboard(ttr_no);
	}

	@Override
	public MemberVO selectpkmem(String mem_id) throws SQLException {
		return userDAO.selectpkmem(mem_id);
	}

	@Override
	public CompanyVO selectpkcom(String com_id) throws SQLException {
		return userDAO.selectpkcom(com_id);
	}

	@Override
	public ResVO selectpkres(String res_id) throws SQLException {
		return userDAO.selectpkres(res_id);
	}

	@Override
	public void updateboard(BoardVO board) throws SQLException {
		userDAO.updateboard(board);
	}
}
