package com.cdp.app;

public class DeadlockSimulator {

	public static void main(String[] args) {
		final Account a = new Account(1000);
		final Account b = new Account(2000);

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				transfer(a, b, 500);
			}
		}).start();
		
		transfer(b, a, 300);
		
		System.out.println("Account a " + a.getBalance());
		System.out.println("Account b " + b.getBalance());
	}
	
	static void transfer(Account acc1, Account acc2, int amount) {
		System.out.println("Trying to lock Account 1");
		synchronized(acc1) {
			System.out.println("Locked Account 1");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Trying to lock Account 2");
			synchronized(acc2) {
				System.out.println("Locked Account 2");
				acc1.withdraw(amount);
				acc2.deposit(amount);
			}	
		}
	}

}
