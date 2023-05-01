package org.example.behavioral.iterator;

import java.util.List;

public class ConcreteIterator<T> implements Iterator<T> {
    private final List<T> items;
    private int currentIndex;

    public ConcreteIterator(List<T> items) {
        this.items = items;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < items.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements.");
        }
        return items.get(currentIndex++);
    }
}
