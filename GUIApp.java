import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIApp extends JFrame {

	private JPanel contentPane;
	private JTextArea txtEditor;
	private FileLoaders load = new FileLoaders();
	private GDKHighlighter highlight = new GDKHighlighter();
	String strLanguage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApp frame = new GUIApp();
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
	public GUIApp() {
		setTitle("GDK IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 528);
		
		JMenuBar mnuEditor = new JMenuBar();
		setJMenuBar(mnuEditor);
		
		JMenu mnFile = new JMenu("File");
		mnuEditor.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtEditor.setText(load.read(""));
				if(strLanguage != null){
					highlight(strLanguage);
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnLanguage = new JMenu("Language");
		mnuEditor.add(mnLanguage);
		
		JMenuItem mntmJava = new JMenuItem("Java");
		mntmJava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				highlight("Java");
				strLanguage = "Java";
			}
		});
		mnLanguage.add(mntmJava);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtEditor = new JTextArea();
		txtEditor.setEditable(false);
		txtEditor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(txtEditor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(txtEditor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void highlight(String language){
		String[] keywords = load.openLanguage(language);
		if(txtEditor.getText().length() == 0){
			return;
		}
		else{
			highlight.highlight(keywords, txtEditor);
		}
	}
}
