
# Java Native Interface (JNI)

JNI allows java classes to call functions in C/C++. This allows to invoke directly native code.


## Steps to set up native calls


1. Define the native methods in the Java class.
2. Create the C/C++ header using:

```shell
$ javac -h . JniHelloWorld.java
$ ls
JniHelloWorld.class  JniHelloWorld.java  org_core_java_chapter16_JniHelloWorld.h  README.md
```

The `javac` command compiles and creates the `.h` header that needs to be implemented.

3. Implement the header in a cpp file.

The JNI types are defined in https://docs.oracle.com/en/java/javase/11/docs/specs/jni/types.html

4. Set the JAVA_HOME environment variable.

```shell
$ export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
```

5. Compile and link the files.

```shell
$ g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux \
       org_core_java_chapter16_JniHelloWorld.cpp \
       -o org_core_java_chapter16_JniHelloWorld.o
$ ls
JniHelloWorld.class  org_core_java_chapter16_JniHelloWorld.cpp  org_core_java_chapter16_JniHelloWorld.o
JniHelloWorld.java   org_core_java_chapter16_JniHelloWorld.h    README.md
```

6. Create the shared library

```shell
$ g++ -shared -fPIC -o libnative101.so org_core_java_chapter16_JniHelloWorld.o -lc
$ ls
libnative101.so
```

7. Run the program:

```shell
# Go to the root directory of the source and run:
$ cd ~/src
$ ls
org

$ java -cp . -Djava.library.path=./org/core/java/chapter16  \
              org.core.java.chapter16.JniHelloWorld
JNI C++ greet()
JNI c++ sum()
sum = 23
Mr. Silver
Ms. Diana
```


