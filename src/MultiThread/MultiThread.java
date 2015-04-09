package MultiThread;

public class MultiThread {
	public static void main (String[] argv) {
		System.out.println("This is a main thread!");
		
		ThreadUseExtends thread1 = new ThreadUseExtends();
		Thread thread2 = new Thread(new ThreadUseRunnable(), "SecondThread");
		
		thread1.start();
		System.out.println("Main thread will be hang up for 7 sec!");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			return ;
		}
		System.out.println("Back to main thread!");
		if (thread1.isAlive()) {
			try {
				thread1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Main thread kills thread 1!!");
			System.out.println(thread1.isAlive());
		} else {
			System.out.println("OMG! Thread 1 has finished its task!");
		}
		
		thread2.start();
		System.out.println("Main thread will be hang up for 7 sec!");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			return ;
		}
		System.out.println("Back to main thread!");
		if (thread2.isAlive()) {
			try {
				thread2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Main thread kills thread 2!!");
			System.out.println(thread2.isAlive());
		} else {
			System.out.println("OMG! Thread 2 has finished its task!");
		}
	}
}
