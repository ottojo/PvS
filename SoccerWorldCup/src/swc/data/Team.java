package swc.data;

public class Team implements Comparable<Team> {

    private String strName;
    private int points;
    private int gf;
    private int ga;
    private int played;
    private int won;
    private int loss;
    private int draw;

    public Team(String name, int points, int gf, int ga, int played, int won, int loss, int draw) {
        this.strName = name;
        this.points = points;
        this.gf = gf;
        this.ga = ga;
        this.played = played;
        this.won = won;
        this.loss = loss;
        this.draw = draw;
    }

    @Override
    public int compareTo(Team o) {
        if (o.points > points)
            return 1;
        else if (o.points < points)
            return -1;
        else if (o.gf - o.ga > gf - ga)
            return 1;
        else if (gf - ga < o.gf - o.ga)
            return -1;
        else return o.gf > gf ? 1 : -1;
    }

    public void clearTeam() {
        this.strName = "";
        this.points = 0;
        this.gf = 0;
        this.ga = 0;
        this.played = 0;
        this.won = 0;
        this.loss = 0;
        this.draw = 0;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGa() {
        return ga;
    }

    public void setGa(int ga) {
        this.ga = ga;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
