package mypage.dept.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import emp.model.MypageDbEmp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.dept.dao.MypageDeptDAO;

public class MypageSelectDeptService {
   private MypageDeptDAO mypageDeptDAO = new MypageDeptDAO();
   
   public List<MypageDbEmp> deptAllSelect() throws SQLException {
      Connection conn = ConnectionProvider.getConnection();
      List<MypageDbEmp> mypageDbEmpList = mypageDeptDAO.deptAllSelect(conn);
      if(mypageDbEmpList!=null) {
         JdbcUtil.close(conn);
         return mypageDbEmpList;
      }else {
         return null;
      }
   }
}