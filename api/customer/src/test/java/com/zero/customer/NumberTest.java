package com.zero.customer;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author yezhaoxing
 * @date 2017/12/14
 */
@SpringBootTest
public class NumberTest {

    @Test
    public void testNum() {
        int source = 1500;
        while (true) {
            List<Integer> calc = calc(source, 50);
            if (calc.get(9) > 10) {
                print(calc);
                return;
            }
        }
    }

    private void print(List<Integer> list) {
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    private List<Integer> calc(Integer source, Integer limit) {
        Integer total = source;
        List<Integer> rtn = new ArrayList<>(10);
        int sum = 0;
        Random random = new Random();
        for (int i = 1; i < 11; i++) {
            int num = 0;
            if (i == 1) {
                num = random.nextInt(100) + 10;
            }
            if (1 < i && i < 8) {
                num = random.nextInt(1000) + limit;
            }
            if (i >= 8 && i < 10) {
                num = random.nextInt(2000);
            }
            if (i != 10) {
                total -= num;
            }
            if (i == 10) {
                num = total - sum;
            }
            if (i != 10) {
                rtn.add(num);
            } else {
                rtn.add(total);
            }
        }
        print(rtn);
        return rtn;
    }
}
