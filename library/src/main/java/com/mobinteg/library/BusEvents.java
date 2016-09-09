package com.mobinteg.library;

/**
 * Created by soopa on 07/09/2016.
 */

public class BusEvents {

    public BusEvents(Recycler recycler) {
        this.recycler = recycler;
    }

    public Recycler getRecycler() {
        return recycler;
    }

    public void setRecycler(Recycler recycler) {
        this.recycler = recycler;
    }

    Recycler recycler;

    public static class Recycler {
    }
}
