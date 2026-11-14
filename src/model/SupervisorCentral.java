/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alumne
 */
public class SupervisorCentral {

    private boolean alerta = false;
    private int robotsPausados = 0;

    public synchronized void verificaEstado(int robotId) throws InterruptedException {
        while (alerta) {
            robotsPausados++;
            try {
                wait();
            } finally {
                robotsPausados--;
            }
        }
    }

    public synchronized void activarAlerta() {
        alerta = true;
    }

    public synchronized void resolverAlerta() {
        alerta = false;
        notifyAll();
    }

    public synchronized boolean isAlertaActiva() {
        return alerta;
    }

    public synchronized int getRobotsPausados() {
        return robotsPausados;
    }
}
