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
public class DashboardTask {
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
}
