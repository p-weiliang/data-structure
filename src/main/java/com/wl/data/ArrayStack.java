package com.wl.data;


import java.io.UnsupportedEncodingException;
import java.util.EmptyStackException;
import java.util.Stack;

class test{
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s0 = new StringBuilder().append("he").append("llo").toString();
        System.out.println(s0.intern() == s0);

        String s1 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s1.intern() == s1);
        System.out.println(Byte.MAX_VALUE);
        System.out.println((char)97);
        String a = new String("abc");
        byte[] bytes = a.getBytes("ISO-8859-1");
        System.out.println(bytes.toString());
        System.out.println(new String(bytes,"ISO-8859-1"));
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.list();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.list();
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        Stack stack1 = new Stack<Integer>();
    }
}

/**
 * 作者: wl
 * 创建时间: 2020/5/19 11:34
 * 声明:数组模拟栈实现
 * 栈特点：后进先出（LIFO，Last In First Out）
 * 实现思路 ：
 * 1. 使用数组作为容器存数据
 * 2. 提供push，pop,isEmpty,isFull,toString等方法
 * 3. 定义maxSize作为栈最大容量
 * 4. 定义top作为栈顶指针，数据操作都在栈顶实现
 */
public class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == maxSize-1;
    }

    public void push(int value){
        if(isFull()){
            throw new StackOverflowError("栈满了");
        }
        top++;
        stack[top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if (isEmpty()){
            throw new EmptyStackException();
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }

}
