* GUI for the code highlighter app


import java.awt.Color;


public class Window {

	private JFrame frmCodeHighlighter;
	public static final JEditorPane textArea = new JEditorPane();
	public static JFrame window;
//	public static Window window;
	//TODO make textarea visible for other classes
	private JComboBox comboBox;
	private JButton btnKeywordFolder;
	private JButton btnLoadCode;
	private JLabel lblTheme = new JLabel("Theme");


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
		frmCodeHighlighter.getContentPane().setBackground(Color.WHITE);
		frmCodeHighlighter.setTitle("Code Highlighter");
		frmCodeHighlighter.setBounds(100, 100, 529, 550);
		frmCodeHighlighter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCodeHighlighter.getContentPane().setLayout(null);
		
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 493, 426);
		frmCodeHighlighter.getContentPane().add(textArea);
		
		btnLoadCode = new JButton("Load Code");
		btnLoadCode.setBackground(Color.WHITE);
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
		btnLoadCode.setBounds(251, 448, 230, 23);
		frmCodeHighlighter.getContentPane().add(btnLoadCode);
		
		btnKeywordFolder = new JButton("Keyword Folder");
		btnKeywordFolder.setBackground(Color.WHITE);
		btnKeywordFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Logic.locateKeywordsFolder();
			}
		});
		btnKeywordFolder.setBounds(10, 448, 230, 23);
		frmCodeHighlighter.getContentPane().add(btnKeywordFolder);
		
		
		lblTheme.setBounds(19, 485, 46, 14);
		frmCodeHighlighter.getContentPane().add(lblTheme);
		
	
	
		String[] themes = {"Light","Dark"};
		
		comboBox = new JComboBox(themes);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex()==1){
					btnKeywordFolder.setBackground(Color.BLACK);
					btnKeywordFolder.setForeground(Color.WHITE);
					btnLoadCode.setBackground(Color.BLACK);
					btnLoadCode.setForeground(Color.WHITE);
					textArea.setForeground(Color.WHITE);
					textArea.setBackground(Color.DARK_GRAY);
					frmCodeHighlighter.getContentPane().setBackground(Color.BLACK);
					lblTheme.setForeground(Color.WHITE);
					comboBox.setBackground(Color.BLACK);
					comboBox.setForeground(Color.white);
					
					
					
				}else if(comboBox.getSelectedIndex()==0){
					btnKeywordFolder.setBackground(Color.WHITE);
					btnKeywordFolder.setForeground(Color.BLACK);
					btnLoadCode.setBackground(Color.WHITE);
					btnLoadCode.setForeground(Color.BLACK);
					textArea.setForeground(Color.BLACK);
					textArea.setBackground(Color.WHITE);
					frmCodeHighlighter.getContentPane().setBackground(Color.WHITE);
					lblTheme.setForeground(Color.black);
					comboBox.setBackground(Color.white);
					comboBox.setForeground(Color.black);
				}
			}
		});
		comboBox.setBounds(75, 482, 80, 20);
		frmCodeHighlighter.getContentPane().add(comboBox);
		

		
	
	
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
