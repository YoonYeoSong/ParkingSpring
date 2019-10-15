package com.parking.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
  int reviewNo;
  int reviewUserHistoryNo;
  String reviewTitle;
  String reviewContent;
  Date reviewCreatedDate;
  int reviewRating;
  private int reviewReadcount;

  public Review(int reviewUserHistoryNo, String reviewTitle, String reviewContent, int reviewRating) {
    super();
    this.reviewUserHistoryNo = reviewUserHistoryNo;
    this.reviewTitle = reviewTitle;
    this.reviewContent = reviewContent;
    this.reviewRating = reviewRating;
  }

}
