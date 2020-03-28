Synchronize Essense

Have the look with the SynchronizeClass method, it include the synchronize method
and one method which has synchronize block.

use javac -h . SynchronizeClass.java to generate the *.class file
use javap -v SynchronizeClass.class to generate the execute file like below:

Classfile /Users/nisid/concurrentprogramming/src/main/java/com/niyongcheng/synchronize/SynchronizeClass.class
  Last modified Mar 28, 2020; size 734 bytes
  MD5 checksum 877e0b666f78242b9a31f430089d20b2
  Compiled from "SynchronizeClass.java"
public class com.niyongcheng.synchronize.SynchronizeClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #8.#23         // java/lang/Object."<init>":()V
   #2 = Fieldref           #7.#24         // com/niyongcheng/synchronize/SynchronizeClass.count:I
   #3 = Fieldref           #25.#26        // java/lang/System.out:Ljava/io/PrintStream;
   #4 = String             #27            // this is sync on method
   #5 = Methodref          #28.#29        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #6 = String             #30            // this is one sync block demo
   #7 = Class              #31            // com/niyongcheng/synchronize/SynchronizeClass
   #8 = Class              #32            // java/lang/Object
   #9 = Utf8               count
  #10 = Utf8               I
  #11 = Utf8               <init>
  #12 = Utf8               ()V
  #13 = Utf8               Code
  #14 = Utf8               LineNumberTable
  #15 = Utf8               Add
  #16 = Utf8               StackMapTable
  #17 = Utf8               Increase
  #18 = Class              #31            // com/niyongcheng/synchronize/SynchronizeClass
  #19 = Class              #32            // java/lang/Object
  #20 = Class              #33            // java/lang/Throwable
  #21 = Utf8               SourceFile
  #22 = Utf8               SynchronizeClass.java
  #23 = NameAndType        #11:#12        // "<init>":()V
  #24 = NameAndType        #9:#10         // count:I
  #25 = Class              #34            // java/lang/System
  #26 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
  #27 = Utf8               this is sync on method
  #28 = Class              #37            // java/io/PrintStream
  #29 = NameAndType        #38:#39        // println:(Ljava/lang/String;)V
  #30 = Utf8               this is one sync block demo
  #31 = Utf8               com/niyongcheng/synchronize/SynchronizeClass
  #32 = Utf8               java/lang/Object
  #33 = Utf8               java/lang/Throwable
  #34 = Utf8               java/lang/System
  #35 = Utf8               out
  #36 = Utf8               Ljava/io/PrintStream;
  #37 = Utf8               java/io/PrintStream
  #38 = Utf8               println
  #39 = Utf8               (Ljava/lang/String;)V
{
  public com.niyongcheng.synchronize.SynchronizeClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_0
         6: putfield      #2                  // Field count:I
         9: return
      LineNumberTable:
        line 6: 0
        line 8: 4

  public synchronized void Add();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_SYNCHRONIZED
    Code:
      stack=2, locals=2, args_size=1
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
      LineNumberTable:
        line 13: 0
        line 14: 9
        line 13: 17
        line 16: 23
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 20

  public void Increase();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
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
      LineNumberTable:
        line 22: 0
        line 23: 4
        line 24: 12
        line 25: 22
      StackMapTable: number_of_entries = 2
        frame_type = 255 /* full_frame */
          offset_delta = 17
          locals = [ class com/niyongcheng/synchronize/SynchronizeClass, class java/lang/Object ]
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 4
}
SourceFile: "SynchronizeClass.java"


you can the monitorenter and monitorexit for the synchronize block.(line 109,line 114)
this is JVM feature for Synchronize

for the synchronize method, it will use ACC_SYNCHRONIZED flag(line 75).