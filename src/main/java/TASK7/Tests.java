package TASK7;

public class Tests {

    static void test1() {
        long r;
        System.out.println("TEST 1");
        var test = new CollectionPerformanceExperiment();
        for (int i = 0; i < 2; i++) {
            r=test.pushEnd(test.array);
            r=test.pushEnd(test.linked);
            test.clear();
        }
        long[] resArr = new long[5];
        long[] resLin = new long[5];
        for (int i = 0; i < 5; i++) {
            resArr[i] = test.pushEnd(test.array);
            resLin[i] = test.pushEnd(test.linked);
            test.clear();
        }
        long res=0;
        System.out.print("ArrayList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resArr[i]+" ");
            res+=resArr[i];
        }
        System.out.print(res/5);
        res=0;
        System.out.println();
        System.out.print("LinkedList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resLin[i]+" ");
            res+=resLin[i];
        }
        System.out.println(res/5);
    }
    static void test2() {
        long r;
        System.out.println("TEST 2");
        var test = new CollectionPerformanceExperiment();
        for (int i = 0; i < 2; i++) {
            r=test.pushStart(test.array);
            r=test.pushStart(test.linked);
            test.clear();
        }
        long[] resArr = new long[5];
        long[] resLin = new long[5];
        for (int i = 0; i < 5; i++) {
            resArr[i] = test.pushStart(test.array);
            resLin[i] = test.pushStart(test.linked);
            test.clear();
        }
        long res=0;
        System.out.print("ArrayList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resArr[i]+" ");
            res+=resArr[i];
        }
        System.out.print(res/5);
        res=0;
        System.out.println();
        System.out.print("LinkedList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resLin[i]+" ");
            res+=resLin[i];
        }
        System.out.println(res/5);
    }
    static void test3() {
        long r;
        System.out.println("TEST 3");
        var test = new CollectionPerformanceExperiment();
        for (int i = 0; i < 2; i++) {
            r=test.pushMid(test.array);
            r=test.pushMid(test.linked);
            test.clear();
        }
        long[] resArr = new long[5];
        long[] resLin = new long[5];
        for (int i = 0; i < 5; i++) {
            resArr[i] = test.pushMid(test.array);
            resLin[i] = test.pushMid(test.linked);
            test.clear();
        }
        long res=0;
        System.out.print("ArrayList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resArr[i]+" ");
            res+=resArr[i];
        }
        System.out.print(res/5);
        res=0;
        System.out.println();
        System.out.print("LinkedList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resLin[i]+" ");
            res+=resLin[i];
        }
        System.out.println(res/5);
    }
    static void test4() {
        long r;
        System.out.println("TEST 4");
        var test = new CollectionPerformanceExperiment();
        for (int i = 0; i < 2; i++) {
            r=test.getElems(test.array);
            r=test.getElems(test.linked);
            test.clear();
        }
        long[] resArr = new long[5];
        long[] resLin = new long[5];
        for (int i = 0; i < 5; i++) {
            resArr[i] = test.getElems(test.array);
            resLin[i] = test.getElems(test.linked);
            test.clear();
        }
        long res=0;
        System.out.print("ArrayList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resArr[i]+" ");
            res+=resArr[i];
        }
        System.out.print(res/5);
        res=0;
        System.out.println();
        System.out.print("LinkedList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resLin[i]+" ");
            res+=resLin[i];
        }
        System.out.println(res/5);
    }
    static void test5() {
        System.out.println("TEST 5");
        long r;
        var test = new CollectionPerformanceExperiment();
        for (int i = 0; i < 2; i++) {
            r=test.deleteAllStart(test.array);
            r=test.deleteAllStart(test.linked);
            test.clear();
        }
        long[] resArr = new long[5];
        long[] resLin = new long[5];
        for (int i = 0; i < 5; i++) {
            resArr[i] = test.deleteAllStart(test.array);
            resLin[i] = test.deleteAllStart(test.linked);
            test.clear();
        }
        long res=0;
        System.out.print("ArrayList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resArr[i]+" ");
            res+=resArr[i];
        }
        System.out.print(res/5);
        res=0;
        System.out.println();
        System.out.print("LinkedList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resLin[i]+" ");
            res+=resLin[i];
        }
        System.out.println(res/5);
    }
    static void test6() {
        System.out.println("TEST 6");
        long r;
        var test = new CollectionPerformanceExperiment();
        for (int i = 0; i < 2; i++) {
            r=test.deleteAllEnd(test.array);
            r=test.deleteAllEnd(test.linked);
            test.clear();
        }
        long[] resArr = new long[5];
        long[] resLin = new long[5];
        for (int i = 0; i < 5; i++) {
            resArr[i] = test.deleteAllEnd(test.array);
            resLin[i] = test.deleteAllEnd(test.linked);
            test.clear();
        }
        long res=0;
        System.out.print("ArrayList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resArr[i]+" ");
            res+=resArr[i];
        }
        System.out.print(res/5);
        res=0;
        System.out.println();
        System.out.print("LinkedList: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(resLin[i]+" ");
            res+=resLin[i];
        }
        System.out.println(res/5);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

}
