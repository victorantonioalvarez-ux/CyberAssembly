/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;
import tasks.RoboTask;

/**
 *
 * @author Alumne
 */
public class DashboardTask implements Runnable{
    private CintaTransportadora cinta;
    private CentralSoldadura soldadura;
    private SupervisorCentral supervisor;
    private List<RoboTask> robots;
    private int capacidadCinta;

    public DashboardTask(CintaTransportadora cinta, CentralSoldadura soldadura,
                         SupervisorCentral supervisor, List<RoboTask> robots,
                         int capacidadCinta) {
        this.cinta = cinta;
        this.soldadura = soldadura;
        this.supervisor = supervisor;
        this.robots = robots;
        this.capacidadCinta = capacidadCinta;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                imprimir();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void imprimir() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------- DASHBOARD ----------\n");
        sb.append("Alerta por calentamiento: ")
          .append(supervisor.isAlertaActiva() ? "ACTIVA" : "no").append('\n');
        sb.append("Robots pausados: ").append(supervisor.getRobotsPausados()).append('\n');
        sb.append("Estaciones ocupadas: ").append(soldadura.getEstacionesOcupadas())
          .append("/").append(soldadura.getTotalEstaciones()).append('\n');
        sb.append("Cinta: ").append(cinta.getOcupacion())
          .append("/").append(capacidadCinta).append('\n');
        sb.append("Certificados firmados: ").append(soldadura.getCertificados()).append('\n');
        for (RoboTask t : robots) {
            sb.append("  Robot ").append(t.getId()).append(": ")
              .append(t.getEstado()).append(" | coches: ")
              .append(t.getCochesTerminados()).append('\n');
        }
        System.out.print(sb);
    }
}
