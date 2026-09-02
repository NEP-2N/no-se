import java.util.Random;

public class Ordenar extends Thread {
    private String algoritmo;
    private int arreglo[];

    public Ordenar(String algoritmo, int arreglo[]) {
        this.algoritmo = algoritmo;
        this.arreglo = arreglo;
    }

    public static void QuickSort(int arreglo[], int bajo, int alto) {
        // if (bajo == alto)
        //     return arreglo;

        // int pivote = (alto - bajo) / 2;

        // return 1;

        if (bajo < alto) {
            // 1. Elegimos el último elemento como pivote
            int pivot = arreglo[alto];
            int i = (bajo - 1);

            // 2. Particionamiento integrado en la misma función
            for (int j = bajo; j < alto; j++) {
                if (arreglo[j] <= pivot) {
                    i++;
                    // Intercambio (swap)
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }

            // Colocar el pivote en su posición correcta
            int temp = arreglo[i + 1];
            arreglo[i + 1] = arreglo[alto];
            arreglo[alto] = temp;

            int pi = i + 1; // Índice de partición

            // 3. Llamadas recursivas para ordenar las subpartes
            QuickSort(arreglo, bajo, pi - 1);
            QuickSort(arreglo, pi + 1, alto);
        }
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

        System.out.println(algoritmo + " ha terminado de ordenar los datos: ");
        for (int i = 0; i < arreglo.length - 1; i++) {
            System.out.print(arreglo[i] + ", ");
        }
        System.out.println(arreglo[arreglo.length - 1]);

    }

    public static void main(String[] args) {
        int size = 10;
        int arreglo[] = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++)
            arreglo[i] = random.nextInt(1000);


        Thread hiloBurbuja = new Thread(new Ordenar("Burbuja", arreglo));
        Thread hiloInsercion = new Thread(new Ordenar("Insercion", arreglo));
        Thread hiloQuickSort = new Thread(new Ordenar("QuickSort", arreglo));

        hiloBurbuja.start();
        try {
            hiloBurbuja.join();
        } catch (InterruptedException e) {
        }

        hiloInsercion.start();
        try {
            hiloInsercion.join();
        } catch (InterruptedException e) {
        }


        hiloQuickSort.start();
        try {
            hiloQuickSort.join();
        } catch (InterruptedException e) {
        }

    }
}
