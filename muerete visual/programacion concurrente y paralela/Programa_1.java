
public class Programa_1 extends Thread{
   int resultado=0;

   static int resultado_final = 0;

   public Programa_1(int inicio, int fin){
      for(int i=inicio; i<=fin; i++){
         resultado += i;
      }
   }

   public void run(){
      System.out.print(resultado);

      resultado_final += resultado;
   }

   public static void main(){
      int N=78;
      int cantidad = N/4;


      Programa_1[] hilos = new Programa_1[4];

      for(int i=0; i<3; i++){
         int inicio = (cantidad*i)+1;
         int fin = cantidad*(i+1);

         System.out.println("Inicio: "+inicio+" Fin: "+fin);

         hilos[i] = new Programa_1(inicio, fin);
      }
      int inicio = (cantidad*3)+1;
      int fin = N;

      System.out.println("Inicio: "+inicio+" Fin: "+fin);

      hilos[3] = new Programa_1(inicio, fin);

      for(int i=0; i<4; i++){
         hilos[i].start();
         try{
            hilos[i].join();
         }catch(InterruptedException e){}
         
         if(i+1 != 4) System.out.print(" + ");
      }

      System.out.print(" = " + resultado_final + "\n");


      for(int i=1;i<=3;i++){
         System.out.print(i);
      }
   }
}
