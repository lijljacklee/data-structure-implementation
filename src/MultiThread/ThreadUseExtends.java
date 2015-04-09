package MultiThread;

public class ThreadUseExtends extends Thread{
	ThreadUseExtends () {};
	
	public void run () {
		System.out.println("This is a child thread that extends Thread Class!");
		System.out.println("I will be hang up fro 10 sec!");
		
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			return ;
		}
		System.out.println("My task is finished. Thread 1 will suicide!");
	}
}
