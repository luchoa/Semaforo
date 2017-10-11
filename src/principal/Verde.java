package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Verde extends Thread {
    
    int n = 0;
    Monitor m;
    int s;
    
    public Verde( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.m = m;
        
        // Inicializa o sem. verde
        Semaphore verde = new Semaphore( 1 );
        verde.acquire();
        s = this.m.add( verde );
        System.out.println("POS VERDE = " + s);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
                System.out.println("Verde dormindo...");
                this.sleep( (int) (Math.random()*9000));
                System.out.println("Verde acordando...");
                this.m.get( (s)<0?m.get_total()-1:(s-1) ).acquire();
                System.out.println("VERDE");
                System.out.println("-----------------------");
                this.m.get( (s) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
