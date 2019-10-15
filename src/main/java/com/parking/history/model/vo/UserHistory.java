package com.parking.history.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserHistory {
  
  private int userHistoryNo;
  private String userHistoryUserCode;
  private String userHistoryParkingCode;
  private Date userHistoryParkingDate;
  private int userHistoryPayment;
  
}
