package principal;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Monitor {
    
    private ArrayList<Semaphore> semaforos = new ArrayList<Semaphore>();
    
    public int add( Semaphore s ){
        this.semaforos.add( s );
        return this.semaforos.indexOf( s );
    }
    
    public void del( Semaphore s ){
        this.semaforos.remove( s );
    }
    
    public int get_total(){
        return this.semaforos.size();
    }
    
    public Semaphore get( int i ){
        return this.semaforos.get( i );
    }
    
    public void wait_reg(){
        try {
            this.semaforos.get( 0 ).acquire();
            this.semaforos.add( this.semaforos.remove(0) );
        } catch (InterruptedException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void signal_reg(){
        this.semaforos.get( 0 ).release();
    }
    
}
