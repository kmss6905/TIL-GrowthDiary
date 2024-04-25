package thread;

public class RunMultiThreads {

  public static void main(String[] args) {
    RunMultiThreads runMultiThreads = new RunMultiThreads();
    runMultiThreads.runMultiThread();
  }

  public void runMultiThread() {
    RunnableSample[] runnableSamples = new RunnableSample[5];
    ThreadSample[] threadSamples = new ThreadSample[5];
    for (int i = 0; i <5; i++) {
      runnableSamples[i] = new RunnableSample();
      threadSamples[i] = new ThreadSample();

      new Thread(runnableSamples[i]).start();
      threadSamples[i].start();
    }

    System.out.println("RunMultiThreads.runMultiThread() method is ended");
  }
}
