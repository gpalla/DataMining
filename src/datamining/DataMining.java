

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
    
    //Start calcRev
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
    //End calcRev
    
    //Start extractYear
    //Used to split all data and make specific arrays for 1 year.
    public static ArrayList<RawData> extractYear(ArrayList<RawData> mainArray, int year){
        ArrayList<RawData> selectedYear = new ArrayList<RawData>();
        String dateSplit = "/";
        
        for(int i=0; i<mainArray.size(); i++){
            String[] subArray = mainArray.get(i).getDate().split(dateSplit);
            int arrayYear = Integer.parseInt(subArray[2]);
            if(arrayYear == year){
                selectedYear.add(mainArray.get(i));
            }
        }
        
        return selectedYear;
    }
    //End extractYear
 
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
        
        ArrayList<RawData> year2008 = extractYear(mainArray, 2008);
        double cost2008 = calcCost(year2008);
        double rev2008 = calcRev(year2008);
        System.out.println("2008 Cost: "+cost2008+" 2008 Rev: "+rev2008);
        
        ArrayList<RawData> year2009 = extractYear(mainArray, 2009);
        double cost2009 = calcCost(year2009);
        double rev2009 = calcRev(year2009);
        System.out.println("2009 Cost: "+cost2009+" 2009 Rev: "+rev2009);
        
        ArrayList<RawData> year2010 = extractYear(mainArray, 2010);
        double cost2010 = calcCost(year2010);
        double rev2010 = calcRev(year2010);
        System.out.println("2010 Cost: "+cost2010+" 2010 Rev: "+rev2010);
        
        ArrayList<RawData> year2011 = extractYear(mainArray, 2011);
        double cost2011 = calcCost(year2011);
        double rev2011 = calcRev(year2011);
        System.out.println("2011 Cost: "+cost2011+" 2011 Rev: "+rev2011);
        
        ArrayList<RawData> year2012 = extractYear(mainArray, 2012);
        double cost2012 = calcCost(year2012);
        double rev2012 = calcRev(year2012);
        System.out.println("2012 Cost: "+cost2012+" 2012 Rev: "+rev2012);
        
        ArrayList<RawData> year2013 = extractYear(mainArray, 2013);
        double cost2013 = calcCost(year2013);
        double rev2013 = calcRev(year2013);
        System.out.println("2013 Cost: "+cost2013+" 2009 Rev: "+rev2013);
	
    }
    
}
