package org.vwvm.paperManagement.api.meail;

import java.util.*;

/**
 * there are some number, use + - * / and ( ), to get all the solution which can get the result target number
 */
public class Test015 {
    public static void main(String[] args) {
        int n = 8;
        int m = 8;
        int result = 1000;
        int[] ints = new int[n];
        Arrays.fill(ints, m);
        // new Test015().getResult(ints, result).forEach(System.out::println);

        new Test015().getResult(new int[] {2, 9, 3, 8, 5}, 100).forEach(System.out::println);
    }

    public Test015() {
    }

    public Set<String> getResult(int[] nums, int target) {
        Map<Integer, Set<String>> result = func(nums, 0, nums.length - 1);
        return result.getOrDefault(target, null);
    }

    public Map<Integer, Set<String>> func (int[] nums, int start, int end) {
        // end condition
        if (start == end) {
            Map<Integer, Set<String>> result = new HashMap<>();
            result.put(nums[start], new HashSet<>(Arrays.asList(String.valueOf(nums[start]))));
            return result;
        }

        Map<Integer, Set<String>> result = new HashMap<>();
        for (int i = start; i < end; i++) {
            Map<Integer, Set<String>> left = func(nums, start, i);
            Map<Integer, Set<String>> right = func(nums, i + 1, end);
            for (Integer leftKey : left.keySet()) {
                Set<String> leftValues = left.get(leftKey);
                for (Integer rightKey : right.keySet()) {
                    Set<String> rightValues = right.get(rightKey);
                    updateResult(result, leftValues, rightValues, leftKey, rightKey, 1);
                    updateResult(result, leftValues, rightValues, leftKey, rightKey, 2);
                    updateResult(result, leftValues, rightValues, leftKey, rightKey, 3);
                    //
                    if (rightKey == 0 || leftKey % rightKey != 0) {
                        continue;
                    }
                    updateResult(result, leftValues, rightValues, leftKey, rightKey, 4);
                }
            }
        }

        return result;
    }

    private void updateResult(Map<Integer, Set<String>> result, Set<String> leftValues, Set<String> rightValues, Integer leftKey, Integer rightKey, int type) {
        int operateResult = getOperateResult(leftKey, rightKey, type);
        Set<String> v = result.getOrDefault(operateResult, new HashSet<>());
        for (String leftValue : leftValues) {
            for (String rightValue : rightValues) {
                v.add("(" + leftValue + getOperatorString(type) + rightValue + ")");
            }
        }
        result.put(operateResult, v);
    }

    private int getOperateResult(int num1, int num2, int type) {
        int result = switch (type) {
            case 1 -> num1 + num2;
            case 2 -> num1 - num2;
            case 3 -> num1 * num2;
            case 4 -> num1 / num2;
            default -> throw new RuntimeException("not support type" + type);
        };
        return result;
    }

    private String getOperatorString(int type) {
        String result = switch (type) {
            case 1 -> "+";
            case 2 -> "-";
            case 3 -> "*";
            case 4 -> "/";
            default -> throw new RuntimeException("not support type" + type);
        };
        return result;
    }

    /* default */
    public static <T> void print(T t) {
        System.out.println(t);
    }
}
