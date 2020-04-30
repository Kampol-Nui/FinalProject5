package genarate;

import java.util.concurrent.atomic.AtomicLong;

public class GetNextID {
    private static AtomicLong numberGenerator = new AtomicLong(100000000);
    private static AtomicLong numberGenerator2 = new AtomicLong();

    public static long getNext() {
        return numberGenerator.getAndIncrement();
    }
    public static long getCartID() {
        return numberGenerator2.getAndIncrement();
    }

}