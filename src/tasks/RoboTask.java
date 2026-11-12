/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import java.util.Random;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;

/**
 *
 * @author Alumne
 */
public class RoboTask implements Runnable{
    private int id;
    private SupervisorCentral supervisor;
    private CintaTransportadora cinta;
    private CentralSoldadura soldadura;

    Random r = new Random();

    public RoboTask(int id, SupervisorCentral supervisor, CintaTransportadora cinta, CentralSoldadura soldadura) {
        this.id = id;
        this.supervisor = supervisor;
        this.cinta = cinta;
        this.soldadura = soldadura;
    }
    
    
    
    @Override
    public void run(){
        while(Thread.currentThread().isInterrupted()) {
            try{
                supervisor.verificaEstado(id);
                String bateria = cinta.extraerBateria(id);
                int num = soldadura.coger(id);
                Thread.sleep(r.nextInt());
                soldadura.liberar(id,num);
                soldadura.registroCentral(id);
            }catch{
                Thread.currentThread().interrupt();
            }
        }
    }
}
