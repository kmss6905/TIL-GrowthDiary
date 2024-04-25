package thread;

public class RunThreads {


  public static void main(String[] args) {
    RunThreads runThreads = new RunThreads();
    runThreads.runBasic();
  }

  public void runBasic() {
    Runnable runnable = new RunnableSample();
    new Thread(runnable).start();

    Thread threadSample = new ThreadSample();
    System.out.println("runThreads.runBasic() method is ended");
    threadSample.start();
  }
}
