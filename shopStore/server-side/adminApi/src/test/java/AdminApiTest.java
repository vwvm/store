import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class AdminApiTest {

    @Test
    void x() {
        System.out.println(new List(10, true));
        String s1 = "aaa";
        String s2 = "aaa";
        System.out.println(s1 == s2);
    }

    @Test
    void isUgly() {
        int n = 2;
        if (n <= 0) {
            System.out.println(false);
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        if (n == 1) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }


    }

    int numberOff(int n) {
        // 定义数组变量标识某人是否还在圈内
        boolean[] isIn = new boolean[n];
        Arrays.fill(isIn, true);
        // 定义圈内人数、报数、索引
        int inCount = n, countNum = 0, index = 0;
        while (inCount > 1) {
            if (isIn[index]) {
                countNum++;
                if (countNum == 3) {
                    countNum = 0;
                    isIn[index] = false;
                    inCount--;
                }
            }
            index++;
            if (index == n)
                index = 0;
        }
        for (int i = 0; i < n; i++) {
            if (isIn[i]) {
                return i + 1;
            }
        }
        return 0;
    }

    @Test
    void te() {
        System.out.println(numberOff(33));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Test
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] ans = new int[arr1.length];// res[]：res[]下标表示arr1中元素值，res[]值表示该值在arr1中出现的次数
        int res[] = new int[1001];//ans[]：输出数组
        int i2, j = 0, t = 0;
        int i1 = 0, k = 0;
        for (i1 = 0; i1 < arr1.length; i1++) {
            res[arr1[i1]]++;
        }
        for (i2 = 0; i2 < arr2.length; i2++) {
            k = res[arr2[i2]];
            for (j = 0; j < k; j++) {
                ans[t++] = arr2[i2];
            }
            res[arr2[i2]] = 0;
        }
        for (i1 = 0; i1 < res.length; i1++) {
            k = res[i1];
            if (k != 0) {
                for (j = 0; j < k; j++) {
                    ans[t++] = i1;
                }
            }
        }
        return ans;
    }


//    public class SingletonDemo {
//        private static SingletonDemo instance;
//        private SingletonDemo(){
//
//        }
//        public static SingletonDemo getInstance(){
//            if(instance==null){
//                instance=new SingletonDemo();
//            }
//            return instance;
//        }
//    }

}
