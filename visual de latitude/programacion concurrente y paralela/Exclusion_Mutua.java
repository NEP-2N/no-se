class Agujero{
    int numPeces;

    public Agujero(int numPeces){
        this.numPeces = numPeces;
    }
}

class Esquimal extends Thread{
    int misPeces;
    Agujero agujero;

    public Esquimal(String nombre, Agujero agujero){
        super(nombre);
        this.misPeces = 0;
        this.agujero = agujero;
    }

    @Override
    public void run(){
        while(true){
            synchronized(agujero){
                if(agujero.numPeces == 0)
                    break;
                else{
                    agujero.numPeces--; // Esquimal pesca
                    misPeces++; // Esquimal guarda el pez
                }
            }
            
            try {sleep(0);} catch (InterruptedException e) {}
        }
        System.out.println(getName(/*nombre*/) + " pesque " + misPeces + " peces...");
    }
}

public class Exclusion_Mutua {
    public static void main(String[] args) {
        Agujero agujero = new Agujero(100);
        Esquimal esq1 = new Esquimal("Esquimal 1", agujero);
        Esquimal esq2 = new Esquimal("Esquimal 2", agujero);
        Esquimal esq3 = new Esquimal("Esquimal 3", agujero);
        Esquimal esq4 = new Esquimal("Esquimal 4", agujero);

        esq1.start();
        esq2.start();
        esq3.start();
        esq4.start();
    }
}
