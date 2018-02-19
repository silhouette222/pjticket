package com.ticket.dao;

import java.sql.SQLException;
import java.util.List;

import com.ticket.domain.MusicalVO;
import com.ticket.domain.SearchCriteria;


public interface MusicalDAO {

	void insertMc(MusicalVO mc) throws SQLException;//등록
	void updateMc(MusicalVO mc) throws SQLException;//수정
	void deleteMc(int ttr_no) throws SQLException;//삭제
	List<MusicalVO> selectMcList() throws SQLException;//리스트
	MusicalVO selectMusicalBymcno(int ttr_no) throws SQLException;//글번호

	//검색조건의 동적 쿼리문
	List<MusicalVO> selectSearchList(SearchCriteria cri)throws SQLException;
	int selectSearchListCount(SearchCriteria cri) throws SQLException;
	
	//첨부파일 추가/삭제
	void insertAttach(String file_name,int ttr_no)throws SQLException;
	List<String> selectAttach(int ttr_no)throws SQLException;
	void deleteAttach(int ttr_no)throws SQLException;

	//썸네일추가
	void insertThumb(String thumb_name,int ttr_no) throws SQLException;
	void deleteThumb(int ttr_no) throws SQLException;
	String selectThumb(int ttr_no) throws SQLException;
	
	//구조도추가
	void insertseatmap(String seatmap_name,int ttr_no) throws SQLException;
	void deleteseatmap(int ttr_no) throws SQLException;
	String selectseatmap(int ttr_no) throws SQLException;

	
}
