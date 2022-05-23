public class Main {

    private static final long SLEEP = 35000;

    public static void main(String[] args) throws InterruptedException {
        ATS ats = new ATS();

        Thread atsThread = new Thread(null, ats::makeCalls, "MegaFon");
        atsThread.start();

        new Thread(null, new Specialist(ats)::receiveCall, "Оператор 1").start();
        new Thread(null, new Specialist(ats)::receiveCall, "Оператор 2").start();
        new Thread(null, new Specialist(ats)::receiveCall, "Оператор 3").start();

        Thread.sleep(SLEEP);
        atsThread.interrupt();
    }
}
