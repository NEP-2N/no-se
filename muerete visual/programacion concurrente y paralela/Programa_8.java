
class Programa_8 extends Thread{
	
	public Programa_8(String nombre){
		super(nombre);
	}
	
	@Override
	public void run(){

		for(int i=0; i<10; i++){
			System.out.print(getName()+" -> [");
			for(int j=0; j<=i; j++){
				System.out.print("-");
			}for(int j=i; j<9; j++){
				System.out.print(" ");
			}
			System.out.print("]\n");


			try{
				Thread.sleep(100);
			}catch(Exception e){}
		}
	}

	public static void main() throws Exception{
		Thread h1 = new Programa_8("Hilo 1");
		Thread h2 = new Programa_8("Hilo 2");
		
		h1.start();
		h1.join();

		h2.start();

	}
}