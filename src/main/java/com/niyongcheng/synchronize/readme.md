Synchronize Essense

Have the look with the SynchronizeClass method, it include the synchronize method
and one method which has synchronize block.

use javac -h . SynchronizeClass.java to generate the *.class file
use javap -c SynchronizeClass.class to generate the execute file like below:

Compiled from "SynchronizeClass.java"
public class com.niyongcheng.synchronize.SynchronizeClass {
  public com.niyongcheng.synchronize.SynchronizeClass();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: iconst_0
       6: putfield      #2                  // Field count:I
       9: return

  public synchronized void Add();
    Code:
       0: iconst_0
       1: istore_1
       2: iload_1
       3: sipush        1000
       6: if_icmpge     23
       9: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
      12: ldc           #4                  // String this is sync on method
      14: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      17: iinc          1, 1
      20: goto          2
      23: return

  public void Increase();
    Code:
       0: aload_0
       1: dup
       2: astore_1
       3: monitorenter
       4: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       7: ldc           #6                  // String this is one sync block demo
       9: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      12: aload_1
      13: monitorexit
      14: goto          22
      17: astore_2
      18: aload_1
      19: monitorexit
      20: aload_2
      21: athrow
      22: return
    Exception table:
       from    to  target type
           4    14    17   any
          17    20    17   any
}

you can the monitorenter and monitorexit for the synchronize block.
this is JVM feature for Synchronize