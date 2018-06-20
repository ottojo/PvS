package swc.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class LoadServerDialog extends JDialog {
	private static final long serialVersionUID = 336345634571L;
	private String url = "http://85.214.73.226/pvs2010/default.swc10.xml";
	
	
	public LoadServerDialog(Frame owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		labelUrl = new JLabel();
		textFieldUrl = new JTextField();
		buttonLoad = new JButton();
		buttonCancel = new JButton();

		//======== this ========
		setTitle("Load file from Server");
		setModal(true);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

		//---- labelUrl ----
		labelUrl.setText("Url for file: ");
		contentPane.add(labelUrl, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(10, 10, 5, 5), 0, 0));
		textFieldUrl.setText(url);
		contentPane.add(textFieldUrl, new GridBagConstraints(1, 0, 0, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(10, 0, 5, 10), 0, 0));

		//---- buttonLoad ----
		buttonLoad.setText("Load file");
		contentPane.add(buttonLoad, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 5), 0, 0));
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonLoadActionPerformed();
			}
		});	
		//---- buttonCancel ----
		buttonCancel.setText("Cancel");
		contentPane.add(buttonCancel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 10, 10), 0, 0));
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCancelActionPerformed(e);
			}
		});
		
		KeyListener kl = new KeyListener() {		
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == 10)
					buttonLoadActionPerformed();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		};
		textFieldUrl.addKeyListener(kl);
		
		pack();
		setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}

	private void buttonLoadActionPerformed() {
		if(textFieldUrl.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Please enter a valid URL!", "Load from server", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.url = textFieldUrl.getText();
		this.dispose();
	}

	private void buttonCancelActionPerformed(ActionEvent e) {
		this.dispose();
		this.url = "";
	}

	private JLabel labelUrl;
	private JTextField textFieldUrl;
	private JButton buttonLoad;
	private JButton buttonCancel;
	
	public String getUrl(){
		return this.url;
	}
}
