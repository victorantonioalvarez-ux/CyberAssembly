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
    private Queue Baterias;
    private ReentrantLock lock = new ReentrantLock();
    private Condition lleno = lock.newCondition();
    private Condition vacio = lock.newCondition();

    public CintaTransportadora(int capacidadMax, Queue Baterias) {
        this.capacidadMax = capacidadMax;
        this.Baterias = Baterias;
    }
