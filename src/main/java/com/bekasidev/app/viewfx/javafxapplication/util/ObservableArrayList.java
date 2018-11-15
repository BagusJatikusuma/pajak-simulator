/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bekasidev.app.viewfx.javafxapplication.util;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ModifiableObservableListBase;

/**
 *
 * @author USER
 */
public class ObservableArrayList<T> extends ModifiableObservableListBase<T> {
    private final List<T> delegate = new ArrayList<>();
    
    @Override
    public T get(int index) {
        return delegate.get(index);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    protected void doAdd(int index, T element) {
        delegate.add(index, element);
    }

    @Override
    protected T doSet(int index, T element) {
        return delegate.set(index, element);
    }

    @Override
    protected T doRemove(int index) {
        return delegate.remove(index);
    }
    
}
