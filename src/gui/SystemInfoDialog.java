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
	
	/* �� Ŭ������ ���� ������ �� �ְ� ���ִ� �޼ҵ� */
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
		/* Dialog ���� */
		//-----------------------------------------------------------
		setTitle("�ý��� ����");
		setSize(500, 640);
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		//-----------------------------------------------------------
		
		
		/* �޴� ��� */
		//----------------------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("����");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("����");
		mntmSave.addActionListener(new MenuActionListener());
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("â �ݱ�");
		mntmExit.addActionListener(new MenuActionListener());
		mnFile.add(mntmExit);
		//---------------------------------------------------------
		
		
		/* ���̺� ��� */
		//----------------------------------------------------------------------------
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);
		
		String header[] = {"�̸�", "��"};
		
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
	
	/* �̺�Ʈ Ŭ���� ���*/
	//---------------------------------------------------------------------
	private class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String command = arg0.getActionCommand();
			
			switch (command) {
			
			case "����":
				FileDialog dialog = new FileDialog(MainFrame.frame, "�ٸ� �̸����� ����", FileDialog.SAVE);
				dialog.setFile("*.txt");
				dialog.setVisible(true);
				
				//FileDialog�� �׳� �� ���
				if(dialog.getFile() == null)
					break;
				// ���� Ȯ���� �Է����� �ʾ��� ��� Ȯ���� �߰� - ���õ� ���� ���� ���Ϳ� ���� �б��ϵ��� �����ϸ� �� 
		        if(!dialog.getFile().endsWith(".txt") && !dialog.getFile().endsWith(".TXT")) {
		            String fileName = dialog.getFile();
		        	fileName += ".txt";
		        	dialog.setFile(fileName);
		        }
				
				try {
					FileWriter fileW = new FileWriter(dialog.getDirectory() + dialog.getFile());
					for (int i=0; i<value.length; i++) {
						fileW.write(value[i][0]);
						fileW.write("\t\t\t");		//���̺� �� ������ ���� �� ����
						fileW.write(value[i][1]);
						fileW.write("\r\n");	// ���� ���� ���� �ٷ� �Ѿ�� ���� ����
					}
					fileW.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			case "â �ݱ�":
				dispose();
				break;
			}
		}
	}
	//---------------------------------------------------------------------
}
