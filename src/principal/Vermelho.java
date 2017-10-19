package principal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vermelho extends Thread {
    
    int n = 0;
    Monitor monitor;
    int posicao;
    
    public Vermelho( int n, Monitor m ) throws InterruptedException{
        this.n = n;
        this.monitor = m;
        
        // Inicializa o sem. vermelho
        Semaphore vermelho = new Semaphore( 1 );
        vermelho.acquire();
        posicao = this.monitor.add( vermelho );
        System.out.println("POS VERM = " + posicao);
    }
    
    @Override
    public void run(){
        try {
            for( int i = 0; i < n; i++ ){
                System.out.println("Vermelho dormindo...");
                this.sleep( (int) (Math.random()*9000));
                System.out.println("Vermelho acordando...");
                
                //Verifica se processo anterior terminou de executar
                if( i != 0 ) //condicao para primeira execução
                    this.monitor.get( (posicao)==0?monitor.get_total()-1:(posicao-1) ).acquire();
                System.out.println("i = " + i);
                System.out.println("VERMELHO");
                this.monitor.get( (posicao) ).release();
            }
        } catch (InterruptedException ex) {}
    }
    
}
