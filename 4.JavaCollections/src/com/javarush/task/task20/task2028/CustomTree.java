package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    static int SIZE = 0;

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String str) {
            elementName = str;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        public Entry findEntry(String s) {
            ArrayDeque<Entry> deque = new ArrayDeque<>();
            Entry x = this;
            deque.add(x);
            do {
                if (!deque.isEmpty()) x = deque.poll();
                if (x.elementName.equals(s)) return x;
                if (x.leftChild != null) deque.add(x.leftChild);
                if (x.rightChild != null) deque.add(x.rightChild);
            } while (!deque.isEmpty());
            return null;
        }

        public int size() {
            ArrayDeque<Entry> deque = new ArrayDeque<>();
            Entry x = this;
            int result = 0;
            deque.add(x);
            do {
                if (!deque.isEmpty()) x = deque.poll();
                if (x.leftChild != null) {
                    deque.add(x.leftChild);
                    result += 1;
                }
                if (x.rightChild != null) {
                    deque.add(x.rightChild);
                    result += 1;
                }
            } while (!deque.isEmpty());
            return result;
        }
    }

    public CustomTree() {
        root = new Entry<>("root");
    }


    public boolean add(String s) {
        Entry x = root;
        if (notAvailableToAdd(x)) {
            makeAvailableToAdd(x);
        }
        boolean added = false;
        ArrayDeque<Entry> deque = new ArrayDeque<>();
        deque.add(x);
        do {
            if (!deque.isEmpty()) x = deque.poll();
            if (x.leftChild != null) deque.add(x.leftChild);
            else if (x.availableToAddLeftChildren) {
                x.leftChild = new Entry(s);
                x.leftChild.parent = x;
                x.checkChildren();
                added = true;
                break;
            }
            if (x.rightChild != null) deque.add(x.rightChild);
            else if (x.availableToAddRightChildren) {
                x.rightChild = new Entry(s);
                x.rightChild.parent = x;
                x.checkChildren();
                added = true;
                break;
            }
        } while (!deque.isEmpty());
        return true;
    }

    public boolean notAvailableToAdd(Entry x){
        boolean result = true;
        ArrayDeque<Entry> deque = new ArrayDeque<>();
        deque.add(x);
        do {
            if (!deque.isEmpty()) x = deque.poll();
            if (x.leftChild != null) deque.add(x.leftChild);
            else if (x.availableToAddLeftChildren) {
                result = false;
            }
            if (x.rightChild != null) deque.add(x.rightChild);
            else if (x.availableToAddRightChildren) {
                result = false;
            }
        } while (!deque.isEmpty());
        return result;
    }

    public void makeAvailableToAdd(Entry entry){
        ArrayDeque<Entry> deque = new ArrayDeque<>();
        Entry x = root;
        deque.add(x);
        do {
            if (!deque.isEmpty()) x = deque.poll();
            if (x.leftChild == null) x.availableToAddLeftChildren = true;
            else deque.add(x.leftChild);
            if (x.rightChild == null) x.availableToAddRightChildren = true;
            else deque.add(x.rightChild);
        } while (!deque.isEmpty());
    }

    public boolean remove(Object o) {
        String s = "";
        boolean deleted = false;
        try {
            s = (String) o;
        } catch (ClassCastException e) {
            throw new UnsupportedOperationException();
        }
        Entry entry = root.findEntry(s);
        if (entry.parent.leftChild != null && entry.parent.leftChild.equals(entry)) {
            entry.parent.leftChild = null;
            entry.parent.availableToAddLeftChildren = false;
            deleted = true;
        }
        if (entry.parent.rightChild != null && entry.parent.rightChild.equals(entry)) {
            entry.parent.rightChild = null;
            entry.parent.availableToAddRightChildren = false;
            deleted = true;
        }
        return deleted;
    }

    @Override
    public int size() {
        return root.size();
    }

    public String getParent(String s) {
        Entry entry = root.findEntry(s);
        if (entry == null) return null;
        else return entry.parent.elementName;
    }

    /*@Override
    public boolean add(String s) {
        return true;
    }*/

    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }
}

