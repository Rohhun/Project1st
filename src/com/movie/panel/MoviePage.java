package com.movie.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.movie.DAO.DAOMovie;

public class MoviePage extends JPanel {
	JPanel MovieList, contents;
	JScrollPane scroll;
	DAOMovie daom =new DAOMovie();//영화정보 DB

	public MoviePage(){
		//	 패널 생성	 //
		MovieList =new JPanel();// 메인 패널
		contents =new JPanel();//포스터 및 영화 정보를 모두 넣을 패널

		//		패널 설정		//
		MovieList.setBorder(BorderFactory.createEmptyBorder(60,70,60,80));//패널 공간 여백 만듦
		MovieList.setPreferredSize(new Dimension(1400,860));//크기지정
		MovieList.setBackground(Color.cyan);

		contents.setBackground(Color.lightGray);
		contents.setPreferredSize(new Dimension(1200,249*(daom.getMovie().length/2+1/2)));
		//스크롤바 사용을 위해 증가하는 방식으로 패널 크기지정
		
		MovieList.setLayout(new BorderLayout());
		contents.setLayout(new FlowLayout(FlowLayout.LEFT));
			
		/**내부 패널에 작은 패널들 추가**/
		for(int i=0;i<daom.getMovie().length;i++) {
			MovieList.add(new Mp(i));
		}
		MovieList.add(contents,BorderLayout.CENTER);

		add(MovieList);
	
//		//	스크롤 만들기		//
//		scroll=new JScrollPane(contents,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		//	내부 패널에 스크롤 적용 후 상하스크롤 항상 보이게, 좌우스크롤 항상 숨김
//		scroll.getVerticalScrollBar().setUnitIncrement(16);
//		//		내부패널에 스크롤 적용	//
//		MovieList.add(scroll,BorderLayout.EAST);
		
		}//생성자

	//	패널 정리할 내부 클래스	생성//
	public class Mp extends JPanel {
		//	영화정보에 사용될 패널,라벨,버튼(포스터 이미지) 만들기		//
		JPanel MovieP,MovieLine,pb;
		JButton PosterB;
		JLabel MovieName,genre,runningTime,Directer,actor;
		//	이미지 만들기	//
		ImageIcon img =new ImageIcon();
		//		폰트 객체 만들기~		// 
		Font f1=new Font("함초롱바탕",Font.BOLD,30);
		Font f2=new Font("함초롱바탕",Font.PLAIN,15);//보통글씨로

		// 영화값을 넣을 생성자 //
		public Mp (int index) {
			//	 패널 객체 생성	//
			MovieP=new JPanel();
			MovieLine=new JPanel();
			
			//		패널  설정		//
			MovieP.setPreferredSize(new Dimension(190,233));
			MovieLine.setPreferredSize(new Dimension(390,233));
			
			//		버튼에 넣을 이미지 생성		//
			MovieLine.setLayout(new GridLayout(0,1));
			ImageIcon preImg = new ImageIcon(daom.getMovie()[index][1]);//포스터 넣을 이미지 객체 생성
			Image originImg = preImg.getImage();//ImageIcon을 Image로 전환
			Image changeImg = originImg.getScaledInstance(150,220,java.awt.Image.SCALE_SMOOTH);
			//이미지 사이즈 가로130,세로180,이미지를 스무스하게
			ImageIcon poster = new ImageIcon(changeImg);//Image로 ImageIcon 지정

			//			버튼 객체 생성			//
			PosterB = new JButton(poster);//포스터 DB

			//	라벨 객체생성	//
			MovieName = new JLabel(daom.getMovie()[index][0]);
			genre= new JLabel("개요: "+daom.getMovie()[index][2]);
			runningTime= new JLabel("상영시간:"+daom.getMovie()[index][3]);
			Directer= new JLabel("감독: "+daom.getMovie()[index][4]);
			actor= new JLabel("출연: "+daom.getMovie()[index][5]);

			/*포스터 이미지 아이콘 사이즈 변환*/
			//	폰트 지정		//
			MovieName.setFont(f1);	genre.setFont(f2);	runningTime.setFont(f2);
			Directer.setFont(f2);	actor.setFont(f2);
			// 	버튼 설정	//
			PosterB.setBackground(Color.GRAY);
			PosterB.setFocusPainted(false);//버튼 선택시 테두리 사용(안함)
			PosterB.setBorderPainted(false);//버튼 테두리 사용(안함)
			PosterB.setOpaque(true);//투명도를 (투명하게)

			// 			영화정보 패널에 라벨 넣기		//
			MovieLine.add(MovieName);	
			MovieP.add(genre);
			MovieP.add(runningTime);	MovieP.add(Directer);
			MovieP.add(actor);
			//			정보패널 정렬		//
			MovieP.setLayout(new GridLayout(0,1));
			MovieLine.add(MovieP);
			contents.add(PosterB);
			contents.add(MovieLine);
		}//생성자
	}
	public static void main(String[] args) {
		JFrame test=new JFrame();
		test.setSize(1400,860); 
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(new MoviePage());
		test.pack();
		test.setVisible(true);
	}//main

}
