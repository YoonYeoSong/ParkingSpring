package com.parking.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Parking {
	private String parkingCode; 		// 주차장 코드
	private String parkingName; 		// 주차장명
	private String addr; 				// 주소
	private String parkingType;			// 주차장종류 
	private String parkingTypeNm;		// 주차장 종류명
	private int operationRule;			// 주차장 운영구분
	private String operationRuleNm;		// 운영 구분
	private String tel;					// 전화번호
	private int queStatus;				// 주차현황 정보 제공여부
	private String queStatusNm;			// 주차현황 정보 제공여부
	private int capacity;				// 주차 면(주차 가능 차량 수)
	private int curParking;				// 현재 주차중인 대수
	private String curParkingTime; 		// 현재 주차 차량 업데이트 시간
	private	String payYn; 				// 유무료 구분
	private String payNm;				// 유무료 구분명
	private String nightFreeOpen;		// 야간무료 개방여부
	private String nightFreeOpenNm; 	// 야간무료 개방여부명
	private String weekdayBeginTime;	// 평일운영시작시각(hhmm)
	private String weekdayEndTime;		// 평일 운영종료시각(hhmm)
	private String weekendBeginTime;	// 주말운영시작시각(hhmm)
	private String weekendEndTime;		// 주말운영종료시각(hhmm)
	private String holidayBeginTime;	// 공휴일 운영시작시각
	private String holidayEndTime;		// 공휴일 운영종료시각
	private String syncTime;			// 최종데이터 동기화 시각
	private String saturdayPayYn;		// 토요일 유 무료 구분
	private String saturdayPayNm;		// 토요일 유 무료 구분명
	private String holidayPayYn;		// 공휴일 유 무료 구분
	private String holidayPayNm;		// 공휴일 유 무료 구분명
	private String fulltimeMonthly;		// 월 정기권 금액
	private String grpParkNm;				// 노상 주차장 관리그룹번호
	private int rates;					// 기본 주차 요금
	private int timeRate;				// 기본주차 시간(분 단위)
	private int addRates; 				// 추가 단위 요금
	private int addTimeRate;			// 추가 단위 요금
	private int busRates;				// 버스 기본 주차 요금
	private int busTimeRate;			// 버스 기본 주차 시간(분 단위)
	private int busAddTimeRate;			// 버스 추가 단위 시간(분 단위)
	private int busAddRate;				// 버스 추가 단위 요금
	private int dayMaximum;				// 일최대요금
	private double latitude; 			// 위도 
	private double longitude; 			// 경도longitude
	private String assignCode;			// 배정코드
	private String assignCodeNm;		// 배정코드명
	
	private String insttNm;
	
	
	//https://data.seoul.go.kr/dataList/datasetView.do?infId=OA-13122&srvType=S&serviceKind=1&currentPageNo=1
	


}
