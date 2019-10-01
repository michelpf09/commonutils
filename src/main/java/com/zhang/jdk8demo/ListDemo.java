package com.zhang.jdk8demo;

import org.junit.jupiter.api.Test;
import org.raistlic.common.permutation.Permutation;

import java.math.BigInteger;
import java.util.*;

/**
 * @program: utilsdemo
 * @description:
 * @author: zhangyu
 * @create: 2019-09-25 17:40
 */
public class ListDemo {

    @Test
    public void fun() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list);
        Collections.swap(list, 0, list.size() - 1);
        System.out.println(list);
    }

    @Test
    public void fun2() {
        for (List<String> list : Permutation.of(Arrays.asList("a", "b", "c"))) {
            // System.out.println(list);
            for (String ele : list) {
                System.out.println(ele);
            }
        }
    }

    @Test
    public void fun3() {
        // Permutation.of()
        System.out.println(Permutation.of(Arrays.asList("a", "b", "c"), 3).iterator());
        Iterator iter = Permutation.of(Arrays.asList("a", "b", "c"), 3).iterator();
        List<List<String>> list = new ArrayList<>();
        BigInteger b = Permutation.of(Arrays.asList("a", "b", "c")).getPermutationCount();
        System.out.println(b);
        while (iter.hasNext()) {
            List<String> oneList = (List<String>) iter.next();
            System.out.println(list.add(oneList));
        }

        System.out.println(list);
    }
}
