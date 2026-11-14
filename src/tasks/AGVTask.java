/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tasks;

import model.CintaTransportadora;
import java.lang.Thread;
import java.util.Random;

/**
 *
 * @author Alumne
 */
public class AGVTask implements Runnable {

    private int id;
    private int countP;
    private CintaTransportadora cinta;

    Random r = new Random();

    public AGVTask(int id, int countP, CintaTransportadora cinta) {
        this.id = id;
        this.countP = countP;
        this.cinta = cinta;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                cinta.addBattery(id, countP);
                countP++;
                Thread.sleep(100 + r.nextInt(400));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
