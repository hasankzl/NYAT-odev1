/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hasan
 */
public class ObserverEkran {

    private List<Observer> observers = new ArrayList<Observer>();
    private boolean durum;

    public void addObserver(Observer channel) {
        this.observers.add(channel);
    }

    public void removeObserver(Observer channel) {
        this.observers.remove(channel);
    }

    public void setDurum(Boolean durum) {
        this.durum = durum;
        for (Observer channel : this.observers) {
            channel.update(this.durum);
        }
    }
}
