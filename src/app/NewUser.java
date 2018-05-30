package app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class NewUser extends JInternalFrame  {//JFrame 상속
	
//콤보박스나 체크박스에 넣을 배열 생성
private String[] local = {"지역","대구광역시", "서울특별시", "부산광역시", "광주광역시", "기타"};
private String[] special1 = {"특기1", "외국어", "컴퓨터", "축구","악기연주", "기타"};
private String[] special2 = {"특기2", "외국어", "컴퓨터", "축구","악기연주", "기타"};

public NewUser() {
	super("회원가입");// 프레임 제목
	setClosable(true);
	setIconifiable(true);
	setMaximizable(true);
	setResizable(false);
	
	
	Container c = getContentPane();//컨텐트팬 달기(컨테이너객체)
	c.setLayout(new BorderLayout());//c 배치관리자 설정
	c.add(new MyPanel(), BorderLayout.SOUTH);
	
	
	JPanel p1 = new JPanel();//각 패널 생성 및 초기화
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JPanel p7 = new JPanel();
	JPanel p8 = new JPanel();
	JPanel p9 = new JPanel();
	JPanel p10 = new JPanel();
	JPanel p11 = new JPanel();
	JPanel p12 = new JPanel();
	JPanel p13 = new JPanel();
	JPanel p14 = new JPanel();
	
	c.add(p1, BorderLayout.NORTH);//패널을 컨테이너에 추가(위치는 정렬)
	
	
	
	p1.setLayout(new BorderLayout());//각 패널에 배치관리자 설정
	p2.setLayout(new FlowLayout());//Flow>>위치설정없이 순서대로 배치
	p3.setLayout(new BorderLayout());//Border>>설정한 위치대로 배치
	p4.setLayout(new BorderLayout());
	p5.setLayout(new BorderLayout());
	p6.setLayout(new BorderLayout());
	p7.setLayout(new BorderLayout());
	p8.setLayout(new BorderLayout());
	p9.setLayout(new BorderLayout());
	p10.setLayout(new BorderLayout());
	p11.setLayout(new FlowLayout());
	p12.setLayout(new FlowLayout());
	p13.setLayout(new FlowLayout());
	p14.setLayout(new FlowLayout());
	
	JLabel nameLabel = new JLabel("이 름(ID)");//JLabel 생성 및 초기화
	JTextField nameField = new JTextField(10);
	//10자 텍스트 제한 JTextField 생성
	p6.add(nameLabel, BorderLayout.WEST);//p6에 각각 위치대로 
	p6.add(nameField, BorderLayout.EAST);//배치
	p3.add(p6, BorderLayout.WEST);//p3에 p6배치
	
	
	JLabel passwdLabel = new JLabel("암 호");
	//JLabel 객체 생성 및 초기화
	 JPasswordField passField = new JPasswordField(10);
	//10자 제한 패스워드필드 생성
	p7.add(passwdLabel, BorderLayout.WEST);//p7에 지정한대로 배치
	p7.add(passField, BorderLayout.EAST);
	p3.add(p7, BorderLayout.EAST);//p3에 지정한대로 배치
	
	JLabel gradeLabel = new JLabel("학 력");
	ButtonGroup gra = new ButtonGroup(); //버튼그룹객체 생성 및 초기화
	JRadioButton grade1 = new JRadioButton("중졸");
	JRadioButton grade2 = new JRadioButton("고졸");
	JRadioButton grade3 = new JRadioButton("대졸");
	JRadioButton grade4 = new JRadioButton("대학원");
	p11.add(grade1);//패널p11에 학력 추가하기
	p11.add(grade2);
	p11.add(grade3);
	p11.add(grade4);
	gra.add(grade1); //라디오버튼 그룹에 속해
	gra.add(grade2); // 하나만 선택할 수 있음
	gra.add(grade3);
	gra.add(grade4);
	p8.add(gradeLabel, BorderLayout.WEST);
	//패널p8에 gradeLabel를 왼쪽에 추가
	p8.add(p11, BorderLayout.CENTER);
	//패널p8에 p11를 중앙에 추가
	JLabel bornLabel = new JLabel("출신지");
	
	JComboBox born = new JComboBox(local);
	
	
	p9.add(bornLabel, BorderLayout.WEST);
	p9.add(born, BorderLayout.EAST);
	
	
	
	JLabel hobbyLabel = new JLabel("취 미");//각 라벨에 
	JCheckBox chk1 = new JCheckBox("독서"); //입력된 텍스트
	JCheckBox chk2 = new JCheckBox("공부");
	JCheckBox chk3 = new JCheckBox("낚시");
	JCheckBox chk4 = new JCheckBox("등산");
	JCheckBox chk5 = new JCheckBox("게임");
	JCheckBox chk6 = new JCheckBox("스포츠"); 
	JCheckBox chk7 = new JCheckBox("기타");
	
	ButtonGroup hobby = new ButtonGroup();//체크박스를 하나의 버튼그룹으로
	hobby.add( chk1);
	hobby.add( chk2);
	hobby.add( chk3);
	hobby.add( chk4);
	hobby.add( chk5);
	hobby.add( chk6);
	hobby.add( chk7);
	
	
	p12.add(chk1); //p12에 체크박스에 속한 모든 객체들을 추가
	p12.add(chk2);
	p12.add(chk3);
	p12.add(chk4);
	p12.add(chk5);
	p12.add(chk6);
	p12.add(chk7);
	p10.add(hobbyLabel, BorderLayout.WEST);
	//p10에 hobbyLabel을 왼쪽에 추가
	p10.add(p12, BorderLayout.EAST);
	//p10에 p12를 오른쪽에 추가
	p4.add(p8, BorderLayout.NORTH);
	//p4에 p8을 위쪽에 추가
	p4.add(p9, BorderLayout.CENTER);
	//p4에 p9를 중앙에추가
	p4.add(p10, BorderLayout.SOUTH);
	//p4에 p10을 위쪽에 추가 
	
	JLabel PriceLabel = new JLabel("특 기");
	//JLabel 생성및 초기화
	JComboBox Price1 = new JComboBox(special1);
	//JComboBox 생성 및 초기화
	JComboBox Price2 = new JComboBox(special2);
	p13.add(Price1); //p13에 price1추가 
	p13.add(Price2);//p13에 price2추가 
	p5.add(PriceLabel, BorderLayout.WEST);
	
	p5.add(p13, BorderLayout.EAST);
	
	p1.add(p3, BorderLayout.NORTH);//p3을 p1위쪽에 배치
	p1.add(p4, BorderLayout.CENTER);//p4을 p1중앙에 배치
	p1.add(p5, BorderLayout.SOUTH);//p5을 p1아래쪽에 배치
	
	c.add(p14,BorderLayout.CENTER);
	JButton resetBtn = new JButton("Reset");
	p14.add(resetBtn,BorderLayout.CENTER);
	
	
	
	
	setSize(500,270);//프레임 크기 알맞게
	setVisible(true);//화면에 프레임을 출력
	
	
	resetBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent c) {
			nameField.setText(null); //리셋 버튼을 누르고 각각의
			passField.setText(null);//초기화 과정
			gra.clearSelection();
			hobby.clearSelection();
			born.setSelectedItem("지역");
			Price1.setSelectedItem("특기1");
			Price2.setSelectedItem("특기2");
		}
	});
}

