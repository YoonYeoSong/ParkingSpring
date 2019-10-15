package com.parking.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coupon {
  private String couponCode;
  private String userCode;
  private int discountRate;
  private int duration;
  private int expiredYn;

  public Coupon(String couponCode,String userCode)
  {
    super();
    this.couponCode = couponCode;
    this.userCode = userCode;
  }
  
  
}
