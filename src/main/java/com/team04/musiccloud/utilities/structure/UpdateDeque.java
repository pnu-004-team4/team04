package com.team04.musiccloud.utilities.structure;

public class UpdateDeque<T> extends FixedDeque<T> {
    public UpdateDeque(int capacity) {
        super(capacity);
    }
    
    @Override
    public void addFirst(T t) {
        if ( this.isFull() ) {
            this.removeLast();
        }
        
        super.addFirst(t);
    }
    
    @Override
    public void addLast(T t) {
        if ( this.isFull() ) {
            this.removeFirst();
        }
        
        super.addLast(t);
    }
    
    @Override
    public boolean add(T t) {
        if ( this.isFull() ) {
            this.remove();
        }
        
        return super.add(t);
    }
    
    @Override
    public boolean offerFirst(T t) {
        if ( this.isFull() ) {
            this.pollLast();
        }
        
        return super.offerFirst(t);
    }
    
    @Override
    public boolean offerLast(T t) {
        if ( this.isFull() ) {
            this.pollFirst();
        }
        
        return super.offerLast(t);
    }
    
    @Override
    public boolean offer(T t) {
        if ( this.isFull() ) {
            this.poll();
        }
        
        return super.offer(t);
    }
    
    @Override
    public void push(T t) {
        if ( this.isFull() ) {
            this.pop();
        }
        
        super.push(t);
    }
}
