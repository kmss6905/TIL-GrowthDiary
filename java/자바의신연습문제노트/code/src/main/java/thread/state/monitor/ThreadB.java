package thread.state.monitor;

public class ThreadB implements Runnable {

    ThreadA threadA;

    public ThreadB(ThreadA threadA) {
        this.threadA = threadA;
    }

    public synchronized void run() {
        for(int i=0; i<5; i++) {
            threadA.processMessage("Message" + i);
        }
    }
}