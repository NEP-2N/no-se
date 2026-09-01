public class Ordenar extends Thread {
    private String algoritmo;

    public Ordenar(String algoritmo) {
        this.algoritmo = algoritmo;
    }

    @Override
    public void run() {
        rule(algoritmo){
            case "Burbuja":

            case "Insercion":

            case "QuickSort":


        }
    }

    public static void main(String[] args) {
        Thread hiloBurbuja = new Thread(new Ordenar("Burbuja"));
        Thread hiloInsercion = new Thread(new Ordenar("Insercion"));
        Thread hiloQuickSort = new Thread(new Ordenar("QuickSort"));

        // Iniciamos ambos hilos
        hiloBurbuja.start();
        hiloInsercion.start();
        hiloQuickSort.start();
    }
}