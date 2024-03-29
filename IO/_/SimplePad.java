public class SimplePad extends JFrame{
	private JTextArea taEditor;
	
	private JMenuBar mBar;
	private JMenu mFile;
	private JMenuItem miNew;
	private JMenuItem miOpen;
	private JMenuItem miSave;
	private JMenuItem miSaveAs;
	private JMenuItem miExit;
	
	private JFileChooser chooser;
	
	public static final int OPEN = 0;
	public static final int SAVE = 1;
	
	public File currentFile;
	public static final String TITLE = "SimplePad ver1.0";
	
	public SimplePad(){
		init();
		setDisplay();
		addListeners();
		showFrame();
	}
	private void init(){
		taEditor = new JTextArea();
		taEditor.setTabSize(4);//
		taEditor.setLineWrap(true);//
		taEditor.setWrapStyleWord(true);//
		
		mBar = new JMenuBar();
		mFile = new JMenu("File");
		miNew = new JMenuItem("New");
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		
		miSave = new JMenuItem("Save");
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		
		miSaveAs = new JMenuItem("SaveAs");
		miOpen = new JMenuItem("Open");
		
		miExit = new JMenuItem("Exit");
		miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		
		mFile.add(miNew);
		mFile.add(miOpen);
		mFile.add(miSave);
		mFile.add(miSaveAs);
		mFile.addSeparator();
		mFile.add(miExit);
		mBar.add(mFile);
		
		chooser = new JFileChooser(".");
		
	}
	private void setDisplay(){
		setJMenuBar(mBar);
		add(new JScrollPane(taEditor),BorderLayout.CENTER);
	}
	private void addListeners(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if(src == miOpen){
					File f = getFile(OPEN);
					if(f != null){
						open(f);
					}
				}else if(src == miSave){
					save();
				}else if(src == miNew){
					makeNew();
				}else if(src == miSaveAs){
					File f = getFile(SAVE);
					if(f != null){
						save(f);
					}
				}else{
					exit();
				}
				
			}
		};
		miNew.addActionListener(listener);
		miOpen.addActionListener(listener);
		miSave.addActionListener(listener);
		miSaveAs.addActionListener(listener);
		miExit.addActionListener(listener);
		
		Document doc = taEditor.getDocument();
		doc.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				documentChanged();
			}			
			@Override
			public void insertUpdate(DocumentEvent e) {
				documentChanged();
			}
			@Override
			public void changedUpdate(DocumentEvent e) { }
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we){
				exit();
			}
		});
	}
	private void documentChanged(){
		if(!isUpdated()){
			setTitle(getTitle()+"*");
		}
	}
	private void exit(){
		checkAndSave();
		int result = JOptionPane.showConfirmDialog(
			this,
			"종료하시겠습니까?",
			"종료",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		if(result == JOptionPane.YES_NO_OPTION){
			System.exit(0);
		}
	}
	private void changeTitle(){
		String newTitle = TITLE;
		if(isNew()){
			newTitle += " : noname";
		}else{
			newTitle += " : " + currentFile.getName();
		}
		setTitle(newTitle);
	}
	private void showFrame(){
		changeTitle();
		setSize(500,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	/*
		1.하는일
		2.파라미터
		3.리턴
	*/
	private File getFile(int mode){
		int choice = -1;
		if(mode == OPEN){
			choice = chooser.showOpenDialog(this);
		}else{
			choice = chooser.showSaveDialog(this);
		}
		if(choice == JFileChooser.APPROVE_OPTION){
			return chooser.getSelectedFile();
		}else{
			JOptionPane.showMessageDialog(
				this,
				"작업이 취소되었습니다.",
				"알립",
				JOptionPane.INFORMATION_MESSAGE
			);
			return null;
		}
	}
	private void open(File f){
		checkAndSave();
		FileReader fr =null;
		
		try{
			fr = new FileReader(f);
			char[] buf = new char[100];//
			int count = -1;
			StringBuffer sb = new StringBuffer();//스트링 버퍼와 비슷
			while((count = fr.read(buf)) != -1){
				sb.append(buf,0,count);//인덱스 부터 count(char 갯수)를 꺼내기
			}
			taEditor.setText(sb.toString());
			currentFile = f;
			changeTitle();
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			if(fr != null){
				try{
					fr.close();
				}catch(IOException e){}
			}
		}
	}
	private void save(File f){
		FileWriter fw = null;
		
		try{
			fw = new FileWriter(f);
			String data = taEditor.getText();
			fw.write(data);
			currentFile = f;
			changeTitle();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(fw != null){
				try{
					fw.close();
				}catch(IOException e){}
			}
		}
	}
	private void makeNew(){
		checkAndSave();
		taEditor.setText("");
		currentFile =null;
		changeTitle();
	}
	private void checkAndSave(){
		if(isUpdated()){
			int result = JOptionPane.showConfirmDialog(
				this,
				"변경된 내용이 있습니다. 저장하시겠습니까?",
				"알림",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			if(result == JOptionPane.YES_NO_OPTION){
				save();
			}
		}
	}
	private void save(){
		if(isNew()){
			File f = getFile(SAVE);
			if(f != null){
				save(f);
			}
		}else{
			save(currentFile);
		}
	}
	private boolean isNew(){
		return currentFile == null;
	}
	private boolean isUpdated(){
		return getTitle().contains("*");
	}
	private static void main(String[] args){
		new SimplePad();
	}
	
}
