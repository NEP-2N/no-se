class S1 extends Thread{
    int a,x,y;

    public S1(int x, int y){
        this.x = x;
        this.y = y;
        a=0;
    }

    public void run(){
        a=x+y;
        System.out.println("a = "+a);
    }
}

class S2 extends Thread{
    int b,z;

    public S2(int z){
        this.z = z;
        b=0;
    }

    public void run(){
        b = z-1;

        System.out.println("b = " + b);
    }
}

class S3 extends Thread{
    int c;
    S1 s1;
    S2 s2;

    public S3(S1 s1, S2 s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    public void run(){
        try {
            s1.join();
            s2.join();
        } catch (InterruptedException e) {}
        
        c = s1.a - s2.b;
        System.out.println("c = "+c);

    }
}

class S4 extends Thread{
    int w;
    S3 s3;

    public S4(S3 s3){
        this.s3 = s3;
    }

    public void run(){
        try{
            s3.join();
        }catch(InterruptedException e){}
        w = s3.c + 1;
        System.out.println("w = "+w);
    }
}

public class Programa_Bernstein_1{
    public static void main(){
        int x=1, y=2, z=3;

        S1 hiloS1 = new S1(x,y);
        S2 hiloS2 = new S2(z);
        S3 hiloS3 = new S3(hiloS1, hiloS2);
        S4 hiloS4 = new S4(hiloS3);

        hiloS1.start();
        hiloS2.start();
        hiloS3.start();
        hiloS4.start();

        try{
            hiloS4.join();
        }catch(InterruptedException e){}
    }
}