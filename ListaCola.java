//import java.util.*;

class ListaCola<T> implements EstrucLista<T, Nodo<T>>{
    Nodo<T> head;
    Nodo<T> tail;
    int size; 

    ListaCola(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override   
    public boolean isEmpty(){
        if (this.size == 0) return true;
        return false;
    }

    @Override   
    public int getSize(){
        return this.size;
    }

    @Override
    public void pushFront(T dato){
        Nodo<T> newNodo = new Nodo<>(dato);

        if (this.head == null){
            this.head = newNodo;
            this.tail = newNodo;
            size ++; 
            return;
        }

        newNodo.next = this.head;
        this.head = newNodo;
        this.size++;
    }
    @Override
    public void pushBack(T dato){
        Nodo<T> newNodo = new Nodo<>(dato);

        if (this.head == null){
            this.head = newNodo;
            this.tail = newNodo;
            size ++; 
            return;
        }

        this.tail.next = newNodo;
        this.tail = newNodo;
        this.size++;
    }
    @Override
    public T popFront(){
        if (this.head == null) return null;

        Nodo<T> temp = this.head;
        this.head = this.head.next;
        if (this.head == null) this.tail = null;

        temp.next = null;
        this.size--;

        return temp.val;
    }
    @Override
    public T popBack(){
        if (this.head == null) return null;
        if (this.head.next == null) return this.popFront();

        Nodo<T> temp = this.head;
        Nodo<T> last = temp.next;
        while (last != this.tail){
            temp = temp.next;
            last = last.next;
        }

        temp.next = null;
        this.tail = temp;
        this.size--;
        return last.val;

    }
    @Override
    public Nodo<T> find(T dato){
        if (this.head == null) return null;

        Nodo<T> temp = this.head;
        while(temp != null && !temp.val.equals(dato)){
            temp = temp.next;
        }

        return temp; 
    }
    @Override
    public T Erase(Nodo<T> datoObj){
        if (this.head == null) return null;
        if (this.head == datoObj) return this.popFront();
            
        Nodo<T> temp = this.head;
        Nodo<T> last = temp.next;

        while (last != null && last != datoObj){
            temp = temp.next;
            last = last.next;
        }

        if (last == null) return null;
        if (last == this.tail) this.tail = temp;
        temp.next = last.next;
        last.next = null;
        this.size--;
        return last.val;
    }
    @Override
    public void addBefore(T dato, Nodo<T> datoObj){
        if (this.head == null) return;
        if (this.head == datoObj) {
            this.pushFront(dato);
            return;
        }
        Nodo<T> newNodo = new Nodo<>(dato);
        Nodo<T> temp = this.head;
        Nodo<T> last = temp.next;

        while (last != null && last != datoObj){
            temp = temp.next;
            last = last.next;
        }
        if (last == null) return;
        temp.next = newNodo;
        newNodo.next = last;
        this.size++;
    }
    @Override
    public void addAfter(T dato, Nodo<T> datoObj){
        if (this.head == null) return;
        
        Nodo<T> temp = datoObj;
        if (temp==null) return;

        Nodo<T> newNodo = new Nodo<>(dato);
        if (temp == this.tail) this.tail = newNodo;
        newNodo.next= temp.next;
        temp.next = newNodo;
        this.size++;
    }

    @Override   
    public void printAs(){
        Nodo<T> temp = this.head;

        if (temp==null){
            System.out.println("[empty]");
            return;
        }
        while (temp != null){

            System.out.print(temp.val);
            if (temp.next !=null) System.out.print("--");
            temp = temp.next;
        }
        System.out.println();
    }

    

}