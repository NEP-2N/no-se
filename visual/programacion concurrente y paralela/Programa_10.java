
class Programa_10 extends Thread{
	
	public Programa_10(String nombre){
		super(nombre);
	}
	
	@Override
	public void run(){

		for(int i=0; i<10; i++){
			System.out.println(getName()+"\t-> ["+getPriority()+"]");
		}
	}

	public static void main() throws Exception{
		Thread h1 = new Programa_10("Prioridad baja");
		Thread h2 = new Programa_10("Prioridad media");
		Thread h3 = new Programa_10("Prioridad Alta");

		h1.setPriority(MIN_PRIORITY);
		h2.setPriority(NORM_PRIORITY);
		h3.setPriority(MAX_PRIORITY);
		
		h1.start();
		h2.start();
		h3.start();

	}
}