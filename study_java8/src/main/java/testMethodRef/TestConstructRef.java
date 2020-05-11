package testMethodRef;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class TestConstructRef {
    public static void main(String[] args) {
        Supplier<List<User>> userSupplier = () -> new ArrayList<>();
        List<User> user = userSupplier.get();

        Supplier<List<User>> userSupplier2 = ArrayList<User>::new;
        List<User> user2 = userSupplier.get();
    }
}
