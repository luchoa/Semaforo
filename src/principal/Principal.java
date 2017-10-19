package principal;

import java.util.concurrent.Semaphore;

public class Principal {
    
    public static void main( String args[] ) throws InterruptedException{
        
        if( args.length != 1 ) System.exit(1);
        int n = Integer.valueOf( args[0] );
        
        Monitor monitor = new Monitor();        
        Vermelho vermelho = new Vermelho( n, monitor );
        Azul     azul = new Azul( n, monitor );
        Verde    verde = new Verde( n, monitor );
        
        vermelho.start();
        azul.start();
        verde.start();
        
        vermelho.join();
        azul.join();
        verde.join();
        
    }
    
}
