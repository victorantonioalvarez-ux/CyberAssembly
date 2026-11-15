/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.ArrayList;
import java.util.List;
import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;
import tasks.AGVTask;
import tasks.RoboTask;
import tasks.SupervisorTask;
import view.DashboardTask;

/**
 *
 * @author Alumne
 */
public class MainApp {

    void main() {

        int estaciones = 3;
        int capacidad = 5;
        int numRobots = 12;
        int numAgvs = 5;
        int segundos = 30;

        IO.println("Numero de estaciones de soldadura: " + estaciones);
        IO.println("Capacidad de la cinta: " + capacidad);
        IO.println("Numero de robots: " + numRobots);
        IO.println("Numero de AGVs: " + numAgvs);
        IO.println("Duracion de la simulacion (segundos): " + segundos);

        CintaTransportadora cinta = new CintaTransportadora(capacidad);
        CentralSoldadura soldadura = new CentralSoldadura(estaciones);
        SupervisorCentral supervisor = new SupervisorCentral();

        List<RoboTask> robots = new ArrayList<>();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 1; i <= numRobots; i++) {
            RoboTask rt = new RoboTask(i, supervisor, cinta, soldadura);
            robots.add(rt);
            hilos.add(new Thread(rt, "Robot-" + i));
        }
        for (int i = 1; i <= numAgvs; i++) {
            hilos.add(new Thread(new AGVTask(i, 1, cinta), "AGV-" + i));
        }
        hilos.add(new Thread(new SupervisorTask(supervisor), "Supervisor"));
        hilos.add(new Thread(new DashboardTask(cinta, soldadura, supervisor,
                robots, capacidad), "Dashboard"));

        for (Thread t : hilos) {
            t.start();
        }
        
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        for (Thread t : hilos) t.interrupt();
        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        IO.println("\n------ RESUMEN FINAL ------");
        int total = 0;
        for (RoboTask rt : robots) {
            IO.println("Robot " + rt.getId() + ": " + rt.getCochesTerminados() + " coches");
            total += rt.getCochesTerminados();
        }
        IO.println("Total coches: " + total);
        IO.println("Certificados firmados: " + soldadura.getCertificados());
        IO.println("Baterias restantes en la cinta: " + cinta.getOcupacion());
    }
}
