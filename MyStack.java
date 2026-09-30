public interface MyStack<T>{
    void push(T x);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
    int delete(T x);
}