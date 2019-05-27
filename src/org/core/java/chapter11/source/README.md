# Source-Level Annotation Processing

One use for annotation is the automatic processing of source files
to produce more source code, configuration files, scripts, or whatever
else one might want to generate.

This package will generate for the toString() methods.

## Annotation Processors

Annotation processing is integrated into the Java compiler. During compilation, 
you can invoke annotation processors by running

```shell
    javac -processor ProcessorClassName,ProcessorClassName2... sourceFiles
```

The compiler locates the annotations of the source files. Each annotation processor is executed
in turn and given the annotations in which it expressed an interest. If an annotation processor
creates a new source file, the process is repeated. Once a processing round yields not further
source files, all source files are compiled.

An annotation processor implements the Processor interface, generally by extending the
{@link AbstractProcessor} class.


## The Language Model API

You use the language model API for analyzing source-level annotations. Unlike the reflection API,
which presents the virtual machine representation of classes and methods, the language model
API lets you analyze a Java program according to the rules of the Java language.

The compiler procudes a tree whose nodes are instances of classes that implement the
javax.lang.model.element.Element interface and its subinterfaces, TypeElement, VariableElement,
ExecutableElement, and so on. These are compiletime analogs to the Class, Field/Parameter,
Method/Constructor reflection classes.

The generated files don't have to be source files. Annotation processors may choose to generate
XML descriptors, property files, shell scripts, HTML documentation and so on.

