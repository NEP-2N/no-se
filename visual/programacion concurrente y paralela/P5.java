import java.util.Random;

class Burbuja extends Thread {
    private int arreglo[];

    public Burbuja(int arreglo[]) {
        this.arreglo = arreglo;
    }

    @Override
    public void run() {

        boolean ordenado = false;
        int temp;

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

        System.out.println("Burbuja ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo.length - 1; i++) {
            System.out.print(arreglo[i] + ", ");
        }
        System.out.println(arreglo[arreglo.length - 1]);

    }

}


class Insercion extends Thread {
    private int arreglo[];

    public Insercion(int arreglo[]) {
        this.arreglo = arreglo;
    }

    @Override
    public void run() {

        int temp;

        for (int i = 0; i < arreglo.length - 1; i++) {
            if (arreglo[i + 1] < arreglo[i]) {
                for (int j = i + 1; j > 0; j--) {
                    if (arreglo[j] < arreglo[j - 1]) {
                        temp = arreglo[j];
                        arreglo[j] = arreglo[j - 1];
                        arreglo[j - 1] = temp;
                    } else
                        break;
                }
            }
        }

        System.out.println("Insercion ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo.length - 1; i++) {
            System.out.print(arreglo[i] + ", ");
        }
        System.out.println(arreglo[arreglo.length - 1]);
    }
}


class QuickSort extends Thread {
    private int arreglo[];

    public static void QuickSort(int arreglo[], int inicio, int fin) {
        if (inicio >= fin)
            return;

        int pivote = arreglo[fin];
        int a = inicio;

        int temp;

        for (int i = inicio; i < fin; i++) {
            if (arreglo[i] <= pivote) {
                temp = arreglo[a];
                arreglo[a++] = arreglo[i];
                arreglo[i] = temp;
            }
        }

        arreglo[fin] = arreglo[a];
        arreglo[a] = pivote;

        QuickSort(arreglo, inicio, a - 1);
        QuickSort(arreglo, a + 1, fin);
    }

    public QuickSort(int arreglo[]) {
        this.arreglo = arreglo;
    }

    @Override
    public void run() {

        QuickSort(arreglo, 0, arreglo.length - 1);

        System.out.println("Quicksort ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo.length - 1; i++) {
            System.out.print(arreglo[i] + ", ");
        }
        System.out.println(arreglo[arreglo.length - 1]);
    }
}


public class P5 extends Thread {
    public static void main(String[] args) {
        int size = 100;
        int arreglo[][] = new int[3][size];
        Random random = new Random();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < size; j++)
                arreglo[i][j] = random.nextInt(1000);

        Burbuja hiloBurbuja = new Burbuja(arreglo[0]);
        Insercion hiloInsercion = new Insercion(arreglo[1]);
        QuickSort hiloQuickSort = new QuickSort(arreglo[2]);

        hiloBurbuja.start();

        // Esperamos a que burbuja termine para no mezclar la impresion de los algoritmos en consola
        try {
            hiloBurbuja.join();
        } catch (InterruptedException e) {
        }

        hiloInsercion.start();
        
        // Esperamos a que insercion termine para no mezclar la impresion de los algoritmos en consola 
        try {
            hiloInsercion.join();
        } catch (InterruptedException e) {
        }

        hiloQuickSort.start();

    }
}
