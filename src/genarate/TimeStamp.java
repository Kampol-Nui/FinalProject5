package genarate;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TimeStamp {
    private static String formattedDate;
    
    public TimeStamp(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        TimeStamp.formattedDate = dateTime.format(myFormat);
    }

    public static String getFormattedDate() {
        return formattedDate;
    }


}
