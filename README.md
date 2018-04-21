## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |  100,000,000         |       0.046          |
| Using ReentrantLock     |       100,000             |  0.017              |
| Syncronized method      |                    |    0.03880             |
| AtomicLong for total    |                    |                 |

## 1. Using unsynchronized counter object

answer the questions (1.1 - 1.3)
1.1 - no.
1.3 - because the each thread used the same data and the data were not sync.
## 2. Implications for Multi-threaded Applications

How might this affect real applications?  
 when you deposit your account 2 place at the same time it might have one place that the data did not update so when you deposit your money will only deposit just one time but you deposit 2 times.
## 3. Counter with ReentrantLock

answer questions 3.1 - 3.4
3.1 - yes.
3.2 - the value is always 0
3.3 - It locks until it finished it's job may be use in banking.
3.4 - to unlock for another thread can use it. 

## 4. Counter with synchronized method

answer question 4
4.1 - Yes
4.2 - It synchronized 

## 5. Counter with AtomicLong

answer question 5

## 6. Analysis of Results

answer question 6

## 7. Using Many Threads (optional)

