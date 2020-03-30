import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class URLMaker extends JFrame{
	private JTextField addressBar;
	private JEditorPane display;
	
	public URLMaker() {
		addressBar = new JTextField("enter a word");
		addressBar.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						loadWord(event.getActionCommand());
					}
				}
				);
		add(addressBar, BorderLayout.NORTH);
		
		display = new JEditorPane();
		display.setEditable(false);
		display.addHyperlinkListener((HyperlinkListener) new HyperlinkListener() {
					public void hyperlinkUpdate(HyperlinkEvent event) {
						if(event.getEventType()==HyperlinkEvent.EventType.ACTIVATED) {
							loadWord(event.getURL().toString());
						}
					}
				});
		add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(700,500);
		setVisible(true);
	}
	
	private void loadWord(String userText) {
		try {
			display.setPage("https://www.yourdictionary.com/" + userText);
			addressBar.setText(userText);
		}catch(Exception e) {
			System.out.println("oops");

	}
	
	}
public static void main(String[] args) {
	URLMaker dude = new URLMaker();
	dude.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

}
