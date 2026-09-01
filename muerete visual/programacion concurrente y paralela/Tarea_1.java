import java.util.Scanner;

public class Tarea_1 extends Thread {
    private int segmentSize;
    private int offset;
    public Integer[] arr;
    public static Integer sum = 0;
    private static final int THREADS = 4;

    public Tarea_1(int segmentSize, int offset, Integer[] arr) {
        this.segmentSize = segmentSize;
        this.offset = offset;
        this.arr = arr;
    }


    @Override
    public void run() {
        int localSum = 0;

        for (int i = offset; i < offset + segmentSize; i++) {
            localSum += arr[i];
            sum += arr[i];
            System.out.println(Thread.currentThread().getName() + " sumando: " + arr[i]);
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " terminó con la suma parcial: " + localSum);
    }

    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = 0;

        try {
            size = input.nextInt();          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        size = size%2==0 && size%4==0 && size!=0 ? size : 16;

        Integer arr[] = new Integer[size];

        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }

        int seg = size/THREADS;

        Tarea_1 hilos[] = new Tarea_1[THREADS];

        for (int i = 0; i < THREADS; i++) {
                hilos[i] = new Tarea_1(seg, seg*i, arr);
                hilos[i].setName("Hilo " + (i + 1));
                hilos[i].start();
        }

        for (int i = 0; i < THREADS; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Suma total: " + sum);
    }
}