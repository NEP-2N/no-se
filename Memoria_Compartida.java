

class BufferCirc{
    private int bufTam;
    private int bufin;
    private int bufout;
    public int[] buffer;

    public int disp;

    public BufferCirc(int bufTam){
        this.bufTam = bufTam;
        bufin = 0;
        bufout = 0;
        disp = 10;
        buffer = new int[bufTam];
    }
    
    public int get_dato(){
        int valor;
        valor = buffer[bufout]; // Agarrar un dato del indice bufout
        bufout = (bufout+1) % bufTam; // Avanzar el indice de bufout de forma circular

        disp++;

        return valor;
    }

    public void put_dato(int dato){
        buffer[bufin] = dato; // Meter un dato en el indice bufin
        bufin = (bufin+1) % bufTam; // Avanzar el indice de bufin de forma circular

        disp--;
    }

    public boolean is_full(){
        if(bufin == bufout) if(disp >= 10){
            return true;
        }
        return false;
    }

    public boolean is_empty(){
        if(bufin == bufout) if(disp <= 0){
            return true;
        }
        return false;
    }
}

class Productor extends Thread{
    private int dato;
    private BufferCirc buf;

    public Productor(BufferCirc buf){
        dato = 1;
        this.buf = buf;
    }

    @Override 
    public void run(){
        while(true){
            synchronized(buf){
                if(!buf.is_full()){
                    System.out.println("Productor metio el dato: " + dato);
                    buf.put_dato(dato++);
                }else{
                    try {
                        sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}

class Consumidor extends Thread{
    private BufferCirc buf;

    public Consumidor(BufferCirc buf){
        this.buf = buf;
    }

    @Override 
    public void run(){
        while(true){
            synchronized(buf){
                if(!buf.is_empty()){
                    System.out.println("Consumidor agarro el dato: " + buf.get_dato());
                }else{
                    try {
                        sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}


public class Memoria_Compartida{
    public static void main(){
        BufferCirc buf = new BufferCirc(10);
        Productor prod = new Productor(buf);
        Consumidor cons = new Consumidor(buf);

        prod.start();
        cons.start();
    }
}