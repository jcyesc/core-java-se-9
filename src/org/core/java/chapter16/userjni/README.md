
# Java Native Interface (JNI) - User

## Steps to set up native calls


1. Define the native methods in the Java class.
2. Create the C/C++ header using:

```shell
$ javac -h . User.java UserService.java UserMain.java
$ ls
org_core_java_chapter16_userjni_UserService.h  User.class  UserMain.class  UserService.class
README.md                                      User.java   UserMain.java   UserService.java
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
       org_core_java_chapter16_userjni_UserService.cpp \
       -o org_core_java_chapter16_userjni_UserService.o
$ ls
org_core_java_chapter16_userjni_UserService.cpp  README.md   UserMain.class     UserService.java
org_core_java_chapter16_userjni_UserService.h    User.class  UserMain.java
org_core_java_chapter16_userjni_UserService.o    User.java   UserService.class
```

6. Create the shared library

```shell
$ g++ -shared -fPIC -o libuserservice101.so \
      org_core_java_chapter16_userjni_UserService.o -lc
$ ls
libuserservice101.so
```

7. Run the program:

```shell
# Go to the root directory of the source and run:
$ cd ~/src
$ ls
org

$ java -cp . -Djava.library.path=./org/core/java/chapter16/userjni \
              org.core.java.chapter16.userjni.UserMain
Running Native interface
JNI c++ create()
JNI c++ print()
[name]=Luna, [age]=21, [favoriteNumbers]=[1000, 1001, 1002, 1003, 1004]
JNI c++ setFavoriteNumbe()
JNI c++ favoriteNumbers[0] = 1000
JNI c++ favoriteNumbers[1] = 1001
JNI c++ favoriteNumbers[2] = 1002
JNI c++ favoriteNumbers[3] = 1003
JNI c++ favoriteNumbers[4] = 1004
JNI c++ print()
[name]=Luna, [age]=21, [favoriteNumbers]=[1000, 1001, 23, 1003, 1004]
```
