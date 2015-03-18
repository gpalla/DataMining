/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining;

/**
 *
 * @author andrewchang
 */

public class RawData{
    private String date, category;
    private double amount;
    public RawData(String date, String category, double amount){
        this.date = date;
        this.category = category;
        this.amount = amount;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getDate(){
        return date;
    }
}
