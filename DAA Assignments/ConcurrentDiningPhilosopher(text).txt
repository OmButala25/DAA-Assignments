import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class Philosopher implements Runnable {
    private final String name;
    private final Semaphore leftChopstick;
    private final Semaphore rightChopstick;

    public Philosopher(String name, Semaphore leftChopstick, Semaphore rightChopstick) {
        this.name = name;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(name + " is thinking.");
        Thread.sleep(1000); // Simulate thinking time
    }

    private void eat() throws InterruptedException {
        leftChopstick.acquire();
        rightChopstick.acquire();
        System.out.println(name + " is eating.");
        Thread.sleep(1000); // Simulate eating time
        rightChopstick.release();
        leftChopstick.release();
    }
}

public class ConcurrentDiningPhilosopher {
    private static final int NUM_PHILOSOPHERS = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_PHILOSOPHERS);
        Semaphore[] chopsticks = new Semaphore[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            chopsticks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher("Philosopher " + (i + 1), chopsticks[i], chopsticks[(i + 1) % NUM_PHILOSOPHERS]);
            executorService.execute(philosophers[i]);
        }

        // Wait for a while to let the philosophers dine
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shut down the executor service
        executorService.shutdownNow();
    }
}


// In this concurrent implementation, each philosopher is represented by a Runnable and executed by an ExecutorService. The Semaphore class is used to represent the chopsticks, with each chopstick having an initial permit of 1.

// The program creates a fixed-size thread pool with the number of philosophers as the pool size. Each philosopher acquires the left chopstick, then the right chopstick before eating. If any philosopher is interrupted during their execution (e.g., due to program termination), they will gracefully exit.

// After running for a certain duration (in this case, 10 seconds), the executor service is shut down, terminating all the philosopher threads.

// When you run this code, you will see the philosophers thinking and eating concurrently, ensuring that they can access the chopsticks without deadlock.

// Note: The program may not terminate automatically in all cases due to the interruption handling. You can adjust the sleep duration or modify the termination condition to meet your requirements.

