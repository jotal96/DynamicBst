package net.joaosilva.bst.logic.lambdaFunctions;

@FunctionalInterface
public interface NodeComparator<T> {
    int compare(T a, T b);
}
