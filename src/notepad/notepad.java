package notepad;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class notepad {

	private JFrame frmNotepad;
	
	JTextArea txt = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					notepad window = new notepad();
					window.frmNotepad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public notepad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNotepad = new JFrame();
		frmNotepad.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Desktop\\icon.png"));
		frmNotepad.setTitle("New NotePad");
		frmNotepad.setBounds(100, 100, 597, 436);
		frmNotepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frmNotepad.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(txt);
		
		JMenuBar menuBar = new JMenuBar();
		frmNotepad.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("");
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser open= new JFileChooser();
				 int choice = open.showOpenDialog(frmNotepad);
				 
				 if (choice == JFileChooser.APPROVE_OPTION)
				 {
					 try {
						Scanner sc = new Scanner(new FileReader(open.getSelectedFile().getPath()));
						
						while(sc.hasNext())
						{
							txt.append(sc.nextLine()+ "\n");
						}
						
						
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e);
					}
				 }
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save as");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser save = new JFileChooser ();
				int choice = save.showSaveDialog(frmNotepad);
				
				if(choice == JFileChooser.APPROVE_OPTION)
				{
					try {
						BufferedWriter bf = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
						bf.write(txt.getText());
						bf.close();
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		mnFile.add(mntmSave);
		
		JMenu mnFormate = new JMenu("Formate");
		menuBar.add(mnFormate);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mnHelp.add(mntmAboutUs);
	}

}
