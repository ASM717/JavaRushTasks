package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set {

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        /** Вычисли свою Capacity по такой формуле:
         * максимальное из 16 и округленного в большую сторону значения (collection.size()/.75f) */
        int capacity = (int) Math.max(16, Math.ceil(collection.size()/.75f));
        this.map = new HashMap<>(capacity);
        for (E e : collection) {
            map.put(e, PRESENT);
        }
    }

    public boolean add(Object e){
        return map.put((E) e, PRESENT) == null;
    }

    @Override
    public Iterator iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        if (this.map.isEmpty()) {
            return true;
        } else return false;
    }

    @Override
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return this.map.remove(o) == PRESENT;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            AmigoSet copy = (AmigoSet) super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }
    //реализация сериализации
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
        oos.writeInt(map.size());

        for (E e : map.keySet()) oos.writeObject(e);

    }
    //реализация десериализации
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        int size = ois.readInt();

        map = new HashMap<>(capacity,loadFactor);

        for (int i = 0; i < size; i++)
        {
            E e = (E) ois.readObject();
            map.put(e,PRESENT);
        }
    }
}
