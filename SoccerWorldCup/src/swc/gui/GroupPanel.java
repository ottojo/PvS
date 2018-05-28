package swc.gui;

import swc.data.Group;
import swc.data.Team;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class GroupPanel extends JPanel {
    private Mainframe mainframe;
    private Group group;

    private JTable standingsTable;
    private JTable matchesTable;

    public GroupPanel(Mainframe mainframe, Group group) {
        super();
        this.mainframe = mainframe;
        this.group = group;

        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        add(new JLabel("Table for Group " + group.getStrGroupName() + " - Top two teams will advance."));


        standingsTable = new JTable(new StandingsTableModel(group));
        add(standingsTable.getTableHeader());
        add(standingsTable);

//        add(matchesTable);
    }

    private class StandingsTableModel extends AbstractTableModel {
        private Group group;

        public StandingsTableModel(Group group) {
            this.group = group;
        }

        @Override
        public int getRowCount() {
            return group.getTeams().size();
        }

        @Override
        public int getColumnCount() {
            return 10;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "#";
                case 1:
                    return "Team";
                case 2:
                    return "Played";
                case 3:
                    return "Won";
                case 4:
                    return "Draw";
                case 5:
                    return "Loss";
                case 6:
                    return "GF";
                case 7:
                    return "GA";
                case 8:
                    return "Difference";
                case 9:
                    return "Points";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Team t = group.getTeams().get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return t.getName();
                case 2:
                    return t.getPlayed();
                case 3:
                    return t.getWon();
                case 4:
                    return t.getDraw();
                case 5:
                    return t.getLoss();
                case 6:
                    return t.getGf();
                case 7:
                    return t.getGa();
                case 8:
                    return t.getGf() - t.getGa();
                case 9:
                    return t.getPoints();
            }
            return null;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }


    }

}
