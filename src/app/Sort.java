package app;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Sort extends JInternalFrame {
	private static final long serialVersionUID = -5824032554481874133L;
	
	
	public Sort() {
		/* 프레임 세팅 */
		//-----------------------------------------------------------------------
		setTitle("정렬 프로그램");
		setSize(397, 370);
		setResizable(false);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		//-----------------------------------------------------------------------
		
		/* 컨텐트팬 */
		//------------------------------------------------------------
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));
		//------------------------------------------------------------
		
		
		/* 제목 라벨 */
		//------------------------------------------------------------
		JLabel lbl1 = new JLabel("정렬할 문장을 입력하세요.");
		lbl1.setForeground(Color.BLACK);
		lbl1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		c.add(lbl1);
		//------------------------------------------------------------
		
		
		/* 텍스트 입력 창*/
		//------------------------------------------------------------
		JTextArea ta1 = new JTextArea(13,30);
		ta1.setFont(new Font("굴림", Font.PLAIN, 15));
		c.add(new JScrollPane(ta1));
		//------------------------------------------------------------
		
		
		/* 오름정렬 버튼 */
		//-----------------------------------------------------------------------
		JButton btn1 = new JButton("오름차순 정렬");
		btn1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = ta1.getText();
				int rows = ta1.getLineCount();
				StringTokenizer tokens = new StringTokenizer(input, "\n");
				
				String array_tokens[] = new String[rows];
				
				for (int i=0; i<array_tokens.length; i++)
					array_tokens[i] = "";
				
				for(int i=0; tokens.hasMoreTokens(); i++)
					array_tokens[i] = tokens.nextToken();
				
				Arrays.sort(array_tokens);
				
				ta1.setText("");
				for(int i=0; i<array_tokens.length; i++) {
					ta1.append(array_tokens[i]);
					
					if (i != array_tokens.length-1)
						ta1.append("\n");
				}
			}
		});
		c.add(btn1);
		//-----------------------------------------------------------------------
		
		
		/* 내림정렬 버튼 */
		//-----------------------------------------------------------------------
		JButton btn2 = new JButton("내림차순 정렬");
		btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = ta1.getText();
				int rows = ta1.getLineCount();
				StringTokenizer tokens = new StringTokenizer(input, "\n");
				
				String array_tokens[] = new String[rows];
				
				for (int i=0; i<array_tokens.length; i++)
					array_tokens[i] = "";
				
				for(int i=0; tokens.hasMoreTokens(); i++)
					array_tokens[i] = tokens.nextToken();
				
				Arrays.sort(array_tokens);
				
				ta1.setText("");
				for(int i=array_tokens.length-1; i>=0; i--) {
					ta1.append(array_tokens[i]);
					
					if (i != 0)
					   ta1.append("\n");
				}
			}
		});
		c.add(btn2);
		//-----------------------------------------------------------------------
		
		
		/* 텍스트 창 지우는 버튼 */
		//------------------------------------------------------------
		JButton btnReset = new JButton("지우기");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ta1.setText("");
			}
		});
		btnReset.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		getContentPane().add(btnReset);
		//------------------------------------------------------------
		
		
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);  // 창 종료시 프로그램까지 같이 종료
		setVisible(true); // 창 표시여부
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Sort();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

/*  개발자: Lulin Pollux, 김태욱 */
/*  MIT License
	해당 소스코드 (.java)는 MIT License에 따라 이용할 수 있습니다. */
