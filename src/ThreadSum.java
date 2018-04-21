import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSum {
	public static void main(String[] args) {

		// upper limit of numbers to add/subtract to Counter
		final int LIMIT = 100000;
		// The counter that accumulates a total.
		Counter counter = new CounterWithLock();
		runThreads(6, counter, LIMIT);

	}

	public static void runThreads(int nThread, Counter counter, final int limit) {
		ExecutorService executor1 = Executors.newFixedThreadPool(nThread);
		ExecutorService executor2 = Executors.newFixedThreadPool(nThread);

		System.out.println("Starting tasks");
		Long startTime = System.nanoTime();
		for (int k = 1; k <= nThread; k++) {
			// two tasks that add and subtract values using same Counter
			Runnable addtask = new AddTask(counter, limit);
			Runnable subtask = new SubtractTask(counter, limit);
			executor1.submit(addtask);
			executor2.submit(subtask);
		}

		// wait for threads to finish
		try {
			// shutdown tells Executor not to accept any more jobs
			executor1.shutdown();
			executor1.awaitTermination(1, TimeUnit.MINUTES); // wait at most 1 minute
			executor2.shutdown();
			executor2.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println("All done");
		} catch (InterruptedException e) {
			System.out.println("Threads interrupted");
		}
		double elapsed = 1.0E-9 * (System.nanoTime() - startTime);
		System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
		// the sum should be 0. Is it?
		System.out.printf("Counter value is %d\n", counter.get());
	}
}

/**
 * AddTask adds number 1 ... limit to the counter, then exits.
 */
class AddTask implements Runnable {
	private Counter counter;
	private int limit;

	public AddTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	public void run() {
		for (int k = 1; k <= limit; k++)
			counter.add(k);
		// If you want to see when a thread finishes, add this line:
		System.out.println("Done " + Thread.currentThread().getName());
	}
}

/**
 * SubtractTask subtracts number 1 ... limit to the counter, then exits.
 */
class SubtractTask implements Runnable {
	private Counter counter;
	private int limit;

	public SubtractTask(Counter counter, int limit) {
		this.counter = counter;
		this.limit = limit;
	}

	public void run() {
		for (int k = -1; k >= -limit; k--)
			counter.add(k);
		// If you want to see when a thread finishes, add this line:
		System.out.println("Done " + Thread.currentThread().getName());
	}
}
