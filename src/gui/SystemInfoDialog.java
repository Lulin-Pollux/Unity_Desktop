package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

class SystemInfoDialog extends JDialog {
	private static final long serialVersionUID = 7928252656870559242L;
	
	private JPanel contentPanel = new JPanel();
	private static String value[][] = new String[58][2];
	
	/* 이 클래스를 따로 실행할 수 있게 해주는 메소드 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SystemInfoDialog();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	SystemInfoDialog() {
		/* Dialog 세팅 */
		//-----------------------------------------------------------
		setTitle("시스템 정보");
		setSize(500, 640);
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		//-----------------------------------------------------------
		
		
		/* 메뉴 목록 */
		//----------------------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("파일");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("저장");
		mntmSave.addActionListener(new MenuActionListener());
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("창 닫기");
		mntmExit.addActionListener(new MenuActionListener());
		mnFile.add(mntmExit);
		//---------------------------------------------------------
		
		
		/* 테이블 목록 */
		//----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		String header[] = {"이름", "값"};
		
		Properties sysprops = System.getProperties();
		int i=0;
	    for (Enumeration<?> e = sysprops.propertyNames(); e.hasMoreElements();) {
	        value[i][0] = (String) e.nextElement();
	        value[i][1] = sysprops.getProperty(value[i][0]);
	        i++;
	    }
	    for ( ;i<value.length; i++) {
	    	value[i][0] = "";
	    	value[i][1] = "";
	    }
	    
		JTable table = new JTable(value, header);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(20);
		table.setRowSelectionAllowed(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		scrollPane.setViewportView(table);
		//----------------------------------------------------------------------------
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/* 이벤트 클래스 목록*/
	//---------------------------------------------------------------------
	private class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String command = arg0.getActionCommand();
			
			switch (command) {
			
			case "저장":
				FileDialog dialog = new FileDialog(MainFrame.frame, "다른 이름으로 저장", FileDialog.SAVE);
				dialog.setFile("*.txt");
				dialog.setVisible(true);
				
				//FileDialog를 그냥 끈 경우
				if(dialog.getFile() == null)
					break;
				// 파일 확장자 입력하지 않았을 경우 확장자 추가 - 선택된 파일 유형 필터에 따라 분기하도록 개발하면 됨 
		        if(!dialog.getFile().endsWith(".txt") && !dialog.getFile().endsWith(".TXT")) {
		            String fileName = dialog.getFile();
		        	fileName += ".txt";
		        	dialog.setFile(fileName);
		        }
				
				try {
					FileWriter fileW = new FileWriter(dialog.getDirectory() + dialog.getFile());
					for (int i=0; i<value.length; i++) {
						fileW.write(value[i][0]);
						fileW.write("\t\t\t");		//테이블 열 구분을 위해 탭 해줌
						fileW.write(value[i][1]);
						fileW.write("\r\n");	// 라인 끝에 다음 줄로 넘어가는 문자 삽입
					}
					fileW.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			case "창 닫기":
				dispose();
				break;
			}
		}
	}
	//---------------------------------------------------------------------
}
