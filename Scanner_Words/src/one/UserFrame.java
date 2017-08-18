package one;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.io.*;




public class UserFrame extends JFrame implements ActionListener,MouseListener,DocumentListener{
	
	private static final long serialVersionUID = -2584958993984933493L;

	//定义菜单项
	JMenuItem openItem = new JMenuItem("打开");
	JMenuItem saveItem = new JMenuItem("保存");
	JMenuItem saveAsItem = new JMenuItem("另存为");
	JMenuItem exitItem = new JMenuItem("退出");
	JMenuItem copyItem = new JMenuItem("复制");
	JMenuItem pasteItem = new JMenuItem("粘贴");
	JMenuItem cutItem = new JMenuItem("剪切");
	JMenuItem analysisItem = new JMenuItem("词法分析");
	JMenuItem copyRItem = new JMenuItem("复制");
	JMenuItem pasteRItem = new JMenuItem("粘贴");
	JMenuItem cutRItem = new JMenuItem("剪切");
	//定义标签
	JLabel label = new JLabel("至尊第八组");
	//定义菜单
	JMenu fileMenu = new JMenu("文件");
	JMenu editMenu = new JMenu("编辑");
	JMenu analysisMenu = new JMenu("词法分析器");
	JMenu helpMenu = new JMenu("帮助");
	
	//定义右键菜单
	JPopupMenu pop = new JPopupMenu();
	//定义菜单条
	static JMenuBar jmb = new JMenuBar(); //菜单条
	
	//定义工具栏
	JButton openBtn = new JButton(new ImageIcon("images/open.gif"));
	JButton saveBtn = new JButton(new ImageIcon("images/save.gif"));
	JButton copyBtn = new JButton(new ImageIcon("images/copy.gif"));
	JButton pasteBtn = new JButton(new ImageIcon("images/paste.gif"));
	JButton cutBtn = new JButton(new ImageIcon("images/cut.gif"));
	JButton analysisBtn = new JButton(new ImageIcon("images/do.gif"));
	
	//定义滚动条和文本域
	JTextArea text1 = new JTextArea();
	JTextArea text2 = new JTextArea();
	JTextArea text3 = new JTextArea();
	//JTextArea text0 = new JTextArea();
	
	DefaultListModel dlm = new DefaultListModel();
	JList text0 = new JList(dlm);
	
	JScrollPane scroll0 = new JScrollPane(text0);
	JScrollPane scroll1 = new JScrollPane(text1);
	JScrollPane scroll2 = new JScrollPane(text2);
	JScrollPane scroll3 = new JScrollPane(text3);
	
	//定义几个面板
	JPanel btnPanel = new JPanel();
	JPanel textPanel1 = new JPanel();
	JPanel textPanel2 = new JPanel();
	JPanel textPanel3 = new JPanel();
	
	Document doc = text1.getDocument();
	
	private Analysis analysis= new Analysis();
	
