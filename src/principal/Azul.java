package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Azul extends Thread {
    
    int n = 0;
    Monitor m;
    int s;
    
    public Azul( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.m = m;
        
        // Inicializa o sem. azul
        Semaphore azul = new Semaphore( 1 );
        azul.acquire();
        s = this.m.add( azul );
        System.out.println("POS AZUL = " + s);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
                System.out.println("Azul dormindo...");
                this.sleep( (int) (Math.random()*2000));
                System.out.println("Azul acordando...");
                this.m.get( (s)<0?m.get_total()-1:(s-1) ).acquire();
                System.out.println("AZUL");
                this.m.get( (s) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
