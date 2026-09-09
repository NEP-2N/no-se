
class Programa_3 extends Thread{
	
	public Programa_3(String nombre){
		super(nombre);
	}
	
	@Override
	public void run(){
		for(int i=1; i<=5; i++){
			System.out.println(getName()+" -> Mensaje "+i);
			try{
				Thread.sleep(100);
			}catch(Exception e){}
		}
	}

	public static void main(){
		Thread h1 = new Programa_3("Hilo-1");
		Thread h2 = new Programa_3("Hilo-2");
		Thread h3 = new Programa_3("Hilo-3");
		
		h1.start();
		h2.start();
		h3.start();

		try{
			h1.join();
			h3.join();
			h3.join();
		} catch(InterruptedException e){}

		System.out.println("Main finalizo...");
		//System.exit(1);
	}
}