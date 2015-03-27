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
public class CategoryData {
   private String category;
    private double cost, rev;
    
    public CategoryData(String category){
        this.category = category;
    }
    
    public String getCategory(){
        return category;
    }
    
    public double getCost(){
        return cost;
    }
    
    public double getRev(){
        return rev;
    }
    
    public void setCategory(String category){
        this.category = category;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }
    
    public void setRev(double rev){
        this.rev = rev;
    }
}
