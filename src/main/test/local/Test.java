package local;

import java.util.*;
import java.util.concurrent.CountDownLatch;


public class Test {

    public static void main(String[] args) {
//		generate(2,1);
//		checkString("{(()})".toCharArray());
//		System.out.println(checkString("{(())}".toCharArray()));
//		System.out.println(checkString("{(})()".toCharArray()));
//        intEqual();
//        System.out.println(firstUniqChar("abaccdeff"));
//        System.out.println(longestSubarray(new int[]{10,1,2,4,7,2},5));
        multiAdd();

    }

    public static void intEqual() {
        Integer i1 = 150;
        Integer i2 = 150;
        System.out.println(i1 == i2);

        int i = 1;
        double d = 0.9;
        System.out.println(i + d);
    }

    public static int longestSubarray(int[] nums, int limit) {
        int res = 0,current=0;
        int left = 0;
        TreeMap<Integer, Integer> window = new TreeMap<>();

        for (int i=0;i<nums.length;i++) {
            window.put(nums[i],i);
            while (window.lastKey()-window.firstKey()>limit){
                window.remove(nums[left]);
                left++;
                current--;
            }
            current++;
            res = Math.max(res,current);
        }

        return res;
    }

    /**
     * 编写一个函数以生成n个小括号，和m个大括号，要求输出格式正确的括号的所有组合。
     * 例如，
     * 给定n = 1，m=1
     * 输出为 ({}) ,(){}, {}(), {()}
     * 给定n = 2，m=1
     * 输出为{}()(), {()}(),{()()}, ({})(),(){}(),(){()}, ()({}), ()(){},{}(()),{(())},({}()),({()}),(({})),((){}),(()){}
     */
    public static Set<String> generate(int m, int n) {
        List<Set<String>> temp = new ArrayList<Set<String>>();
        HashSet<String> s0 = new HashSet<String>();
        s0.add("");
        temp.add(s0);
        HashSet<String> s1 = new HashSet<String>();
        s1.add("()");
        temp.add(s1);

        for (int i = 1; i < m + n; i++) {
            HashSet<String> s = new HashSet<String>();
            for (int j = 0; j < temp.size(); j++) {
                Set<String> sj = temp.get(j);
                Set<String> sElse = temp.get(temp.size() - 1 - j);
                for (String string : sj) {
                    for (String string2 : sElse) {
                        s.add("(" + string + ")" + string2);
                    }
                }
            }
            temp.add(s);
        }

        // 1)获取m+n对小括号组成的所有字符串
        Set<String> temp1 = temp.get(m + n);

        // 2)将m对小括号改为大括号
        HashSet<String> temp2 = new HashSet<String>();
        for (int i = 0; i < m; i++) {
            for (String string : temp1) {
                temp2.addAll(replace(string));
            }
            temp1.clear();
            temp1.addAll(temp2);
            temp2.clear();
        }

        for (String string : temp1) {
            System.out.println(string);
        }
        System.out.println(String.join(",", temp1));
        return temp1;
    }

    // 将传入字符串中的一对小括号改为大括号
    public static Set<String> replace(String string) {
        HashSet<String> res = new HashSet<String>();
        for (int i = 0; i < string.length(); i++) {
            char[] charArray = string.toCharArray();
            if (charArray[i] == '(') {
                charArray[i] = '{';
                for (int j = i + 1; j < string.length(); j++) {
                    if (charArray[j] == ')') {
                        charArray[j] = '}';
                        //校验字符串是否合法
                        if (checkString(charArray)) {
                            res.add(new String(charArray));
                        }
                        charArray[j] = ')';
                    }
                }
            }
        }
        return res;
    }

    public static boolean checkString(char[] charArray) {
        LinkedList<Character> stack = new LinkedList<Character>();
        for (Character character : charArray) {
            if (character.equals('(') || character.equals('{')) {
                stack.addLast(character);
            } else {
                if (character.equals(')') && !stack.getLast().equals('(')) {
                    return false;
                }
                if (character.equals('}') && !stack.getLast().equals('{')) {
                    return false;
                }
                stack.removeLast();
            }
        }
        return true;
    }

    public static int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer p = map.get(c);
            if (p != null) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }

        for (Map.Entry e : map.entrySet()) {
            if ((Integer) e.getValue() != -1) {
                return (Integer) e.getValue();
            }
        }
        return -1;
    }

    static int num;
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void multiAdd(){

        Runnable r = () -> {
            for (int i=0;i<1000;i++){
                num++;
            }
//            countDownLatch.countDown();
        };


        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(num);
    }
}
