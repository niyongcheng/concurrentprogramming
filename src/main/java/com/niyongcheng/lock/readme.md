in Java, lock have basic three types:
1:reentrantLock
2:reentrantReadWriteLock

some important interfaces:
1: Lock
2: ReadWriteLock
3: Condition

two (abstract) classes:
1: AbstractOwnableSynchronizer
2: AbstractQueueSynchronizer

for the util lock,
1: CountDownLatch
2: CyclicBarrier
3: Semaphore

detail for the lock in java 8, see below link

https://www.cnblogs.com/skywang12345/p/3496098.html

for lock it use AQS framework,

AQS depend on the CLH queue and volatile State variable and CAS to confirm the lock

AQS是一个用来构建锁和同步器的框架，
使用AQS能简单且高效地构造出应用广泛的大量的同步器，
比如我们提到的ReentrantLock，Semaphore，
其他的诸如ReentrantReadWriteLock，SynchronousQueue，FutureTask等等皆是基于AQS的。

AQS核心思想是，
如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。
如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制AQS是用CLH队列锁实现的，
即将暂时获取不到锁的线程加入到队列中。

CLH(Craig,Landin,and Hagersten)队列
是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）。
AQS是将每条请求共享资源的线程封装成一个CLH锁队列的一个结点（Node）来实现锁的分配。

AQS使用一个int成员变量来表示同步状态，通过内置的FIFO队列来完成获取资源线程的排队工作。AQS使用CAS对该同步状态进行原子操作实现对其值的修改。

private volatile int state;//共享变量，使用volatile修饰保证线程可见性
状态信息通过protected类型的getState，setState，compareAndSetState进行操作

//返回同步状态的当前值
protected final int getState() {  
        return state;
}
 // 设置同步状态的值
protected final void setState(int newState) { 
        state = newState;
}
//原子地（CAS操作）将同步状态值设置为给定值update如果当前同步状态的值等于expect（期望值）
protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
}

以ReentrantLock为例，state初始化为0，表示未锁定状态。
A线程lock()时，会调用tryAcquire()独占该锁并将state+1。
此后，其他线程再tryAcquire()时就会失败，直到A线程unlock()到state=0（即释放锁）为止，其它线程才有机会获取该锁。
当然，释放锁之前，A线程自己是可以重复获取此锁的（state会累加），这就是可重入的概念。
但要注意，获取多少次就要释放多么次，这样才能保证state是能回到零态的。

再以CountDownLatch以例，任务分为N个子线程去执行，state也初始化为N（注意N要与线程个数一致）。这N个子线程是并行执行的，每个子线程执行完后countDown()一次，state会CAS(Compare and Swap)减1。等到所有子线程都执行完后(即state=0)，会unpark()主调用线程，然后主调用线程就会从await()函数返回，继续后余动作。

一般来说，自定义同步器要么是独占方法，要么是共享方式，他们也只需实现tryAcquire-tryRelease、tryAcquireShared-tryReleaseShared中的一种即可。但AQS也支持自定义同步器同时实现独占和共享两种方式，如ReentrantReadWriteLock。