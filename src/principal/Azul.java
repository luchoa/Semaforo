package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Azul extends Thread {
    
    int n = 0;
    Monitor monitorm;
    int posicao;
    
    public Azul( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.monitorm = m;
        
        // Inicializa o sem. azul
        Semaphore azul = new Semaphore( 1 );
        azul.acquire();
        posicao = this.monitorm.add( azul );
        System.out.println("POS AZUL = " + posicao);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
                System.out.println("Azul dormindo...");
                this.sleep( (int) (Math.random()*9000));
                System.out.println("Azul acordando...");
                this.monitorm.get( (posicao)<0?monitorm.get_total()-1:(posicao-1) ).acquire();
                System.out.println("AZUL");
                this.monitorm.get( (posicao) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
