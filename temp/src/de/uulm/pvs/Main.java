package de.uulm.pvs;

public class Main {
    public static void main(String[] args) {
        forEachLetters(Main::print);
        forEachLetters(Main::print2);
        forEachLetters((s1, s2, s3) -> {
            System.out.println(s1);
        });
    }

    public static void print(String s1, String s2, String s3) {
        System.out.println(s1 + " " + s2 + " " + s3);
    }

    public static void print2(String s1, String s2, String s3) {
        System.out.println(s1 + "," + s2 + "," + s3);
    }


    public static void forEachLetters(Testing t) {
        String foos[] = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        for (int i = 0; i < foos.length; i += 3) {
            t.foo(foos[i], foos[i + 1], foos[i + 2]);
        }
    }


}

interface Testing {
    void foo(String s1, String s2, String s3);
}