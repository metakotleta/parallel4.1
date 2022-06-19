public class Specialist {
    private static final long SLEEP = 1000;
    private static final long TIMES_BEFORE_DEATH = 5;
    private int deathCounter;
    private ATS ats;


    public Specialist(ATS ats) {
        this.ats = ats;
    }

    public void receiveCall() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                try {
                    Call call = ats.getCallsQueue().poll();
                    if (call = null) {
                        continue;
                    }
                    System.out.printf("%s принял вызов с callId %d, в очереди ещё %d вызовов\n", Thread.currentThread().getName(),
                            call.getCallId(), ats.getCallsQueue().size());
                    Thread.sleep(SLEEP);
                } catch (NullPointerException e) {
                    System.out.printf("%s: очередь пуста, пью кофе\n", Thread.currentThread().getName());
                    Thread.sleep(5000);
                    deathCounter++;
                    if (deathCounter > TIMES_BEFORE_DEATH) {
                        System.out.printf("%s умер из-за отравления кофеином\n", Thread.currentThread().getName());
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
