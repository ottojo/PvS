import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        BulletinBoard bulletinBoard = new BulletinBoard();
        List<Person> personList = new LinkedList<>();
        personList.add(new Person(bulletinBoard, 40, "P1"));
        personList.add(new Person(bulletinBoard, 70, "P2"));
        personList.add(new Person(bulletinBoard, 30, "P3"));
        personList.add(new Person(bulletinBoard, 40, "P4"));
        personList.add(new Person(bulletinBoard, 30, "P5"));
        personList.add(new Person(bulletinBoard, 30, "P6"));
        personList.add(new Person(bulletinBoard, 30, "P7"));
        personList.add(new Person(bulletinBoard, 30, "P8"));
        personList.add(new Person(bulletinBoard, 30, "P9"));
        personList.forEach(Thread::start);
    }
}
