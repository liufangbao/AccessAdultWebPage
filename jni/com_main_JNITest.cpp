#include "com_main_JNITest.h"

/*
 * Class:     com_main_JNITest
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_main_JNITest_add
  (JNIEnv *env, jobject o, jint arg1, jint arg2)
{
	return arg1+arg2;
}
