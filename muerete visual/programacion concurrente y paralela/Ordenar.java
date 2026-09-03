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

    }

}


class Insercion extends Thread {
    private int arreglo[];

    public Insercion(int arreglo[]) {
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

    }
}


class QuickSort extends Thread {
    private int arreglo[];

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

    public QuickSort(int arreglo[]) {
        this.arreglo = arreglo;
    }

    @Override
    public void run() {

        QuickSort(arreglo, 0, arreglo.length - 1);

    }
}


public class Ordenar extends Thread {
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
        hiloInsercion.start();
        hiloQuickSort.start();

        try {
            hiloBurbuja.join();
            hiloInsercion.join();
            hiloQuickSort.join();
        } catch (InterruptedException e) {
        }

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
