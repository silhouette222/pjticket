package com.ticket.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.dao.MusicalDAO;
import com.ticket.domain.MusicalVO;
import com.ticket.domain.SearchCriteria;
import com.ticket.domain.Seatinfo;
import com.ticket.domain.ZzimVO;
import com.ticket.service.MusicalService;

public class MusicalServiceImpl implements MusicalService{

	private MusicalDAO musicalDAO;
	public void setMusicalDAO(MusicalDAO musicalDAO){
		this.musicalDAO=musicalDAO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})	
	@Override
	public void createMc(MusicalVO mc) throws SQLException {
		musicalDAO.insertMc(mc);
		
		int ttr_no = musicalDAO.selectSearchListCount();
		
		String[] files = mc.getFiles();

		Seatinfo[] seats=new Seatinfo[10];

		String[] seat_grds = mc.getSeat_grd();
		int[] seat_nos = mc.getSeat_no();
		int[] seat_pris = mc.getSeat_pri();
		Date[] seat_dates = mc.getSeat_date();
		Date[] seat_times = mc.getSeat_time();
		
		if(seat_grds!=null){
			System.out.println(seat_grds[0]);
		for (int i = 0; i < seat_grds.length; i++) {
			seats[i]=new Seatinfo("",ttr_no,seat_grds[i],seat_nos[i],seat_pris[i],seat_dates[i],seat_times[i]);
			}
		}
		String thumb = mc.getThumb_name();
		String seatmap = mc.getThumb_name();
		musicalDAO.insertThumb(thumb, ttr_no);
		musicalDAO.insertseatmap(seatmap, ttr_no);
		if (files == null)
			return;
		for (String file_name : files) {
			musicalDAO.insertAttach(file_name, ttr_no);
		}
		if (seat_grds != null)
		for (int i = 0; i < seat_grds.length; i++) {
			musicalDAO.insertseat(seats[i]);
		}

	}
	
	@Transactional(propagation=Propagation.REQUIRED,
			   rollbackFor={Exception.class})
	@Override
	public void updateMc(MusicalVO mc) throws SQLException {

		musicalDAO.updateMc(mc);
		
		int ttr_no=mc.getTtr_no();
		musicalDAO.deleteAttach(ttr_no);
		musicalDAO.deleteThumb(ttr_no);
		musicalDAO.deleteseatmap(ttr_no);
		List<Seatinfo> seat_bef=musicalDAO.selectseatbyttr_no(ttr_no);
		
		String[] files=mc.getFiles();

		Seatinfo[] seats =new Seatinfo[10];
		
		String[] seat_grds = mc.getSeat_grd();
		int[] seat_nos = mc.getSeat_no();
		int[] seat_pris = mc.getSeat_pri();
		Date[] seat_dates = mc.getSeat_date();
		Date[] seat_times = mc.getSeat_time();
		
		if(seat_grds!=null){
		for (int i = 0; i < seat_grds.length; i++) {
			seats[i]=new Seatinfo("",ttr_no,seat_grds[i],seat_nos[i],seat_pris[i],seat_dates[i],seat_times[i]);
		}
		}
		
		musicalDAO.insertThumb(mc.getThumb_name(), ttr_no);
		musicalDAO.insertseatmap(mc.getThumb_name(), ttr_no);
		if (files == null)
			return;
		for (String file_name : files) {
			musicalDAO.insertAttach(file_name, ttr_no);
		}
		
		
	}
	@Transactional(propagation=Propagation.REQUIRED,
		       rollbackFor={Exception.class})
	@Override
	public void deleteMc(int ttr_no) throws SQLException {
		
		musicalDAO.deleteAttach(ttr_no);
		musicalDAO.deleteThumb(ttr_no);
		musicalDAO.deleteseatmap(ttr_no);
		musicalDAO.deleteseatbyttr_no(ttr_no);
		musicalDAO.deleteMc(ttr_no);
/*		musicalDAO.deleteAttach(ttr_no);*/
	}
	@Transactional(isolation=Isolation.READ_COMMITTED,readOnly=true)
	@Override
	public List<MusicalVO> readMcList() throws SQLException {
		List<MusicalVO> mcList=musicalDAO.selectMcList();
		
		for (int i = 0; i < mcList.size(); i++) {
			MusicalVO bef = mcList.get(i);
			String thumb = musicalDAO.selectThumb(bef.getTtr_no());
			bef.setThumb_name(thumb);
			mcList.set(i, bef);
		};
		return mcList;
	}
	@Override
	public MusicalVO readMusicalBymcno(int ttr_no) throws SQLException {
		MusicalVO mc=musicalDAO.selectMusicalBymcno(ttr_no);
		
		List<Seatinfo> seats=musicalDAO.selectseatbyttr_no(ttr_no);
		
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
		
		mc.setThumb_name(musicalDAO.selectThumb(ttr_no));
		mc.setSeatmap_name(musicalDAO.selectseatmap(ttr_no));
		mc.setSeat_id(seat_ids);
		mc.setSeat_grd(seat_grds);
		mc.setSeat_no(seat_nos);
		mc.setSeat_pri(seat_pris);
		mc.setSeat_date(seat_dates);
		mc.setSeat_time(seat_times);
		
		return mc;
	}
	@Override
	public List<MusicalVO> readSearchMusicalList(SearchCriteria cri)
			throws SQLException {
		List<MusicalVO> mcList=musicalDAO.selectSearchList(cri);
		return mcList;
	}
	@Override
	public int readSearchMcListCount(SearchCriteria cri) throws SQLException {
		int countList=musicalDAO.selectSearchListCount();
		return countList;
	}
	@Override
	public List<String> getAttach(int ttr_no) throws SQLException {
		List<String> file_names=musicalDAO.selectAttach(ttr_no);
		return file_names;
	}

	@Override
	public Seatinfo readseatbyseat_id(String seat_id) throws SQLException {
		Seatinfo seat = musicalDAO.selectseatbyseat_id(seat_id);
		return seat;
	}

	@Override
	public List<Seatinfo> readseatbyttr_no(int ttr_no) throws SQLException {
		List<Seatinfo> seat = musicalDAO.selectseatbyttr_no(ttr_no);
		return seat;
	}

	@Override
	public List<Seatinfo> readseat() throws SQLException {
		List<Seatinfo> seat = musicalDAO.selectseat();
		return seat;
	}

	@Override
	public Double scoreavg(int ttr_no) throws SQLException {
		double avg=musicalDAO.scoreavg(ttr_no);
		return avg;
	}

	@Override
	public void insertzzim(String mem_id, int ttr_no) throws SQLException {
		musicalDAO.insertzzim(mem_id, ttr_no);		
	}

	@Override
	public void deletezzim(String mem_id, int ttr_no) throws SQLException {
		musicalDAO.deletezzim(mem_id, ttr_no);		
	}

	@Override
	public ZzimVO selectzzin(String mem_id, int ttr_no) throws SQLException {
		ZzimVO zzim=musicalDAO.selectzzin(mem_id, ttr_no);
		return zzim;
	}

	@Override
	public List<MusicalVO> searchMusicalList(SearchCriteria cri)
			throws SQLException {
		List<MusicalVO> mcList=musicalDAO.selectsearchmusicallist(cri);
		for (int i = 0; i < mcList.size(); i++) {
			MusicalVO bef = mcList.get(i);
			String thumb = musicalDAO.selectThumb(bef.getTtr_no());
			bef.setThumb_name(thumb);
			mcList.set(i, bef);
		}
		;
		return mcList;
	}

	@Override
	public List<MusicalVO> readMcListbyComId(String com_id) throws SQLException {
		List<MusicalVO> mcList=musicalDAO.selectMusicalList(com_id);
		return mcList;
	}
	
	
	
	
}
