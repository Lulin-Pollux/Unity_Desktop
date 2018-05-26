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

	/* ��� ���� ��� */
	//------------------------------------------------------
	public static MainFrame frame;
	private JPanel contentPane;
	static ImportWallpaper desktop_panel;
	private JDesktopPane desktopPane;
	private JPanel appList_panel;
	public static JLabel lblDate;
	
	public static boolean console_used = false;	//�ܼ� ��뿩�� ����
	public static boolean shell_run = false;		//shell ���࿩�� ����
	public static boolean musicPlayer_run = false;	//Music Player ���࿩�� ����
	private static String error_message;			//���� �޽��� �����
	//------------------------------------------------------
	
	
	/* GUI ���� �޼ҵ� ��� */
	//----------------------------------------------------------
	
	//�޴� ����
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnView = new JMenu("����");
		menuBar.add(mnView);
		
		JMenuItem mntmResolution = new JMenuItem("�ػ� ����");
		mnView.add(mntmResolution);
		
		JMenuItem mntmWallpaper = new JMenuItem("������ ����");
		mnView.add(mntmWallpaper);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("������");
		mnView.add(mntmExit);
		
		JMenu mnInfo = new JMenu("����");
		menuBar.add(mnInfo);
		
		JMenuItem mntm_ProgramInfo = new JMenuItem("Unity Desktop ����");
		mnInfo.add(mntm_ProgramInfo);
		
		//�� �޴� �����ۿ� ������ ����
		MenuActionListener listener = new MenuActionListener();	//Action ������ ���� �� listener�� ��ũ
		mntmResolution.addActionListener(listener);
		mntmExit.addActionListener(listener);
		mntm_ProgramInfo.addActionListener(listener);
		mntmWallpaper.addActionListener(listener);
	}
	
	//�۾� ǥ���� ����
	private void createTaskBar() {
		JPanel �۾�_ǥ���� = new JPanel();
		�۾�_ǥ����.setBackground(new Color(10, 18, 26));
		�۾�_ǥ����.setBorder(new EmptyBorder(1, 2, 2, 2));
		contentPane.add(�۾�_ǥ����, BorderLayout.SOUTH);
		
		JButton btnSystem_info = new JButton("�ý��� ����");
		btnSystem_info.setFont(new Font("���� ���", Font.PLAIN, 13));
		btnSystem_info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SystemInfoDialog();
			}
		});
		�۾�_ǥ����.setLayout(new BoxLayout(�۾�_ǥ����, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		�۾�_ǥ����.add(horizontalStrut);
		�۾�_ǥ����.add(btnSystem_info);
		
		Component glue = Box.createGlue();
		�۾�_ǥ����.add(glue);
		
		lblDate = new JLabel("��¥");
		TimerThread th = new TimerThread();
		th.start();
		lblDate.setFont(new Font("���� ���", Font.PLAIN, 13));
		lblDate.setForeground(Color.WHITE);
		�۾�_ǥ����.add(lblDate);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		�۾�_ǥ����.add(horizontalStrut_1);
	}
	
	//�� ����Ʈ ����
	private void createAppList() {
		Component verticalStrut_1 = Box.createVerticalStrut(7);
		appList_panel.add(verticalStrut_1);
		
		JLabel lblCalculator = new JLabel("����");
		lblCalculator.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//����Ŭ�� ó��
				if (e.getClickCount() == 2) {
					Calculator calc = new Calculator();
					desktopPane.add(calc);
					calc.show();
				}
			}
		});
		lblCalculator.setForeground(Color.WHITE);
		lblCalculator.setFont(new Font("���� ���", Font.PLAIN, 13));
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
		
		JLabel lblNewUser = new JLabel("���� �����");
		lblNewUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//����Ŭ�� ó��
				if (arg0.getClickCount() == 2) {
					//�� ���� �ڵ� ��ġ�ؾ� ��
				}
			}
		});
		lblNewUser.setForeground(Color.WHITE);
		lblNewUser.setFont(new Font("���� ���", Font.PLAIN, 13));
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
		
		JLabel lblExternalApp = new JLabel("<html> �ܺ� ��<br> ����� </html>");
		lblExternalApp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//����Ŭ�� ó��
				if (arg0.getClickCount() == 2) {
					ExternalAppExecute exec = new ExternalAppExecute();
					desktopPane.add(exec);
					exec.show();
				}
			}
		});
		lblExternalApp.setForeground(Color.WHITE);
		lblExternalApp.setFont(new Font("���� ���", Font.PLAIN, 13));
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
		
		JLabel lblMusicPlayer = new JLabel("<html> &nbsp&nbsp����<br>�÷��̾�</html>");
		lblMusicPlayer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//����Ŭ�� ó��
				if (e.getClickCount() == 2) {
					
					//�����߻� ���â ǥ�� ���
					//-------------------------------------------------------------------------------------------
					if (musicPlayer_run == true) {
						error_message = "�̹� " + lblMusicPlayer.getText() + "��(��) ����ǰ� �ֽ��ϴ�.";
						JOptionPane.showMessageDialog(null, error_message, "����", JOptionPane.ERROR_MESSAGE);
					}
					else if (console_used == true) {
						error_message = "�ٸ� ���α׷����� �ܼ��� ���� ���Դϴ�.";
						JOptionPane.showMessageDialog(null, error_message, "����", JOptionPane.ERROR_MESSAGE);
					}
					//-------------------------------------------------------------------------------------------
					
					//�� ���� �ڵ�
					if (musicPlayer_run == false && console_used == false) {
						String message = "���� �÷��̾�� CUI ���ø����̼��Դϴ�. \n"
										+ "�ü���� cmd���� �����մϴ�.";
						JOptionPane.showMessageDialog(null, message, "�˸�", JOptionPane.INFORMATION_MESSAGE);
						MusicPlayerThread player = new MusicPlayerThread();	//���� �÷��̾� ������ ����
						player.start();		//�÷��̾� ����
						console_used = true;	//�ܼ� ���� �� ǥ��
						musicPlayer_run = true;	//���� �÷��̾� ���� �� ǥ��
					}
				}
			}
		});
		lblMusicPlayer.setForeground(Color.WHITE);
		lblMusicPlayer.setFont(new Font("���� ���", Font.PLAIN, 13));
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
		
		JLabel lblSort = new JLabel("<html>����<br> ���α׷�</html>");
		lblSort.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//����Ŭ�� ó��
				if (arg0.getClickCount() == 2) {
					//�� ���� �ڵ� ��ġ�ؾ� ��
				}
			}
		});
		lblSort.setForeground(Color.WHITE);
		lblSort.setFont(new Font("���� ���", Font.PLAIN, 13));
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
				//����Ŭ�� ó��
				if (e.getClickCount() == 2) {
					
					//�����߻� ǥ�� ���
					//-------------------------------------------------------------------------------------------
					if (shell_run == true) {
						error_message = "�̹� " + lblShell.getText() + "��(��) ����ǰ� �ֽ��ϴ�.";
						JOptionPane.showMessageDialog(null, error_message, "����", JOptionPane.ERROR_MESSAGE);
					}
					else if (console_used == true) {
						error_message = "�ٸ� ���α׷����� �ܼ��� ���� ���Դϴ�.";
						JOptionPane.showMessageDialog(null, error_message, "����", JOptionPane.ERROR_MESSAGE);
					}
					//-------------------------------------------------------------------------------------------
					
					//�� ���� �ڵ�
					if ((shell_run == false) && (console_used == false)) {
						String message = "Shell�� �ü���� cmd���� ����˴ϴ�. \n"
									+ "��Ƽ������� �۵��ǹǷ� Shell�� Desktop�� ���ÿ� ��� �����մϴ�.";
						JOptionPane.showMessageDialog(null, message, "�˸�", JOptionPane.INFORMATION_MESSAGE);
						
						ShellThread shell = new ShellThread();	//shell ������ ����
						shell.start();		//shell ����
						console_used = true;	//�ܼ� ���� �� ǥ��
						shell_run = true;	//shell ���� �� ǥ��
					}
				}
			}
		});
		lblShell.setForeground(Color.WHITE);
		lblShell.setFont(new Font("���� ���", Font.PLAIN, 13));
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
	
	
	/* ������ ���� */
	public MainFrame() {
		/* ������ ���� */
		//----------------------------------------------------------------
		setTitle("Unity Desktop");	//���� ����
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("desktop.png")));//������ ����
		setSize(1280, 768);			//������ ũ�� ����
		setMinimumSize(new Dimension(800, 480));	//�ּ� ������ ũ�� ����
		setLocationRelativeTo(null);	//ȭ�� �߾ӿ� ������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�ݱ� ��ư�� ������ ���α׷� ����
		//----------------------------------------------------------------
		
		
		/* �޴� ���� */
		createMenu();
		
		
		/* ����Ʈ�� ���� */
		//----------------------------------------------------------------
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//----------------------------------------------------------------
		
		
		/* Center ���� �г� ���� */
		//----------------------------------------------------------------
		desktop_panel = new ImportWallpaper();
		contentPane.add(desktop_panel, BorderLayout.CENTER);
		desktop_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		desktop_panel.add(desktopPane);
		//----------------------------------------------------------------
		
		
		/* South ���� �г� ���� */
		createTaskBar();
		
		
		/* West ���� �г� ���� */
		//----------------------------------------------------------------
		appList_panel = new JPanel();
		appList_panel.setBackground(new Color(40, 40, 40));
		contentPane.add(appList_panel, BorderLayout.WEST);
		appList_panel.setLayout(new BoxLayout(appList_panel, BoxLayout.Y_AXIS));
		
		createAppList();	//�� ����Ʈ ����
		//----------------------------------------------------------------
	}
	//----------------------------------------------------------
	
	
	/* ���α׷� ���� */
	public static void main(String[] args) {
		// main �Ű����� Ȯ��
		//----------------------------------------------------
		for(int i=0; i<args.length; i++) {
			switch (args[i]) {
			
			case "-stillalive":
				System.out.println("Still alive!!");
				System.out.println("�ƽ����� Still alive ������ �����ϴ�.");
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
