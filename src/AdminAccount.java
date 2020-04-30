
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;







public class AdminAccount extends Account{
    

    public AdminAccount(String username, String password, Person person) {
        super(username, password, person);

    }
    
     public void readCustomer(AdminAccount ad,String filename){
        try(FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis)){
        
            //cus = new CustomerAccount(dis.readDouble(),dis.readUTF(),dis.readUTF(),dis.readDouble());
//            while(dis.available() > 0){
//                double d = dis.readDouble();
//                String n = dis.readUTF();
//                String p = dis.readUTF();
//                double m = dis.readDouble();
//                System.out.println("Cus_ID = "+d+"  Cus_Username = "+n+"  Cus_Password = "+p+"  Cus_Money = "+m);
//            //System.out.println("Cus_ID = "+dis.readDouble()+"Cus_Username = "+dis.readUTF()+"Cus_Password = "+dis.readUTF()+"Cus_Money = "+dis.readDouble());
//            }
              int numOfmember = dis.readInt();
              AllCustomer[] readCus = new AllCustomer[numOfmember];
              for (int i = 0; i < numOfmember; i++) {
                readCus[i] = new AllCustomer(dis.readDouble(),dis.readUTF(),dis.readUTF(),dis.readDouble());
                  System.out.println(readCus[i]);
                
            }
//            for (int i = 0; i <= dis.readInt(); i++) {
//                
//            System.out.println(dis.readDouble());
//            System.out.println(dis.readUTF());
//            System.out.println(dis.readUTF());
//            System.out.println(dis.readDouble());
//                System.out.println(dis.readDouble());
//                System.out.println(dis.readUTF());
//            System.out.println(dis.readUTF());
//            System.out.println(dis.readDouble());
//            }
//                dis.readInt();
//            System.out.println(dis.readDouble());
//            System.out.println(dis.readUTF());
//            System.out.println(dis.readUTF());
//            System.out.println(dis.readDouble());}
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
     }
     
     public void WriteCustomerData(AdminAccount admin,AllCustomer allcus){
     try (   FileOutputStream fos = new FileOutputStream("file"+"_"+".dat");
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dout = new DataOutputStream(bos) ) 
        { 
            int numOfcus = allcus.getCus().length;
            //CustomerAccount [] cus = allcus.getCus();
            dout.writeInt(numOfcus);
            for (int i = 0; i < numOfcus; i++) {
                 dout.writeDouble(allcus.getCus(i).getUniqueId()); 
            dout.writeUTF(allcus.getCus(i).getUsername()); 
            dout.writeUTF(allcus.getCus(i).getPassword()); 
            dout.writeDouble(allcus.getCus(i).getMyMoney());
                
            }
            
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } 
        
    }
}
