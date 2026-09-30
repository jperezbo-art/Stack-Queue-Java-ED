public interface MyQueue<T>{
    void enqueue(T x);
    T dequeue();
    T front();
    boolean isEmpty();
    int size();
    int delete(T x);
}