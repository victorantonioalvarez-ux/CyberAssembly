/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Alumne
 */
public class CentralSoldadura {

    private Semaphore estaciones;
    private boolean[] ocupada;
    private ConcurrentLinkedQueue<Integer> libres;
    private Semaphore tablero = new Semaphore(1, true);
    private AtomicInteger certificados = new AtomicInteger();

    public CentralSoldadura(int numEstaciones) {
        this.estaciones = new Semaphore(numEstaciones, true);
        this.ocupada = new boolean[numEstaciones];
        for (int i = 0; i < numEstaciones; i++) {
            libres.offer(i);
        }
    }
    
    public int coger(int robotId) throws InterruptedException{
        estaciones.acquire();
        Integer estacion = libres.poll();
        if(estacion == null) {
            estaciones.release();
            throw new IllegalStateException("Sin estacion libre pese al permiso");
        }
        return estacion;
    }
    
    public void liberar(int robotId, int estacion){
        libres.offer(estacion);
        estaciones.release();
    }
    
    public void registroCentral(int robotId) throws InterruptedException{
        tablero.acquire();
        try{
            Thread.sleep(50);
            certificados.incrementAndGet();
        }finally {
            tablero.release();
        }
    }
}
