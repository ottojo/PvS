import javax.swing.*; 
import java.awt.*;

public class jframedemo {
	public static void main(String [] args) {
		
		JFrame frame = new JFrame("Das Alter");
		frame.setPreferredSize(new Dimension(300, 200));
		JLabel myLabel = new JLabel("<html><i>Das Alter ist ein h�flich' Mann:<br>Einmal �bers andre klopft er an;<br>Aber nun sagt niemand: Herein!<br>Und vor der T�re will er nicht sein.<br>Da klinkt er auf, tritt ein so schnell,<br>Und nun hei�t's, er sei ein grober Gesell.</i></html>");
		
		frame.getContentPane( ).add(myLabel, BorderLayout.CENTER);
		
		WindowDestroyer myListener = new WindowDestroyer();
		frame.addWindowListener(myListener);
		frame.pack(); 
		
		frame.setVisible(true);
		
		
		
	}
	
}