package com.dingfubing.redispractice;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/13 21:00
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();
        while (true){
            oomObjects.add(new OOMObject());
        }
    }

}
