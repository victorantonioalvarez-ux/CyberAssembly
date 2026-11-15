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
    
    private volatile String estado = "iniciado";
    private volatile int cochesTerminados = 0;

    public RoboTask(int id, SupervisorCentral supervisor, CintaTransportadora cinta, CentralSoldadura soldadura) {
        this.id = id;
        this.supervisor = supervisor;
        this.cinta = cinta;
        this.soldadura = soldadura;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public int getCochesTerminados() {
        return cochesTerminados;
    }
    
    @Override
    public void run(){
        try {
            while (!Thread.currentThread().isInterrupted()) {
                estado = "Comprobando alerta";
                supervisor.verificaEstado(id);
                estado = "Esparando bateria";
                String bateria = cinta.extraerBateria(id);
                
                estado = "Esperando estacion";
                int estacion = soldadura.coger(id);
                try {
                    estado = "Soldado(estacion: " + estacion + " || " + bateria + ")";
                    Thread.sleep(200 + r.nextInt(500));
                } finally {
                    soldadura.liberar(id, estacion);
                }
                estado = "Firmado certificado";
                soldadura.registroCentral(id);
                cochesTerminados++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
