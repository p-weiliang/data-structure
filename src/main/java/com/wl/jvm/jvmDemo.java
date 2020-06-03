package com.wl.jvm;

/**
 * 作者: wl
 * 创建时间: 2020/5/19 16:37
 * 声明:jvm相关命令
 */
public class jvmDemo {
    public static void main(String[] args) {
        //返回CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        long maxMemory = Runtime.getRuntime().maxMemory();//java虚拟机可以使用最大内存
        long totalMemory = Runtime.getRuntime().totalMemory();//java虚拟机默认出场的内存
        System.out.printf("-Xmx:MAX_MEMORY = %d 字节，%d M\n",maxMemory,maxMemory/1024/1024);
        System.out.printf("-Xmx:TOTAL_MEMORY = %d 字节，%d MB\n",totalMemory,totalMemory/1024/1024);
        //制造内存溢出错误
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        byte[] bytes = new byte[10*1024*1024];

    }
}