	public UserFrame(String sname){
		super(sname);
		Container cp = getContentPane();
		cp.setLayout(null);
		
		//添加菜单
		jmb.add(fileMenu);
		jmb.add(editMenu);
		jmb.add(analysisMenu);
		jmb.add(helpMenu);
		
		//添加菜单项
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(exitItem);
		
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(cutItem);
		copyItem.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.ALT_MASK));
    	pasteItem.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.ALT_MASK));
    	cutItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
    	
		analysisMenu.add(analysisItem);
		
		pop.add(copyRItem);
		pop.add(pasteRItem);
		pop.add(cutRItem);
		copyRItem.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.ALT_MASK));
    	pasteRItem.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.ALT_MASK));
    	cutRItem.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
    	
		//添加工具栏按钮
		btnPanel.setLayout(null);
		openBtn.setBounds(4, 4, 22, 22);
		saveBtn.setBounds(30, 4, 22, 22);
		copyBtn.setBounds(56, 4, 22, 22);
		pasteBtn.setBounds(82, 4, 22, 22);
		cutBtn.setBounds(108, 4, 22, 22);
		analysisBtn.setBounds(134, 4, 22, 22);
		label.setBounds(475, 4, 80, 22);
		label.setForeground(Color.MAGENTA);
		
		btnPanel.add(openBtn);
		btnPanel.add(saveBtn);
		btnPanel.add(copyBtn);
		btnPanel.add(pasteBtn);
		btnPanel.add(cutBtn);
		btnPanel.add(analysisBtn);
		btnPanel.add(label);
		
		//添加文本域
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		text2.setEditable(false);
		text3.setEditable(false);
		textPanel1.setLayout(null);
		textPanel2.setLayout(null);
		textPanel3.setLayout(null);
		scroll0.setBounds(10, 0, 25, 440);
		scroll1.setBounds(33, 0, 167, 440);
		scroll2.setBounds(10, 0, 320, 220);
		scroll3.setBounds(10, 0, 320, 189);
		textPanel1.add(scroll0);
		textPanel1.add(scroll1);
		textPanel2.add(scroll2);
		textPanel3.add(scroll3);
		
		//位置绝对定位
		btnPanel.setBounds(0, 0, 600, 30);
		textPanel1.setBounds(0, 40, 200, 480);
		textPanel2.setBounds(210, 40, 340, 240);
		textPanel3.setBounds(210, 290, 340, 230);

		//添加面板
		cp.add(btnPanel);
		cp.add(textPanel1);
		cp.add(textPanel2);
		cp.add(textPanel3);
		
		text1.addMouseListener(this);
		editMenu.addMouseListener(this);
		
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
		copyItem.addActionListener(this);
		pasteItem.addActionListener(this);
		cutItem.addActionListener(this);
		
		copyRItem.addActionListener(this);
		pasteRItem.addActionListener(this);
		cutRItem.addActionListener(this);
		
		analysisItem.addActionListener(this);
		helpMenu.addActionListener(this);
		
		openBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		copyBtn.addActionListener(this);
		pasteBtn.addActionListener(this);
		cutBtn.addActionListener(this);
		analysisBtn.addActionListener(this);
		
		doc.addDocumentListener(this);
	
	}
	
	public void doAnalysis(){
		ArrayList<Word> sourceWords = new ArrayList<Word>();
		ArrayList<ErrRow> errList = new ArrayList<ErrRow>();
		String ss,ts,str;
		Word word;
		int resultInt, result = -1;
		int errNum = 0;
		
		//第一步：从text1中获取源程序
		ss = text1.getText();
		if(ss.length() > 1){
            //第二步：预处理
			ts = analysis.preTreat(ss);
			//第三步：分解
			sourceWords = analysis.divide(ts);
			while(sourceWords.size() > 0){
				word = (Word)sourceWords.remove(0);
				str = word.getWord();
               //第四步：判断以确定调用类型
				resultInt = analysis.check(str);
				switch(resultInt){
				//第五步：调用不同判断函数
				case 1:result = analysis.checkDigit(str);break;
				case 2:result = analysis.checkChar(str);break;
				case 3:result = analysis.checkString(str);break;
				}
				//第六步：记录错误数
				if(result == 0){
					ErrRow er = new ErrRow();
					er.setErrRow(word.getRow());
					er.setErrStr(str);
					errList.add(er);
					errNum++;
					
				}
				//第七步：打印输出分析结果
				text2.append(word.getRow()+"    ");
				text2.append(str+"\t"+",\t"+result+"\n");
			}
			
			text3.append("共有"+errNum+"处错误."+"\n");
			
            while(errList.size()>0){
            	int row;
            	String errTemp;
            	ErrRow erTemp = errList.remove(0);
            	row = erTemp.getErrRow();
            	errTemp = erTemp.getErrStr();
            	text3.append("第"+row+"行："+"错误，"+errTemp+"\n");
            }
			
		}else{
			int i;
			i = JOptionPane.showConfirmDialog(this, "请输入源程序！");
			if(i != JOptionPane.YES_OPTION){
				return;
			}
		}
		
		
	}
	
	public void actionPerformed(ActionEvent ae){
		String tmp = "";
		//int rowCount = 1;
		Object source = ae.getSource();
		if(source.equals(openItem) || source.equals(openBtn)){
			//定义文件过滤器
			JFileChooser chooser1 = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
			chooser1.setFileFilter(filter);
			int returnVal1 = chooser1.showOpenDialog(this);
			if(returnVal1 == JFileChooser.APPROVE_OPTION){
				//导入文件
				try{
				   //text0.setText("");
				   text1.setText("");
				   text2.setText("");
				   text3.setText("");
                   text1.setForeground(Color.BLUE);
					FileReader fr = new FileReader(chooser1.getSelectedFile());
					BufferedReader br = new BufferedReader(fr);
					tmp = br.readLine();
					while(tmp != null){
						//text0.append(rowCount+"\n");
						text1.append(tmp + "\n");
						tmp = br.readLine();
						//rowCount++;
					}
					fr.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		else if(source.equals(saveItem) || source.equals(saveBtn) || source.equals(saveAsItem)){
			JFileChooser chooser2 = new JFileChooser();
			int returnVal2 = chooser2.showSaveDialog(this);
			String fname = "";
			if(returnVal2 == JFileChooser.APPROVE_OPTION){
				File f = chooser2.getSelectedFile();
				fname = chooser2.getName(f);  //获取文件名
				if(fname.trim().length() > 0){
					if(!fname.endsWith(".txt")){
						fname = fname.concat(".txt");
					}
					//获取文件完整路径，创建文件
					f = chooser2.getCurrentDirectory();
					f = new File(f.getPath().concat(File.separator).concat(fname));
					
					//如果文件已经存在，则请求用户确认覆盖
					if(f.exists()){
						int i;
						i = JOptionPane.showConfirmDialog(this, "文件已存在，确定覆盖吗？");
						if(i != JOptionPane.YES_OPTION){
							return;
						}
					}
					
					//创建新文件，将分析结果写到文件中
					try{
						f.createNewFile();
						FileWriter fw = new FileWriter(f);
						tmp = text2.getText();
						fw.write(tmp);
						fw.close();
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
        else if(source.equals(analysisItem) || source.equals(analysisBtn)){
        	text2.setForeground(Color.BLACK);
        	text3.setForeground(Color.RED);
        	text2.setText("----------------------------分析结果--------------------------------"+"\n");
        	text3.setText("----------------------------出错信息--------------------------------"+"\n");
			doAnalysis();
		}
        else if(source.equals(copyItem) || source.equals(copyBtn) || source.equals(copyRItem)){
        	text1.copy();
        }
        else if(source.equals(pasteItem) || source.equals(pasteBtn) || source.equals(pasteRItem)){
        	text1.paste();
        }
        else if(source.equals(cutItem) || source.equals(cutBtn) || source.equals(cutRItem)){
        	text1.cut();
        }
	}
	
	public static void main(String[] args) {
		
		UserFrame uf = new UserFrame("编译原理词法分析演示系统");
		uf.setJMenuBar(jmb);
		uf.setResizable(false);
		uf.setSize(580, 550);
		uf.setLocation(200, 100);
		uf.setVisible(true);
		uf.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});

	}
	//文本组件中是否具备复制的条件
	public boolean isCanCopy(){
		int start,end;
		start = text1.getSelectionStart();
		end = text1.getSelectionEnd();
		if(start != end){
			return true;
		}else{
			return false;
		}
	}
	//剪切板中是否具有文本数据可供粘贴
	public boolean isClipboardString(){
		boolean b = false;
		Clipboard board = this.getToolkit().getSystemClipboard();
		Transferable content = board.getContents(this);
		try{
			if(content.getTransferData(DataFlavor.stringFlavor) instanceof String){
				b = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return b;
		
	}
	

	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成方法存根
		if(e.getSource().equals(editMenu)){
			copyItem.setEnabled(isCanCopy());
			pasteItem.setEnabled(isClipboardString());
			cutItem.setEnabled(isCanCopy());
		}
	}
	
	

	public void mousePressed(MouseEvent e) {
		// TODO 自动生成方法存根
		if(e.getButton() ==MouseEvent.BUTTON3){
			copyRItem.setEnabled(isCanCopy());
			pasteRItem.setEnabled(isClipboardString());
			cutRItem.setEnabled(isCanCopy());
			pop.show(this, e.getX()+38, e.getY()+100);
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成方法存根
		
	}
	
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成方法存根
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO 自动生成方法存根
		
	}
	
	public void insertUpdate(DocumentEvent de) {
		// TODO 自动生成方法存根
		
		showRowNum();
	}

	public void removeUpdate(DocumentEvent de) {
		// TODO 自动生成方法存根
		showRowNum();
	}

	public void changedUpdate(DocumentEvent de) {
		// TODO 自动生成方法存根
		showRowNum();
	}
	
	public void showRowNum(){
		
		int rowCount = text1.getRows();
		for(int i=1; i<=rowCount; i++){
			dlm.addElement(i);
		}
	}

}





















