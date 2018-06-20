package swc.pers;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class ReadWC {

    public static void readFromCSV(String filename, SoccerWC worldCup) throws IOException {
        Vector<String[]> teams = new Vector<String[]>();
        Vector<String[]> games = new Vector<String[]>();
        String separator = ",";

        BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
        String readString;

        // world cup name
        worldCup.setName(in.readLine());
        worldCup.setFilename(filename);

        // read the groups
        for (int i = 0; i < 8; i++) {
            while (!in.readLine().startsWith("Position")) ;
            for (int j = 0; j < 4; j++) {
                readString = in.readLine();
                teams.add(readString.split(separator));
            }
            while (!in.readLine().startsWith("Game Id")) ;
            for (int j = 0; j < 6; j++) {
                readString = in.readLine();
                games.add(readString.split(separator));
            }
        }

        // read the finals
        while (!in.readLine().startsWith("Game Id")) ;
        for (int j = 0; j < 8; j++) {
            readString = in.readLine();
            games.add(readString.split(separator));
        }

        while (!in.readLine().startsWith("Game Id")) ;
        for (int j = 0; j < 4; j++) {
            readString = in.readLine();
            games.add(readString.split(separator));
        }

        while (!in.readLine().startsWith("Game Id")) ;
        for (int j = 0; j < 2; j++) {
            readString = in.readLine();
            games.add(readString.split(separator));
        }

        while (!in.readLine().startsWith("Game Id")) ;
        readString = in.readLine();
        games.add(readString.split(separator));

        while (!in.readLine().startsWith("Game Id")) ;
        readString = in.readLine();
        games.add(readString.split(separator));

        // set new world cup (cleaning old)
        Vector<Group> groupObjects = worldCup.getGroups();
        groupObjects.clear();
        Vector<Game> r16 = worldCup.getFinals().getRoundOf16();
        r16.clear();
        Vector<Game> q = worldCup.getFinals().getQuarterFinals();
        q.clear();
        Vector<Game> s = worldCup.getFinals().getSemiFinals();
        s.clear();

        groupObjects.add(new Group("Group A"));
        groupObjects.add(new Group("Group B"));
        groupObjects.add(new Group("Group C"));
        groupObjects.add(new Group("Group D"));
        groupObjects.add(new Group("Group E"));
        groupObjects.add(new Group("Group F"));
        groupObjects.add(new Group("Group G"));
        groupObjects.add(new Group("Group H"));

        int i = 0;
        int j = 0;
        for (Group g : groupObjects) {
            for (int k = 0; k < 4; k++, i++) {
                String[] currentTeam = teams.get(i);
                g.addTeam(new Team(currentTeam[1],
                        Integer.parseInt(currentTeam[8]),
                        Integer.parseInt(currentTeam[6]),
                        Integer.parseInt(currentTeam[7]),
                        Integer.parseInt(currentTeam[2]),
                        Integer.parseInt(currentTeam[3]),
                        Integer.parseInt(currentTeam[4]),
                        Integer.parseInt(currentTeam[5])
                ));
            }

            for (int l = 0; l < 6; l++, j++) {
                String[] currentGame = games.get(j);
                Game newGame = new Game();
                newGame.setIntId(Integer.parseInt(currentGame[0]));
                newGame.setDate(currentGame[1]);
                newGame.setTime(currentGame[2]);
                newGame.setLocation(currentGame[3]);
                newGame.setGoalsH(Integer.parseInt(currentGame[6]));
                newGame.setGoalsG(Integer.parseInt(currentGame[7]));
                if (currentGame[8].equals("true"))
                    newGame.setPlayed(true);
                else
                    newGame.setPlayed(false);
                for (Team team : g.getTeams()) {
                    if (team.getName().equals(currentGame[4]))
                        newGame.setTeamH(team);
                    if (team.getName().equals(currentGame[5]))
                        newGame.setTeamG(team);
                }
                g.addGame(newGame);
            }
        }

        for (int k = 0; k < 8; k++, j++) {
            String[] currentGame = games.get(j);
            Game newGame = new Game();
            newGame.setIntId(Integer.parseInt(currentGame[0]));
            newGame.setDate(currentGame[1]);
            newGame.setTime(currentGame[2]);
            newGame.setLocation(currentGame[3]);
            newGame.setGoalsH(Integer.parseInt(currentGame[6]));
            newGame.setGoalsG(Integer.parseInt(currentGame[7]));
            if (currentGame[8].equals("true"))
                newGame.setPlayed(true);
            else
                newGame.setPlayed(false);

            Team home = new Team();
            home.setName(currentGame[4]);
            newGame.setTeamH(home);

            Team guest = new Team();
            guest.setName(currentGame[5]);
            newGame.setTeamG(guest);

            for (Group g : worldCup.getGroups()) {
                for (Team team : g.getTeams()) {
                    if (team.getName().equals(currentGame[4]))
                        newGame.setTeamH(team);
                    if (team.getName().equals(currentGame[5]))
                        newGame.setTeamG(team);
                }
            }
            r16.add(newGame);
        }

        for (int k = 0; k < 4; k++, j++) {
            String[] currentGame = games.get(j);
            Game newGame = new Game();
            newGame.setIntId(Integer.parseInt(currentGame[0]));
            newGame.setDate(currentGame[1]);
            newGame.setTime(currentGame[2]);
            newGame.setLocation(currentGame[3]);
            newGame.setGoalsH(Integer.parseInt(currentGame[6]));
            newGame.setGoalsG(Integer.parseInt(currentGame[7]));
            if (currentGame[8].equals("true"))
                newGame.setPlayed(true);
            else
                newGame.setPlayed(false);

            Team home = new Team();
            home.setName(currentGame[4]);
            newGame.setTeamH(home);

            Team guest = new Team();
            guest.setName(currentGame[5]);
            newGame.setTeamG(guest);

            for (Group g : worldCup.getGroups()) {
                for (Team team : g.getTeams()) {
                    if (team.getName().equals(currentGame[4]))
                        newGame.setTeamH(team);
                    if (team.getName().equals(currentGame[5]))
                        newGame.setTeamG(team);
                }
            }
            q.add(newGame);
        }

        for (int k = 0; k < 2; k++, j++) {
            String[] currentGame = games.get(j);
            Game newGame = new Game();
            newGame.setIntId(Integer.parseInt(currentGame[0]));
            newGame.setDate(currentGame[1]);
            newGame.setTime(currentGame[2]);
            newGame.setLocation(currentGame[3]);
            newGame.setGoalsH(Integer.parseInt(currentGame[6]));
            newGame.setGoalsG(Integer.parseInt(currentGame[7]));
            if (currentGame[8].equals("true"))
                newGame.setPlayed(true);
            else
                newGame.setPlayed(false);

            Team home = new Team();
            home.setName(currentGame[4]);
            newGame.setTeamH(home);

            Team guest = new Team();
            guest.setName(currentGame[5]);
            newGame.setTeamG(guest);

            for (Group g : worldCup.getGroups()) {
                for (Team team : g.getTeams()) {
                    if (team.getName().equals(currentGame[4]))
                        newGame.setTeamH(team);
                    if (team.getName().equals(currentGame[5]))
                        newGame.setTeamG(team);
                }
            }
            s.add(newGame);
        }


        String[] currentGame = games.get(j);
        Game newGame = new Game();
        newGame.setIntId(Integer.parseInt(currentGame[0]));
        newGame.setDate(currentGame[1]);
        newGame.setTime(currentGame[2]);
        newGame.setLocation(currentGame[3]);
        newGame.setGoalsH(Integer.parseInt(currentGame[6]));
        newGame.setGoalsG(Integer.parseInt(currentGame[7]));
        if (currentGame[8].equals("true"))
            newGame.setPlayed(true);
        else
            newGame.setPlayed(false);

        Team home = new Team();
        home.setName(currentGame[4]);
        newGame.setTeamH(home);

        Team guest = new Team();
        guest.setName(currentGame[5]);
        newGame.setTeamG(guest);

        for (Group g : worldCup.getGroups()) {
            for (Team team : g.getTeams()) {
                if (team.getName().equals(currentGame[4]))
                    newGame.setTeamH(team);
                if (team.getName().equals(currentGame[5]))
                    newGame.setTeamG(team);
            }
        }
        worldCup.getFinals().setThirdGame(newGame);
        j++;

        currentGame = games.get(j);
        newGame = new Game();
        newGame.setIntId(Integer.parseInt(currentGame[0]));
        newGame.setDate(currentGame[1]);
        newGame.setTime(currentGame[2]);
        newGame.setLocation(currentGame[3]);
        newGame.setGoalsH(Integer.parseInt(currentGame[6]));
        newGame.setGoalsG(Integer.parseInt(currentGame[7]));
        if (currentGame[8].equals("true"))
            newGame.setPlayed(true);
        else
            newGame.setPlayed(false);

        home = new Team();
        home.setName(currentGame[4]);
        newGame.setTeamH(home);

        guest = new Team();
        guest.setName(currentGame[5]);
        newGame.setTeamG(guest);

        for (Group g : worldCup.getGroups()) {
            for (Team team : g.getTeams()) {
                if (team.getName().equals(currentGame[4]))
                    newGame.setTeamH(team);
                if (team.getName().equals(currentGame[5]))
                    newGame.setTeamG(team);
            }
        }
        worldCup.getFinals().setFinalGame(newGame);

        if (worldCup.getFinals().getFinalGame().isPlayed())
            worldCup.getFinals().setWinner(CtrlFinals.calculateWinner(worldCup.getFinals().getFinalGame()).getName());

        in.close();
    }

    public static void readFromXML(String filename, SoccerWC worldCup) throws IOException, JDOMException {
        if (worldCup == null) worldCup = new SoccerWC();

        Document document = new SAXBuilder().build(new BufferedInputStream(new FileInputStream(filename)));
        Element rootElement = document.getRootElement();

        // Name
        worldCup.setName(rootElement.getChild("Name").getContent().get(0).getValue());

        // Groups
        worldCup.setGroups(new Vector<>());
        for (Element groupElement : rootElement.getChild("Groups").getChildren()) {
            // Name
            Group g = new Group(groupElement.getChild("Name").getContent().get(0).getValue());

            // Teams
            Element teamsElement = groupElement.getChild("Teams");
            for (Element teamElement : teamsElement.getChildren())
                g.addTeam(new Team(teamElement.getAttribute("name").getValue(),
                        0, 0, 0, 0, 0, 0, 0));

            // Games
            Element gamesElement = groupElement.getChild("Games");
            for (Element gameElement : gamesElement.getChildren()) {
                g.addGame(new Game(
                        gameElement.getAttribute("id").getIntValue(),
                        gameElement.getChild("Date").getValue(),
                        gameElement.getChild("Time").getValue(),
                        gameElement.getChild("Location").getValue(),
                        getTeamByName(gameElement.getChild("TeamH").getValue(), g.getTeams()),
                        getTeamByName(gameElement.getChild("TeamG").getValue(), g.getTeams()),
                        Integer.parseInt(gameElement.getChild("GoalsH").getValue()),
                        Integer.parseInt(gameElement.getChild("GoalsG").getValue()),
                        Boolean.parseBoolean(gameElement.getChild("IsPlayed").getValue())
                ));
            }

            CtrlGroup.calculateGroupTable(g);
            worldCup.addGroup(g);

        }


    }

    private static Team getTeamByName(String name, List<Team> teams) {
        for (Team t : teams)
            if (Objects.equals(t.getName(), name))
                return t;
        return null;
    }

}
