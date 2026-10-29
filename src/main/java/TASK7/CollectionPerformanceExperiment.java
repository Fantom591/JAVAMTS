package TASK7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionPerformanceExperiment {
    List<Integer> array = new ArrayList<>();
    List<Integer> linked = new LinkedList<>();
    void clear(){
        array.clear();
        linked.clear();
    }
    long pushEnd(List<Integer> a) {
        long startTime = System.nanoTime();
        for (int i = 1; i <= 10_000; i++) {
            a.add(1);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    long pushStart(List<Integer> a) {
        long startTime = System.nanoTime();
        for (int i = 1; i <= 10_000; i++) {
            a.add(0,1);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    long pushMid(List<Integer> a) {
        long startTime = System.nanoTime();
        for (int i = 1; i <= 10_000; i++) {
            a.add((a.size()) / 2,1);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    long getElems(List<Integer> a) {
        int chek=0;
        for (int i = 1; i <= 10_000; i++) {
            a.add(1);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            chek+=a.get(i);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    long deleteAllStart(List<Integer> a) {
        int r=0;
        for (int i = 1; i <= 10_000; i++) {
            a.add(1);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            r+=a.remove(0);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    long deleteAllEnd(List<Integer> a) {
        int r=0;
        for (int i = 1; i <= 10_000; i++) {
            a.add(1);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            r+=a.remove(a.size()-1);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }


}
