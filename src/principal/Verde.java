package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Verde extends Thread {
    
    int n = 0;
    Monitor monitor;
    int posicao;
    
    public Verde( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.monitor = m;
        
        // Inicializa o sem. verde
        Semaphore verde = new Semaphore( 1 );
        verde.acquire();
        posicao = this.monitor.add( verde );
        System.out.println("POS VERDE = " + posicao);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
            	int tempo = (int) (Math.random()*9000);
                this.sleep(tempo);
                System.out.println("Verde dormiu por " + tempo/1000 + " segundos");
                
                //Verifica se processo anterior terminou de executar
                this.monitor.get( (posicao)<0?monitor.get_total()-1:(posicao-1) ).acquire();
                System.out.println("----->VERDE<-----");
                System.out.println("________________________________________");
                this.monitor.get( (posicao) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
