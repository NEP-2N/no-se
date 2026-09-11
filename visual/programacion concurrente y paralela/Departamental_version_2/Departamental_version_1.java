import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

class Mensajero extends Thread {
    public String ruta;
    public String correo = "";
    public String nombreReceptor; // Para saber a quién le escribe
    public Mensajero(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void run() {
        // Imprime el nombre del hilo EMISOR y el nombre del hilo RECEPTOR al que le toca escribir
        System.out.println(getName() + " escribiendo correo al " + nombreReceptor);
        // Apertura de lo que hay en los txt
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            correo = "\"";
            linea = br.readLine();
            correo = correo + (linea != null ? linea : "");

            while ((linea = br.readLine()) != null) {
                correo = correo + "\n" + linea;
            }
            correo = correo + "\"";
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }
}

class Mensajeado extends Thread {
    Mensajero mensajero;

    public Mensajeado(Mensajero mensajero) {
        this.mensajero = mensajero;
    }

    @Override 
    public void run() {
        // Imprime el texto del correo que le tocó al hilo RECEPTOR mientras se ejecuta
        System.out.println(getName() + " leyendo correo: " + mensajero.correo);
    }
}

public class Departamental_version_1 {
    public static void main(String[] args) {

        Random random = new Random();
        int semilla_1 = random.nextInt(4);
        int semilla_2 = random.nextInt(4);

        String rutas[] = new String[4];
        rutas[0] = "correo_1.txt";
        rutas[1] = "correo_2.txt";
        rutas[2] = "correo_3.txt";
        rutas[3] = "correo_4.txt";

        Mensajero[] hilosMensajeros = new Mensajero[4];
        Mensajeado[] hilosMensajeados = new Mensajeado[4];

        // 1. Instanciar y nombrar mensajeros
        for (int i = 0; i < 4; i++) {
            hilosMensajeros[i] = new Mensajero(rutas[(semilla_1 + i) % 4]);
        }
        for (int i = 1; i <= 4; i++) {
            hilosMensajeros[(semilla_2 + i) % 4].setName("Hilo E" + i);
        }

        // 2. Instanciar y nombrar receptores antes de arrancar emisores para conocer sus nombres
        for (int i = 0; i < 4; i++) {
            hilosMensajeados[i] = new Mensajeado(hilosMensajeros[(semilla_2 + i) % 4]);
        }
        for (int i = 1; i <= 4; i++) {
            hilosMensajeados[(semilla_2 + i) % 4].setName("Hilo R" + i);
        }

        // 3. Vincular a cada mensajero con el nombre del receptor que le tocó
        for (int i = 0; i < 4; i++) {
            hilosMensajeados[i].mensajero.nombreReceptor = hilosMensajeados[i].getName();
        }

        // 4. FASE 1: Arrancar mensajeros y esperar a que terminen todos
        for (int i = 0; i < 4; i++) {
            hilosMensajeros[i].start();
        }
        try {
            for (int i = 0; i < 4; i++) {
                hilosMensajeros[i].join();
            }
        } catch (InterruptedException e) {
        }

        // Línea divisoria entre la fase de envío y la de recepción
        System.out.println("____________________________________________________________________________________");

        // 5. FASE 2: Arrancar receptores y esperar a que muestren sus correos
        for (int i = 0; i < 4; i++) {
            hilosMensajeados[i].start();
        }
        try {
            for (int i = 0; i < 4; i++) {
                hilosMensajeados[i].join();
            }
        } catch (InterruptedException e) {
        }
        System.out.println("____________________________________________________________________________________");
    }
}