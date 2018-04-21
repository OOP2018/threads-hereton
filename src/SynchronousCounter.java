
public class SynchronousCounter extends Counter {

	@Override
	public synchronized void add(int amount) {
		// To avoid the overhead of calling the superclass add,
		// define the superclass attribute (total) as protected and
		// directly add to the total here. Don't define a new attribute
		// in this class!
		total += amount;
	}
}
