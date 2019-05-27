
#Annotations

1. You can annotate declarations just as you use modifiers such as
public or static.
2. You can also annotate types that appear in declarations, casts,
instanceof checks, or method references.
3. An annotation starts with a @ symbol and may contain key/value pairs called
elements.
4. Annotation values must be compile-time constants, primitive types, enum constants,
class literals, other annotations, or arrays thereof.
5. An item can have repeating annotations or annotations of different types.
6. To define an annotations, specify an annotation interface whose methods
correspond to the annotation elements.
7. The Java library defines over a dozen annotations, and annotations are
extensively used in the Java Enterprise Edition.
8. To process annotations in a running Java program, you can use reflection
and query the reflected items for annotations.
9. Annotation processors process source files during compilation, using the
Java language model API to locate annotated items.

## Defining Annotations

Each annotation must be declared by an annotation interface, with the @interface syntax.
The methods of the interface correspond to the elements of the annotation. For example,
the JUnit Test annotation is defined by the following interface:

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
  long timeout();
}
```

The @interface declaration creates an actual Java interface. Tools that process annotations receive
objects that implement the annotation interface.

The @Target and @Retention annotations are meta-annotations. They annotate the Test
annotation, indicating the places where the annotation can occur and where it can be accessed.

The value of the @Target meta-annotation is an array of ElementType objects, specifying the items
to which the annotation can apply.

The @Retention meta-annotation specifies where the annotation can be accessed. There are three choices:

1. RetentionPolicy.SOURCE: The annotation is available to source processors, but it is not included
in class files.
2. RetentionPolicy.CLASS: The annotation is included in class files, but the virtual machine does not
load them. This is the default.
3. RetentionPolicy.RUNTIME: The annotation is available at runtime and can be accessed through the
reflection API.

Note: To process annotations in class files, we usually process the annotations on the fly when
the class file is being loaded into the virtual machine. We need a tool such as ASM (http://asm.ow2.org)
to locate and evaluate the annotations, and rewrite the byte codes.


