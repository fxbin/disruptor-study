package cn.fxbin.learn.disruptor.demo;

import cn.fxbin.learn.disruptor.demo.factory.LongEventFactory;
import cn.fxbin.learn.disruptor.demo.handler.LongEventHandler;
import cn.fxbin.learn.disruptor.demo.model.LongEvent;
import cn.fxbin.learn.disruptor.demo.producer.LongEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * DemoMain
 *
 * @link https://github.com/LMAX-Exchange/disruptor/blob/master/src/perftest/java/com/lmax/disruptor/sequenced/OneToOneSequencedThroughputTest.java
 *
 * @author fxbin
 * @version v1.0
 * @since 2019/11/28 11:06
 */
public class LongEventMain {

    //
//    public static void main(String[] args) throws Exception {
//        // The factory for the event
//        LongEventFactory factory = new LongEventFactory();
//
//        // Specify the size of the ring buffer, must be power of 2.
//        int bufferSize = 1024;
//
//        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);
//
//        // Connect the handler
//        disruptor.handleEventsWith(new LongEventHandler());
//
//        // Start the Disruptor, starts all threads running
//        disruptor.start();
//
//        // Get the ring buffer from the Disruptor to be used for publishing.
//        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//
//        LongEventProducer producer = new LongEventProducer(ringBuffer);
//
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for (long l = 0; true; l++) {
//            bb.putLong(0, l);
//            producer.onData(bb);
//            Thread.sleep(1000);
//        }
//    }

    // =================================================================================================================

//    // Java8 实现
//    public static void main(String[] args) throws Exception {
//        // Specify the size of the ring buffer, must be power of 2.
//        int bufferSize = 1024;
//
//        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
//
//        // Connect the handler
//        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));
//
//        // Start the Disruptor, starts all threads running
//        disruptor.start();
//
//        // Get the ring buffer from the Disruptor to be used for publishing.
//        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//
//        ByteBuffer bb = ByteBuffer.allocate(8);
////        for (long l = 0; true; l++) {
////            bb.putLong(0, l);
////            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
////            Thread.sleep(1000);
////        }
//
//        for (long l = 0; true; l++)
//        {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent((event, sequence) -> event.setValue(bb.getLong(0)));
//            Thread.sleep(1000);
//        }
//    }


    // =================================================================================================================

    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println(event);
    }

    public static void translate(LongEvent event, long sequence, ByteBuffer buffer) {
        event.setValue(buffer.getLong(0));
    }

    public static void main(String[] args) throws Exception {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        // single
//        Disruptor<LongEvent> disruptor = new Disruptor<>(
//                LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());

        // multi
        Disruptor<LongEvent> disruptor = new Disruptor<>(
                LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());


        // Connect the handler
        disruptor.handleEventsWith(LongEventMain::handleEvent);

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent(LongEventMain::translate, bb);
            Thread.sleep(1000);
        }
    }

}
