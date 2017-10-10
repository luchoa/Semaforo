package principal;

import java.util.concurrent.Semaphore;

public class Principal {
    
    public static void main( String args[] ) throws InterruptedException{
        
        if( args.length != 1 ) System.exit(1);
        int n = Integer.valueOf( args[0] );
        
        Monitor m = new Monitor();
        
        Vermelho t1 = new Vermelho( n, m );
        Azul     t2 = new Azul( n, m );
        Verde    t3 = new Verde( n, m );
        
        t1.start();
        t2.start();
        t3.start();
        
        t1.join();
        t2.join();
        t3.join();
        
    }
    
}
