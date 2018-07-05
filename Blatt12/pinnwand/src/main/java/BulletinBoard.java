public class BulletinBoard {

    synchronized void publishToBoard(String[] messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }
}
