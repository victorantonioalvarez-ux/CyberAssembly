/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import java.util.Random;
import model.SupervisorCentral;

/**
 *
 * @author v
 */
public class SupervisorTask implements Runnable{
    
    private SupervisorCentral supervisor;
    private Random r = new Random();

    public SupervisorTask(SupervisorCentral supervisor) {
        this.supervisor = supervisor;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(3000 + r.nextInt(3000)); // funcionamiento normal
                supervisor.activarAlerta();
                Thread.sleep(1500 + r.nextInt(1000)); // alerta activa
                supervisor.resolverAlerta();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
