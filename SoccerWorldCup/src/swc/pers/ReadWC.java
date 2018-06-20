package swc.pers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import swc.ctrl.CtrlFinals;
import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

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
			while(!in.readLine().startsWith("Position"));
			for (int j = 0; j < 4; j++) {
				readString = in.readLine();
				teams.add(readString.split(separator));
			}
			while(!in.readLine().startsWith("Game Id"));
			for (int j = 0; j < 6; j++) {
				readString = in.readLine();
				games.add(readString.split(separator));
			}
		}
		
		// read the finals
		while(!in.readLine().startsWith("Game Id"));
		for (int j = 0; j < 8; j++) {
			readString = in.readLine();
			games.add(readString.split(separator));
		}
		
		while(!in.readLine().startsWith("Game Id"));
		for (int j = 0; j < 4; j++) {
			readString = in.readLine();
			games.add(readString.split(separator));
		}
		
		while(!in.readLine().startsWith("Game Id"));
		for (int j = 0; j < 2; j++) {
			readString = in.readLine();
			games.add(readString.split(separator));
		}
		
		while(!in.readLine().startsWith("Game Id"));
		readString = in.readLine();
		games.add(readString.split(separator));
		
		while(!in.readLine().startsWith("Game Id"));
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
			for(int k = 0; k < 4; k++,i++){
				String [] currentTeam = teams.get(i);
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
			
			for (int l = 0; l < 6; l++,j++) {
				String [] currentGame = games.get(j);
				Game newGame = new Game();
				newGame.setIntId(Integer.parseInt(currentGame[0]));
				newGame.setDate(currentGame[1]);
				newGame.setTime(currentGame[2]);
				newGame.setLocation(currentGame[3]);
				newGame.setGoalsH(Integer.parseInt(currentGame[6]));
				newGame.setGoalsG(Integer.parseInt(currentGame[7]));
				if(currentGame[8].equals("true"))
					newGame.setPlayed(true);
				else 
					newGame.setPlayed(false);		
				for (Team team : g.getTeams()) {
					if(team.getName().equals(currentGame[4]))
						newGame.setTeamH(team);
					if(team.getName().equals(currentGame[5]))
						newGame.setTeamG(team);
				}
				g.addGame(newGame);
			}
		}
		
		for (int k = 0; k < 8; k++,j++) {
			String [] currentGame = games.get(j);
			Game newGame = new Game();
			newGame.setIntId(Integer.parseInt(currentGame[0]));
			newGame.setDate(currentGame[1]);
			newGame.setTime(currentGame[2]);
			newGame.setLocation(currentGame[3]);
			newGame.setGoalsH(Integer.parseInt(currentGame[6]));
			newGame.setGoalsG(Integer.parseInt(currentGame[7]));
			if(currentGame[8].equals("true"))
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
					if(team.getName().equals(currentGame[4]))
						newGame.setTeamH(team);
					if(team.getName().equals(currentGame[5]))
						newGame.setTeamG(team);
				}
			}			
			r16.add(newGame);
		}
		
		for (int k = 0; k < 4; k++,j++) {
			String [] currentGame = games.get(j);
			Game newGame = new Game();
			newGame.setIntId(Integer.parseInt(currentGame[0]));
			newGame.setDate(currentGame[1]);
			newGame.setTime(currentGame[2]);
			newGame.setLocation(currentGame[3]);
			newGame.setGoalsH(Integer.parseInt(currentGame[6]));
			newGame.setGoalsG(Integer.parseInt(currentGame[7]));
			if(currentGame[8].equals("true"))
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
					if(team.getName().equals(currentGame[4]))
						newGame.setTeamH(team);
					if(team.getName().equals(currentGame[5]))
						newGame.setTeamG(team);
				}
			}			
			q.add(newGame);
		}
		
		for (int k = 0; k < 2; k++,j++) {
			String [] currentGame = games.get(j);
			Game newGame = new Game();
			newGame.setIntId(Integer.parseInt(currentGame[0]));
			newGame.setDate(currentGame[1]);
			newGame.setTime(currentGame[2]);
			newGame.setLocation(currentGame[3]);
			newGame.setGoalsH(Integer.parseInt(currentGame[6]));
			newGame.setGoalsG(Integer.parseInt(currentGame[7]));
			if(currentGame[8].equals("true"))
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
					if(team.getName().equals(currentGame[4]))
						newGame.setTeamH(team);
					if(team.getName().equals(currentGame[5]))
						newGame.setTeamG(team);
				}
			}			
			s.add(newGame);
		}
		
		
			String [] currentGame = games.get(j);
			Game newGame = new Game();
			newGame.setIntId(Integer.parseInt(currentGame[0]));
			newGame.setDate(currentGame[1]);
			newGame.setTime(currentGame[2]);
			newGame.setLocation(currentGame[3]);
			newGame.setGoalsH(Integer.parseInt(currentGame[6]));
			newGame.setGoalsG(Integer.parseInt(currentGame[7]));
			if(currentGame[8].equals("true"))
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
					if(team.getName().equals(currentGame[4]))
						newGame.setTeamH(team);
					if(team.getName().equals(currentGame[5]))
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
			if(currentGame[8].equals("true"))
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
					if(team.getName().equals(currentGame[4]))
						newGame.setTeamH(team);
					if(team.getName().equals(currentGame[5]))
						newGame.setTeamG(team);
				}
			}			
			worldCup.getFinals().setFinalGame(newGame);	
			
			if(worldCup.getFinals().getFinalGame().isPlayed())
				worldCup.getFinals().setWinner(CtrlFinals.calculateWinner(worldCup.getFinals().getFinalGame()).getName());
		
		in.close();
	}
	
}
