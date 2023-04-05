package offday.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import offday.model.OffDay;
import offday.model.OffDayUpdate;

public class OffDayDAO {

	
	
	//휴가 신청 관련 인서트문 (사원번호,시작일,종료일,휴가사유)
	
	public static int insertOffDay(Connection conn,int empno,int deptno,Date startday, Date endday,String offnotice) {
		
		PreparedStatement stmt = null;  //insert용 
		
		String sql ="insert into offday(empno,deptno,startday,endday,offnotice) "+
				"values(?,?,?,?,?)";
		
		int cnt = 0;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,empno); //
			stmt.setInt(2,deptno); //부서번호 -> 추후 조인으로 부서이름을 가져온다
			stmt.setTimestamp(3, new Timestamp(startday.getTime()));  //연차 시작일
			stmt.setTimestamp(4, new Timestamp(endday.getTime()));	//연차종료일
			stmt.setString(5, offnotice); //연차사유
			
			
			
		    cnt = stmt.executeUpdate();
			System.out.println("insert결과행수"+cnt);
	} catch(SQLException e){
		e.printStackTrace();
	}finally {
		
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
		
	}
		return cnt;
	}	
	
	//객체변환 (컨트롤러에서 string 타입만 받아서 변환해서 가져오는거같다)
	private OffDay convertOffDay(ResultSet rs) throws SQLException {
		return new OffDay(rs.getInt("empno"),
				rs.getInt("deptno"),
				rs.getString("deptname"),
				rs.getDate("startday"),
				rs.getDate("endday"),
				rs.getString("offnotice"),
				rs.getInt("offdayno"));
	}
	///////////////////////////////셀렉문//////////////////
	
	public OffDay selectOffDay(Connection conn, int empno,int deptno ) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select b.empno AS empno ,b.deptno AS deptno,a.deptname AS deptname,b.startday AS startday "+
				" ,b.endday AS endday , b.offnotice AS offnotice,b.offdayno AS offdayno "+
				"from dept a,offday b "+
				"where a.deptno= b.deptno "+
				"and b.empno=? "+
				"order by startday desc limit 0,5";
		OffDay offday =null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empno);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				offday =new OffDay( 
								rs.getInt("empno"), 
								rs.getInt("deptno"),
								rs.getString("deptname"),
								rs.getDate("startday"),
								rs.getDate("endday"),
								rs.getString("offnotice"),
								rs.getInt("offdayno")
								);
				return offday;
			}
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return offday;
	}
	
/////연차번호로 찾기//////업데이트를 위한 셀렉문/////selectByResvNo////////////
	public OffDay upselectOffDay(Connection conn, int offdayno) throws SQLException {
		PreparedStatement stmt = null;
		String sql =  "select b.empno AS empno,b.deptno AS deptno,a.deptname AS deptname,b.startday AS startday "+
				",b.endday AS endday , b.offnotice AS offnotice,b.offdayno AS offdayno "+
				"from dept AS a "+
				"inner join offday AS b "+ 
				"on a.deptno = b.deptno "+
				"where offdayno=?";     
		
		ResultSet rs = null;		
		OffDay offday =null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, offdayno);
			rs = stmt.executeQuery();
			
				if(rs.next()) {
					offday = convertOffDay(rs);
				}
				
				return offday;
			
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
	}
	
	
	
	
		/////////////////////////연차기록보기/////////////////////////
	public List<OffDay> selectOffDayHis(Connection conn, int empno) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select b.empno AS empno ,b.deptno AS deptno,a.deptname AS deptname, "+
				"b.startday AS startday ,b.endday AS endday , b.offnotice AS offnotice, b.offdayno AS offdayno "+
				"from dept a,offday b "+
				"where a.deptno= b.deptno "+
				"and b.empno=? "+
				"order by startday asc limit 0,10";
		
		List<OffDay> offdaylist = new ArrayList<>();
		
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empno);
			
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				OffDay offday = convertOffday(rs);
				offdaylist.add(offday);
			
			}
			return offdaylist;
			
		}finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
		
		private OffDay convertOffday(ResultSet rs) throws SQLException {
				
				return new OffDay( 
						rs.getInt("empno"), 
						rs.getInt("deptno"),
						rs.getString("deptname"),
						rs.getDate("startday"),
						rs.getDate("endday"),
						rs.getString("offnotice"),
						rs.getInt("offdayno")
						);
			}//converNotice()끝

/////////////offlist에서 연차 삭제 ////////////////
//글삭제-delete/////////////////////////
/*파라미터  int no : 삭제할 글번호
 *리턴타입  int : 삭제(delete)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
public int deleteDayOff(Connection conn, int offdayno) {
		PreparedStatement stmt = null;
			String sql = "delete from offday "+
						 "where offdayno=?";
			
			int cnt = 0;//삭제(delte)된 행 수를 저당할 변수
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, offdayno);
				
				cnt = stmt.executeUpdate();
				System.out.println("dayoff 삭제(delete)된 행 수="+cnt);
				return cnt;
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
			}
		return cnt;
}


///////////////연차 시작일,종료일 수정,사유수정////////////////////
/*파라미터  int no : 삭제할 글번호
 *리턴타입  int : 삭제(update)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
//글수정
/*파라미터  
  */

public void offdayupdate(Connection conn,Date startday,Date endday,String offnotice,int offdayno ) {
      PreparedStatement stmt = null;
      String sql = "update offday "+
				"set startday=?, endday=?, offnotice=? "+
				"where offdayno=?";
      try {
         stmt = conn.prepareStatement(sql);
            //stmt.setTimestamp(1, (Timestamp)resvdate);
            //stmt.setDate(1, (java.sql.Date) resvdate);
            stmt.setTimestamp(1,toTimestamp(startday));  //연차 시작일
            stmt.setTimestamp(2,toTimestamp(endday)); 	//연차 종료일
            stmt.setString(3, offnotice);		//연차 사유
            stmt.setInt(4,offdayno);   //연차일 글번호(form에서 히든값으로받음주로)
            
            
            int cnt = stmt.executeUpdate();
            System.out.println("startday"+startday);
            System.out.println("endday"+endday);
            
            System.out.println("예약수정 행 수 ="+cnt);
            
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }


      
      private Timestamp toTimestamp(Date resvdate) {
         return new Timestamp(resvdate.getTime());
      }

}

