class NodoDos<T>{
    T val;
    NodoDos<T> next;
    NodoDos<T> prev;

    NodoDos(T val){
        this.val = val;
        this.next = null;
        this.prev= null;
    } 
}