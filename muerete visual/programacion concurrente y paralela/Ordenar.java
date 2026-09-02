import java.util.Random;

public class Ordenar extends Thread {
    private String algoritmo;
    private int arreglo[];

    public Ordenar(String algoritmo, int arreglo[]) {
        this.algoritmo = algoritmo;
        this.arreglo = arreglo;
    }

    public static void QuickSort(int arreglo[], int bajo, int alto) {
        if (bajo >= alto)
            return;
        
        int pivote = arreglo[alto];
        int a = bajo;

        int temp;

        for (int i = bajo; i < alto; i++) {
            if (arreglo[i] <= pivote) {
                temp = arreglo[a];
                arreglo[a++] = arreglo[i];
                arreglo[i] = temp;
            }
        }

        arreglo[alto] = arreglo[a];
        arreglo[a] = pivote;

        QuickSort(arreglo, bajo, a - 1);
        QuickSort(arreglo, a + 1, alto);
    }

    @Override
    public void run() {

        boolean ordenado = false;
        int temp;

        switch (algoritmo) {
            case "Burbuja":

                while (!ordenado) {
                    ordenado = true;

                    for (int i = 0; i < arreglo.length - 1; i++) {
                        if (arreglo[i] > arreglo[i + 1]) {
                            ordenado = false;
                            temp = arreglo[i];

                            arreglo[i] = arreglo[i + 1];
                            arreglo[i + 1] = temp;

                        }
                    }
                }

            case "Insercion":
                while (!ordenado) {
                    ordenado = true;

                    for (int i = 0; i < arreglo.length - 1; i++) {
                        if (arreglo[i] > arreglo[i + 1]) {
                            ordenado = false;
                            temp = arreglo[i + 1];

                            for (int j = i + 1; j > 0; j--) {
                                arreglo[j] = arreglo[j - 1];

                                if (arreglo[j - 1] < arreglo[j])
                                    break;
                                if (j == 1)
                                    arreglo[0] = temp;
                            }
                        }
                    }
                }

            case "QuickSort":
                QuickSort(arreglo, 0, arreglo.length - 1);

        }

        // System.out.println(algoritmo + " ha terminado de ordenar los datos: ");
        // for (int i = 0; i < arreglo.length - 1; i++) {
        //     System.out.print(arreglo[i] + ", ");
        // }
        // System.out.println(arreglo[arreglo.length - 1]);
    }

    public static void main(String[] args) {
        boolean esperar = false;

        int size = 100;
        int arreglo[][] = new int[3][size];
        Random random = new Random();

        for(int i=0; i<3; i++)
            for (int j = 0; j < size; j++)
                arreglo[i][j] = random.nextInt(1000);

        Thread hiloBurbuja = new Thread(new Ordenar("Burbuja", arreglo[0]));
        Thread hiloInsercion = new Thread(new Ordenar("Insercion", arreglo[1]));
        Thread hiloQuickSort = new Thread(new Ordenar("QuickSort", arreglo[2]));

        hiloBurbuja.start();
        if(esperar)try{
            Thread.sleep(100);
        }catch(InterruptedException e){}

        hiloInsercion.start();
        if(esperar)try{
            Thread.sleep(100);
        }catch(InterruptedException e){}
        
        hiloQuickSort.start();
        if(esperar)try{
            Thread.sleep(100);
        }catch(InterruptedException e){}

        try {
            hiloBurbuja.join();
            hiloInsercion.join();
            hiloQuickSort.join();
        } catch (InterruptedException e) {}
        
        System.out.println("Burbuja ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo[0].length - 1; i++) {
            System.out.print(arreglo[0][i] + ", ");
        }
        System.out.println(arreglo[0][arreglo[1].length - 1]);
        
        
        System.out.println("Insercion ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo[1].length - 1; i++) {
            System.out.print(arreglo[1][i] + ", ");
        }
        System.out.println(arreglo[1][arreglo[1].length - 1]);
        
        
        System.out.println("Quicksort ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo[2].length - 1; i++) {
            System.out.print(arreglo[2][i] + ", ");
        }
        System.out.println(arreglo[2][arreglo[2].length - 1]);
    }
}