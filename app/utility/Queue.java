package app.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queue<T>{
    private List<T> queue;

    public Queue(){
        this.queue = new ArrayList<T>();
    }

    public Queue(Collection<T> collection){
        this.queue = new ArrayList<T>(collection);
    }
    
    public void add(T item){
        this.queue.add(item);
    }
    public void add(Collection<T> items){
        this.queue.addAll(items);
    }
    public T remove(){
        return this.queue.remove(0);
    }
    public Boolean isEmpty(){
        return this.queue.isEmpty();
    }
    public int size(){
        return this.queue.size();
    }

    public T peek(){
        return this.queue.get(0);
    }
}