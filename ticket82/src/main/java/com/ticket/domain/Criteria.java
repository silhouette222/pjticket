package com.ticket.domain;

public class Criteria {
	
	private int page;//현재 page번호
	private int perPageNum;//page 당 보여주는 board 개수
	
	public Criteria(){
		this.page=1;
		this.perPageNum=10;
	}
	
	public void setPage(int page){
		if(page<=0){
			this.page=1;
		}else{
			this.page=page;
		}
	}
	
	public void setPerPageNum(int perPageNum){
		if(perPageNum<=0||perPageNum>100){
			this.perPageNum=10;
		}else{
			this.perPageNum=perPageNum;
		}
	}
	
	public int getPage(){
		return this.page;
	}
	
	public int getPerPageNum(){
		return this.perPageNum;
	}
	
	public int getPageStart(){ // 현재 페이지에 첫 row 번호
		return (this.page-1)*perPageNum;
	}
	
	@Override
	public String toString(){
		return "Criteria [page="+page+",perPageNum="+perPageNum+
				"]";
	}
}

















