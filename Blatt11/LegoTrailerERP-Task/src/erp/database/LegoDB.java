package erp.database;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Database connectivity class, handles all database read/write access for the
 * program
 */
public class LegoDB {

    private Connection con;

    public LegoDB() {
        openConnection();
    }

    /**
     * connection method, called once on program initialization
     */
    public Connection openConnection() {
        // MARIADB / MYSQL
        String url = "jdbc:mariadb://localhost:3306/legotrailer_db?user=root";
        try {
            this.con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * reads all Auftrag elements from the database and returns them
     *
     * @return a List of the model type Auftrag
     */
    public List<Auftrag> readAuftraege() {
        List<Auftrag> auftraege = new ArrayList<Auftrag>();
        try {
            PreparedStatement getAuftraege = con.prepareStatement("SELECT * FROM auftragsds");
            ResultSet resultSet = getAuftraege.executeQuery();
            while (resultSet.next()) {
                auftraege.add(new Auftrag(
                        resultSet.getInt("AuftrNr"),
                        resultSet.getInt("KdNr"),
                        resultSet.getString("KdAuftrNr"),
                        resultSet.getDate("KdAuftrDatum"),
                        resultSet.getDate("ErfassungsDatum")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auftraege;
    }

    /**
     * method to find the sequentially next auftragsnummer for use with a new
     * Auftrag
     *
     * @return the next unused auftragsnummer
     */
    public int readNextAuftragsnummer() {
        List<Auftrag> auftrags = readAuftraege();
        int id = 0;
        for (Auftrag a : auftrags)
            if (a.getAuftrNr() + 1 > id) id = a.getAuftrNr() + 1;
        return id;
    }

    /**
     * method for saving an Auftrag and all its Auftragspositionen(String[] teileID, int[] farbe, int[] amount) to the database
     * IMPORTANT: the java.util.Date parameter "kdAuftrDatum" must be converted to a java.sql.Date before using it in a preparedstatement
     */
    public void createAuftrag(int kdNr, String kdAuftrNr, java.util.Date kdAuftrDatum, String[] teileID, int[] farbe, int[] amount) {
        int nextAuftragsnummer = readNextAuftragsnummer(); // get the AuftragsNummer for the new auftrag before it is inserted into the database

        try (
                PreparedStatement createAuftragStatement = con.prepareStatement(
                        "INSERT INTO auftragsds(AuftrNr, KdNr, KdAuftrNr, KdAuftrDatum) VALUES (?, ?, ?, ?)");
                PreparedStatement createPosStatement = con.prepareStatement(
                        "INSERT INTO auftragsposds(AuftrNr, AuftrPos, TeileID, Farbe, AnzVonKundeBestellt) VALUES (?,?,?,?,?)")

        ) {
            // Auftrag
            createAuftragStatement.setInt(1, nextAuftragsnummer);
            createAuftragStatement.setInt(2, kdNr);
            createAuftragStatement.setString(3, kdAuftrNr);

            // Time conversion stuff, thanks stackoverflow! Can we just all use unix time + UTC?
            Instant instant = kdAuftrDatum.toInstant();     // Represents a moment on the timeline in UTC
            ZoneId zoneId = ZoneId.systemDefault();         // System Time Zone
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
            LocalDate localDate = zonedDateTime.toLocalDate();

            createAuftragStatement.setDate(4, java.sql.Date.valueOf(localDate));

            createAuftragStatement.execute();

            // AuftragsPos
            for (int i = 0; i < teileID.length; i++) {
                createPosStatement.setInt(1, nextAuftragsnummer);
                createPosStatement.setInt(2, i);
                createPosStatement.setString(3, teileID[i]);
                createPosStatement.setInt(4, farbe[i]);
                createPosStatement.setInt(5, amount[i]);

                createPosStatement.addBatch();
            }
            createPosStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads all Auftragsposition elements for a given auftragsnummer from the
     * database and returns them
     *
     * @param auftragsnummer the auftragsnummer of the Auftrag you want the Auftragspositionen
     *                       of
     * @return a List of the model type Auftragsposition
     */
    public List<Auftragsposition> readAuftragspositionen(int auftragsnummer) {

        List<Auftragsposition> auftragspositions = new ArrayList<>();
        try (PreparedStatement getAuftragsPos = con.prepareStatement(
                "SELECT * FROM auftragsposds WHERE AuftrNr = ?"
        )) {
            getAuftragsPos.setInt(1, auftragsnummer);
            ResultSet resultSet = getAuftragsPos.executeQuery();

            while (resultSet.next()) {
                Auftragsposition pos = new Auftragsposition();
                pos.setAuftragsnummer(resultSet.getInt("AuftrNr"));
                pos.setAnzahlBestellt(resultSet.getInt("AnzVonKundeBestellt"));
                pos.setAuftragspositionsnummer(resultSet.getInt("AuftrPos"));
                pos.setFarbe(resultSet.getInt("Farbe"));
                pos.setTeileID(resultSet.getString("TeileID"));
                pos.setAnzahlNochZuFertigen(resultSet.getInt("AnzNochZuFertigen"));
                pos.setAnzahlReserviert(resultSet.getInt("AnzFuerKundeReserviert"));
                pos.setFertigungBeendet(resultSet.getDate("FertigungBeendet"));
                pos.setFertigungsstatus(resultSet.getInt("FertigungsStatus"));
                pos.setVerkaufspreis(resultSet.getInt("VkPreis"));
                auftragspositions.add(pos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auftragspositions;
    }

    /**
     * checks a given teileID/farbe combination for validity by checking the TEILE
     * relation in the database, only combinations listed there are accepted
     *
     * @param teileID ID number of the part in question
     * @param farbe   color code of the part
     * @return true or false, depending if the part exists in this color
     */
    public boolean checkValidTeil(String teileID, int farbe) {
        try (PreparedStatement checkStatement = con.prepareStatement(
                "SELECT * FROM teile WHERE TeileID = ? AND Farbe = ?")
        ) {
            checkStatement.setString(1, teileID);
            checkStatement.setInt(2, farbe);

            ResultSet resultSet = checkStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * method for reading all kunden from the database
     *
     * @return an ArrayList with all kunde objects in the database (sorted by their KdNr)
     */
    public List<Kunde> readKunden() {
        List<Kunde> kunden = new ArrayList<>();

        try (
                PreparedStatement readKundenStatement = con.prepareStatement("SELECT * FROM kunden ORDER BY KdNr")
        ) {
            ResultSet resultSet = readKundenStatement.executeQuery();
            while (resultSet.next())
                kunden.add(new Kunde(
                        resultSet.getInt("KdNr"),
                        resultSet.getString("KdName"),
                        resultSet.getString("KdStadt"),
                        resultSet.getInt("Bonitaet")
                ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kunden;
    }
}
