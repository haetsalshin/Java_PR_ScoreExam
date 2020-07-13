package com.biz.score.exec;

import com.biz.score.service.ScoreServiceImplV1;
import com.biz.score.service.ScoreServiceImplV2;

import java.util.Scanner;

import com.biz.score.config.Lines;
import com.biz.score.service.ScoreService;

public class ScoreEx_01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ScoreService sService = new ScoreServiceImplV1();
		sService.loadScore();
		ScoreService ssService = new ScoreServiceImplV2();
		ssService.loadScore();
		
		
		while(true) {
			
			System.out.println(Lines.d_line);
			System.out.println("학생 성적 입력 프로그램 V1");
			System.out.println(Lines.d_line);
			System.out.println("저장하고 싶은 파일을 입력하세요.");
			System.out.println("1. 성적 입력(score.txt)");
			System.out.println("2. 파일 저장(scoreList.txt)");
			System.out.print(" 번호 (END:종료) >> ");
			String strMenu = scan.nextLine();
			if(strMenu.equals("END")) {
				break;
			}
			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				System.out.println("메뉴선택은 숫자만 가능합니다.");
				continue;
			}
			
			
			if(intMenu == 1) {
				
				while(true) {
					if(!sService.inputScore()) {
						break;
					}
					sService.calcSum();
					sService.calcAvg();
					sService.saveScore();
					sService.scoreList();
				}
				
				
			}else if(intMenu == 2){
				while(true) {
					if(!ssService.inputScore()) {
						break;
					}
					ssService.calcSum();
					ssService.calcAvg();
					ssService.saveScore();
					ssService.scoreList();
					
				}
				
			}else if(intMenu <1 || intMenu>2) {
				System.out.println("메뉴선택은 1번과 2번만 선택가능합니다. 다시 선택해주세요");
				continue;
			}
			
			
		}
		
		
		

		
		
		
		
	}
	
	
}
