package com.biz.score.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.biz.score.config.Lines;
import com.biz.score.domain.ScoreVO;

public class ScoreServiceImplV2 extends ScoreServiceImplV1 {
	
	private String fileName;

	public ScoreServiceImplV2() {
		fileName = "src/com/biz/score/data/scoreList.txt";
	}

	@Override
	public boolean inputScore() {
		Scanner scan = new Scanner(System.in);
		ScoreVO scoreVO = new ScoreVO();
		
		System.out.print("학번 (END:종료) >> ");
		String strNum = scan.nextLine();
		int intNum =0;
		if(strNum.equals("END")) {
			return false;
		}
		try {
			intNum = Integer.valueOf(strNum);
		} catch (Exception e) {
			System.out.println("학번은 숫자만 입력 할 수 있습니다.");
			return true;
		}
		if(intNum <1 || intNum >99999) {
			System.out.println("학번은 1~99999까지 입력할 수 있습니다.");
			return true;
		}
		
		strNum = String.format("%05d", intNum);
		
		for( ScoreVO sVO : scoreList) {
			if(strNum.equals(sVO.getNum())) {
				System.out.println("이미 저장된 학생 데이터입니다. 다른 학생의 점수를 입력해주세요");
				return true;
			}
		}
		
		
		scoreVO.setNum(strNum);
		
		System.out.print("국어 >> ");
		String strKor = scan.nextLine();
		int intKor = 0;
		try {
			intKor = Integer.valueOf(strKor);
		} catch (Exception e) {
			System.out.println("성적은 숫자만 입력할 수 있습니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		if(intKor<1 || intKor >100) {
			System.out.println("성적은 0~100 점 범위내로만 입력가능합니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		scoreVO.setKor(intKor);
		
		System.out.print("영어 >> ");
		String strEng = scan.nextLine();
		int intEng = 0;
		try {
			intEng = Integer.valueOf(strEng);
		} catch (Exception e) {
			System.out.println("성적은 숫자만 입력할 수 있습니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		if(intEng<1 || intEng >100) {
			System.out.println("성적은 0~100 점 범위내로만 입력가능합니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		scoreVO.setEng(intEng);
		
		System.out.print("수학 >> ");
		String strMath = scan.nextLine();
		int intMath = 0;
		try {
			intMath = Integer.valueOf(strMath);
		} catch (Exception e) {
			System.out.println("성적은 숫자만 입력할 수 있습니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		if(intMath<1 || intMath >100) {
			System.out.println("성적은 0~100 점 범위내로만 입력가능합니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		scoreVO.setMath(intMath);
		
		System.out.print("음악 >> ");
		String strMusic = scan.nextLine();
		int intMusic = 0;
		try {
			intMusic = Integer.valueOf(strMusic);
		} catch (Exception e) {
			System.out.println("성적은 숫자만 입력할 수 있습니다.");
			System.out.println(strMusic + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		if(intMusic<1 || intMusic >100) {
			System.out.println("성적은 0~100 점 범위내로만 입력가능합니다.");
			System.out.println(strNum + "의 학생의 학번부터 다시 입력해주세요");
			return true;
		}
		
		scoreVO.setMusic(intMusic);
		
		
		scoreList.add(scoreVO);
		
		
		return true;
	}

	@Override
	public void loadScore() {
		FileReader fileReader = null;
		BufferedReader buffer = null;
		
		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);
			
			String reader = "";
			while(true) {
				reader = buffer.readLine();
				if(reader == null) {
					break;
				}
				
				
				String[] readers = reader.split("");
				ScoreVO scoreVO = new ScoreVO();
				
				scoreVO.setNum(readers[0]);
				scoreVO.setKor(Integer.valueOf(readers[1]));
				scoreVO.setEng(Integer.valueOf(readers[2]));
				scoreVO.setMath(Integer.valueOf(readers[3]));
				scoreVO.setMusic(Integer.valueOf(readers[4]));
				scoreVO.setSum(Integer.valueOf(readers[4]));
				scoreVO.setAvg(Float.valueOf(readers[4]));
				
				scoreList.add(scoreVO);
				
			}
			buffer.close();
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("파일이 없기 때문에 최초 생성합니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("파일을 읽을 수 없습니다.");
		}
		
	}

	@Override
	public void saveScore() {
		FileWriter fileWriter = null;
		PrintWriter pWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);
			pWriter = new PrintWriter(fileWriter);
			pWriter.println(Lines.d_line);
			pWriter.println("성적일람표");
			pWriter.println(Lines.d_line);
			pWriter.println("학번\t국어\t영어\t수학\t음악\t총점\t평균");
			pWriter.println(Lines.s_line);
			for(ScoreVO scoreVO : scoreList) {
			
				pWriter.printf("%s\t", scoreVO.getNum());
				pWriter.printf("%d\t\t", scoreVO.getKor());
				pWriter.printf("%d\t\t", scoreVO.getEng());
				pWriter.printf("%d\t\t", scoreVO.getMath());
				pWriter.printf("%d\t\t", scoreVO.getMusic());
				pWriter.printf("%d\t\t", scoreVO.getSum());
				pWriter.printf("%5.2f\n", scoreVO.getAvg());
				
			}
			System.out.println(Lines.s_line);
			int korSum =0;
			int engSum = 0;
			int mathSum = 0;
			int musicSum = 0;
			int subSum = 0;
			
			for(ScoreVO scoreVO : scoreList) {
				korSum += scoreVO.getKor();
				engSum += scoreVO.getEng();
				mathSum += scoreVO.getMath();
				musicSum += scoreVO.getMusic();

			}
			pWriter.println(Lines.s_line);
			subSum = korSum +engSum + mathSum + musicSum;
			pWriter.printf("총점\t");
			pWriter.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%s\n", korSum, engSum, mathSum, musicSum,subSum,"x");
			int size = scoreList.size();
			float subAvg =(float)korSum/size +engSum/size + mathSum/size+
					musicSum/size;
			pWriter.printf("평균\t");
			pWriter.printf("%5.2f\t%5.2f\t%5.2f\t%5.2f\t%s\t\t%5.2f\n",(float)korSum/size,(float)engSum/size, (float)mathSum/size,
					(float)musicSum/size, "x", subAvg/4);
			pWriter.printf(Lines.d_line);	
			
			
			pWriter.flush();
			pWriter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("파일을 열 수 없습니다.");
		}
	
	}
	

	
}
