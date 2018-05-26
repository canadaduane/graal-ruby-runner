# GraalVM Ruby Runner Test

I'm trying to make a simple java app that runs Ruby scripts and can be ahead-of-time (AOT) compiled with GraalVM's `native-image` tool.

## Status

When I run:

```
$GRAALVM_HOME/bin/javac RubyRunner.java
$GRAALVM_HOME/bin/java RubyRunner
```

This works!

But when I run:

```
$GRAALVM_HOME/bin/native-image RubyRunner
```

I get the following error:

```
Build on Server(pid: 51798, port: 26682)
   classlist:     164.90 ms
       (cap):   1,111.21 ms
       setup:   1,372.80 ms
    analysis:   2,932.66 ms
fatal error: java.lang.NullPointerException
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:598)
	at java.util.concurrent.ForkJoinTask.get(ForkJoinTask.java:1005)
	at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:398)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:240)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:337)
	at com.oracle.svm.hosted.server.NativeImageBuildServer.executeCompilation(NativeImageBuildServer.java:378)
	at com.oracle.svm.hosted.server.NativeImageBuildServer.lambda$processCommand$8(NativeImageBuildServer.java:315)
	at com.oracle.svm.hosted.server.NativeImageBuildServer.withJVMContext(NativeImageBuildServer.java:396)
	at com.oracle.svm.hosted.server.NativeImageBuildServer.processCommand(NativeImageBuildServer.java:312)
	at com.oracle.svm.hosted.server.NativeImageBuildServer.processRequest(NativeImageBuildServer.java:256)
	at com.oracle.svm.hosted.server.NativeImageBuildServer.lambda$serve$7(NativeImageBuildServer.java:216)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.NullPointerException
	at com.oracle.graal.pointsto.ObjectScanner.scanField(ObjectScanner.java:113)
	at com.oracle.graal.pointsto.ObjectScanner.doScan(ObjectScanner.java:263)
	at com.oracle.graal.pointsto.ObjectScanner.finish(ObjectScanner.java:307)
	at com.oracle.graal.pointsto.ObjectScanner.scanBootImageHeapRoots(ObjectScanner.java:78)
	at com.oracle.graal.pointsto.ObjectScanner.scanBootImageHeapRoots(ObjectScanner.java:60)
	at com.oracle.graal.pointsto.BigBang.checkObjectGraph(BigBang.java:581)
	at com.oracle.graal.pointsto.BigBang.finish(BigBang.java:552)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:653)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$0(NativeImageGenerator.java:381)
	at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
Error: Processing image build request failed
```
