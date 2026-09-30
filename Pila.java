public class Pila<T> implements MyStack<T> {

    int cap;
    T[] arry;
    int size;

    public Pila(){
        this(10);
    }

    @SuppressWarnings("unchecked")
    public Pila(int capacidad){
        this.cap = capacidad;
        this.arry = (T[]) new Object[this.cap];
        this.size = 0; 
    }

    @SuppressWarnings("unchecked")
    public void push(T x){
        if (this.size == this.cap){
            this.cap = this.cap * 2;
            T[] newArry = (T[]) new Object[this.cap];
            for (int i = 0; i< this.size; i++){
                newArry[i] = this.arry[i];
            }
            this.arry = newArry;
        }
        this.arry[this.size] = x;
        this.size++;
    }
    public T pop(){
        
        if (this.size == 0) return null;
        T temp = this.arry[size-1];
        this.arry[this.size - 1] = null;
        this.size--;
        return temp;

    }

    public T peek(){
        if (this.size == 0) return null;
        return this.arry[size-1];
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    public int delete(T n){
        if (this.size == 0) return -1;
        for (int i=0; i< this.size; i++){
            if (this.arry[i]!=null && this.arry[i].equals(n)) {
                for (int j = i; j < this.size-1; j++){
                    this.arry[j] = this.arry[j+1];
                }
                this.size--;
                this.arry[this.size]= null;
                return i;
            }
        }
        return -1;

    }
    

}