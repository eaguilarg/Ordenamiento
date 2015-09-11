/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ordenamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Ordenamiento <T extends Comparable <T>> {
 
int comparaciones=0;
T []a = (T[]) new Comparable[100];
int numElem;

public Ordenamiento(){
    this.numElem=0;
    
}

public T[] contenido(){
    return a;
}


private void add(int id,int date, String nombre){
  int i=0; 
  Pelicula p=new Pelicula(id,date,nombre);
  
   while(a[i]!=null && i<a.length){
       i++;
   }
   if(i==a.length){
       expandCapacity();
   }
   (Pelicula[])this.a[i]= p;
  numElem++; 
}

    private void expandCapacity() {
		T[] datosMasGrandes = (T[]) new Object[a.length * 2];
		for (int i = 0; i < a.length; i++) {
			datosMasGrandes[i] = a[i];
		}
		a = datosMasGrandes;
	}

  


 //insertion sort (n^2)   ///bien
    public int InsertionSort(T a[]){
    int comparaciones=0;
        for(int i=1;i<a.length;i++){
            T aux=a[i];
            int j=i-1;
            comparaciones++;
            while(j>=0 && aux.compareTo(a[j])<0){
                
                a[j+1]=a[j]; //haces el hueco para insercion
                j--;
            }
            a[j+1]=aux;            //insertas elemento
        }
        return comparaciones;
            
    }
    
  //quick sort  (n^2)  ///bien
    
    public int quicksort(T a[], int izq, int der) {

  T pivote=a[izq]; // tomamos primer elemento como pivote
  int i=izq; // i realiza la búsqueda de izquierda a derecha
  int j=der; // j realiza la búsqueda de derecha a izquierda
  T aux;
  int comparaciones=0;
 
  while(i<j){            // mientras no se crucen las búsquedas
      
     while(a[i].compareTo(pivote)<=0 && i<j) {
         i++; // busca elemento mayor que pivote
         comparaciones++;
     }
     comparaciones++;
     while(a[j].compareTo(pivote)>0){
         j--; // busca elemento menor que pivote
         comparaciones++;
     }
     if (i<j) {                      // si no se han cruzado                      
         aux= a[i];                  // los intercambia
         a[i]=a[j];
         a[j]=aux;
     }
   }
  
   a[izq]=a[j]; // se coloca el pivote en su lugar de forma que tendremos
   a[j]=pivote; // los menores a su izquierda y los mayores a su derecha
   if(izq<j-1)
      quicksort(a,izq,j-1); // ordenamos subarray izquierdo
   if(j+1 <der)
      quicksort(a,j+1,der); // ordenamos subarray derecho
   
   
   return comparaciones;
}
        
 //merge sort (nlogn)
  
    
    
 //Gnome sort (n^2)  ///bien
    public int GnomeSort(T a[]){
        int comparaciones=0;
        
        for(int i=1; i<a.length;){
            comparaciones++;
            
            if(a[i-1].compareTo(a[i])<0) 
                i++;
                         
            else {
                T aux=a[i];
                a[i]=a[i-1];
                a[i-1]= aux;
                --i;
                if(i==0)
                    i=1;
            }
        
        }return comparaciones;
    }
    
    
         
    public void imprime(T a[]){
        System.out.print("[ ");
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+ " ");
        }
        System.out.print("]");
    }
      
    
    
    //bubble sort (n^2)   
    public int bubbleSort(T a[]) {
      boolean done = true;
      int j = 0, comparaciones=0;
      T aux;
      while (done) {
            done = false;
            j++;
             
            for (int i = 0; i < a.length - j; i++) {comparaciones++;                                       
                  if (a[i].compareTo(a[i + 1])>0) {                          
                        aux = a[i];
                        a[i] = a[i + 1];
                        a[i + 1] = aux;
                        done = true;
                       
                  }
            }                
      }
      return comparaciones;
}
    
    
	public void mergeSort(T [ ] a)	{
		Comparable[] tmp = new Comparable[a.length];
		 mergeSort(a, (T[])tmp,  0,  a.length - 1);
                
	}


    private void mergeSort(T [ ] a, T [ ] tmp, int left, int right)	{
        
		if( left < right ){
			int center = (left + right) / 2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
                        
		}
                
	}


    private  void merge(T[ ] a, T[ ] tmp, int left, int right, int rightEnd )    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        
        while(left <= leftEnd && right <= rightEnd){
            comparaciones++;
            if( a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];
        }

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
        
       
        
    }
    
    public T[] cargarDatos(){
     String nombrePeli;
     int date, idPeli;
     Scanner lectura= null;
     File doc=new File ("movie_titles2.txt");
     String contenido[];
     
     try{
         lectura=new Scanner(doc);
     }catch(Exception e){
         System.exit(-1);
     }
    String linea="";
     while(lectura.hasNextLine()){
         linea =lectura.nextLine();
         contenido = linea.split("\\ ,");
         if(contenido.length == 5){
             idPeli= Integer.parseInt(contenido[0]);
             date=Integer.parseInt(contenido[1]);
             nombrePeli=contenido[2];
             add(idPeli,date,nombrePeli);
         }
             
         
     }
     lectura.close();
     
     
     return a;
    }
    

 
    
    public static void main(String[] args) {
        Integer a[] = {1,2,3,4}; Ordenamiento o= new Ordenamiento(); 
      // o.imprime(a);
     //System.out.println("\n" +o.quicksort(a, 0, a.length-1));
    // System.out.println(o.InsertionSort(a));
    // System.out.println(o.bubbleSort(a));
    //System.out.println(o.GnomeSort(a));
     //o.mergeSort(a);
    // System.out.println(o.comparaciones);
     
      
      o.add(15291, 1984,"'Allo 'Allo!: Series 1");
      o.add(5183,1997,"100 Years of Comedy");
      o.add(8088,1947,"13 Rue Madeleine");
        
       o.imprime(o.contenido());
       
      
       
        
        
        
    }

    
}