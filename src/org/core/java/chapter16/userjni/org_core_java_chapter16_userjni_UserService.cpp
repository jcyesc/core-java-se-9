#include "org_core_java_chapter16_userjni_UserService.h"

#include <iostream>

/*
 * Creates a new {@code User}.
 */
JNIEXPORT jobject JNICALL Java_org_core_java_chapter16_userjni_UserService_create(
		JNIEnv *env, jobject thisObj, jstring name, jint age) {
	std::cout << "JNI c++ create()" << std::endl;

	jclass userClass = env->FindClass("org/core/java/chapter16/userjni/User");
	jobject newUser = env->AllocObject(userClass);

	// For type signatures, see:
	// https://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/types.html
	jfieldID nameField = env->GetFieldID(userClass, "name",
			"Ljava/lang/String;");
	jfieldID ageField = env->GetFieldID(userClass, "age", "I");
	jfieldID favoriteNumbersField = env->GetFieldID(userClass,
			"favoriteNumbers", "[J");
	jfieldID numFavoriteNumbersField = env->GetStaticFieldID(userClass,
			"NUM_FAVORITE_NUMBERS", "I");

	env->SetObjectField(newUser, nameField, name);
	env->SetIntField(newUser, ageField, age);

	// Get the value from the static constant define in User class.
	jint size = env->GetStaticIntField(userClass, numFavoriteNumbersField);
	jlong favoriteNumbers[size];
	for (int i = 0; i < size; ++i) {
		favoriteNumbers[i] = 1000 + i;
	}

	// Note that jLong[] != jlongArray.
	jlongArray longArray = env->NewLongArray(size);
	env->SetLongArrayRegion(longArray, 0, size, favoriteNumbers);
	env->SetObjectField(newUser, favoriteNumbersField, longArray);

	return newUser;
}

/*
 * Calls the method {@code User#toString()}.}
 */
JNIEXPORT jstring JNICALL Java_org_core_java_chapter16_userjni_UserService_print(
		JNIEnv *env, jobject thisObj, jobject user) {
	std::cout << "JNI c++ print()" << std::endl;

	jclass userClass = env->GetObjectClass(user);
	jmethodID methodId = env->GetMethodID(userClass, "toString",
			"()Ljava/lang/String;");

	jstring result = (jstring) env->CallObjectMethod(user, methodId);
	return result;
}

/*
 * Sets {@code value} in the array {@code User#favoriteNumbers[]} at {@code index}.
 */
JNIEXPORT void JNICALL Java_org_core_java_chapter16_userjni_UserService_setFavoriteNumber
(JNIEnv *env, jobject thisObj, jobject user, jint index, jlong value) {
	std::cout << "JNI c++ setFavoriteNumbe()" << std::endl;

	jclass userClass = env->GetObjectClass(user);
	jfieldID favoriteNumbersField = env->GetFieldID(userClass,
			"favoriteNumbers", "[J");

	jlongArray longArray = (jlongArray)env->GetObjectField(user, favoriteNumbersField);
	jsize len = env->GetArrayLength(longArray);

	// Get and print the elements in the array.
	jboolean *isCopy = 0;
	jlong *numbers = env->GetLongArrayElements(longArray, isCopy);
	for (int i = 0; i < len; i++) {
		std::cout << "JNI c++ favoriteNumbers[" << i << "] = " << numbers[i] << std::endl;
	}

	// Just set the element that starts at "index" with "value".
	env->SetLongArrayRegion(longArray, index, 1, &value);
}

