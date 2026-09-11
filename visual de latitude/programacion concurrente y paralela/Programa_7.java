
class Programa_7 extends Thread{
	
	public Programa_7(String nombre){
		super(nombre);
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(600);
		}catch(Exception e){}
	}

	public static void main() throws Exception{
		Thread hilo = new Programa_7("video.mp4");
		System.out.println(hilo.getState());
		
		hilo.start();
		System.out.println(hilo.getState());

		Thread.sleep(500);
		System.out.println(hilo.getState());
	
		hilo.join();
		System.out.println(hilo.getState());
	}
}