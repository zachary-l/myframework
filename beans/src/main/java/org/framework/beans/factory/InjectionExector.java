package org.framework.beans.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class InjectionExector {
    private static Iterator<InjectionFactory> it;
    private static List<InjectionFactory> list = new ArrayList<>();
    static {
        it = ServiceLoader.load(InjectionFactory.class).iterator();
            while (it.hasNext()){
                System.out.println(it.next());
                list.add(it.next());
            }
    }

    public static void main(String[] args) {

    }


}
