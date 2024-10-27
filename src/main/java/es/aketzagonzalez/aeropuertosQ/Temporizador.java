package es.aketzagonzalez.aeropuertosQ;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.application.Platform;

/**
 * The Class Temporizador.
 */
public class Temporizador extends GridPane{
	
	/** The lbl min der. */
	@FXML
    private Label lblMinDer;

    /** The lbl min iz. */
    @FXML
    private Label lblMinIz;

    /** The lbl seg der. */
    @FXML
    private Label lblSegDer;

    /** The lbl seg iz. */
    @FXML
    private Label lblSegIz;
    
    /** The tiempo. */
    private int tiempo;
    
    /** The timer. */
    private Timer timer;
    
    /** The fin. */
    private BooleanProperty fin;
    
    /**
     * Instantiates a new temporizador.
     */
    public Temporizador() {
    	fin=new SimpleBooleanProperty(false);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/temporizador.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
    
    /**
     * Set the time.
     *
     * @param tiempo the tiempo
     */
    public void fijarTiempo(int tiempo) {
    	int mins=tiempo/60;
    	if(mins<1||mins>99) {
    		return;
    	}
		this.tiempo = tiempo;
	}
    
    /**
     * Start the temporizador.
     */
    public void iniciar() {
    	timer=new Timer();
    	timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if(tiempo==0) {
					 Platform.runLater(() ->fin.set(true));
					detener();
				}
				int mins=tiempo/60;
				int segs=tiempo%60;
				 Platform.runLater(() -> {
					 lblMinIz.setText(mins/10+"");
					 lblMinDer.setText(mins%10+"");
					 lblSegIz.setText(segs/10+"");
					 lblSegDer.setText(segs%10+"");
				 });
				 tiempo--;
			}
    		
    	},0,1000);
    }
    
    /**
     * Stops the timer.
     */
    public void detener() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
    
    /**
     * Gets the fin.
     *
     * @return the fin
     */
    public BooleanProperty getFin() {
		return fin;
	}
    
}
