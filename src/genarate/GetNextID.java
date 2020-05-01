package genarate;

import java.util.concurrent.atomic.AtomicLong;

public class GetNextID {
    private static AtomicLong numberGenerator = new AtomicLong(999999999);


    public static long getNext() {
        return numberGenerator.getAndIncrement();
    }


}