

package datamining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

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
    
    //Calc y=mx+b
    public static double forecastCost(ArrayList<yearStats> ys, int desiredYear){
        int n = ys.size();
        
        
        double a = 0;
        for(int i = 0; i<n; i++){
            a += ys.get(i).getYear() * ys.get(i).getCost();
        }
        a = a*n;
        
        
        
        double b = 0;
        int bY = 0;
        int bC = 0;
        for(int i = 0; i<n; i++){
            bY += ys.get(i).getYear();
            bC += ys.get(i).getCost();
            b = bY * bC;
        }
        
        
        
        double c = 0;
        for(int i = 0; i<n; i++){
            c+= Math.pow(ys.get(i).getYear(), 2);
        }
        c = c*n;
        
        
        double d = 0;
        for(int i = 0; i<n; i++){
            d = ys.get(i).getYear();
        }
        d = Math.pow(d, 2);
        
        double m = (a - b) / (c - d); 
        
        
        double e = 0;
        for(int i = 0; i<n; i++){
            e += ys.get(i).getCost();
        }
        
        
        double f = 0;
        for(int i = 0; i<n; i++){
            f += ys.get(i).getYear();
        }
        f = f*m;
        double yint = ((e-f)/n);
        
        double forcast = m*desiredYear+yint;
        
        return forcast;
        
    }
    
    public static double forecastRev(ArrayList<yearStats> ys, int desiredYear){
        int n = ys.size();
        
        
        double a = 0;
        for(int i = 0; i<n; i++){
            a += ys.get(i).getYear() * ys.get(i).getRev();
        }
        a = a*n;
        
        
        
        double b = 0;
        int bY = 0;
        int bC = 0;
        for(int i = 0; i<n; i++){
            bY += ys.get(i).getYear();
            bC += ys.get(i).getRev();
            b = bY * bC;
        }
        
        
        
        double c = 0;
        for(int i = 0; i<n; i++){
            c+= Math.pow(ys.get(i).getYear(), 2);
        }
        c = c*n;
        
        
        double d = 0;
        for(int i = 0; i<n; i++){
            d = ys.get(i).getYear();
        }
        d = Math.pow(d, 2);
        
        double m = (a - b) / (c - d); 
        
        
        double e = 0;
        for(int i = 0; i<n; i++){
            e += ys.get(i).getRev();
        }
        
        
        double f = 0;
        for(int i = 0; i<n; i++){
            f += ys.get(i).getYear();
        }
        f = f*m;
        double yint = ((e-f)/n);
        
        double forcast = m*desiredYear+yint;
        
        return forcast;
        
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
        ArrayList<RawData> cronological = sortDate(mainArray);
        ArrayList<yearStats> Stats = new ArrayList<yearStats>();
        String dateSplit = "/";
        String[] firstDate = cronological.get(0).getDate().split(dateSplit);
        int yearBegin = Integer.parseInt(firstDate[2]);
        
        int lastYearLoc = cronological.size() - 1;
        String[] lastDate = cronological.get(lastYearLoc).getDate().split(dateSplit);
        int yearEnd = Integer.parseInt(lastDate[2]);
        
        
        for(int i = yearBegin; i<=yearEnd; i++ ){
            ArrayList<RawData> sYear = extractYear(mainArray, i);
            double sCost = calcCost(sYear);
            double sRev = calcRev(sYear);
            System.out.println("Year : "+i+" Cost: "+sCost+" Revenue: "+sRev);
            yearStats ys = new yearStats(i, sCost, sRev);
            Stats.add(ys);
        }
        
        ArrayList<CategoryData> CategoryList = new ArrayList<CategoryData>();
               
        for(int i = 0; i<CategoryList.size();i++){
            System.out.println(CategoryList.get(i).getCategory());
        }
        
        for(int i=0; i<Stats.size(); i++){
           double yProfit = Stats.get(i).getRev() + Stats.get(i).getCost();
           System.out.println(Stats.get(i).getYear()+" Profit: "+yProfit);
        }
        
        
        double cost = calcCost(mainArray);
        double rev = calcRev(mainArray);
        System.out.println("Total Cost: "+cost+" Total Rev: "+rev);
        double profit = rev + cost;
        System.out.println("Total Profit: "+profit);
        
        int forecastYear = yearEnd + 1;
        double fCost14 = forecastCost(Stats,forecastYear);
        double fRev14 = forecastRev(Stats,forecastYear);
        System.out.println("Year: "+forecastYear+" Forecasted Cost: "+fCost14+" Forecasted Rev: "+fRev14);        
       	
        
    }
    
}
