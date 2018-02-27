package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.dao.BoardDAO;
import com.ticket.domain.BoardVO;
import com.ticket.domain.MemberVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.ZzimVO;
import com.ticket.service.BoardService;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		System.out.println(board);
		boardDAO.insertBoard(board);
		
		int ttr_no = boardDAO.selectmaxttr_no();
		
		String[] files = board.getFiles();

		Seatinfo[] seats=new Seatinfo[10];

		String[] seat_grds = board.getSeat_grd();
		int[] seat_nos = board.getSeat_no();
		int[] seat_pris = board.getSeat_pri();
		Date[] seat_dates = board.getSeat_date();
		Date[] seat_times = board.getSeat_time();
		
		
		if(seat_grds!=null){
			System.out.println(seat_grds[0]);
		for (int i = 0; i < seat_grds.length; i++) {
			seats[i]=new Seatinfo("",ttr_no,seat_grds[i],seat_nos[i],seat_pris[i],seat_dates[i],seat_times[i]);
		}
		}
		String thumb = board.getThumb_name();
		String seatmap = board.getThumb_name();
		boardDAO.insertThumb(thumb, ttr_no);
		boardDAO.insertseatmap(seatmap, ttr_no);
		if (files == null)
			return;
		for (String file_name : files) {
			boardDAO.insertfile(file_name, ttr_no);
		}
		if (seat_grds != null)
		for (int i = 0; i < seat_grds.length; i++) {
			boardDAO.insertseat(seats[i]);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);

		int ttr_no = board.getTtr_no();
		boardDAO.deletefile(ttr_no);
		boardDAO.deleteThumb(ttr_no);
		boardDAO.deleteseatmap(ttr_no);
		List<Seatinfo> seat_bef=boardDAO.selectseatbyttr_no(ttr_no);
		
		String[] files = board.getFiles();

		Seatinfo[] seats =new Seatinfo[10];
		
		String[] seat_ids=board.getSeat_id();
		String[] seat_grds = board.getSeat_grd();
		int[] seat_nos = board.getSeat_no();
		int[] seat_pris = board.getSeat_pri();
		Date[] seat_dates = board.getSeat_date();
		Date[] seat_times = board.getSeat_time();
		
		if(seat_grds!=null){
		for (int i = 0; i < seat_grds.length; i++) {
			seats[i]=new Seatinfo(seat_ids[i],ttr_no,seat_grds[i],seat_nos[i],seat_pris[i],seat_dates[i],seat_times[i]);
		}
		}
		
		boardDAO.insertThumb(board.getThumb_name(), ttr_no);
		boardDAO.insertseatmap(board.getThumb_name(), ttr_no);
		if (files == null)
			return;
		for (String file_name : files) {
			boardDAO.insertfile(file_name, ttr_no);
		}
		if (seat_grds != null)
		for (int i = 0; i < seat_grds.length; i++) {
			if(seats[i].getSeat_id()!=null){
			boardDAO.updateseatbyttr_no(seats[i]);
			}else if(seats[i].getSeat_id()==null){
				boardDAO.insertseat(seats[i]);
			}
		}
		String ids="";
		for(String id:seat_ids){
			ids+=id+",";
		}
		for(Seatinfo seat:seat_bef){
			if(!ids.contains(seat.getSeat_id())){
				boardDAO.deleteseatbyseat_id(seat.getSeat_id());
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	@Override
	public void deleteBoard(int ttr_no) throws SQLException {
		boardDAO.deletefile(ttr_no);
		boardDAO.deleteThumb(ttr_no);
		boardDAO.deleteseatmap(ttr_no);
		boardDAO.deleteseatbyttr_no(ttr_no);
		boardDAO.deleteBoard(ttr_no);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	@Override
	public List<BoardVO> readBoardList() throws SQLException {
		List<BoardVO> boardList = boardDAO.selectBoardList();
		for (int i = 0; i < boardList.size(); i++) {
			BoardVO bef = boardList.get(i);
			String thumb = boardDAO.selectThumb(bef.getTtr_no());
			bef.setThumb_name(thumb);
			boardList.set(i, bef);
		}
		;
		return boardList;
	}

	@Override
	public BoardVO readBoardByNo(int ttr_no) throws SQLException {
		BoardVO board = boardDAO.selectBoatdByNo(ttr_no);
		
		List<Seatinfo> seats=boardDAO.selectseatbyttr_no(ttr_no);
		
		String[] seat_ids=new String[seats.size()];
		String[] seat_grds = new String[seats.size()];
		int[] seat_nos = new int[seats.size()];
		int[] seat_pris = new int[seats.size()];
		Date[] seat_dates = new Date[seats.size()];
		Date[] seat_times = new Date[seats.size()];
		
		if(seat_grds!=null){
		for(int i=0;i<seats.size();i++){
			seat_ids[i]=seats.get(i).getSeat_id();
			seat_grds[i]=seats.get(i).getSeat_grd();
			seat_nos[i]=seats.get(i).getSeat_no();
			seat_pris[i]=seats.get(i).getSeat_pri();
			seat_dates[i]=seats.get(i).getSeat_date();
			seat_times[i]=seats.get(i).getSeat_time();
		}
		}
		
		board.setThumb_name(boardDAO.selectThumb(ttr_no));
		board.setSeatmap_name(boardDAO.selectseatmap(ttr_no));
		board.setSeat_id(seat_ids);
		board.setSeat_grd(seat_grds);
		board.setSeat_no(seat_nos);
		board.setSeat_pri(seat_pris);
		board.setSeat_date(seat_dates);
		board.setSeat_time(seat_times);
		
		return board;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	@Override
	public List<BoardVO> readBoardListByCat(String ttr_cat) throws SQLException {
		List<BoardVO> boardList = boardDAO.selectBoardListBycat(ttr_cat);
		for (int i = 0; i < boardList.size(); i++) {
			BoardVO bef = boardList.get(i);
			String thumb = boardDAO.selectThumb(bef.getTtr_no());
			bef.setThumb_name(thumb);
			boardList.set(i, bef);
		}
		;
		return boardList;
	}

	@Override
	public List<BoardVO> readSearchBoardList(SearchCriteria cri)
			throws SQLException {
		List<BoardVO> boardList = boardDAO.selectSearchList(cri);
		return boardList;
	}

	@Override
	public List<String> getFiles(int ttr_no) throws SQLException {
		List<String> files = boardDAO.selectfile(ttr_no);
		return files;
	}
	@Override
	public Seatinfo readseatbyseat_id(String seat_id) throws SQLException {
		Seatinfo seat = boardDAO.selectseatbyseat_id(seat_id);
		return seat;
	}

	@Override
	public List<Seatinfo> readseatbyttr_no(int ttr_no) throws SQLException {
		List<Seatinfo> seat = boardDAO.selectseatbyttr_no(ttr_no);
		return seat;
	}

	@Override
	public List<Seatinfo> readseat() throws SQLException {
		List<Seatinfo> seat = boardDAO.selectseat();
		return seat;
	}

	@Override
	public List<BoardVO> searchBoardList(SearchCriteria cri)
			throws SQLException {
		List<BoardVO> boardList=boardDAO.selectsearchboardlist(cri);
		for (int i = 0; i < boardList.size(); i++) {
			BoardVO bef = boardList.get(i);
			String thumb = boardDAO.selectThumb(bef.getTtr_no());
			bef.setThumb_name(thumb);
			boardList.set(i, bef);
		}
		;
		return boardList;
	}

	@Override
	public void insertzzim(String mem_id, int ttr_no) throws SQLException {
		boardDAO.insertzzim(mem_id, ttr_no);
	}

	@Override
	public void deletezzim(String mem_id, int ttr_no) throws SQLException {
		boardDAO.deletezzim(mem_id, ttr_no);
	}

	@Override
	public ZzimVO selectzzin(String mem_id, int ttr_no) throws SQLException {
		ZzimVO zzim=boardDAO.selectzzin(mem_id, ttr_no);
		return zzim;
	}

	@Override
	public Double scoreavg(int ttr_no) throws SQLException {
		double avg=boardDAO.scoreavg(ttr_no);
		return avg;
	}

	@Override
	public List<BoardVO> readBoardListByComId(String com_id)
			throws SQLException {
		List<BoardVO> boardList = boardDAO.selectBoardListByComId(com_id);
		for (int i = 0; i < boardList.size(); i++) {
			BoardVO bef = boardList.get(i);
			String thumb = boardDAO.selectThumb(bef.getTtr_no());
			bef.setThumb_name(thumb);
			boardList.set(i, bef);
		}
		;
		return boardList;
	}
}
