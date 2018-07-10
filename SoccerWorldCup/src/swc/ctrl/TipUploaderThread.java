package swc.ctrl;

import swc.data.Tip;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Jonas Otto
 */
public class TipUploaderThread extends Thread {
    private boolean result;
    private String betterEmail;
    private String betterPin;
    private Tip tip;

    public TipUploaderThread(String betterEmail, String betterPin, Tip tip) {
        this.betterEmail = betterEmail;
        this.betterPin = betterPin;
        this.tip = tip;
        System.out.printf("Thread [%s]: created\n", this.toString());
    }

    @Override
    public void run() {
        try {
            System.out.printf("Thread [%s]: Calling betting Service\n", this.toString());
            URL url = new URL("http://swc.dbis.info/api/Betting/" + betterEmail + "/" + betterPin + "/" + tip.getGameId() + "/" + tip.getGoalsHome() + "/" + tip.getGoalsGuest());
            Scanner sc = new Scanner(url.openStream());
            result = sc.nextBoolean();
            System.out.printf("Thread [%s]: Betting Service returned %b\n", this.toString(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean getResult() {
        return result;
    }
}
