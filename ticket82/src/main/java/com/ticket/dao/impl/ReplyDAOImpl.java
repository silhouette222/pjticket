package com.ticket.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ticket.dao.ReplyDAO;
import com.ticket.domain.Criteria;
import com.ticket.domain.ReplyVO;

public class ReplyDAOImpl implements ReplyDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private String namespace;
	public void setNamespace(String namespace){
		this.namespace=namespace;
	}
	
	@Override
	public List<ReplyVO> selectReplyList(int bno) throws SQLException {
		List<ReplyVO> replyList=
				sqlSession.selectList(namespace+".selectReplyList",bno);
		
		return replyList;
	}

	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		sqlSession.update(namespace+".insertReply",reply);
		System.out.println("insert DB");
	}

	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		sqlSession.update(namespace+".updateReply",reply);

	}

	@Override
	public void deleteReply(int rno) throws SQLException {
		sqlSession.update(namespace+".deleteReply",rno);

	}

	
	@Override
	public List<ReplyVO> selectReplyListPage(int bno, Criteria cri)
			throws SQLException {
		int offset=cri.getPageStart();
		int limit=cri.getPerPageNum();
		RowBounds rowbounds=new RowBounds(offset,limit);
		
		List<ReplyVO> replyList=
				sqlSession.selectList(namespace+".selectReplyListPage",
						bno,rowbounds);
		return replyList;
	}

	
	@Override
	public int countReply(int bno) throws SQLException {
		int count=(Integer)sqlSession.selectOne(namespace+".countReply",bno);
		return count;
	}

}

