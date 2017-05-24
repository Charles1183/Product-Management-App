/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;
import java.io.Serializable;
/**
 *
 * @author patrick
 */
public class Product implements Serializable{
    private String code;
    private String description;
    private Double price;
    
    public void Product(){
        this.code = null;
        this.description = null;
        this.price = null;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setPrice(Double price){
        this.price = price;
    }
    
    public Double getPrice(){
        return price;
    }
}
