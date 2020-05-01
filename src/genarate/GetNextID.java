package genarate;

import java.util.concurrent.atomic.AtomicLong;

public class GetNextID {
    private static AtomicLong numberGenerator = new AtomicLong(1000000000);


    public static long getNext() {
        return numberGenerator.getAndIncrement();
    }


}