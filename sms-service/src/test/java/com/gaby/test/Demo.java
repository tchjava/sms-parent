package com.gaby.test;

import org.junit.Test;

import java.util.*;

public class Demo {
    @Test
    public void f1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> list1 = Arrays.asList(2, 3);
        list.stream().filter(x -> x>2).forEach(x->{
            System.out.println(x);
        });
    }

    @Test
    public void f2() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("学生1");
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("学生2");
        Student student3 = new Student();
        student3.setId(3);
        student3.setName("学生3");

        students.add(student1);
        students.add(student2);
        students.add(student3);


    }

    @Test
    public void f3() {
        List<String> strings;
        boolean flag=true;
        if (flag) {
            List<String> strings1 = new ArrayList<>();
            strings1.add("1");
            strings = strings1;
        }else{
            List<String> strings2 = new ArrayList<String>();
            strings2.add("2");
            strings=strings2;
        }
        strings.stream().forEach(s-> System.out.println(s));

    }

    @Test
    public void f4() {
//        List<Integer> integers = new ArrayList<>();
//        integers.add(1);
//        integers.add(2);
//        integers.add(3);
//
//        Map map = new HashMap<>();
//        map.put("1", 1);
//        map.put("2", 2);
//        map.put("3", 3);
//
//        for (String key : map.keySet()) {
//
//        }

    }
}
