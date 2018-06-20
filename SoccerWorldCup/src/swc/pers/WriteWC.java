package swc.pers;

import java.io.FileWriter;
import java.io.IOException;

import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

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
}
