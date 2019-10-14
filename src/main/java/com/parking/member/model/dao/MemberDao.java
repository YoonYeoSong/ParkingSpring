package com.parking.member.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;

import com.parking.member.model.vo.Member;


public class MemberDao {
  
	private Properties prop = new Properties();

  public MemberDao() {
    String path = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
    try {
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public Member selectEmail(Connection conn, String email) {
    Member m = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = prop.getProperty("selectEmail");

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      rs = pstmt.executeQuery();

      if(rs.next()) {
        m = new Member();

        m.setUserCode(rs.getString("user_code"));
        m.setUserEmail(rs.getString("user_email"));
        m.setUserPw(rs.getString("user_pw"));
        m.setUserPhone(rs.getString("user_phone"));
        m.setUserName(rs.getString("user_name"));
        m.setUserAddr(rs.getString("user_addr"));

        Timestamp timestamp = rs.getTimestamp("user_created_date");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        m.setUserCreatedDate(new java.sql.Date(date.getTime()));

        timestamp = rs.getTimestamp("user_login_date");
        if(timestamp != null) {
          date = new java.util.Date(timestamp.getTime());
          m.setUserLoginDate(new java.sql.Date(date.getTime()));
        }

        m.setUserSmsYn(rs.getInt("user_sms_yn"));
        m.setUserEmailYn(rs.getInt("user_email_yn"));
        m.setUserEmailVerified(rs.getInt("user_email_verified"));
        m.setUserSnsAccount(rs.getString("user_sns_account"));
        m.setUserOriginalFilename(rs.getString("user_original_filename"));
        m.setUserRenamedFilename(rs.getString("user_renamed_filename"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return m;
  }

  public boolean selectCheckEmail(Connection conn, String emailToChk) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean result = false;
    String sql = prop.getProperty("selectEmail");

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, emailToChk);
      rs = pstmt.executeQuery();
      if(!rs.next())
        result = true;
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }
    return result;
  }
  
  public int insertMember(Connection conn, Member m) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("insertMember");
    
    try {
      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, m.getUserCode()); //user_code (assigned by sequence)
      pstmt.setString(2, m.getUserEmail());
      pstmt.setString(3, m.getUserPw());
      pstmt.setString(4, m.getUserPhone());
      pstmt.setString(5, m.getUserName());
      pstmt.setString(6, m.getUserAddr());
      pstmt.setTimestamp(7, new java.sql.Timestamp(m.getUserCreatedDate().getTime())); //user_created_date: SYSDATE
      pstmt.setTimestamp(8, null); //user_login_date: NULL
      pstmt.setInt(9, m.getUserSmsYn());
      pstmt.setInt(10, m.getUserEmailYn());
      pstmt.setInt(11, m.getUserEmailVerified());
      pstmt.setString(12, m.getUserSnsAccount());
      pstmt.setString(13, m.getUserOriginalFilename());
      pstmt.setString(14, m.getUserRenamedFilename());
      //insertMember=insert into member values(?,?,?,?,?,?,?,?,?,?,?,?, ?,?)

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }
    
    return result;
  }
  
  public Member selectUserCode(Connection conn, String userCode) {
    Member m = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = prop.getProperty("selectUserCode");

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);
      rs = pstmt.executeQuery();

