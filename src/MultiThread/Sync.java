package MultiThread;

public class Sync implements Runnable{
	public static class Test {
		//synchronized keep this method can be called by only one thread at a time.
		public synchronized void method (int id, int hc) {
			System.out.println("Thread " + id+" with its test " + hc + " comes to this method!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("I'm done!");
		}
	}
	
	private Test test;
	private int id;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		test.method(this.id, this.test.hashCode());
	}
	
	public Sync (Test test, int id) {
		this.test = test;
		this.id = id;
	}
	
	public static void main (String[] argv) {
		Test test1 = new Test();
		Test test2 = new Test();
		Sync sync1 = new Sync(test1, 1);
		Sync sync2 = new Sync(test1, 2);
		new Thread(sync1).start();
		new Thread(sync2).start();
	}
}
