package genarate;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeStamp {
    private static String formattedDate;
    
    public TimeStamp(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        formattedDate = dateTime.format(myFormat);
        
    }

    @Override
    public String toString() {
        return formattedDate;
    }


}
