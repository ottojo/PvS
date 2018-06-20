package swc.pers;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WriteWC {

    public static void writeToCSV(String filename, SoccerWC worldCup) throws IOException {
        FileWriter writer = new FileWriter(filename);

        for (Group g : worldCup.getGroups()) {
            writer.append(worldCup.getName() + "\n");
            writer.append(g.getStrGroupName() + ": \n");
            writer.append("Teams: \n");
            writer.append("Position, Team, Played, Won, Draw, Loss, GF, GA, Points\n");
            for (int i = 0; i < g.getTeams().size(); i++) {
                Team current = g.getTeams().get(i);
                writer.append(i + ",");
                writer.append(current.getName() + ",");
                writer.append(current.getPlayed() + ",");
                writer.append(current.getWon() + ",");
                writer.append(current.getDraw() + ",");
                writer.append(current.getLoss() + ",");
                writer.append(current.getGf() + ",");
                writer.append(current.getGa() + ",");
                writer.append(current.getPoints() + "\n");
            }
            writer.append("Games: \n");
            writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
            for (int i = 0; i < g.getGames().size(); i++) {
                Game current = g.getGames().get(i);
                writer.append(current.getIntId() + ",");
                writer.append(current.getDate() + ",");
                writer.append(current.getTime() + ",");
                writer.append(current.getLocation() + ",");
                writer.append(current.getTeamH().getName() + ",");
                writer.append(current.getTeamG().getName() + ",");
                writer.append(current.getGoalsH() + ",");
                writer.append(current.getGoalsG() + ",");
                writer.append(current.isPlayed() + "\n");
            }
        }

        writer.append("Finals: \n");
        writer.append("Round of 16: \n");
        writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
        for (int i = 0; i < worldCup.getFinals().getRoundOf16().size(); i++) {
            Game current = worldCup.getFinals().getRoundOf16().get(i);
            writer.append(current.getIntId() + ",");
            writer.append(current.getDate() + ",");
            writer.append(current.getTime() + ",");
            writer.append(current.getLocation() + ",");
            writer.append(current.getTeamH().getName() + ",");
            writer.append(current.getTeamG().getName() + ",");
            writer.append(current.getGoalsH() + ",");
            writer.append(current.getGoalsG() + ",");
            writer.append(current.isPlayed() + "\n");
        }

        writer.append("Quarterfinals: \n");
        writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
        for (int i = 0; i < worldCup.getFinals().getQuarterFinals().size(); i++) {
            Game current = worldCup.getFinals().getQuarterFinals().get(i);
            writer.append(current.getIntId() + ",");
            writer.append(current.getDate() + ",");
            writer.append(current.getTime() + ",");
            writer.append(current.getLocation() + ",");
            writer.append(current.getTeamH().getName() + ",");
            writer.append(current.getTeamG().getName() + ",");
            writer.append(current.getGoalsH() + ",");
            writer.append(current.getGoalsG() + ",");
            writer.append(current.isPlayed() + "\n");
        }

        writer.append("Semifinals: \n");
        writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
        for (int i = 0; i < worldCup.getFinals().getSemiFinals().size(); i++) {
            Game current = worldCup.getFinals().getSemiFinals().get(i);
            writer.append(current.getIntId() + ",");
            writer.append(current.getDate() + ",");
            writer.append(current.getTime() + ",");
            writer.append(current.getLocation() + ",");
            writer.append(current.getTeamH().getName() + ",");
            writer.append(current.getTeamG().getName() + ",");
            writer.append(current.getGoalsH() + ",");
            writer.append(current.getGoalsG() + ",");
            writer.append(current.isPlayed() + "\n");
        }

        writer.append("Match for third Place: \n");
        writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
        Game current = worldCup.getFinals().getThirdGame();
        writer.append(current.getIntId() + ",");
        writer.append(current.getDate() + ",");
        writer.append(current.getTime() + ",");
        writer.append(current.getLocation() + ",");
        writer.append(current.getTeamH().getName() + ",");
        writer.append(current.getTeamG().getName() + ",");
        writer.append(current.getGoalsH() + ",");
        writer.append(current.getGoalsG() + ",");
        writer.append(current.isPlayed() + "\n");

        writer.append("Final: \n");
        writer.append("Game Id, Date, Time, Venue, Home Team, Guest Team, GH, GG, IsPlayed\n");
        current = worldCup.getFinals().getFinalGame();
        writer.append(current.getIntId() + ",");
        writer.append(current.getDate() + ",");
        writer.append(current.getTime() + ",");
        writer.append(current.getLocation() + ",");
        writer.append(current.getTeamH().getName() + ",");
        writer.append(current.getTeamG().getName() + ",");
        writer.append(current.getGoalsH() + ",");
        writer.append(current.getGoalsG() + ",");
        writer.append(current.isPlayed() + "\n");

        writer.flush();
        writer.close();

    }

    /**
     * Writes {@link SoccerWC} to XML File
     *
     * @param filename XML File Name
     * @param worldCup {@link SoccerWC} to save
     * @throws IOException on File Saving Failed
     * @author Jonas Otto
     */
    public static void writeToXML(String filename, SoccerWC worldCup) throws IOException {
        Element rootElement = new Element("SoccerWC");
        Element name = new Element("Name").addContent(worldCup.getName());

        Element groups = new Element("Groups");
        for (Group currentGroup : worldCup.getGroups()) {
            Element groupElement = new Element("Group");
            Element groupName = new Element("Name").addContent(currentGroup.getStrGroupName());
            Element teamsElement = new Element("Teams");
            for (Team currentTeam : currentGroup.getTeams()) {
                Element teamElement = new Element("Team");
                teamElement.setAttribute("name", currentTeam.getName());
                teamsElement.addContent(teamElement);
            }

            Element gamesElement = gameListElement("Games", currentGroup.getGames());

            groupElement.addContent(Arrays.asList(groupName, teamsElement, gamesElement));
            groups.addContent(groupElement);
        }

        Element finals = new Element("Finals");
        finals.addContent(Arrays.asList(
                gameListElement("RoundOf16", worldCup.getFinals().getRoundOf16()),
                gameListElement("Quarterfinals", worldCup.getFinals().getQuarterFinals()),
                gameListElement("Semifinals", worldCup.getFinals().getSemiFinals()),
                gameElement("ThirdGame", worldCup.getFinals().getThirdGame()),
                gameElement("FinalGame", worldCup.getFinals().getFinalGame()),
                new Element("Winner").addContent(worldCup.getFinals().getWinner())
        ));

        rootElement.addContent(Arrays.asList(name, groups, finals));
        new XMLOutputter(Format.getPrettyFormat()).output(new Document(rootElement),
                new BufferedOutputStream(new FileOutputStream(filename)));
    }

    /**
     * Encodes {@link Game} to XML {@link Element}
     *
     * @param name Name of XML Element
     * @param game Game to encode
     * @return Encoded Game Element
     * @author Jonas Otto
     */
    private static Element gameElement(String name, Game game) {
        Element gameElement = new Element(name);
        gameElement.setAttribute("id", game.getIntId() + "");
        gameElement.addContent(Arrays.asList(
                new Element("TeamH").addContent(game.getTeamH().getName()),
                new Element("TeamG").addContent(game.getTeamG().getName()),
                new Element("GoalsH").addContent(game.getGoalsH() + ""),
                new Element("GoalsG").addContent(game.getGoalsG() + ""),
                new Element("Date").addContent(game.getDate()),
                new Element("Location").addContent(game.getLocation()),
                new Element("Time").addContent(game.getTime()),
                new Element("IsPlayed").addContent(game.isPlayed() + "")
        ));
        return gameElement;
    }

    /**
     * Encodes {@link List} of {@link Game} to XML {@link Element}
     * Games are encoded via {@link #gameElement(String, Game)}, using "Game" as name.
     *
     * @param name  Name of the XML Element
     * @param games List of Games
     * @return Encoded list of Games
     */
    private static Element gameListElement(String name, List<Game> games) {
        Element gameList = new Element(name);
        for (Game g : games) gameList.addContent(gameElement("Game", g));
        return gameList;
    }
}
