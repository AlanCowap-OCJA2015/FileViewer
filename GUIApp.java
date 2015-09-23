import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Highlighter;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class GUIApp extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea txtEditor;
	private JMenuBar mnuEditor;
	private JMenu mnFile;
	private JMenu mnLanguage;
	private JMenu mnTheme;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmJava;
	private JMenuItem mntmC;
	private JMenuItem mntmInvert;
	private JMenuItem mntmCustomise;
	private Color defaultColour;
	private FileLoaders load = new FileLoaders();
	private GDKHighlighter highlight = new GDKHighlighter();
	String strLanguage;
	private JMenuItem mntmEditable;
	private char[] adminPassword = {'p', 'a','s', 's', 'w', 'o', 'r', 'd'};
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
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setTitle("GDK IDE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 528);
		defaultColour = getBackground();
		mnuEditor = new JMenuBar();
		setJMenuBar(mnuEditor);
		
		mnFile = new JMenu("File");
		mnuEditor.add(mnFile);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load.read("", txtEditor);
				if(strLanguage != null){
					highlight(strLanguage);
				}
			}
		});
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load.save(txtEditor.getText(), "");
			}
		});
		mnFile.add(mntmSave);
		
		mntmEditable = new JMenuItem("Editable");
		mntmEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mntmEditable.getText().equals("Editable")){
					JPanel panel = new JPanel();
					JLabel label = new JLabel("Enter password:");
					JPasswordField pass = new JPasswordField(10);
					panel.add(label);
					panel.add(pass);
					String[] options = new String[]{"OK", "Cancel"};
					int option = JOptionPane.showOptionDialog(null, panel, "The title",
					                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					                         null, options, options[1]);
					if(option == 0) // pressing OK button
					{
					    if(isPassword(pass.getPassword())){
					    	txtEditor.setEditable(true);
					    	mntmEditable.setText("Uneditable");
					    }
					}		
				}else{
					mntmEditable.setText("Editable");
					txtEditor.setEditable(false);
				}
					
			}
		});
		mnFile.add(mntmEditable);
		
		mnLanguage = new JMenu("Language");
		mnuEditor.add(mnLanguage);
		
		mntmJava = new JMenuItem("Java");
		mntmJava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				strLanguage = "Java";
				if(txtEditor.getText().length() != 0){
					highlight(strLanguage);
				}
			}
		});
		mnLanguage.add(mntmJava);
		
		mntmC = new JMenuItem("C#");
		mntmC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strLanguage = "CSharp";
				if(txtEditor.getText().length() != 0){
					highlight(strLanguage);
				}
			}
		});
		mnLanguage.add(mntmC);
		
		mnTheme = new JMenu("Theme");
		mnuEditor.add(mnTheme);
		
		mntmInvert = new JMenuItem("Invert");
		mntmInvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(getBackground() != Color.DARK_GRAY){
					highlight.setHighLightColor(Color.CYAN);
					highlight.setHighlightDarkness();
					setBackgroundColour(Color.DARK_GRAY);
					txtEditor.setBackground(Color.DARK_GRAY);
					txtEditor.setForeground(Color.LIGHT_GRAY);
					setMenuColour(Color.DARK_GRAY);
					setMenuFontColour(Color.LIGHT_GRAY);
					setFontColour(Color.LIGHT_GRAY);
				}else{
					highlight.setHighLightColor(Color.ORANGE);
					setBackgroundColour(defaultColour);
					txtEditor.setBackground(Color.WHITE);
					txtEditor.setForeground(Color.BLACK);
					setMenuColour(defaultColour);
					setMenuFontColour(Color.BLACK);
					setFontColour(Color.BLACK);
				}
			}
		});
		mnTheme.add(mntmInvert);
		
		mntmCustomise = new JMenuItem("Customise");
		mnTheme.add(mntmCustomise);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 240, 240));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
		);
		
		 txtEditor = new JTextArea();
		 txtEditor.setEditable(false);
		 txtEditor.addKeyListener(new KeyAdapter() {
		 	@Override
		 	public void keyTyped(KeyEvent arg0) {
		 		highlight(strLanguage);
		 	}
		 });
		 txtEditor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(txtEditor);
		contentPane.setLayout(gl_contentPane);
	}
	
	private boolean isPassword(char[] userPassword){
		for(int i = 0; i < userPassword.length; ++i){
			if(userPassword[i] != adminPassword[i]){
				JOptionPane.showMessageDialog(null, "Incorrect Password");
				return false;
			}
		}
		return true;
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
	
	private void setBackgroundColour(Color newColour){
		setBackground(newColour);
		contentPane.setBackground(newColour);
		scrollPane.setBackground(newColour);
		mnuEditor.setBackground(newColour);
		//txtEditor.setBackground(newColour);
	}
	
	private void setMenuColour(Color newColour){
		mnFile.setBackground(newColour);
		mnLanguage.setBackground(newColour);
		mnTheme.setBackground(newColour);
		mntmC.setBackground(newColour);
		mntmCustomise.setBackground(newColour);
		mntmInvert.setBackground(newColour);
		mntmJava.setBackground(newColour);
		mntmOpen.setBackground(newColour);
		mntmSave.setBackground(newColour);
	}
	
	private void setMenuFontColour(Color newColour){
		mnFile.setForeground(newColour);
		mnLanguage.setForeground(newColour);
		mnTheme.setForeground(newColour);
		mntmC.setForeground(newColour);
		mntmCustomise.setForeground(newColour);
		mntmInvert.setForeground(newColour);
		mntmJava.setForeground(newColour);
		mntmOpen.setForeground(newColour);
		mntmSave.setForeground(newColour);
		
	}
	
	private void setFontColour(Color newColour){
		//txtEditor.setForeground(newColour);
		mnFile.setForeground(newColour);
		mnLanguage.setForeground(newColour);
		mnTheme.setForeground(newColour);
	}
	
}
