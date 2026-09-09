import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

class Mensajero extends Thread {
    public String ruta;
    public String correo = "";

    public Mensajero(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void run() {
        
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            // Leemos la primera linea antes de todo para que no se inserten saltos de linea erroneos
            correo = "\"";
            linea = br.readLine();
            correo = correo + linea;

            // Si el archivo tiene mas de una linea
            while ((linea = br.readLine()) != null) {
                correo = correo + "\n" + linea;
            }

            // Encerramos el correo entre comillas
            correo = correo + "\"";
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

class Mensajeado extends Thread{
    Mensajero mensajero;

    public Mensajeado(Mensajero mensajero){
        this.mensajero = mensajero;
    }

    @Override 
    public void run(){

        // Mandamos a consola que mensajero mando que correo a este mensajeado
        System.out.println(mensajero.getName() + " ha enviado el correo \"" + mensajero.ruta + "\" a " + getName());
        
        // Esperamos a que su mensajero termine
        try {
            mensajero.join();
        } catch (InterruptedException e) {
        }

        // Imprimimos el correo
        System.out.println(getName() + ": Recibio el correo " + mensajero.correo + " del mensajero " + mensajero.getName());
    }
}


public class Departamental_version_2 extends Thread {
    public static void main(String[] args) {

        // Las lineas son para que se vea bien la consola
        System.out.println("------------------------------------------------------------------------------------------------------------------------");

        Random random = new Random();
        int semilla_1 = random.nextInt(4);
        int semilla_2 = random.nextInt(4);

        String rutas[] = new String [4];

        rutas[0] = "correo_1.txt";
        rutas[1] = "correo_2.txt";
        rutas[2] = "correo_3.txt";
        rutas[3] = "correo_4.txt";

        Mensajero[] hilosMensajeros = new Mensajero[4];
        Mensajeado[] hilosMensajeados = new Mensajeado[4];

        // Creamos los mensajeros
        for(int i=0; i<4; i++){
            hilosMensajeros[i] = new Mensajero(rutas[(semilla_1 + i) % 4]);
        }
        // Creamos los mensajeados
        for(int i=0; i<4; i++){
            hilosMensajeados[i] = new Mensajeado(hilosMensajeros[(semilla_2 + i) % 4]);
        }
        // Iniciamos los mensajeros
        for(int i=0; i<4; i++){
            hilosMensajeros[i].start();
        }
        // Cambiamos los nombres de los hilos para que se entienda mejor en consola
        for(int i=1; i<=4; i++){
            hilosMensajeros[(semilla_2 + i) % 4].setName("hilo_mensajero_" + i);
            hilosMensajeados[(semilla_2 + i) % 4].setName("hilo_mensajeado_" + i);
        }
        for(int i=0; i<4; i++){
            hilosMensajeados[i].start();
        }

        try {
            for(int i=0; i<4; i++){
                hilosMensajeados[i].join();
            }
        } catch (InterruptedException e) {
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }
}
