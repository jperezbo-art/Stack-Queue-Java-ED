//import java.util.*;

class ListaDob<T> implements EstrucLista<T, NodoDos<T>>{
    NodoDos<T> head; 
    int size; 

    ListaDob(){
        this.head = null;
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
        NodoDos<T> newNodo = new NodoDos<T>(dato);
        newNodo.next = this.head;

        if (this.head != null) this.head.prev = newNodo;
        this.head = newNodo;
           this.size++;
    }
    @Override
    public void pushBack(T dato){
        NodoDos<T> newNodo = new NodoDos<T>(dato);
        
        if (this.head == null) {
            this.head = newNodo;
            this.size++;
            return;
        }

        NodoDos<T> temp = this.head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = newNodo;
        newNodo.prev = temp;
        this.size++;
    }
    @Override
    public T popFront(){
        if (this.head == null) return null;

        NodoDos<T> temp = this.head;
        

        this.head = this.head.next;
        if (this.head != null) this.head.prev = null;
        temp.next = null;
        this.size--;

        return temp.val;
    }
    @Override
    public T popBack(){
        if (this.head == null) return null;
        if (this.head.next == null) return this.popFront();

        NodoDos<T> temp = this.head;
        
        while (temp.next != null){
            temp = temp.next;
        }

        temp.prev.next = null;
        temp.prev = null;
        this.size--;
        return temp.val;

    }
    @Override
    public NodoDos<T> find(T dato){
        if (this.head == null) return null;

        NodoDos<T> temp = this.head;
        while(temp != null && !temp.val.equals(dato)){
            temp = temp.next;
        }

        return temp; 
    }
    @Override
    public T Erase(NodoDos<T> datoObj){
        if (this.head == null) return null; //Vacia
        if (this.head == datoObj) return this.popFront(); //Uno
        if (datoObj.prev == null) return null; //No pertenece a la lista 
        
        datoObj.prev.next = datoObj.next;
        if (datoObj.next != null) datoObj.next.prev = datoObj.prev; //No Ultimo
        datoObj.next = null;
        datoObj.prev = null; 
        this.size--;

        return datoObj.val;
    }
    @Override
    public void addBefore(T dato, NodoDos<T> datoObj){
        if (this.head == null) return;
        if (this.head == datoObj) {
            this.pushFront(dato);
            return;
        }
        
        NodoDos<T> newNodo = new NodoDos<>(dato);
        newNodo.next = datoObj;
        newNodo.prev = datoObj.prev;
        datoObj.prev.next = newNodo;
        datoObj.prev = newNodo;
    
        this.size++;
    }
    @Override
    public void addAfter(T dato, NodoDos<T> datoObj){
        if (this.head == null) return; //Vacia
        
        NodoDos<T> newNodo = new NodoDos<>(dato);

        newNodo.next = datoObj.next;
        newNodo.prev = datoObj;

        
        if (datoObj.next != null) datoObj.next.prev = newNodo; //No Ultimo
        datoObj.next = newNodo;
        
        this.size++;
    }
    @Override
    public void printAs(){
        NodoDos<T> temp = this.head;

        if (temp==null){
            System.out.print("[empty]");
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