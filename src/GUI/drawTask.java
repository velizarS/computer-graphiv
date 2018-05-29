/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.TimerTask;

/**
 *
 * @author Velizar
 */
public class drawTask extends TimerTask {public void run() {
        GLOBALVARS.drawFigures();
        GLOBALVARS.drawTimer.cancel();
    }
}
    
