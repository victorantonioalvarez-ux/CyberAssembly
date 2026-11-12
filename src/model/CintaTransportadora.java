/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author Alumne
 */
public class CintaTransportadora {
    private int capacidadMax;
    private Queue<String> baterias = new ArrayDeque<>();
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
   public String extraerBateria(int robotId) throws InterruptedException {
       lock.lock();
       try{
           while(baterias.isEmpty()){
               cintaNoVacia.await();
           }
           String b = baterias.poll();
           cintaNoLlena.signal();
           return b;
       }finally{
           lock.unlock();
       }
   }
   
   public int getOcupacion(){
       lock.lock();
       try{
           return baterias.size();
       }finally{
           lock.unlock();
       }
   }
}