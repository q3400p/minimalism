package com.windf.core.frame;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderController {

    private static Comparator comparator = new Comparator<Orderable>() {
        @Override
        public int compare(Orderable o1, Orderable o2) {
            return o1.getOrder() - o2.getOrder();
        }
    };

    public static void sort(List<? extends Orderable> orderables) {
        Collections.sort(orderables, comparator);
    }


}
