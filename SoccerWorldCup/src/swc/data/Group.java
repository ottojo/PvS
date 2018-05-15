package swc.data;

import java.util.Vector;

public class Group {
    private String strGroupName;
    private Vector<Team> teams = new Vector<>();
    private Vector<Game> games = new Vector<>();
    private boolean isGroupCompleted;

    public Group(String groupName) {
        this.strGroupName = groupName;
    }

    public void addTeam(Team teamName) {
        teams.add(teamName);
    }

    public void addGame(Game newGame) {
        games.add(newGame);
    }

    public Vector<Team> getTeams() {
        return teams;
    }

    public Vector<Game> getGames() {
        return games;
    }

    public String getStrGroupName() {
        return strGroupName;
    }

    public void setStrGroupName(String strGroupName) {
        this.strGroupName = strGroupName;
    }

    public boolean isGroupCompleted() {
        return isGroupCompleted;
    }

    public void setGroupCompleted(boolean groupCompleted) {
        isGroupCompleted = groupCompleted;
    }
}
