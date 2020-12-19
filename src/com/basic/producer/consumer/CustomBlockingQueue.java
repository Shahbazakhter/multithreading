package com.basic.producer.consumer;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomBlockingQueue implements Queue<Integer> {

    private final Queue<Integer> queue;
    private int max = 16;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition empty = lock.newCondition();
    private final Condition full = lock.newCondition();

    public CustomBlockingQueue(int size) {
        this.queue = new LinkedList<>();
        this.max = size;
    }

    @Override
    public synchronized boolean add(final Integer item) {
        lock.lock();
        try {
            while (queue.size() == max) {
                full.await();
            }
            queue.add(item);
            empty.signalAll();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public Integer poll() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                empty.await();
            }
            final Integer integer = queue.remove();
            full.signalAll();
            return integer;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public Iterator iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return queue.addAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean retainAll(Collection c) {
        return queue.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean offer(Integer o) {
        return queue.offer(o);
    }

    @Override
    public Integer remove() {
        return queue.remove();
    }

    @Override
    public Integer element() {
        return queue.element();
    }

    @Override
    public Integer peek() {
        return queue.peek();
    }
}
