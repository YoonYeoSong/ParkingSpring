package com.parking.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnaBoard {

  private int qnaNo;
  private String qnaTitle;
  private String userCode;
  private String qnaContent;
  private Date qnaCreatedDate;
  private int qnaReadcount;

  public QnaBoard(String qnaTitle, String userCode, String qnaContent) {
    super();
    this.qnaTitle = qnaTitle;
    this.userCode = userCode;
    this.qnaContent = qnaContent;
  }

}
