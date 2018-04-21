/**
 * An accumulator for a sum.
 */
public class Counter {
	protected long total;

	public Counter() {
		total = 0;
	}

	/**
	 * Add an amount to the total.
	 */
	public void add(int amount) {
		total += amount;
	}

	/**
	 * Get the total value of counter.
	 */
	public long get() {
		return total;
	}
}
