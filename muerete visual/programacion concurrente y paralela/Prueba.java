public class Prueba extends Thread {
    private String accion;

    public Prueba(String accion) {
        this.accion = accion;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Ejecutando: " + accion + " (paso " + i + ")");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        }
    }

    public static void main(String[] args) {
        // Creamos un hilo para correr
        Thread hiloCorrer = new Thread(new Prueba("Correr"));
        
        // Creamos otro hilo para disparar
        Thread hiloDisparar = new Thread(new Prueba("Disparar"));
        
        Thread hiloBombardearPeru = new Thread(new Prueba("Bombardear Peru"));

        // Iniciamos ambos hilos
        hiloCorrer.start();
        hiloDisparar.start();
        hiloBombardearPeru.start();
    }
}



/*
public class prueba {
    public static void main() throws Exception{
        // Crear y ejecutar el hilo sin declarar una clase explícita
        new Thread(() -> {
            for(int i=0; i<10; i++){
                System.out.println("Hilo 1 -> ["+i+"]");

                try{
                    Thread.sleep(100);
                }catch(Exception e){}
            }
        }).start();

        new Thread(() -> {
            for(int i=0; i<10; i++){
                System.out.println("Hilo 2 -> ["+i+"]");

                try{
                    Thread.sleep(100);
                }catch(Exception e){}
            }
        }).start();

    }
}
*/