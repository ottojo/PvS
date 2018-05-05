import javax.swing.*; 
import java.awt.*;

public class jframedemo {
	public static void main(String [] args) {
		
		JFrame frame = new JFrame("Das Alter");
		frame.setPreferredSize(new Dimension(300, 200));
		JLabel myLabel = new JLabel("<html><i>Das Alter ist ein höflich' Mann:<br>Einmal übers andre klopft er an;<br>Aber nun sagt niemand: Herein!<br>Und vor der Türe will er nicht sein.<br>Da klinkt er auf, tritt ein so schnell,<br>Und nun heißt's, er sei ein grober Gesell.</i></html>");
		
		frame.getContentPane( ).add(myLabel, BorderLayout.CENTER);
		
		WindowDestroyer myListener = new WindowDestroyer();
		frame.addWindowListener(myListener);
		frame.pack(); 
		
		frame.setVisible(true);
		
		
		
	}
	
}