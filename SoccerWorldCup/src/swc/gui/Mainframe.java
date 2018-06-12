package swc.gui;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.ctrl.Print;
import swc.data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Vector;


public class Mainframe extends javax.swing.JFrame {
    private static final long serialVersionUID = 632345753774989L;
    private ImageIcon[] icons;
    private SoccerWC worldCup;
    private FinalsPanel finals;

    public Mainframe(SoccerWC toOpen) {

        try {
            java.util.Locale.setDefault(Locale.ENGLISH);
            UIManager.put("OptionPane.yesButtonText", "Yes");
            UIManager.put("OptionPane.noButtonText", "No");
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
     * Initializes components, listener, etc.
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
        menuItemLoadWC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItemLoadWCActionPerformed(e);
            }
        });
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
        menuItemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItemSaveWCActionPerformed(e);
            }
        });
        menuFile.add(menuItemSave);

        //---- menuItemSaveAs ----
        menuItemSaveAs.setText("Save As...");
        menuItemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuItemSaveAsWCActionPerformed(e);
            }
        });
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

        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.LINE_AXIS));

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

    /**
     * Method called for "Save World Cup" menu option
     *
     * @author Jonas Otto
     */
    private void menuItemSaveWCActionPerformed(ActionEvent e) {
        if (worldCup == null || worldCup.getFilename() == null || worldCup.getFilename().equals("")) {
            menuItemSaveAsWCActionPerformed(null);
        } else {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(worldCup.getFilename()))) {
                for (Group g : worldCup.getGroups()) {
                    bufferedWriter.write(worldCup.getName() + "\n");
                    bufferedWriter.write(g.getStrGroupName() + "\n");
                    bufferedWriter.write("Teams:\n");

                    CSVPrinter teamsPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT
                            .withHeader("Position", "Team", "Played", "Won", "Draw", "Loss", "GF", "GA", "Points"));
                    for (int i = 0; i < g.getTeams().size(); i++) {
                        Team t = g.getTeams().get(i);
                        teamsPrinter.printRecord(i, t.getName(), t.getPlayed(), t.getWon(), t.getDraw(), t.getLoss(),
                                t.getGf(), t.getGa(), t.getPoints());
                    }

                    bufferedWriter.write("Games:\n");
                    printGames(bufferedWriter, g.getGames());
                }

                bufferedWriter.write("Finals:\n");
                bufferedWriter.write("Round of 16:\n");
                printGames(bufferedWriter, worldCup.getFinals().getRoundOf16());
                bufferedWriter.write("Quarterfinals:\n");
                printGames(bufferedWriter, worldCup.getFinals().getQuarterFinals());
                bufferedWriter.write("Semifinals:\n");
                printGames(bufferedWriter, worldCup.getFinals().getSemiFinals());
                bufferedWriter.write("Match for third Place:\n");
                printGames(bufferedWriter, Collections.singletonList(worldCup.getFinals().getThirdGame()));
                bufferedWriter.write("Final:\n");
                printGames(bufferedWriter, Collections.singletonList(worldCup.getFinals().getFinalGame()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Writes {@link Game} to CSV file
     *
     * @param a     CSV file
     * @param games Games to write
     * @throws IOException on writing failure
     * @author Jonas Otto
     */
    private void printGames(Appendable a, Iterable<Game> games) throws IOException {
        CSVPrinter gamesPrinter = new CSVPrinter(a, CSVFormat.DEFAULT
                .withHeader("Game Id", "Date", "Time", "Venue", "Home Team", "Guest Team", "GH", "GG", "IsPlayed"));
        games.forEach(game -> {
            try {
                gamesPrinter.printRecord(game.getIntId(), game.getDate(), game.getTime(), game.getLocation(),
                        game.getTeamH().getName(), game.getTeamG().getName(), game.getGoalsH(), game.getGoalsG(), game.isPlayed());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Method called for "Save As" menu option
     *
     * @author Jonas Otto
     */
    private void menuItemSaveAsWCActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            worldCup.setFilename(fileChooser.getSelectedFile().getAbsolutePath());
            menuItemSaveWCActionPerformed(null);
        }
    }

    /**
     * Method called for "Load World Cup" menu option
     *
     * @author Jonas Otto
     */
    private void menuItemLoadWCActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();
            worldCup = getWorldCupFromCSV(fileName);
            CtrlFinals.calculateFinals(worldCup);
            initializeTabContainer();
        }
    }

    protected void buttonPrintActionPerformed(ActionEvent e) {
        try {
            PrinterJob objPrinterJob = PrinterJob.getPrinterJob();
            if (objPrinterJob.printDialog()) {
                PageFormat objPageFormat = objPrinterJob.pageDialog(objPrinterJob.defaultPage());

                Component toPrint = tabContainer.getSelectedComponent();
                if (toPrint instanceof JScrollPane)
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
        if (!(worldCup.getName() == null)) {
            int ok = JOptionPane.showConfirmDialog(null, "Open new World Cup? Unsaved changes will be lost!",
                    "Confirmation required",
                    JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                CreateDialog cd = new CreateDialog(this, worldCup);
                cd.setVisible(true);
                if (cd.wasSuccessful())
                    initializeTabContainer();
            }
        } else {
            CreateDialog cd = new CreateDialog(this, worldCup);
            cd.setVisible(true);
            if (cd.wasSuccessful())
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
        int intTop = (objScreenSize.height - this.getHeight()) / 2;
        int intLeft = (objScreenSize.width - this.getWidth()) / 2;

        setLocation(intLeft, intTop);
        setTitle("Soccer World Cup 2018");
        Toolkit tk = getToolkit();
        setIconImage(CtrlGroup.getMainWindowIcon(tk));
    }

    public void callFinalCalucalion() {
        CtrlFinals.calculateFinals(worldCup);
        finals.drawMatches();
    }

    /**
     * Parses CSV of {@link SoccerWC}
     *
     * @param fileName Filename of CSV file
     * @return Parsed {@link SoccerWC}
     * @author Jonas Otto
     */
    private SoccerWC getWorldCupFromCSV(String fileName) {
        try {
            SoccerWC wc = new SoccerWC();
            wc.setFilename(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            wc.setName(lines.get(0));

            ArrayList<ArrayList<String>> groupsContent = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                groupsContent.add(new ArrayList<>());
                int groupStartIndex = 16 * i + 1;
                int groupEndIndex = groupStartIndex + 14;

                for (int b = groupStartIndex; b <= groupEndIndex; b++) {
                    groupsContent.get(i).add(lines.get(b));
                }
            }
            groupsContent.forEach(l -> wc.addGroup(parseGroup(l)));

            Vector<Team> allTeams = new Vector<>();
            wc.getGroups().forEach(g -> allTeams.addAll(g.getTeams()));

            wc.setFinals(new Final());
            String ro16 = getLineArrayString(lines, 130, 139);
            wc.getFinals().setRoundOf16(parseGames(ro16, allTeams));
            String quarters = getLineArrayString(lines, 140, 145);
            wc.getFinals().setQuarterFinals(parseGames(quarters, allTeams));
            String semis = getLineArrayString(lines, 146, 149);
            wc.getFinals().setSemiFinals(parseGames(semis, allTeams));
            String thirdPlace = getLineArrayString(lines, 150, 152);
            wc.getFinals().setThirdGame(parseGames(thirdPlace, allTeams).get(0));
            String finalGame = getLineArrayString(lines, 153, 155);
            wc.getFinals().setFinalGame(parseGames(finalGame, allTeams).get(0));

            return wc;
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Helper funtction to concatenate text that is present as an {@link ArrayList} of lines.
     *
     * @param l          list of text lines
     * @param startIndex inclusive start index
     * @param endIndex   exclusive end index
     * @return concatenated string
     * @author Jonas Otto
     */
    private String getLineArrayString(ArrayList<String> l, int startIndex, int endIndex) {
        StringBuilder result = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            result.append(l.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Parses CSV representation of a {@link Group}
     *
     * @param l List of CSV lines including header
     * @return Parsed Group
     * @author Jonas Otto
     */
    private Group parseGroup(ArrayList<String> l) {
        try {
            Group g = new Group(l.get(0));

            // Parse Teams
            StringBuilder teams = new StringBuilder();
            for (int i = 2; i < 7; i++) {
                teams.append(l.get(i)).append("\n");
            }
            Vector<Team> teamVector = new Vector<>();
            Iterable<CSVRecord> teamRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new StringReader(teams.toString()));
            teamRecords.forEach(tr ->
                    teamVector.add(
                            new Team(tr.get("Team"), Integer.parseInt(tr.get("Points")),
                                    Integer.parseInt(tr.get("GF")), Integer.parseInt(tr.get("GA")), Integer.parseInt(tr.get("Played")),
                                    Integer.parseInt(tr.get("Won")), Integer.parseInt(tr.get("Loss")), Integer.parseInt(tr.get("Draw")))
                    )
            );
            teamVector.forEach(g::addTeam);


            // Parse Games
            StringBuilder games = new StringBuilder();
            for (int i = 8; i < 15; i++) {
                games.append(l.get(i)).append("\n");
            }
            Vector<Game> gameVector = parseGames(games.toString(), teamVector);

            gameVector.forEach(g::addGame);
            return g;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Parses multiple {@link Game} from CSV
     *
     * @param games CSV data
     * @param teams Teams to link the games to the proper object
     * @return Parsed games
     * @throws IOException if CSV is invalid
     * @author Jonas Otto
     */
    private Vector<Game> parseGames(String games, Vector<Team> teams) throws IOException {
        Vector<Game> gameVector = new Vector<>();
        Iterable<CSVRecord> gameRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new StringReader(games));
        gameRecords.forEach(tr -> {

            String homeTeamName = tr.get("Home Team");
            String guestTeamName = tr.get("Guest Team");

            Team homeTeam = new Team();
            homeTeam.setName(homeTeamName);
            Team guestTeam = new Team();
            guestTeam.setName(guestTeamName);

            for (Team t : teams) {
                if (t.getName().equals(homeTeamName)) homeTeam = t;
                else if (t.getName().equals(guestTeamName)) guestTeam = t;
            }


            gameVector.add(
                    new Game(
                            Integer.parseInt(tr.get("Game Id")), tr.get("Date"), tr.get("Date"), tr.get("Venue"),
                            homeTeam, guestTeam, Integer.parseInt(tr.get("GH")), Integer.parseInt(tr.get("GG")),
                            Boolean.parseBoolean(tr.get("IsPlayed")))
            );
        });
        return gameVector;
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
