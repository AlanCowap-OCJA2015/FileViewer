import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private File codeFile;
	private JTextArea textField;
	private JFileChooser chooser;
	private int returnVal;
	private JButton btnLoadFile;
	private Scanner scan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 809);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		textField = new JTextArea();
		textField.setEditable(false);
		textField.setDropMode(DropMode.INSERT);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, 617, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, 662, SpringLayout.WEST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnLoadFile = new JButton("Load file");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
		sl_contentPane.putConstraint(SpringLayout.WEST, btnLoadFile, 287, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnLoadFile, -10, SpringLayout.SOUTH, contentPane);
		btnLoadFile.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLoadFile.setHorizontalAlignment(SwingConstants.TRAILING);
		contentPane.add(btnLoadFile);
	}
	
	public void loadFile(){
		chooser = new JFileChooser();
		returnVal = chooser.showOpenDialog(btnLoadFile);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			codeFile = chooser.getSelectedFile();
			try {
				scan = new Scanner(new FileReader(codeFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String currentLine = "";
			if(!scan.equals(null)){
				while(scan.hasNextLine()){
					currentLine += scan.nextLine().toString() + "\n";
				}
			}
			
			textField.setText(currentLine);
		}
	}
}
