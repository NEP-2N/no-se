
import java.util.Scanner;

public class Programa_11 extends Thread{
	char c;

    public Programa_11(char c){
       this.c=c;
    }

    public void run(){
       System.out.print(c);
    }

    public static void main(String args[]){
       Scanner lee=new Scanner(System.in);
       System.out.print("Escribe una palabra: ");
       String palabra = lee.nextLine();
       System.out.print("\n");
   
       Programa_11[] hilos=new Programa_11[palabra.length()];
       
       for(int i=0;i<palabra.length();i++){
            hilos[i]=new Programa_11(palabra.charAt(i));
       }

    	for(int i=0;i<palabra.length();i++){
			hilos[i].start();
		}

       for(int i=0;i<palabra.length();i++){
       	   try{
       	   	  hilos[i].join();
       	      }catch(InterruptedException e){}  
       }
       System.out.print("\n");
    }
}