class MyPanel extends Panel  {

	JButton inputBtn = new JButton("확인");		
	JTextField tf = new JTextField(10);
	JButton exitBtn = new JButton("종료");
	JButton messageBtn = new JButton("Help");
	
	MyPanel() {
		//각 버튼을 생성자 삽입
		add(inputBtn);
		add(exitBtn);
		add(messageBtn);
		add(tf);
		
		//리스너 이벤트 (버튼을 누를시)
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력 다이얼로그 생성
				
				String pw = JOptionPane.showInputDialog(" 사이트 비밀번호를 입력해 주세요 ");
				
				
				// 사용자가 입력한 문자열을 텍스트필드 창에 출력
				 if(pw.equals("1234")) { //미리 정해진 암호창
					tf.setText(" 회원가입되셨습니다");
					 }
				else
					 {
					
					 pw = JOptionPane.showInputDialog(" 잘못 입력하셨습니다 \n  "+
			 "한번 더 입력해 주세요");
			 
			    if(pw.equals("kwangwoon1485")) { //미리 정해진 암호창
					tf.setText(" 회원가입되셨습니다");
					 }
			    
			    else {
			    	
					 pw = JOptionPane.showInputDialog(" 잘못 입력하셨습니다 \n  "+
					 "한번 더 입력해 주세요");
					 
					    if(pw.equals("kangwon1485")) { //미리 정해진 암호창
							tf.setText(" 회원가입되셨습니다");
							 }
					    else
					    {
					    	 pw = JOptionPane.showInputDialog(" 잘못 입력하셨습니다 \n  "+
									 "한번 더 입력해 주세요");
					    	 if(pw.equals("kangwon1485")) { //미리 정해진 암호창
									tf.setText(" 회원가입되셨습니다");
									 }
							    else
							    {
									JOptionPane.showMessageDialog(null, "3번모두 잘못입력하셨습니다", "error", JOptionPane.ERROR_MESSAGE);
									dispose();// "3번모두 잘못입력하셨습니다" 메시지 창 뜨고
									//프로그램 종료
									 }
					    	 /* 입력을 하고 올바르게 적을시 회원가입완료
					    	  틀릴때 다시 입력창 출현... 그리고 3번 틀릴때는*/
					    }
			    }	    
				
					 }
			}
		});
		
		
		exitBtn.addActionListener(new ActionListener() {//종료 버튼 이벤트
			public void actionPerformed(ActionEvent e) {//
				//취소 다이얼로그 생성
				int result = JOptionPane.showConfirmDialog(null, 
						"프로그램을 종료 하시겠습니까?", "Exit", JOptionPane.YES_NO_OPTION);
				
				// 사용자가 선택한 버튼에 따라 문자열을 텍스트필드 창에 출력
				if(result == JOptionPane.CLOSED_OPTION)
					tf.setText("Just Closed Out");
				else if(result == JOptionPane.YES_OPTION) { //yes 누를시
			
					dispose();// 프로그램 종료
				}
				else
					tf.setText(" ");
			}
		});
		
		
		

			
		
		
		messageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//경고창 이벤트
				// 메시지 다이얼로그 생성					
				JOptionPane.showMessageDialog(null, "도중에 프로그램 종료 시 저장되지 않을 수 있습니다", "Help", JOptionPane.ERROR_MESSAGE); 
			}
		});
	}
}




	public static void main(String args[]) {
		new NewUser(); //객체 생성시 생성자 호출
	}

}

/*  개발자: 이찬휘 */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
