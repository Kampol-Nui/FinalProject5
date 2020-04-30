
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MINI
 */
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
