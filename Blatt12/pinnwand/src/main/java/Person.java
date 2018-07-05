import java.util.Arrays;

public class Person extends Thread {
    private BulletinBoard bulletinBoard;
    private int messageCount;
    private String name;
    private String[] messages;

    Person(BulletinBoard bulletinBoard, int messageCount, String name) {
        this.bulletinBoard = bulletinBoard;
        this.messageCount = messageCount;
        this.name = name;
        messages = new String[messageCount];
        for (int i = 0; i < messageCount; i++) {
            messages[i] = name + ": " + Integer.toString(i);
        }
    }

    @Override
    public void run() {
        bulletinBoard.publishToBoard(messages);
    }

    @Override
    public String toString() {
        return "Person{" +
                "bulletinBoard=" + bulletinBoard +
                ", messageCount=" + messageCount +
                ", name='" + name + '\'' +
                ", messages=" + (messages == null ? null : Arrays.asList(messages)) +
                '}';
    }
}
