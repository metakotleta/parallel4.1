import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS {
    private static final long SLEEP = 30000;
    private static final int CALLS_PER_MINUTE = 60;
    private ConcurrentLinkedQueue<Call> callsQueue = new ConcurrentLinkedQueue<>();
    private int callCounter;

    public ConcurrentLinkedQueue<Call> getCallsQueue() {
        return callsQueue;
    }

    public void makeCalls()  {
        while(!Thread.interrupted()) {
            callsQueue.add(new Call(callCounter++));
            if (callCounter % CALLS_PER_MINUTE == 0) {
                System.out.printf("Добавлено %d вызовов, текущая очередь: %d\n", CALLS_PER_MINUTE, callsQueue.size());
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
