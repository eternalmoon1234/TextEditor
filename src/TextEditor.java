import javax.swing. *;
import java.awt.event. *;
import java.io. *;

public class TextEditor extends JFrame implements ActionListener {
	
	private final JFrame frame = new JFrame("Text Editor");
	private final JTextArea textField = new JTextArea();
	
	private final JMenuBar menuBar = new JMenuBar();
	
	private final JMenu file = new JMenu("File");
	private final JMenu edit = new JMenu("Edit");
	
	private final JMenuItem newDoc = new JMenuItem("New Document");
	private final JMenuItem open = new JMenuItem("Open");
	private final JMenuItem save = new JMenuItem("Save");
	private final JMenuItem print = new JMenuItem("Print");
	
	private final JMenuItem close = new JMenuItem("Close");
	
	private final JMenuItem cut = new JMenuItem("Cut");
	private final JMenuItem copy = new JMenuItem("Copy");
	private final JMenuItem paste = new JMenuItem("Paste");
	
	
	public TextEditor() {
		file.add(newDoc);
		file.add(open);
		file.add(save);
		file.add(print);
		
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		
		newDoc.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		print.addActionListener(this);
		close.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		
		
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(close);
		
		frame.setJMenuBar(menuBar);
		frame.add(textField);
		System.out.println("Window Created");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.show();
	}
	
	public void actionPerformed(ActionEvent event) {
		String button = event.getActionCommand();
		
		if (button.equals("Copy")) {
			textField.copy();
			System.out.println("Text Copied");
			
		} else if (button.equals("Paste")) {
			textField.paste();
			System.out.println("Text Pasted");
			
		} else if (button.equals("Cut")) {
			textField.cut();
			System.out.println("Text Cut");
			
		} else if (button.equals("New Document")) {
			textField.setText("");
			System.out.println("New Document created");
			
		} else if (button.equals("Close")) {
			frame.setVisible(false);
			frame.dispose();
			
			System.out.println("Software Closed");
		} else if (button.equals("Print")) {
			try {
				textField.print();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else if (button.equals("Save")) {
			JFileChooser fc = new JFileChooser("f:");
			
			int showSavePanel = fc.showSaveDialog(null);
			
			if (showSavePanel == JFileChooser.APPROVE_OPTION) {
				File f = new File(fc.getSelectedFile().getAbsolutePath());
				
				try {
					FileWriter fr = new FileWriter(f, false);
					
					BufferedWriter bw = new BufferedWriter(fr);
					
					bw.write(textField.getText());
					bw.flush();
					bw.close();
					
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				System.out.println("User cancelled the operation");
			}
		} else if (button.equals("Open")) {
			JFileChooser fc = new JFileChooser("f:");
			
			int dialog = fc.showOpenDialog(null);
			
			if (dialog == JFileChooser.APPROVE_OPTION) {
				File f = new File(fc.getSelectedFile().getAbsolutePath());
				
				try {
					String string1;
					String text;
					
					FileReader read = new FileReader(f);
					BufferedReader br = new BufferedReader(read);
					
					string1 = br.readLine();
					
					while ((text = br.readLine()) != null) {
						text = text + "\n" + string1;
					}
					
					textField.setText(text);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
