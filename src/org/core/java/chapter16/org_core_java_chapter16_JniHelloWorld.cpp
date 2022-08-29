
#include "org_core_java_chapter16_JniHelloWorld.h"

#include <iostream>

JNIEXPORT void JNICALL Java_org_core_java_chapter16_JniHelloWorld_greet
  (JNIEnv *env, jobject obj) {
	std::cout << "JNI C++ greet()" << std::endl;
}


JNIEXPORT jlong JNICALL Java_org_core_java_chapter16_JniHelloWorld_sum
  (JNIEnv *env, jobject obj, jint first, jint second) {
	std::cout << "JNI c++ sum() " << std::endl;
    return (long)first + (long)second;
}

JNIEXPORT jstring JNICALL Java_org_core_java_chapter16_JniHelloWorld_greetTo(
		JNIEnv *env, jobject obj, jstring name, jboolean isMale) {
	const char *name_ptr = env->GetStringUTFChars(name, NULL);
	std::string title;

	if (isMale) {
		title = "Mr. ";
	} else {
		title = "Ms. ";
	}

	std::string fullName = title + name_ptr;
	return env->NewStringUTF(fullName.c_str());
}
