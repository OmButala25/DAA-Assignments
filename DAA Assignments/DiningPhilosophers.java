import java.util.Random; 
import java.util.concurrent.Semaphore; 

public class DiningPhilosophers { 
// The number of philosophers 
    private static final int NUM_PHILOSOPHERS = 5; 
    public static void main(String[] args) { 

        // Model each chopstick with a lock 
        Semaphore[] chopsticks = new Semaphore[NUM_PHILOSOPHERS]; 

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) { 
            chopsticks[i] = new Semaphore(1); 
        } 
        // Create the philosophers and start each running in its own thread. 
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS]; 

        for (int i = 0; i < NUM_PHILOSOPHERS; i++) { 
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUM_PHILOSOPHERS]); 
            new Thread(philosophers[i]).start(); 
        } 
    } 
} 
class Philosopher implements Runnable { 
    // Used to vary how long a philosopher thinks before eating and how long 
    // philosopher eats 
    private Random numGenerator = new Random(); 
    // The philosopher's unique id private int id; 
    // The chopsticks this philosopher may use private Semaphore leftChopstick; private Semaphore rightChopstick; 
    public Philosopher(int id, Semaphore leftChopstick, Semaphore rightChopstick) { 
        this.id = id; 
        this.leftChopstick = leftChopstick; 
        this.rightChopstick = rightChopstick; 
    } 
    public void run() { 
        try { 
            while (true) { 
                think(); 
                pickUpLeftChopstick(); 
                pickUpRightChopstick(); 
                eat(); 
                putDownChopsticks(); 
            } 
        } catch (InterruptedException e) { 
            System.out.println("Philosopher " + id + " was interrupted.\n"); 
        } 
    } 
    private void think() throws InterruptedException { 
        System.out.println("Philosopher " + id + " is thinking.\n"); 
        System.out.flush(); 
        Thread.sleep(numGenerator.nextInt(10)); 
    } 
    private void pickUpLeftChopstick() throws InterruptedException{ 
        if(leftChopstick.availablePermits() ==0){ 
            System.out.println("Philosopher " +id +" is WAITING for left chopstick"); 
        } 
        leftChopstick.acquire(); 
        System.out.println("Philosopher " + id + " is HOLDING left chopstick.\n"); 
        System.out.flush(); 
    } 
    private void pickUpRightChopstick() throws InterruptedException{ 
        if(rightChopstick.availablePermits() ==0){ 
        System.out.println("Philosopher " +id +" is WAITING for right chopstick"); 
        } 
        rightChopstick.acquire(); 
        System.out.println("Philosopher " + id + " is HOLDING right chopstick.\n"); 
        System.out.flush();     } 
    private void eat() throws InterruptedException { 
        System.out.println("Philosopher " + id + " is eating.\n"); 
        System.out.flush(); 
        Thread.sleep(numGenerator.nextInt(10)); 
    } 
    private void putDownChopsticks() { 
    leftChopstick.release(); 
    rightChopstick.release(); 
    } 
} 
