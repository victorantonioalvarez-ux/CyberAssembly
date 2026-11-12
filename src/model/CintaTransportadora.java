/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author Alumne
 */
public class CintaTransportadora {
    private int capacidadMax;
    private Queue baterias;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cintaNoLlena = lock.newCondition();
    private Condition cintaNoVacia = lock.newCondition();

    public CintaTransportadora(int capacidadMax, Queue baterias) {
        this.capacidadMax = capacidadMax;
        this.baterias = baterias;
    }
    public void addBattery(int agvId, int numPaquete) throws InterruptedException{
        try{
            while(baterias.size() == capacidadMax){
                cintaNoLlena.wait();
            }
            baterias.add("BAT-"+agvId+ "-"+numPaquete);
            cintaNoVacia.signal();
        }finally{
            lock.unlock();
        }
    }
    
    public String extraerBateria(int agvId)throws InterruptedException{
        
        return "";
    }
    
    
}