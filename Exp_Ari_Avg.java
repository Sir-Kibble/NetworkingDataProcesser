package networking4;
import java.io.File;  
import java.io.PrintWriter;
import java.io.FileNotFoundException;  
import java.util.Scanner;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
import java.util.ArrayList;  
import java.io.IOException;  
import java.util.Random;

public class Exp_Ari_Avg{ 
public static void main (String[] args){ 
	 	try{
			Scanner fileScanner = new Scanner(new File("data.txt")); 
			PrintWriter filePrinter = new PrintWriter(new File("output.txt"));
			
			filePrinter.print("SampleRTTs\tExpAvgs\tAriAvgs");
			filePrinter.println();
			
			sampleRTT2EstimatedRTT(fileScanner, filePrinter);
			
			fileScanner.close();
			filePrinter.close();
	
		}catch(IOException e){
			System.out.println("Error opening file.");
			System.exit(0);
		}
	 }    
         static String s;
         static Random R;
         static int  temp1, temp2;
         static double n,ExAvg,AriAv;
	 public static double expAvg(double preExpAvg, int newSample){
         //EstimatedRTT = (1- )*EstimatedRTT + *SampleRTT
	 	return (.5* newSample) + .5*(preExpAvg);
	 }
	 
	 public static double ariAvg(double preAriAvg, int newSample, double n){
		return ((n-1)/n)*newSample + (1/n)*preAriAvg;
	 }
	 
	 public static void sampleRTT2EstimatedRTT(Scanner in, PrintWriter out)throws IOException{ 
	 	//in.useDelimiter("\n");
                R = new Random();
                n = 1;
                AriAv = 0.0;
                ExAvg = 0.0;
                temp2 = R.nextInt(100);
                for(int x = 0; x < 9;x++)
                    s = in.next();
                while(in.hasNext()){
                    if(s.equals("Reply")){//41
                        for(int x = 0; x < 3;x++)
                            in.next();
                        s = in.next();
                        try{
                            temp1 = Integer.parseInt(s.substring(5, 7));
                        }catch(Exception e){
                            try{
                            temp1 = Integer.parseInt(s.substring(5, 6));
                            }catch(Exception x){System.out.println("Shiz went down! ");}
                        }//end catch
                        if(n == 0){
                            AriAv = ariAvg(AriAv, temp2, n);
                            ExAvg = expAvg(ExAvg,temp2);
                            n++;
                            out.print(temp1+"\t"+AriAv+"\t"+ExAvg);
                            out.println();
                        }else{
                            AriAv = ariAvg(AriAv, temp1, n);
                            ExAvg = expAvg(ExAvg,temp1);
                            n++;
                            out.print(temp1+"\t"+AriAv+"\t"+ExAvg);
                            out.println();
                        }//end else
                        in.next();
                        s = in.next();
                }//end if
                s = in.next();    
            }//end while
         }//end sample...
	 
}  