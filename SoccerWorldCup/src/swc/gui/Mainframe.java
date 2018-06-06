package swc.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.ctrl.Print;
import swc.data.SoccerWC;


public class Mainframe extends javax.swing.JFrame {
	private static final long serialVersionUID = 632345753774989L;
	private ImageIcon [] icons;
	private SoccerWC worldCup;
	private FinalsPanel finals;
	
	public Mainframe(SoccerWC toOpen) {

		try {
			java.util.Locale.setDefault(Locale.ENGLISH);
			UIManager.put("OptionPane.yesButtonText","Yes");
			UIManager.put("OptionPane.noButtonText","No");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		worldCup = toOpen;
	    initComponents();
	    displayDialog();
	}

	/**
	 * Initalizes components, listener, etc.
	 */
	private void initComponents() {	
		
		//initialize Components
		menuBar = new JMenuBar();
		menuFile = new JMenu();
		menuExtra = new JMenu();
		menuHelp = new JMenu();
		menuItemLoadWCfromServer = new JMenuItem();
		menuItemAbout = new JMenuItem();
		menuItemWCBetting = new JMenuItem();
		menuItemLoadWC = new JMenuItem();
		menuItemNewWC = new JMenuItem();
		menuItemSave = new JMenuItem();
		menuItemSaveAs = new JMenuItem();
		menuItemExit = new JMenuItem();
		tabContainer = new JTabbedPane();
		toolBar = new JToolBar();
	
		//prepare frame
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		//======== menuFile ========
			menuFile.setText("File");

			//---- menuItemLoadWC ----
			menuItemLoadWC.setText("Load World Cup");
			menuFile.add(menuItemLoadWC);
			
			//---- menuItemNewWC ----
			menuItemNewWC.setText("New World Cup");
			menuItemNewWC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuItemNewWCActionPerformed(e);
				}
			});
			menuFile.add(menuItemNewWC);
			
			menuFile.addSeparator();
			
			//---- menuItemSave ----
			menuItemSave.setText("Save");
			menuFile.add(menuItemSave);
			
			//---- menuItemSaveAs ----
			menuItemSaveAs.setText("Save As...");
			menuFile.add(menuItemSaveAs);
			
			menuFile.addSeparator();
			
			//---- menuItemExit ----
			menuItemExit.setText("Exit");
			menuItemExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuItemExitActionPerformed(e);
				}
			});
			menuFile.add(menuItemExit);
			
		menuBar.add(menuFile);
		
		
		//======== menuExtra ========
			menuExtra.setText("Extra");
	
			//---- menuItemWCBetting ----
			menuItemWCBetting.setText("World Cup betting");
			menuExtra.add(menuItemWCBetting);
			
			//---- menuItemLoadWCfromServer ----
			menuItemLoadWCfromServer.setText("Load from sever...");
			menuExtra.add(menuItemLoadWCfromServer);
		
		menuBar.add(menuExtra);	
		
		
		//======== menuHelp ========
			menuHelp.setText("Help");
	
			//---- menuItemAbout ----
			menuItemAbout.setText("About");
			menuItemAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					menuItemAboutActionPerformed(e);
				}
			});
			menuHelp.add(menuItemAbout);
			
		menuBar.add(menuHelp);	
		
		//======== Toolbar ========
		
		toolBar.setLayout(new BoxLayout(toolBar,BoxLayout.LINE_AXIS));

		toolBar.add(new JLabel(" World Cup:   "));
		wcName = new JLabel();
		wcName.setFont(new Font("Arial", Font.BOLD, 13));
		toolBar.add(wcName);
		toolBar.add(new JLabel("    Status:   "));
		wcStatus = new JLabel();
		wcStatus.setFont(new Font("Arial", Font.BOLD, 11));
		toolBar.add(wcStatus);
				
		toolBar.add(Box.createHorizontalGlue());
		
		icons = CtrlGroup.getDefaultIcons();

		buttonOpen = new JButton(icons[0]);
		buttonOpen.setToolTipText("Open World Cup");
		buttonNew = new JButton(icons[1]);
		buttonNew.setToolTipText("Create New World Cup");
		buttonNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				menuItemNewWCActionPerformed(e);
			}
		});
		//buttonEdit = new JButton(icons[2]);
		//buttonEdit.setToolTipText("Edit current Group");
		buttonSave = new JButton(icons[3]);
		buttonSave.setToolTipText("Save World Cup");
		buttonPrint = new JButton(icons[4]);
		buttonPrint.setToolTipText("Print current Group");
		buttonPrint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				buttonPrintActionPerformed(e);
			}
		});
		toolBar.add(buttonOpen);
		toolBar.add(buttonNew);
		//toolBar.add(buttonEdit);
		toolBar.add(buttonSave);
		toolBar.add(buttonPrint);
		toolBar.setVisible(false);
		toolBar.setFloatable(false);
	
		//======== adding components ========
		
		setJMenuBar(menuBar);
		contentPane.add(toolBar, BorderLayout.PAGE_START);
		contentPane.add(tabContainer);
	}

	protected void buttonPrintActionPerformed(ActionEvent e) {
		try {
	    	PrinterJob objPrinterJob = PrinterJob.getPrinterJob();
	    	if (objPrinterJob.printDialog()) {
	            PageFormat objPageFormat = objPrinterJob.pageDialog(objPrinterJob.defaultPage());

	            Component toPrint = tabContainer.getSelectedComponent();
	            if(toPrint instanceof JScrollPane)
	            	toPrint = ((JScrollPane) toPrint).getViewport().getComponent(0);
	            objPrinterJob.setPrintable(new Print(toPrint), objPageFormat);
	            objPrinterJob.print();        
	        }
	    } catch (PrinterException objException) {
	    	objException.printStackTrace();
	    }
	}

	protected void menuItemExitActionPerformed(ActionEvent e) {
		this.dispose();
		System.exit(0);
	}

	protected void menuItemNewWCActionPerformed(ActionEvent e) {
		if(!(worldCup.getName() == null)){
			int ok = JOptionPane.showConfirmDialog(null,"Open new World Cup? Unsaved changes will be lost!", 
                    "Confirmation required", 
                    JOptionPane.YES_NO_OPTION);
			if (ok == JOptionPane.YES_OPTION){
				CreateDialog cd = new CreateDialog(this, worldCup);
				cd.setVisible(true);
				if(cd.wasSuccessful())
					initializeTabContainer();
			}	
		}else{
			CreateDialog cd = new CreateDialog(this, worldCup);
			cd.setVisible(true);
			if(cd.wasSuccessful())
				initializeTabContainer();
		}
	}

	private void menuItemAboutActionPerformed(ActionEvent e) {
		String message = "This Soccer World Cup 2018 managing program was created for " +
				"the practices along\n with the lecture 'Programmierung von Systemen' at Ulm University, summer 2018.\n\n" +
				"Created by Martin Liebrecht and Florian Rapp, administered by Kevin Andrews.\n" +
				"To get information regarding this tool contact kevin.andrews@uni-ulm.de.";
		JOptionPane.showMessageDialog(this, message, "Soccer World Cup 2018", JOptionPane.INFORMATION_MESSAGE);
	}

	private void initializeTabContainer() {
		createHeadLine();	
		toolBar.setVisible(true);
		tabContainer.removeAll();
		for (int i = 0; i < 8; i++) {
			tabContainer.addTab(worldCup.getGroups().get(i).getStrGroupName(), new GroupPanel(this, worldCup.getGroups().get(i)));
		}
		JScrollPane fsp = new JScrollPane();
		JScrollBar jsb = new JScrollBar();
		jsb.setAutoscrolls(false);
		jsb.setUnitIncrement(15);
		jsb.setBlockIncrement(15);
		fsp.setVerticalScrollBar(jsb);
		finals = new FinalsPanel(this, worldCup.getFinals(), fsp);
		finals.setPreferredSize(new Dimension(600, 760));
		fsp.setViewportView(finals);
		tabContainer.addTab("Finals", fsp);
		tabContainer.addChangeListener(e -> {
			if (tabContainer.getSelectedComponent().equals(fsp)){
				CtrlFinals.calculateFinals(worldCup);
			}
		});
	}

	public void createHeadLine() {
		wcName.setText(worldCup.getName());
		wcStatus.setText(CtrlFinals.getStatus(worldCup));
	}

	/**
	 * Configures the frame setup an displays.
	 */
	private void displayDialog() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(690, 600);
		setResizable(false);
		Dimension objScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int intTop              = (objScreenSize.height - this.getHeight()) / 2;
	    int intLeft             = (objScreenSize.width - this.getWidth()) / 2;    
	         
	    setLocation(intLeft, intTop);
	    setTitle("Soccer World Cup 2018");
	    Toolkit tk = getToolkit();
	    setIconImage(CtrlGroup.getMainWindowIcon(tk));
	}

	/**
	 * GUI Elements.
	 */
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuExtra;
	private JMenu menuHelp;
	private JMenuItem menuItemWCBetting;
	private JMenuItem menuItemLoadWCfromServer;
	private JMenuItem menuItemAbout;
	private JMenuItem menuItemLoadWC;
	private JMenuItem menuItemNewWC;
	private JMenuItem menuItemSave;
	private JMenuItem menuItemSaveAs;
	private JMenuItem menuItemExit;
	private JTabbedPane tabContainer;
	private JToolBar toolBar;
	private JLabel wcName;
	private JLabel wcStatus;
	private JButton buttonOpen;
	private JButton buttonNew;
	private JButton buttonSave;
	private JButton buttonPrint;
}
