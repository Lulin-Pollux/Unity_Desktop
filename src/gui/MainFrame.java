package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.Calculator;
import app.ExternalAppExecute;
import terminal.Shell;
import thread.MusicPlayerThread;
import thread.ShellThread;
import thread.TimerThread;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/* 멤버 변수 목록 */
	//------------------------------------------------------
	public static MainFrame frame;
	private JPanel contentPane;
	static ImportWallpaper desktop_panel;
	private JDesktopPane desktopPane;
	private JPanel appList_panel;
	public static JLabel lblDate;
	
	public static boolean console_used = false;	//콘솔 사용여부 저장
	public static boolean shell_run = false;		//shell 실행여부 저장
	public static boolean musicPlayer_run = false;	//Music Player 실행여부 저장
	private static String error_message;			//에러 메시지 저장용
	//------------------------------------------------------
	
	
	/* GUI 구성 메소드 목록 */
	//----------------------------------------------------------
	
	//메뉴 생성
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnView = new JMenu("보기");
		menuBar.add(mnView);
		
		JMenuItem mntmResolution = new JMenuItem("해상도 변경");
		mnView.add(mntmResolution);
		
		JMenuItem mntmWallpaper = new JMenuItem("배경사진 변경");
		mnView.add(mntmWallpaper);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("끝내기");
		mnView.add(mntmExit);
		
		JMenu mnInfo = new JMenu("정보");
		menuBar.add(mnInfo);
		
		JMenuItem mntm_ProgramInfo = new JMenuItem("Unity Desktop 정보");
		mnInfo.add(mntm_ProgramInfo);
		
		//각 메뉴 아이템에 리스너 부착
		MenuActionListener listener = new MenuActionListener();	//Action 리스터 생성 후 listener에 링크
		mntmResolution.addActionListener(listener);
		mntmExit.addActionListener(listener);
		mntm_ProgramInfo.addActionListener(listener);
		mntmWallpaper.addActionListener(listener);
	}
	
	//작업 표시줄 생성
	private void createTaskBar() {
		JPanel 작업_표시줄 = new JPanel();
		작업_표시줄.setBackground(new Color(10, 18, 26));
		작업_표시줄.setBorder(new EmptyBorder(1, 2, 2, 2));
		contentPane.add(작업_표시줄, BorderLayout.SOUTH);
		
		JButton btnSystem_info = new JButton("시스템 정보");
		btnSystem_info.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btnSystem_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SystemInfoDialog();
			}
		});
		작업_표시줄.setLayout(new BoxLayout(작업_표시줄, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		작업_표시줄.add(horizontalStrut);
		작업_표시줄.add(btnSystem_info);
		
		Component glue = Box.createGlue();
		작업_표시줄.add(glue);
		
		lblDate = new JLabel("날짜");
		TimerThread th = new TimerThread();
		th.start();
		lblDate.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblDate.setForeground(Color.WHITE);
		작업_표시줄.add(lblDate);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		작업_표시줄.add(horizontalStrut_1);
	}
	
	//앱 리스트 생성
	private void createAppList() {
		Component verticalStrut_1 = Box.createVerticalStrut(7);
		appList_panel.add(verticalStrut_1);
		
		JLabel lblCalculator = new JLabel("계산기");
		lblCalculator.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//더블클릭 처리
				if (e.getClickCount() == 2) {
					Calculator calc = new Calculator();
					desktopPane.add(calc);
					calc.show();
				}
			}
		});
		lblCalculator.setForeground(Color.WHITE);
		lblCalculator.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblCalculator.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCalculator.setIconTextGap(7);
		lblCalculator.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalculator.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCalculator.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon img1 = new ImageIcon(getClass().getClassLoader().getResource("calculator.png"));
		Image changedimg = img1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblCalculator.setIcon(new ImageIcon(changedimg));
		appList_panel.add(lblCalculator);
		
		Component verticalStrut_2 = Box.createVerticalStrut(15);
		appList_panel.add(verticalStrut_2);
		
		JLabel lblNewUser = new JLabel("계정 만들기");
		lblNewUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//더블클릭 처리
				if (arg0.getClickCount() == 2) {
					//앱 실행 코드 위치해야 함
				}
			}
		});
		lblNewUser.setForeground(Color.WHITE);
		lblNewUser.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblNewUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewUser.setIconTextGap(5);
		lblNewUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUser.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon img2 = new ImageIcon(getClass().getClassLoader().getResource("user.png"));
		Image changedimg2 = img2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblNewUser.setIcon(new ImageIcon(changedimg2));
		appList_panel.add(lblNewUser);
		
		Component verticalStrut_3 = Box.createVerticalStrut(15);
		appList_panel.add(verticalStrut_3);
		
		JLabel lblExternalApp = new JLabel("<html> 외부 앱<br> 실행기 </html>");
		lblExternalApp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//더블클릭 처리
				if (arg0.getClickCount() == 2) {
					ExternalAppExecute exec = new ExternalAppExecute();
					desktopPane.add(exec);
					exec.show();
				}
			}
		});
		lblExternalApp.setForeground(Color.WHITE);
		lblExternalApp.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblExternalApp.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblExternalApp.setIconTextGap(3);
		lblExternalApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblExternalApp.setHorizontalTextPosition(SwingConstants.CENTER);
		lblExternalApp.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon img3 = new ImageIcon(getClass().getClassLoader().getResource("external_icon.png"));
		Image changedimg3 = img3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblExternalApp.setIcon(new ImageIcon(changedimg3));
		appList_panel.add(lblExternalApp);
		
		Component verticalStrut_4 = Box.createVerticalStrut(15);
		appList_panel.add(verticalStrut_4);
		
		JLabel lblMusicPlayer = new JLabel("<html> &nbsp&nbsp음악<br>플레이어</html>");
		lblMusicPlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//더블클릭 처리
				if (e.getClickCount() == 2) {
					
					//오류발생 경고창 표시 목록
					//-------------------------------------------------------------------------------------------
					if (musicPlayer_run == true) {
						error_message = "이미 " + lblMusicPlayer.getText() + "이(가) 실행되고 있습니다.";
						JOptionPane.showMessageDialog(null, error_message, "오류", JOptionPane.ERROR_MESSAGE);
					}
					else if (console_used == true) {
						error_message = "다른 프로그램에서 콘솔을 점유 중입니다.";
						JOptionPane.showMessageDialog(null, error_message, "오류", JOptionPane.ERROR_MESSAGE);
					}
					//-------------------------------------------------------------------------------------------
					
					//앱 실행 코드
					if (musicPlayer_run == false && console_used == false) {
						String message = "음악 플레이어는 CUI 애플리케이션입니다. \n"
										+ "운영체제의 cmd에서 동작합니다.";
						JOptionPane.showMessageDialog(null, message, "알림", JOptionPane.INFORMATION_MESSAGE);
						MusicPlayerThread player = new MusicPlayerThread();	//음악 플레이어 스레드 생성
						player.start();		//플레이어 시작
						console_used = true;	//콘솔 점유 중 표시
						musicPlayer_run = true;	//음악 플레이어 실행 중 표시
					}
				}
			}
		});
		lblMusicPlayer.setForeground(Color.WHITE);
		lblMusicPlayer.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblMusicPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMusicPlayer.setIconTextGap(7);
		lblMusicPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicPlayer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMusicPlayer.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon img4 = new ImageIcon(getClass().getClassLoader().getResource("music_player.png"));
		Image changedimg4 = img4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblMusicPlayer.setIcon(new ImageIcon(changedimg4));
		appList_panel.add(lblMusicPlayer);
		
		Component verticalStrut_5 = Box.createVerticalStrut(15);
		appList_panel.add(verticalStrut_5);
		
		JLabel lblSort = new JLabel("<html>정렬<br> 프로그램</html>");
		lblSort.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//더블클릭 처리
				if (arg0.getClickCount() == 2) {
					//앱 실행 코드 위치해야 함
				}
			}
		});
		lblSort.setForeground(Color.WHITE);
		lblSort.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblSort.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSort.setIconTextGap(5);
		lblSort.setHorizontalAlignment(SwingConstants.CENTER);
		lblSort.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSort.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon img5 = new ImageIcon(getClass().getClassLoader().getResource("sort.png"));
		Image changedimg5 = img5.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblSort.setIcon(new ImageIcon(changedimg5));
		appList_panel.add(lblSort);
		
		Component verticalStrut_6 = Box.createVerticalStrut(15);
		appList_panel.add(verticalStrut_6);
		
		JLabel lblShell = new JLabel("Shell");
		lblShell.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//더블클릭 처리
				if (e.getClickCount() == 2) {
					
					//오류발생 표시 목록
					//-------------------------------------------------------------------------------------------
					if (shell_run == true) {
						error_message = "이미 " + lblShell.getText() + "이(가) 실행되고 있습니다.";
						JOptionPane.showMessageDialog(null, error_message, "오류", JOptionPane.ERROR_MESSAGE);
					}
					else if (console_used == true) {
						error_message = "다른 프로그램에서 콘솔을 점유 중입니다.";
						JOptionPane.showMessageDialog(null, error_message, "오류", JOptionPane.ERROR_MESSAGE);
					}
					//-------------------------------------------------------------------------------------------
					
					//앱 실행 코드
					if ((shell_run == false) && (console_used == false)) {
						String message = "Shell은 운영체제의 cmd에서 실행됩니다. \n"
									+ "멀티스레드로 작동되므로 Shell과 Desktop을 동시에 사용 가능합니다.";
						JOptionPane.showMessageDialog(null, message, "알림", JOptionPane.INFORMATION_MESSAGE);
						
						ShellThread shell = new ShellThread();	//shell 스레드 생성
						shell.start();		//shell 시작
						console_used = true;	//콘솔 점유 중 표시
						shell_run = true;	//shell 실행 중 표시
					}
				}
			}
		});
		lblShell.setForeground(Color.WHITE);
		lblShell.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblShell.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblShell.setIconTextGap(4);
		lblShell.setHorizontalAlignment(SwingConstants.CENTER);
		lblShell.setHorizontalTextPosition(SwingConstants.CENTER);
		lblShell.setVerticalTextPosition(SwingConstants.BOTTOM);
		ImageIcon img6 = new ImageIcon(getClass().getClassLoader().getResource("shell.png"));
		Image changedimg6 = img6.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblShell.setIcon(new ImageIcon(changedimg6));
		appList_panel.add(lblShell);
	}
	//----------------------------------------------------------
	
	
	/* 프레임 생성 */
	public MainFrame() {
		/* 프레임 세팅 */
		//----------------------------------------------------------------
		setTitle("Unity Desktop");	//제목 지정
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("desktop.png")));//아이콘 설정
		setSize(1280, 768);			//프레임 크기 지정
		setMinimumSize(new Dimension(800, 480));	//최소 프레임 크기 지정
		setLocationRelativeTo(null);	//화면 중앙에 프레임 띄우기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//닫기 버튼을 누르면 프로그램 종료
		//----------------------------------------------------------------
		
		
		/* 메뉴 생성 */
		createMenu();
		
		
		/* 컨텐트팬 생성 */
		//----------------------------------------------------------------
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//----------------------------------------------------------------
		
		
		/* Center 지역 패널 생성 */
		//----------------------------------------------------------------
		desktop_panel = new ImportWallpaper();
		contentPane.add(desktop_panel, BorderLayout.CENTER);
		desktop_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		desktop_panel.add(desktopPane);
		//----------------------------------------------------------------
		
		
		/* South 지역 패널 생성 */
		createTaskBar();
		
		
		/* West 지역 패널 생성 */
		//----------------------------------------------------------------
		appList_panel = new JPanel();
		appList_panel.setBackground(new Color(40, 40, 40));
		contentPane.add(appList_panel, BorderLayout.WEST);
		appList_panel.setLayout(new BoxLayout(appList_panel, BoxLayout.Y_AXIS));
		
		createAppList();	//앱 리스트 생성
		//----------------------------------------------------------------
	}
	//----------------------------------------------------------
	
	
	/* 프로그램 시작 */
	public static void main(String[] args) {
		// main 매개변수 확인
		//----------------------------------------------------
		for(int i=0; i<args.length; i++) {
			switch (args[i]) {
			
			case "-stillalive":
				System.out.println("Still alive!!");
				System.out.println("아쉽지만 Still alive 재생기는 없습니다.");
				System.out.println();
				return;
			
			case "-shell": case "-terminal":
				Shell.shell();
				return;
			}
        }
		//----------------------------------------------------
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
