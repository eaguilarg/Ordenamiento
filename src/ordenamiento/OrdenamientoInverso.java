/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamiento;

/**
 *
 * @author Usuario
 */
import java.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
public class OrdenamientoInverso<T extends Comparable <T>> {
    
int comparaciones=0;
//T []a = (T[]) new Object[100];
int numElem;

public OrdenamientoInverso(){
    this.numElem=0;
    
}

 //insertion sort (n^2)   ///bien
    public int InsertionSort(T a[]){
    int comparaciones=0;
        for(int i=1;i<a.length;i++){
            T aux=a[i];
            int j=i-1;
            comparaciones++;
            while(j>=0 && aux.compareTo(a[j])>0){
                
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
      
     while(a[i].compareTo(pivote)>=0 && i<j) {
         i++; // busca elemento mayor que pivote
         comparaciones++;
     }
     comparaciones++;
     while(a[j].compareTo(pivote)<0){
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
        
  
 //cocktail (n^2)  ///bien
public int cocktailSort( T[] a ){
	boolean swapped;
        int comparaciones=0;
	do {
		swapped = false;
		for (int i =0; i<=  a.length  - 2;i++) {
                    comparaciones++;
			if (a[ i ].compareTo(a[ i + 1 ])<0) {
				//test whether the two elements are in the wrong order
				T temp = a[i];
				a[i] = a[i+1];
				a[i+1]=temp;
				swapped = true;
			}
		}
		if (!swapped) {
			//we can exit the outer loop here if no swaps occurred.
			break;
		}
		swapped = false;
		for (int i= a.length - 2;i>=0;i--) {
                    comparaciones++;
			if (a[ i ].compareTo(a[ i + 1 ])<0) {
				T temp = a[i];
				a[i] = a[i+1];
				a[i+1]=temp;
				swapped = true;
			}
		}
		//if no elements have been swapped, then the list is sorted
	} while (swapped);
        return comparaciones;
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
                  if (a[i].compareTo(a[i + 1])<0) {                          
                        aux = a[i];
                        a[i] = a[i + 1];
                        a[i + 1] = aux;
                        done = true;
                       
                  }
            }                
      }
      return comparaciones;
}
     //merge sort (nlogn)
    
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
            if( a[left].compareTo(a[right]) >= 0)
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
    
       
    public String[] cargarDatos() throws FileNotFoundException {
        String[] arr = new String[17770];
        FileReader fr;

        BufferedReader br;
        File ffile=new File ("C:\\HOLA\\movie_titles2.txt");
        int id, año, i = 0;
        String titulo, linea;
        try {
            fr = new FileReader(ffile); 
            br = new BufferedReader(fr);
            linea = br.readLine();
            while (linea != null) {
                StringTokenizer st = new StringTokenizer(linea, ",");
                id = Integer.parseInt(st.nextToken());
                año = Integer.parseInt(st.nextToken());
                arr[i] = st.nextToken();
                linea = br.readLine();
                i++;
            }

        } catch (Exception e) {

            System.out.println(" ");
        }
        return arr;
    }
  
    public T[] datosAleatorios(T[] a, int tamaño){
       T []b =  (T[])new Comparable[tamaño];
        Random rnd = new Random();
        int i=0;
        T elem= a[rnd.nextInt(a.length)];
        while(i<tamaño && !contains(elem, b)){
            b[i]=elem;
            i++;
        }
                   
    return b;
            
    }
    
    
    private boolean contains(T elem, T [] a){
        boolean resp=false;//no lo contiene
        int i=0;
        T actual= a[i];
        while(i<a.length && !elem.equals(actual) ){ 
            i++;
            actual=a[i];
        }
        if(i<a.length)
            resp=true;//si lo contiene
        return resp;
    }
    
    public String[] copiaDatos(String[] a, int n){
        String[] aux=new String[n];
        for(int i=0; i<n;i++){
            aux[i]=a[i];
        }
        return aux;
    }

public static void main(String[] args) throws FileNotFoundException{
    OrdenamientoInverso o= new OrdenamientoInverso();
    Long start,end;
   
    
    
    //Datos ordenados
        String a[]=o.cargarDatos();//lectura archivo .txt
            
        //n=100
      //  String[] n1=o.copiaDatos(a, 100); 
        //    System.out.println("N=100");
            
          /*  System.out.println("Cocktail Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.cocktailSort(n1));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
           /* System.out.println("Insertion Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.InsertionSort(n1));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
          /*  System.out.println("Bubble Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.bubbleSort(n1));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
           /* System.out.println("Merge Sort");
            start=System.currentTimeMillis();
            o.mergeSort(n1);
            System.out.println("Comparaciones:"+o.comparaciones);
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
           /* System.out.println("Quick Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.quicksort(n1, 0, n1.length-1));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
        
        //n=1,000
     //   String[] n2=o.copiaDatos(a, 1000);
     //   System.out.println("N=1000");
            
          /*  System.out.println("Cocktail Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.cocktailSort(n2));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
          /*  System.out.println("Insertion Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.InsertionSort(n2));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
          /*  System.out.println("Bubble Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.bubbleSort(2));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
           /* System.out.println("Merge Sort");
            start=System.currentTimeMillis();
            o.mergeSort(n2);
            System.out.println("Comparaciones:"+o.comparaciones);
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
           /* System.out.println("Quick Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.quicksort(n2, 0, n2.length-1));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
        
        //n=10,000
        String[] n3=o.copiaDatos(a, 10000);
        System.out.println("N=10,000");
            
           /* System.out.println("CocktailSort Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.cocktailSort(n3));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
          /*  System.out.println("Insertion Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.InsertionSort(n3));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
          /*  System.out.println("Bubble Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.bubbleSort(n3));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
           /* System.out.println("Merge Sort");
            start=System.currentTimeMillis();
            o.mergeSort(n3);
            System.out.println("Comparaciones:"+o.comparaciones);
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");*/
            
            System.out.println("Quick Sort");
            start=System.currentTimeMillis();
            System.out.println("Comparaciones:"+o.quicksort(n3, 0, n3.length-1));
            end = System.currentTimeMillis();
            System.out.println("ExecutionTime: "+ ( end - start ) +" milliseconds");
    
    
}
}
