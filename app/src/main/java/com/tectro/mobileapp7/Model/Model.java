package com.tectro.mobileapp7.Model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

public class Model {

    //region Singleton
    private static Model current;

    public static Model CreateInstance() {
        if (current == null) current = new Model();
        return current;
    }
    //endregion

    //region Accessors
    public ArrayList<AtomicReference<String>> getList1() {
        return list1;
    }

    public ArrayList<AtomicReference<String>> getList2() {
        return list2;
    }
    //endregion

    //region Updater
    private ArrayList<Runnable> notifies;
    public void Subscribe(Runnable func)
    {
        notifies.add(func);
    }

    public void Unsubscribe(Runnable func)
    {
        notifies.remove(func);
    }

    public void invoke()
    {
        notifies.forEach(Runnable::run);
    }
    //endregion

    private final ArrayList<AtomicReference<String>> list1;
    private final ArrayList<AtomicReference<String>> list2;

    public void move(AtomicReference<String> value)
    {
        if(list1.contains(value))
        {
            list1.remove(value);
            list2.add(value);
        } else
        if(list2.contains(value))
        {
            list2.remove(value);
            list1.add(value);
        }
        invoke();
    }

    public void remove(AtomicReference<String> value)
    {
        list1.remove(value);
        list2.remove(value);
        invoke();
    }

    private Model() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        notifies = new ArrayList<>();
    }
}
