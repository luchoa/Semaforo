package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Azul extends Thread {
    
    int n = 0;
    Monitor monitor;
    int posicao;
    
    public Azul( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.monitor = m;
        
        Semaphore azul = new Semaphore( 1 );
        azul.acquire();
        posicao = this.monitor.add( azul );
        System.out.println("POS AZUL = " + posicao);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
            	int tempo = (int) (Math.random()*9000);
                this.sleep(tempo);
                System.out.println("Azul dormiu por " + tempo/1000 + " segundos");
                
                //Verifica se processo anterior terminou de executar
                this.monitor.get( (posicao)<0?monitor.get_total()-1:(posicao-1) ).acquire();
                System.out.println("AZUL");
                this.monitor.get( (posicao) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
