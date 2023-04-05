package resv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import resv.model.Resv;

public class ResvDAO {
	
	
	//내 예약확인하기(리스트)
	public List<Resv> selectByEmpno(Connection conn,int empno) throws SQLException {
		System.out.println("ResvDAO-select");
		PreparedStatement stmt = null; 
		String sql = "SELECT resvno,empno,resvdate,roomname,resvmemo,resvname,resvtel,resvemail " + 
					"FROM erpdb.resv " + 
					"where empno=? " +
					"order by resvdate asc ";
		ResultSet rs = null;
		List<Resv> resvList = new ArrayList<Resv>();
		
		try {//limit 시작행인덱스번호,1페이지당출력게시물수
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empno);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Resv resv = convertResv(rs);
				resvList.add(resv);
			}
				
			return resvList;

		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}

	//객체변환
	private Resv convertResv(ResultSet rs) throws SQLException {
		return new Resv(rs.getInt("resvno"),
				rs.getInt("empno"),
				rs.getDate("resvdate"),
				rs.getString("roomname"),
				rs.getString("resvmemo"),
				rs.getString("resvname"),
				rs.getString("resvtel"),
				rs.getString("resvemail"));
	}
	
	//전체게시글 수 p646
		public int selectCount(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			String sql="select count(empno) " + 
						"from Resv ";
			
			ResultSet rs = null;
			try{ stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
				if(rs.next()) {
				return rs.getInt(1);
				}
				return 0;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		}
		
	//예약하기
		public void insert(Resv resv, Connection conn) throws SQLException {
			String sql = "insert into resv(empno,resvdate,roomname,resvmemo,resvname,resvtel,resvemail) " + 
						"values (?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, resv.getEmpno());
			stmt.setTimestamp(2, new Timestamp(resv.getResvdate().getTime()));
			stmt.setString(3, resv.getRoomname());
			stmt.setString(4, resv.getResvmemo());
			stmt.setString(5, resv.getResvname());
			stmt.setString(6, resv.getResvtel());
			stmt.setString(7, resv.getResvemail());
			int result = stmt.executeUpdate();
		}
		
	//예약시 중복값 찾기(예약일,회의실 중복)
		public boolean resvDuplicate(Date resvdate, String roomname, Connection conn) throws SQLException {
			String sql="select count(empno) " + 
						"from erpdb.resv " + 
						"where resvdate='?' and roomname='?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			boolean result = false;
			
			try{ stmt = conn.prepareStatement(sql);
				stmt.setDate(1, (java.sql.Date) resvdate);
				stmt.setString(2, roomname);
				rs = stmt.executeQuery();
				if(rs.next()) {
					result=false;
				}else {
					result=true;
				}
					
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
			return result;
		}
		
	//예약 삭제(delete)
	//파라미터 int no : 삭제할 예약번호 / 리턴타입 int : 삭제된 행 수(1이면 삭제성공)
		public int deleteResv(Connection conn, int resvDelNo) {
			PreparedStatement stmt = null;
			String sql ="delete from resv " + 
						"where resvno=?";
			int cnt = 0;
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, resvDelNo);
				cnt = stmt.executeUpdate();
				return cnt;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
			}
			return cnt;
		}
	
	//예약수정
	public void update(Connection conn, Date resvdate, String roomname, String resvmemo, String resvname, String resvtel, String resvemail, int resvno) {
		PreparedStatement stmt = null;
		String sql = "update resv " + 
					 "set resvdate=?, roomname=?, resvmemo=?, resvname=?, resvtel=?, resvemail=? " + 
					 "where resvno=?";
		try {
			stmt = conn.prepareStatement(sql);
				stmt.setTimestamp(1,toTimestamp(resvdate));
				stmt.setString(2, roomname);
				stmt.setString(3, resvmemo);
				stmt.setString(4, resvname);
				stmt.setString(5, resvtel);
				stmt.setString(6, resvemail);
				stmt.setInt(7, resvno);
				
				int cnt = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(stmt);
	}

	//예약번호로 예약찾기
	public Resv selectByResvNo(Connection conn, int resvno) throws SQLException {
		PreparedStatement stmt = null;
		String sql="select resvno,empno,resvdate,roomname,resvmemo,resvname,resvtel,resvemail " + 
				"from erpdb.resv " + 
				"where resvno=?";
		ResultSet rs = null;
		Resv resv = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, resvno);
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				resv = convertResv(rs);
			}
			
			return resv;
		
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

		
		private Timestamp toTimestamp(Date resvdate) {
			return new Timestamp(resvdate.getTime());
		}
		
		
		
		

		
		
}