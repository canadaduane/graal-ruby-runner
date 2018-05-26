# GraalVM Ruby Runner Test

I'm trying to make a simple java app that runs Ruby scripts and can be ahead-of-time (AOT) compiled with GraalVM's `native-image` tool.

## Status

This can be run as a standard java JIT compiled class:

```
$GRAALVM_HOME/bin/javac RubyRunner.java
$GRAALVM_HOME/bin/java RubyRunner
```

Or, an AOT compiled class:

```
$GRAALVM_HOME/bin/native-image --language:ruby RubyRunner
Build on Server(pid: 57721, port: 26682)*
   classlist:   3,176.92 ms
       (cap):   1,797.19 ms
       setup:   4,530.11 ms
  (typeflow):  32,921.98 ms
   (objects):  48,411.12 ms
  (features):   5,916.38 ms
    analysis:  89,228.54 ms
5309 method(s) included for runtime compilation
    universe:   2,435.47 ms
     (parse):   6,453.59 ms
    (inline):   8,834.04 ms
   (compile):  45,526.35 ms
     compile:  64,499.44 ms
       image:  12,442.06 ms
       write:  34,852.53 ms
     [total]: 211,586.70 ms
```

The size of the executable (on my Mac) is 106 MB. A bit large, but I guess it's including everything ruby can do in there.

Now, when I run the AOT compiled `./rubyrunner`, I get the following warnings, but it works ("Hey!" at the end shows it works):

```
[ruby] WARNING could not determine TruffleRuby's home - the standard library will not be available
Evaluating script.rb as ruby
[ruby] WARNING could not determine TruffleRuby's home - the standard library will not be available
Hey!
```
