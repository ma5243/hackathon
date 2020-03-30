import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrontEnd extends JFrame {
	private GridLayout layout = new GridLayout();
	private DictionaryMaker diction;
	private static URLMaker url;
	private JButton readText, getDict, browser, directions;
	private JTextArea textArea, dict;
	private JScrollPane scroll;
	static int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	String[] listOfBody = null;

	public DictionaryMaker getDictionary() {
		return diction;
	}

	public void setDictionary(DictionaryMaker dictionaryMaker) {
		this.diction = dictionaryMaker;
	}

	public static void setURL(URLMaker url) {
		FrontEnd.url = url;
	}

	public URLMaker getURL() {
		return url;
	}

	public void addComponentsToPane(Container pane) {
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("clickfront")) {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filename = f.getAbsolutePath();

					try {
						FileReader reader = new FileReader(filename);
						BufferedReader br = new BufferedReader(reader);
						textArea.read(br, null);
						br.close();
						textArea.requestFocus();
						String textBody = textArea.getText();
						listOfBody = textBody.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");				
					} catch (Exception e1) {
						System.out.println("no");
					}
				}
				else if(e.getActionCommand().equals("dict")) {
					HashMap<String, ArrayList> dictionaryMap = diction.alphaList();
					String outPutString = "";
					for (String working: listOfBody) {
						for(String key: dictionaryMap.keySet()) {
							if(working.substring(0, 1).equals(key)) {
								for(ArrayList arrVals : dictionaryMap.values()) {
									for(Object word : arrVals) {
										if(word.equals(working)) {
											outPutString += word + " ";

										}
									}
								}
							}
						}
					}
					String[] listToTree = outPutString.split(" ");


					TreeSet<String> orderedList = new TreeSet<String>();

					for(String wrd : listToTree) {
						if(!orderedList.contains(wrd)) {
							orderedList.add(wrd);
						}
					}
					String newLine = "Difficult words in text: ";
					for(String wrd : orderedList) {
						newLine += "\n" +wrd + "\n";
					}

					dict.setText(newLine);
				}
				else if(e.getActionCommand().equals("lookUp")) {
					FrontEnd.setURL(new URLMaker());
				}
				else if(e.getActionCommand().equals("direction")) {
					JOptionPane.showMessageDialog(null, "This Program is a basic form of a book reader. There are four possible commands located at the top-right of the window. \n \n"
							+ " On the top left is the 'Chose File' option. When it is selected the user will be prompted to search for a .docx or .txt file to display. \n \n"
							+ " To the right of that is the 'Get Get Hard Words' option. When this is selected the program will go through the file and show the difficult words in the file. \n  \n"
							+ " On the bottom left is the 'Look It Up' option. This will create a pop-up window that you can search for a word and it will return the definition \n \n"
							+ " Lastly, the bottom right is the 'Directions' option. This is where you are now, it is to remind you how to use this program. \n  \n"
							+ " To begin using this program please press 'OK'.");
				}
			}
		}

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,2));
		readText = new JButton("Choose File");
		getDict = new JButton("Get Hard Words");
		browser = new JButton("Look It Up");
		directions = new JButton("Directions");
		readText.setActionCommand("clickfront");
		getDict.setActionCommand("dict");
		browser.setActionCommand("lookUp");
		directions.setActionCommand("direction");
		directions.addActionListener(new ButtonListener());
		browser.addActionListener(new ButtonListener());
		getDict.addActionListener(new ButtonListener());
		readText.addActionListener(new ButtonListener());
		panel2.add(readText);
		panel2.add(getDict);
		panel2.add(browser);
		panel2.add(directions);


		JPanel panel = new JPanel();
		textArea = new JTextArea(height - 698, 71);
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(textArea.getFont().deriveFont(14f));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setVisible(true);
		textArea.setBackground(Color.CYAN);
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

		scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scroll.setEnabled(true);
		scroll.setVisible(true);
		panel.add(scroll);

		JPanel dictPanel = new JPanel();
		dictPanel.setLayout(layout);
		dictPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dict = new JTextArea(10,70);
		dict.setLineWrap(true);
		dict.setEditable(false);
		dict.setVisible(true);
		dict.setBackground(Color.green);
		dict.setFont(new Font("Times New Roman", Font.BOLD, 15));

		scroll = new JScrollPane(dict, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dictPanel.add(scroll);

		pane.add(dictPanel, BorderLayout.SOUTH);
		pane.add(panel2, BorderLayout.EAST);
		pane.add(panel, BorderLayout.WEST);
		validate();
	}

	private void createAndShowGUI() {
		this.setTitle("BookReader");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width - 10, height - 25);
		this.addComponentsToPane(this.getContentPane());
		this.setVisible(true);
	}

	public static void main(String[] args) {
		FrontEnd myViewer = new FrontEnd();
		myViewer.setDictionary(new DictionaryMaker("hardWords.txt"));
		// myViewer.setURL(new URLMaker());
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myViewer.createAndShowGUI();
			}
		});
	}
}