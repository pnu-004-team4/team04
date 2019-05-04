package com.team04.musiccloud.utilities;

import java.util.ArrayDeque;
import java.util.Objects;

public class FixedDeque<T> extends ArrayDeque<T> {
    private int capacity;
    
    public FixedDeque(int capacity) {
        super(capacity);
        setCapacity(capacity);
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public boolean isFull() {
        return this.getCapacity() == this.size();
    }
    
    @Override
    public void addFirst(T t) {
        if ( this.size() >= capacity ) throw new OverSizeLimitException();
        
        super.addFirst(t);
    }
    
    @Override
    public void addLast(T t) {
        if ( this.size() >= capacity ) throw new OverSizeLimitException();
        
        super.addLast(t);
    }
    
    @Override
    public boolean add(T t) {
        boolean offered = false;
        
        if ( this.size() < capacity ) {
            offered = super.add(t);
        }
        
        return offered;
    }
    
    @Override
    public boolean offerFirst(T t) {
        boolean offered = false;
        
        if ( this.size() < capacity ) {
            offered = super.offerFirst(t);
        }
        
        return offered;
    }
    
    @Override
    public boolean offerLast(T t) {
        boolean offered = false;
        
        if ( this.size() < capacity ) {
            offered = super.offerLast(t);
        }
        
        return offered;
    }
    
    @Override
    public boolean offer(T t) {
        boolean offered = false;
        
        if ( this.size() < capacity ) {
            offered = super.offer(t);
        }
        
        return offered;
    }
    
    @Override
    public void push(T t) {
        if ( this.size() >= capacity ) throw new OverSizeLimitException();
        
        super.push(t);
    }
    
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        
        FixedDeque<?> that = (FixedDeque<?>) o;
        
        return capacity == that.capacity;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }
}
