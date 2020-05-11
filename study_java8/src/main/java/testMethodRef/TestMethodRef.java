package testMethodRef;

import java.util.Arrays;

public class TestMethodRef {
    public static void main(String[] args) {
        String[] stringArray = { "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);
    }
}
