package com.winter.app.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.users.UserDAO;

@Component
@EnableScheduling
public class ScheduleTest {
	
	@Autowired
	private UserDAO userDAO;
	@Scheduled(cron = "50 * * * * *")// 초, 분, 시, 일, 월, 요일 // 요일: 1-3 월화수, 1-5 월화수목금
	// 9/2 : 아침 9시부터 2시간 간격으로
	// 3 0 * * * * // 매시 0분 3초마다 반복
	// 0 0 0 1 * * // 
	public void s3() {
		System.out.println("반복");
	}
	
	
//	@Scheduled(fixedDelayString = "1000", initialDelay = 1000)
	public void s1() {
		System.out.println("일정 간격으로 반복");
	}
	
	
//	@Scheduled(fixedRate = 2000, initialDelayString = "1000")
	public void s2() {
		System.out.println("고정 간격으로 반복");
		
	}

}
