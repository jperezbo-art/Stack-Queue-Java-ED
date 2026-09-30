//import java.util.*;

class ListaDobCola<T> implements EstrucLista<T, NodoDos<T>>{
    NodoDos<T> head; 
    NodoDos<T> tail; 
    int size; 

    ListaDobCola(){
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

        if (this.head == null) this.tail = newNodo;  //Vacia
        else this.head.prev = newNodo;   //No Vacia
    
        this.head = newNodo;
        this.size++;
    }

    @Override
    public void pushBack(T dato){
        NodoDos<T> newNodo = new NodoDos<T>(dato);
        
        if (this.head == null) { //Vacia
            this.head = newNodo;
            this.tail = newNodo;
        }
        else { //No Vacia
            this.tail.next = newNodo;
            newNodo.prev = this.tail;
            this.tail = newNodo;
        }
        this.size++;
    }

    @Override
    public T popFront(){
        if (this.head == null) return null; // Vacia

        NodoDos<T> temp = this.head;
        
        this.head = this.head.next;
        if (this.head == null) this.tail = null; // Uno
        else this.head.prev = null; //Mas de Uno
        temp.next = null;
        this.size--;

        return temp.val;
    }

    @Override
    public T popBack(){
        if (this.head == null) return null; //Vacio
        if (this.head.next == null) return this.popFront();//Uno

        NodoDos<T> temp = this.tail;

        this.tail = this.tail.prev; 
        this.tail.next = null;
        temp.prev = null;

        this.size--;
        return temp.val;
    }

    @Override
    public NodoDos<T> find(T dato){
        if (this.head == null) return null; //Vacia

        NodoDos<T> temp = this.head;
        while(temp != null && !temp.val.equals(dato)){
            temp = temp.next;
        }

        return temp; 
    }

    @Override
    public T Erase(NodoDos<T> datoObj){
        if (this.head == null) return null; //Vacia
        if (this.head == datoObj) return this.popFront(); //Head
        if (this.tail == datoObj) return this.popBack(); //Tail
        if (datoObj.prev == null) return null; //No pertenece a la lista 
        
        datoObj.prev.next = datoObj.next;
        datoObj.next.prev = datoObj.prev;
        datoObj.next = null;
        datoObj.prev = null; 
        this.size--;

        return datoObj.val;
    }

    @Override
    public void addBefore(T dato, NodoDos<T> datoObj){
        if (this.head == null) return; //Vacia
        if (this.head == datoObj) {   //Head
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
        if (this.tail == datoObj) {   //Tail
            this.pushBack(dato); 
            return;
        }

        NodoDos<T> newNodo = new NodoDos<>(dato);

        newNodo.next = datoObj.next;
        newNodo.prev = datoObj;

        datoObj.next.prev = newNodo;
        datoObj.next = newNodo;
        
        this.size++;
    }
    @Override
    public void printAs(){
        NodoDos<T> temp = this.head;

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

    public void printDes(){
        NodoDos<T> temp = this.tail;

        if (temp==null){
            System.out.print("[empty]");
            return;
        }
        while (temp != null){

            System.out.print(temp.val);
            if (temp.prev !=null) System.out.print("--");
            temp = temp.prev;
        }
        System.out.println();
    }
}