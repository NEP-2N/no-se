import java.util.Random;

class Mensajero extends Thread {
    public String mensaje;

    public Mensajero(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {

    }

}

class Mensajeado extends Thread{
    Mensajero mensajero;

    public Mensajeado(Mensajero mensajero){
        this.mensajero = mensajero;
    }

    @Override 
    public void run(){
        
        try {
            mensajero.join();
        } catch (InterruptedException e) {
        }

        System.out.println(mensajero.mensaje);
    }
}


public class Departamental_version_2 extends Thread {
    public static void main(String[] args) {
        int arreglo;
        Random random = new Random();

        arreglo = random.nextInt(1000);

        Mensajero hiloMensajero = new Mensajero("Hola");
        Mensajeado hiloMensajeado = new Mensajeado(hiloMensajero);

        hiloMensajero.start();
        hiloMensajeado.start();

        try {
            hiloMensajero.join();
        } catch (InterruptedException e) {
        }
    }
}
