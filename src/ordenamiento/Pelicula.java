/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ordenamiento;

/**
 *
 * @author eaguilarg
 */
public class Pelicula<T extends Comparable<T>> {
    private int date;
    private int id;
    private String nombre;
    T[] contener;
    
   
    
    public Pelicula(int id,int date, String nombre){
        this.id=id;
        this.date=date;
        this.nombre=nombre;
        
    }
    public Pelicula(){
        
    }
    
    public String getNombre(){
        return nombre;
    }
    public int getDate(){
        return date;
    }
    public int getId(){
        return id;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setDate(int date){
        this.date=date;
    }
    public void setId(int Id){
        this.id=Id;
    }
    
    public int compareTo(Pelicula otra) {
	return this.nombre.compareTo(otra.getNombre());
	}
    
    

    
    
    
    
    
    
    
    
    
}
