
class Programa_6 extends Thread{
	
	public Programa_6(String nombre){
		super(nombre);
	}
	
	@Override
	public void run(){
		for(int i=1; i<=100; i++){
			System.out.println(getName()+" -> Progreso: "+i+"%");

			try{
				//Thread.sleep(100);
			}catch(Exception e){}
		}
		System.out.println(getName()+" -> Terminado");
	}

	public static void main(){
		Thread h1 = new Programa_6("video.mp4");
		Thread h2 = new Programa_6("juego.iso");
		Thread h3 = new Programa_6("programa.exe");
		
		h1.start();
		h2.start();
		h3.start();

		try{
			h1.join();
			h3.join();
			h3.join();
		} catch(InterruptedException e){}

		System.out.println("Todo descargado...");
		//System.exit(1);
	}
}