package swc.data;

public class Game {
    private int intId;
    private String time;
    private String date;
    private String location;
    private int goalsH;
    private int goalsG;
    private boolean isPlayed;
    private Team teamH;
    private Team teamG;

    public Game() {

    }

    public Game(int intId, String date, String time, String location, Team teamHome, Team teamGuest,
                int goalsH, int goalsG, boolean isPlayed) {
        this.intId = intId;
        this.time = time;
        this.date = date;
        this.location = location;
        this.goalsH = goalsH;
        this.goalsG = goalsG;
        this.isPlayed = isPlayed;
        this.teamH = teamHome;
        this.teamG = teamGuest;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGoalsH() {
        return goalsH;
    }

    public void setGoalsH(int goalsH) {
        this.goalsH = goalsH;
    }

    public int getGoalsG() {
        return goalsG;
    }

    public void setGoalsG(int goalsG) {
        this.goalsG = goalsG;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public Team getTeamH() {
        return teamH;
    }

    public void setTeamH(Team teamH) {
        this.teamH = teamH;
    }

    public Team getTeamG() {
        return teamG;
    }

    public void setTeamG(Team teamG) {
        this.teamG = teamG;
    }
}
