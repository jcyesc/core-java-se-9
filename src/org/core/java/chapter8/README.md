# Streams

The key points of this chapter are:

1. Iterators imply a specific traversal strategy and prohibit efficient concurrent execution.

2. You can create streams from collections, arrays, generators or iterators.

3. Use filter to select elements and map to transform elements.

4. Other operations for transforming streams include limit, distinct, and sorted.

5. To obtain a result from a stream, use a reduction operator such as count, max, min,
findFirst, and findAny. Some of these methods return an Optional value.

6. To Optional type is intended as a safe alternative to working with null values. To use
it safely, take advantage of the ifPresent and orElse methods.

7. You can collect stream results in collection, arrays, strings, or maps.

8. To groupingBy and partitioningBy methods of the Collectors class allow you to split
the contents of a stream into groups, and to obtain a result for each group.

9. There are specialized streams for the primitive types int, long, and double.

10. Parallel streams automatically parallelize stream operations.


Streams Workflow

The typical workflow when you work with streams is to set up a pipeline of operations
in three stages:

1. Create a stream.

2. Specify intermediate operations for transforming the initial stream into others,
possible in multiple steps.

3. Apply a terminal operation to produce a result. This operation forces the execution
of the lazy operations that precede it. Afterwards, the stream can no longer be used.







