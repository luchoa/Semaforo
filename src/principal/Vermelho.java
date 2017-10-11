package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vermelho extends Thread {
    
    int n = 0;
    Monitor m;
    int s;
    
    public Vermelho( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.m = m;
        
        // Inicializa o sem. vermelho
        Semaphore vermelho = new Semaphore( 1 );
        vermelho.acquire();
        s = this.m.add( vermelho );
        System.out.println("POS VERM = " + s);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
                System.out.println("Vermelho dormindo...");
                this.sleep( (int) (Math.random()*9000));
                System.out.println("Vermelho acordando...");
                if( i != 0 )
                    this.m.get( (s)==0?m.get_total()-1:(s-1) ).acquire();
                System.out.println("i = " + i);
                System.out.println("VERMELHO");
                this.m.get( (s) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
