package de.uulm.pvs.blatt5.aufgabe2;

public class Test {
    public static void main(String[] args) {
        BlackBoard blackBoard = new BlackBoard();
        Student s1 = new Student("Linus", blackBoard);
        Student s2 = new Student("Richard", blackBoard);
        Student s3 = new Student("Bill", blackBoard);

        s1.addMessageToBlackBoard("Try this OS: linux.org");
        s2.addMessageToBlackBoard("Actually, it's GNU/Linux");
        s3.addMessageToBlackBoard("Whatever dudes.");
    }
}