      if(rs.next()) {
        m = new Member();

        m.setUserCode(rs.getString("user_code"));
        m.setUserEmail(rs.getString("user_email"));
        m.setUserPw(rs.getString("user_pw"));
        m.setUserPhone(rs.getString("user_phone"));
        m.setUserName(rs.getString("user_name"));
        m.setUserAddr(rs.getString("user_addr"));

        Timestamp timestamp = rs.getTimestamp("user_created_date");
        java.util.Date date = new java.util.Date(timestamp.getTime());
        m.setUserCreatedDate(new java.sql.Date(date.getTime()));

        timestamp = rs.getTimestamp("user_login_date");
        if(timestamp != null) {
          date = new java.util.Date(timestamp.getTime());
          m.setUserLoginDate(new java.sql.Date(date.getTime()));
        }

        m.setUserSmsYn(rs.getInt("user_sms_yn"));
        m.setUserEmailYn(rs.getInt("user_email_yn"));
        m.setUserEmailVerified(rs.getInt("user_email_verified"));
        m.setUserSnsAccount(rs.getString("user_sns_account"));
        m.setUserOriginalFilename(rs.getString("user_original_filename"));
        m.setUserRenamedFilename(rs.getString("user_renamed_filename"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return m;
  }
  
  
  public boolean updateLoginDate(Connection conn, String email) {
    PreparedStatement pstmt = null;
    boolean logged = false;
    String sql = prop.getProperty("updateLoginDate");

    try {

      //updateLoginDate=update member set user_login_date=? where user_email=?
      pstmt = conn.prepareStatement(sql);

      java.sql.Date date  = new java.sql.Date(Calendar.getInstance().getTime().getTime());

      pstmt.setTimestamp(1, new java.sql.Timestamp(date.getTime())); //user_login_date SYSDATE
      pstmt.setString(2, email);
      logged = pstmt.executeUpdate() > 0;
      
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }

    return logged;
  }

  public int updateMember(Connection conn, Member m, Map<String, String> newAttr) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("updateMember");
    
    String userPhone = newAttr.get("userPhone");
    String userName = newAttr.get("userName");
    String userAddr = newAttr.get("userAddr");
    int userSmsYn = Integer.parseInt(newAttr.get("userSmsYn"));
    int userEmailYn = Integer.parseInt(newAttr.get("userEmailYn"));
    String ori = newAttr.get("ori");
    String re = newAttr.get("re");
    String userCode = m.getUserCode();
    
    try {
      pstmt = conn.prepareStatement(sql);

      pstmt.setString(1, userPhone);
      pstmt.setString(2, userName);
      pstmt.setString(3, userAddr);
      pstmt.setInt(4, userSmsYn);
      pstmt.setInt(5, userEmailYn);
      pstmt.setString(6, ori);
      pstmt.setString(7, re);
      pstmt.setString(8, userCode);

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }
    
    m.setUserPhone(userPhone);
    m.setUserName(userName);
    m.setUserAddr(userAddr);
    m.setUserSmsYn(userSmsYn);
    m.setUserEmailYn(userEmailYn);
    m.setUserOriginalFilename(ori);
    m.setUserRenamedFilename(re);
    
    return result;
  }

  public int deleteMember(Connection conn, String userCode) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("deleteMember");

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }

    return result;
  }
  
  

	public int activateaccount(Connection conn, String receivedemail) {
		 	PreparedStatement pstmt = null;
		    int result = 0;
		    String sql = prop.getProperty("activateMember");
		    
		    try {
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, receivedemail);
		      result = pstmt.executeUpdate();
//		      System.out.println("from query result :" +result);
		    
		    } catch(SQLException e) {
		      e.printStackTrace();
		    } finally {
		      close(pstmt);
		    }
		    
		    return result;
	}

	public String getUserEmail(Connection conn, String decryptedcode) {
		 PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    String sql = prop.getProperty("selectEmail");

		    try {
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, decryptedcode);
		      rs = pstmt.executeQuery();
		      if(rs.next())
		       return rs.getString(1);
		    } catch(SQLException e) {
		      e.printStackTrace();
		    } finally {
		      close(rs);
		      close(pstmt);
		    }
		    return null;
	}

	public int changepassword(Connection conn, String email, String password) {
	 	PreparedStatement pstmt = null;
	    int result = 0;
	    String sql = prop.getProperty("changepassword");
	    
	    try {
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, password);
	      pstmt.setString(2, email);
	      result = pstmt.executeUpdate();
//	      System.out.println("result :" +result);
	    
	    } catch(SQLException e) {
	      e.printStackTrace();
	    } finally {
	      close(pstmt);
	    }
	    
	    return result;
	}

	public boolean fbselectEmail(Connection conn, String user_email) {
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean result = false;
	    String sql = prop.getProperty("selectEmail");

	    try {
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1, user_email);
	      rs = pstmt.executeQuery();
	      if(rs.next())
	        result = true;
	    } catch(SQLException e) {
	      e.printStackTrace();
	    } finally {
	      close(rs);
	      close(pstmt);
	    }
	    return result;


	}
}



