import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/*

1. Generar un areglo de N numeros enteros aleatorios entre 1-100
2. Crear K hilos con k<N y lanzarlos en paralelo
	a. Los K hilos deberan sumar entre todos los numeros almacenados en el arreglo
		en exclusion mutua
	b. Al final de la ejecucion de los hilos, cada uno habra obtenido una suma
		parcial de las casillas que logro sumar
	c. Mostrar dicha suma parcial en pantalla
3. El main() tomara las sumas parciales de los K hilos y las sumara obteniendo
	la suma total del arreglo y mostrara el resultado en pantalla



EVIDENCIAS A ENTREGAR:
1. El código fuente completo libre de errores sintácticos
2. Un documento PDF que contenga una portada con nombre completo y matrícula,
seguido del enunciado completo del ejercicio, el código fuente completo (copiar
y pegar, no captura de pantalla) y las capturas de pantalla que muestren varias
ejecuciones paralelas de los hilos.

FORMA DE ENTREGA: 
Esta actividad podrá ser realizada en equipos de máximo 4 personas, sin embargo
cada integrante del equipo deberá subir sus evidencias por separado. Esta actividad
no podrá entregarse de forma atrasada
 */

class Datos{
	int indice;
	int arreglo[];

    public Datos(int arreglo[]){
        this.indice = 0;
		this.arreglo = arreglo;
    }
}

class Sumador extends Thread{
    int sumaParcial;
	int size;
    Datos datos;

	int arreglo[];

    public Sumador(Datos datos, int size){
		this.size = size;
        this.sumaParcial = 0;
        this.datos = datos;
    }

    @Override
    public void run(){
        while(true){
            synchronized(datos){
                if(datos.indice == size)
                    break;
                else{
                    sumaParcial = sumaParcial + datos.arreglo[datos.indice++];
                }
            }
            
            try {sleep(0);} catch (InterruptedException e) {}
        }
        System.out.println("Suma parcial: " + sumaParcial);
    }
}

public class T2 extends Thread {
    public static void main(String[] args) {

		int N = 3;
		int K = 2;

		if(K >= N){
			K = N-1;
		}

        Random random = new Random();

		int arreglo[] = new int[N];

		for(int i=0; i<N; i++){
			arreglo[i] = random.nextInt(100) + 1; // Sumamos uno por que si no lo hacemos, el rango seria de 0-99
			System.out.print(arreglo[i] + ", ");
		}
		System.out.println();

		Datos datos = new Datos(arreglo);

        Sumador[] hilosSumadores = new Sumador[K];

        for(int i=0; i<K; i++){
            hilosSumadores[i] = new Sumador(datos, N);
        }
        for(int i=0; i<K; i++){
            hilosSumadores[i].start();
        }
        for(int i=0; i<K; i++){
			try{
            	hilosSumadores[i].join();
			}catch (InterruptedException e){};
        }

		int sumaTotal = 0;

		for(int i=0; i<K; i++){
			sumaTotal += hilosSumadores[i].sumaParcial;
		}

		System.out.println("Suma total: " + sumaTotal);
	}
}