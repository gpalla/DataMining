/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

/**
 *
 * @author gpalla
 */
public class yearStats {
    int year;
    double rev,cost;
    
    public yearStats(int year, double cost, double rev){
        this.year = year;
        this.cost = cost;
        this.rev = rev;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public void setRev(double rev){
        this.rev = rev;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }
    
    public int getYear(){
        return year;
    }
    
    public double getCost(){
        return cost;
    }
    
    public double getRev(){
        return rev;
    }
}
