package swc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

public class CtrlFinals {

	public static String getStatus(SoccerWC worldCup) {
		int count = 0;
		for (Group g : worldCup.getGroups()) {
			for (Game game : g.getGames()) {
				if(game.isPlayed())
					count++;
			}
		}
		for (Game g: worldCup.getFinals().getRoundOf16()){
			if(g.isPlayed())
				count++;
		}	
		for (Game g: worldCup.getFinals().getQuarterFinals()){
			if(g.isPlayed())
				count++;
		}
		for (Game g: worldCup.getFinals().getSemiFinals()){
			if(g.isPlayed())
				count++;
		}
		if(worldCup.getFinals().getThirdGame().isPlayed())
			count++;
		if(worldCup.getFinals().getFinalGame().isPlayed())
			count++;
		if(count >= 48 && count < 64)
			return "" + count + " played, finals ongoing.";
		if(count == 64){
			return "" + count + " played, World Cup completed!";
		}	
		return "" + count + " played, group phase ongoing.";
	}
	
	public static void createDefaultFinals(SoccerWC worldCup) throws NumberFormatException, IOException {
		BufferedReader br = null;
		try {
			URL confUrl = CtrlGroup.class.getResource("/data/config/finals.cfg");
			InputStreamReader isR = new InputStreamReader(confUrl.openStream());
			br = new BufferedReader(isR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Vector<Game> round16 = worldCup.getFinals().getRoundOf16();
		round16.clear();
		Vector<Game> quarter = worldCup.getFinals().getQuarterFinals();
		quarter.clear();
		Vector<Game> semi = worldCup.getFinals().getSemiFinals();
		semi.clear();
		Game thirdGame = worldCup.getFinals().getThirdGame();
		Game finalGame = worldCup.getFinals().getFinalGame();
		worldCup.getFinals().setWinner("");
		
		for (int i = 0; i < 8; i++) {
			round16.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			round16.get(i).getTeamH().setName(br.readLine());
			round16.get(i).getTeamG().setName(br.readLine());
		}
		for (int i = 0; i < 4; i++) {
			quarter.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			quarter.get(i).getTeamH().setName(br.readLine());
			quarter.get(i).getTeamG().setName(br.readLine());
		}
		for (int i = 0; i < 2; i++) {
			semi.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			semi.get(i).getTeamH().setName(br.readLine());
			semi.get(i).getTeamG().setName(br.readLine());
		}
		thirdGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		thirdGame.setDate(br.readLine());
		thirdGame.setTime(br.readLine());
		thirdGame.setLocation(br.readLine());
		thirdGame.setTeamH(new Team());
		thirdGame.setTeamG(new Team());
		thirdGame.setGoalsH(0);
		thirdGame.setGoalsG(0);
		thirdGame.setPlayed(false);
		
		thirdGame.getTeamH().setName(br.readLine());
		thirdGame.getTeamG().setName(br.readLine());
		
		
		finalGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		finalGame.setDate(br.readLine());
		finalGame.setTime(br.readLine());
		finalGame.setLocation(br.readLine());
		finalGame.setTeamH(new Team());
		finalGame.setTeamG(new Team());
		finalGame.setGoalsH(0);
		finalGame.setGoalsG(0);
		finalGame.setPlayed(false);
		
		finalGame.getTeamH().setName(br.readLine());
		finalGame.getTeamG().setName(br.readLine());
	}

    public static void calculateFinals(SoccerWC worldCup) {
        worldCup.getGroups().forEach(CtrlGroup::calculateGroupTable);

        Vector<Game> roundOf16 = new Vector<>(8);
        for (int i = 0; i < 8; i++) {
            Game game = new Game();
            game.setTeamH(worldCup.getGroups().get(i < 4 ? 2 * i : 2 * (i - 4) + 1).getTeams().get(0));
            game.setTeamG(worldCup.getGroups().get(i < 4 ? 2 * i + 1 : 2 * (i - 4)).getTeams().get(1));

            // If this game has not changed, do not reset it thank you very much
            if (game.getTeamG().equals(worldCup.getFinals().getRoundOf16().get(i).getTeamG())
                    && game.getTeamH().equals(worldCup.getFinals().getRoundOf16().get(i).getTeamH()))
                game = worldCup.getFinals().getRoundOf16().get(i);

            roundOf16.add(game);
        }
        worldCup.getFinals().setRoundOf16(roundOf16);

        Vector<Game> quarterFinals = new Vector<>();
        for (int i = 0; i < 4; i++) {

            if (!worldCup.getFinals().getRoundOf16().get(2 * i).isPlayed() || !worldCup.getFinals().getRoundOf16().get((2 * i) + 1).isPlayed()) {
                quarterFinals.add(new Game());
                continue;
            }

            Game g = worldCup.getFinals().getRoundOf16().get(2 * i);
            // Im Viertelfinale gibts kein unentschieden oder?
            Team winner1 = g.getGoalsH() > g.getGoalsG() ? g.getTeamH() : g.getTeamG();
            g = worldCup.getFinals().getRoundOf16().get((2 * i) + 1);
            Team winner2 = g.getGoalsH() > g.getGoalsG() ? g.getTeamH() : g.getTeamG();
            Game newGame = new Game();
            newGame.setTeamH(winner1);
            newGame.setTeamG(winner2);

            if (winner1.equals(worldCup.getFinals().getQuarterFinals().get(i).getTeamH())
                    && winner2.equals(worldCup.getFinals().getQuarterFinals().get(i).getTeamG()))
                newGame = g;

            quarterFinals.add(newGame);
        }

        worldCup.getFinals().setQuarterFinals(quarterFinals);

        Vector<Game> semiFinals = new Vector<>();

        for (int i = 0; i < 2; i++) {
            if (!worldCup.getFinals().getQuarterFinals().get(2 * i).isPlayed() || !worldCup.getFinals().getQuarterFinals().get((2 * i) + 1).isPlayed()) {
                semiFinals.add(new Game());
                continue;
            }
            Game g = worldCup.getFinals().getQuarterFinals().get(2 * i);
            Team winner1 = g.getGoalsH() > g.getGoalsG() ? g.getTeamH() : g.getTeamG();
            g = worldCup.getFinals().getQuarterFinals().get((2 * i) + 1);
            Team winner2 = g.getGoalsH() > g.getGoalsG() ? g.getTeamH() : g.getTeamG();
            Game newGame = new Game();
            newGame.setTeamH(winner1);
            newGame.setTeamG(winner2);

            if (winner1.equals(worldCup.getFinals().getSemiFinals().get(i).getTeamH())
                    && winner2.equals(worldCup.getFinals().getSemiFinals().get(i).getTeamG()))
                newGame = g;

            semiFinals.add(newGame);
        }

        worldCup.getFinals().setSemiFinals(semiFinals);

        Game finalGame = new Game();
        if (!worldCup.getFinals().getSemiFinals().get(0).isPlayed() || !worldCup.getFinals().getSemiFinals().get(1).isPlayed()) {
            worldCup.getFinals().setFinalGame(finalGame);
        }
        Game g = worldCup.getFinals().getSemiFinals().get(0);
        Team winner1 = g.getGoalsH() > g.getGoalsG() ? g.getTeamH() : g.getTeamG();
        g = worldCup.getFinals().getSemiFinals().get(1);
        Team winner2 = g.getGoalsH() > g.getGoalsG() ? g.getTeamH() : g.getTeamG();
        finalGame.setTeamH(winner1);
        finalGame.setTeamG(winner2);

        worldCup.getFinals().setFinalGame(finalGame);

    }
}
