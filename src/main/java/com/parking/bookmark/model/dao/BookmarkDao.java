package com.parking.bookmark.model.dao;

import static common.template.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.parking.api.model.vo.Parking;
import com.parking.bookmark.model.vo.Bookmark;


public class BookmarkDao {
  private Properties prop = new Properties();

  public BookmarkDao() {
    String path = BookmarkDao.class.getResource("/sql/bookmark/bookmark-query.properties").getPath();
    try {
      prop.load(new FileReader(path));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public Bookmark selectBookmark(Connection conn, String userCode, String parkingCode) {
    Bookmark b = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = prop.getProperty("selectBookmark");

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);
      pstmt.setString(2, parkingCode);
      rs = pstmt.executeQuery();

      if(rs.next()) {
        b = new Bookmark();
        
        b.setBookmarkNo(rs.getInt("bookmark_no"));
        b.setBookmarkUserCode(rs.getString("bookmark_user_code"));
        b.setBookmarkParkingCode(rs.getString("bookmark_parking_code"));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return b;
  }

  public List<Bookmark> selectBookmarkList(Connection conn, String userCode){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectBookmarkList");
    List<Bookmark> list = new ArrayList<Bookmark>();
    Bookmark b = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);

      rs = pstmt.executeQuery();
      while(rs.next()) {
        b = new Bookmark();

        b.setBookmarkNo(rs.getInt("bookmark_no"));
        b.setBookmarkParkingCode(rs.getString("bookmark_parking_code"));
        b.setBookmarkUserCode(rs.getString("bookmark_user_code"));
        
        list.add(b);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return list;
  }

  public List<Parking> selectBookmarkParkingList(Connection conn, String userCode){
    PreparedStatement pstmt = null;
    ResultSet rs =  null;
    String sql = prop.getProperty("selectBookmarkParkingList");
    List<Parking> list = new ArrayList<Parking>();
    Parking p = null;

    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);

      rs = pstmt.executeQuery();
      while(rs.next()) {
        p = new Parking();

        p.setParkingCode(rs.getString("ps_parking_code"));
        p.setParkingName(rs.getString("ps_name"));
        p.setAddr(rs.getString("ps_addr"));
        p.setLatitude(rs.getDouble("ps_latitude"));
        p.setLongitude(rs.getDouble("ps_longitude"));

        list.add(p);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(rs);
      close(pstmt);
    }

    return list;
  }

  public int insertBookmark(Connection conn, String userCode, String parkingCode) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("insertBookmark");
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);
      pstmt.setString(2, parkingCode);

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }
    
    return result;
  }

  public int deleteBookmark(Connection conn, String userCode, String parkingCode) {
    PreparedStatement pstmt = null;
    int result = 0;
    String sql = prop.getProperty("deleteBookmark");
    
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, userCode);
      pstmt.setString(2, parkingCode);

      result = pstmt.executeUpdate();

    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      close(pstmt);
    }
    
    return result;
  }
}