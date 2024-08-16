# jni-library
A (WiP) gradle plugin for generating a JNI library.

## Goals / TODO
- Motivated by: https://github.com/nokeedev/gradle-native and https://docs.nokee.dev/samples/jni-library-composing-from-source/,
  however, these didn't quite work like I expected. For instance, the consumer can't use the java-library plugin, it must only
  use the java plugin, which isn't ideal for the usecase I had in mind.
- Also hoping to make it much simpler.
- Currently I have a project which uses the `cpp-library` plugin for compiling the cpp side of the JNI project, however,
  the consumer of it is looking for jvm dependencies. The goal of this plugin then should be to provide something that
  the java side can consume.
