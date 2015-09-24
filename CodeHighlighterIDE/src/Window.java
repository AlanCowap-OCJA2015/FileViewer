import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;


public class Window {

	private JFrame frmCodeHighlighter;
	public static final JTextArea textArea = new JTextArea();
	public static JFrame window;
//	public static Window window;
	//TODO make textarea visible for other classes
	private JComboBox comboBox;
	private JButton btnKeywordFolder;
	private JButton btnLoadCode;
	private JLabel lblTheme = new JLabel("Theme");
	private JScrollPane scrollPane;
	private JPanel panel = new JPanel();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmCodeHighlighter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		window = frmCodeHighlighter;
		initialize();
	}
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCodeHighlighter = new JFrame();
		frmCodeHighlighter.setMinimumSize(new Dimension(500, 200));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{514, 0};
		gridBagLayout.rowHeights = new int[]{425, 34, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		frmCodeHighlighter.getContentPane().setLayout(gridBagLayout);
		
			
			scrollPane = new JScrollPane(textArea);
			scrollPane.setBackground(UIManager.getColor("window"));
			scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			scrollPane.setViewportView(textArea);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			frmCodeHighlighter.getContentPane().add(scrollPane, gbc_scrollPane);
			
			
			textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			textArea.setEditable(false);
			textArea.setBounds(10, 10, 493, 426);
		frmCodeHighlighter.setBackground(UIManager.getColor("window"));
		frmCodeHighlighter.getContentPane().setBackground(UIManager.getColor("window"));
		frmCodeHighlighter.setTitle("Code Highlighter");
		frmCodeHighlighter.setBounds(100, 100, 530, 519);
		frmCodeHighlighter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		textArea.getPreferredScrollableViewportSize();
//		textArea.getScrollableTracksViewportHeight();
//		textArea.getScrollableTracksViewportWidth();
		
	
	
		String[] themes = {"Light","Dark"};
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(UIManager.getColor("window"));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frmCodeHighlighter.getContentPane().add(panel, gbc_panel);
		
		btnKeywordFolder = new JButton("Keyword Folder");
		btnKeywordFolder.setBackground(Color.WHITE);
		btnLoadCode = new JButton("Load Code");
		btnLoadCode.setBackground(Color.WHITE);
		
		comboBox = new JComboBox(themes);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()==1){
					frmCodeHighlighter.getContentPane().setBackground(Color.GRAY);
					lblTheme.setForeground(Color.WHITE);
					btnKeywordFolder.setBackground(Color.DARK_GRAY);
					btnKeywordFolder.setForeground(Color.WHITE);
					btnLoadCode.setBackground(Color.DARK_GRAY);
					btnLoadCode.setForeground(Color.WHITE);
					textArea.setBackground(Color.GRAY);
					textArea.setForeground(Color.WHITE);
					comboBox.setBackground(Color.DARK_GRAY);
					comboBox.setForeground(Color.WHITE);
					panel.setBackground(Color.DARK_GRAY);
					panel.setForeground(Color.WHITE);
					
					
					
				}else if(comboBox.getSelectedIndex()==0){
					frmCodeHighlighter.getContentPane().setBackground(Color.WHITE);
					lblTheme.setForeground(Color.DARK_GRAY);
					btnKeywordFolder.setBackground(Color.WHITE);
					btnKeywordFolder.setForeground(Color.DARK_GRAY);
					btnLoadCode.setBackground(Color.WHITE);
					btnLoadCode.setForeground(Color.DARK_GRAY);
					textArea.setBackground(Color.WHITE);
					textArea.setForeground(Color.DARK_GRAY);
					comboBox.setBackground(Color.WHITE);
					comboBox.setForeground(Color.DARK_GRAY);
					panel.setBackground(Color.WHITE);
					panel.setForeground(Color.DARK_GRAY);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblTheme, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
					.addComponent(btnKeywordFolder)
					.addGap(18)
					.addComponent(btnLoadCode, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadCode)
						.addComponent(lblTheme)
						.addComponent(btnKeywordFolder)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		btnLoadCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Thread t = new Thread(new Runnable(){
					public void run(){
						String text = Logic.loadTextFile();						
						textArea.setText(text);
						highlightCode();
					}
				});
				
				t.start();

				
				
			}
		});
		btnKeywordFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logic.locateKeywordsFolder();
			}
		});
		frmCodeHighlighter.setVisible(true);

		
	
	
}
	
	
	private void highlightCode(){
		ArrayList<String> keywords = Logic.getKeywords();
		String text = textArea.getText();
		Highlighter hl = textArea.getHighlighter();
		
		int pos = 0;
		//int end = 0;
		//boolean notEnd = true;
		
		outer: for(String keyword : keywords){
			do{
				pos = text.indexOf(keyword, pos);
				if(pos == -1){
					continue outer;
				}
				
				if(pos == 0 && validCharacter(text.charAt(pos + keyword.length()))){
					try {
						hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(validCharacter(text.charAt(pos - 1)) && pos + keyword.length() == text.length()-1){
					try {
						hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(validCharacter(text.charAt(pos - 1)) && validCharacter(text.charAt(pos + keyword.length()))){
					try {
						hl.addHighlight(pos, pos + keyword.length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				pos++;
			}while(pos != -1);
		}
		
		//keyword in beginning of a file if position is zero - check only end
		//if keyword is in the end of file - check only start
		//if it's in the middle of a file - check start and end for letters {}[]() or space - it's a keyword
		
	}
	
	
	private boolean validCharacter(char c){
		switch(c){
		case ' ' : return true;
		case '[' : return true;
		case '{' : return true;
		case '(' : return true;
		case 9 	: return true;
		case 10 : return true;
		default : return false;
		}
	}
}
