package MultiThread;

public class ThreadUseRunnable implements Runnable{

	ThreadUseRunnable () {}//Constructor.
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("This is a child thread that implements Runnable Interface!");
		System.out.println("I will be hang up for 1 sec!");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			return ;
		}
		System.out.println("My task is finished. Thread 2 will suicide!");
	}

}
