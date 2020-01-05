package com.zpy.concurrency.stage2.producer_consumer_10.PipedCommunication;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道流是用来在多个线程之间进行信息传递的Java流。（典型的生产者消费者模式）
 * 管道流分为字节流管道流和字符管道流。
 * 字节管道流：PipedOutputStream 和 PipedInputStream。
 * 字符管道流：PipedWriter 和 PipedReader。
 * PipedOutputStream、PipedWriter 是写入者/生产者/发送者。
 * PipedInputStream、PipedReader 是读取者/消费者/接收者
 *
 * 管道流仅用于多个线程之间传递信息，若用在同一个线程中可能会造成死锁。
 * 管道流的输入输出是成对的，一个输出流只能对应一个输入流，使用构造函数或者connect函数进行连接。
 */
public class PipedCommunication {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread writerThread = new Thread(new Writer(out), "写入线程");
        Thread readerThread = new Thread(new Reader(in), "读出线程");
        writerThread.start();
        readerThread.start();
    }

    public static class Writer implements Runnable {
        private PipedWriter pipedWriter;

        public Writer(PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = System.in.read()) != -1) {
                    System.out.println(Thread.currentThread().getName() + "写入字符：" + (char) receive);
                    //写入字符到管道字符输出流中
                    pipedWriter.write(receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Reader implements Runnable {
        private PipedReader pipedReader;

        public Reader(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = pipedReader.read()) != -1) {
                    System.out.println(Thread.currentThread().getName() + "读出字符：" + (char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
