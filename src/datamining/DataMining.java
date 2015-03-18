

package datamining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DataMining {
    
    // Sort by date oldest to youngest
    public static ArrayList<RawData> sortDate(ArrayList<RawData> array){
        ArrayList<RawData> dateSort = array;
        String dateSplit = "/";
        int length = dateSort.size();
        for(int i=length; i>=0; i--){
            for(int j=0; j<length - 1; j++){
                int k = j + 1;
                String[] date1 = dateSort.get(j).getDate().split(dateSplit);
                String[] date2 = dateSort.get(k).getDate().split(dateSplit);
                int m1 = Integer.parseInt(date1[0]);
                int d1 = Integer.parseInt(date1[1]);
                int y1 = Integer.parseInt(date1[2]);
                int m2 = Integer.parseInt(date2[0]);
                int d2 = Integer.parseInt(date2[1]);
                int y2 = Integer.parseInt(date2[2]);
                
                if(y1 > y2){
                    RawData temp = dateSort.get(j);
                    dateSort.set(j, dateSort.get(k));
                    dateSort.set(k, temp);
                }
                if(y1 == y2){
                    if(m1 > m2){
                        RawData temp = dateSort.get(j);
                        dateSort.set(j, dateSort.get(k));
                        dateSort.set(k, temp);
                    }
                }
                if(y1 == y2){
                    if(m1 == m2){
                        if(d1 > d2){
                            RawData temp = dateSort.get(j);
                            dateSort.set(j, dateSort.get(k));
                            dateSort.set(k, temp);
                        }
                    }
                }
                
            }
        
        }
        return dateSort;
    }
    //End sortDate
    
    //Start Calculating Cost
    public static double calcCost(ArrayList<RawData> array){
        double cost = 0;
        for(int i=0; i<array.size();i++){
            double amount = array.get(i).getAmount();
            if(amount < 0){
                cost += amount;
            }
        }
        
        return cost;
    }
    //End calcCost
    
    public static double calcRev(ArrayList<RawData> array){
        double rev = 0;
        for(int i=0; i<array.size();i++){
            double amount = array.get(i).getAmount();
            if(amount > 0){
                rev += amount;
            }
        }
        return rev;
    }
 
    public static void main(String[] args) {
        
        ArrayList<RawData> mainArray = new ArrayList<RawData>();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please enter the files location.");
        String csvFile = sc.next();
        
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        
        try{
               br = new BufferedReader(new FileReader(csvFile));
               while((line = br.readLine()) != null){
                   String[] data = line.split(csvSplitBy);
                   String date = data[0];
                   String category = data[1];
                   double amount = Double.parseDouble(data[2]);
                   RawData rw = new RawData(date, category, amount);
                   mainArray.add(rw);
               }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
       // ArrayList<RawData> cronological = sortDate(mainArray);
        double cost = calcCost(mainArray);
        double rev = calcRev(mainArray);
        System.out.println("Total Cost: "+cost+" Total Rev: "+rev);
        
	
    }
    
}
