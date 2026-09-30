import java.util.Random;

public class TestArrDinamicoTime {
    
    public static void exeBenchmarck(){
        calentar();
        int[] tam = {1000,10000,100000,1000000,10000000};
        String[] names = {"Pila Dinamica", "Cola Dinamica"};
        String[] metodos = {"Insertar", "Extraer", "Tope", "isEmpty", "Size", "Delete"};

        long[][][] matrizTimes = new long[names.length][metodos.length][tam.length];
        
        for (int s = 0; s <tam.length; s++) {
            int size = tam[s];
            System.out.println("\n========================================");
            System.out.println(" SIZE: " + size);
            System.out.println("========================================");

            for (int tipo = 0; tipo < 2; tipo++) {
                System.out.println("\n--- " + names[tipo] + " ---");
                 try {
                    long[] times;
                    if (tipo == 0) {
                        MyStack<Integer> pila = new Pila<>(tam[s]+200);
                        times = takeTimePila(pila, size);
                    } else {
                        MyQueue<Integer> cola = new Cola<>(tam[s]+200);
                        times = takeTimeCola(cola, size);
                    }

                    for (int m = 0; m < metodos.length;m++){
                        matrizTimes[tipo][m][s] = times[m];
                    }
                } catch (OutOfMemoryError e) {
                    System.out.println("OUT OF MEMORY, SIZE: " + size );
                    for (int m = 0; m < metodos.length; m++) matrizTimes[tipo][m][s] = -1;
                }
            }
            System.gc(); 
        }
        JsonWriter.expJson(matrizTimes, tam, names, metodos,"resultados_Arr.json");
    }

    private static <N> long[] takeTimePila(MyStack<Integer> pila, int size) {
        Random random = new Random();
        long[] resultados = new long[6];
        int K = 100;
        //Llenar 
        for (int j = 0; j < size; j++) {
            if (j<K) pila.push(-1);
            else pila.push(random.nextInt(Integer.MAX_VALUE - 1) + 1); 
        }
        System.gc();
        long inicio, fin;
        
        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) pila.push(999);
        fin = System.nanoTime();
        resultados[0] = (fin-inicio)/K;
        System.out.println("push: \t\t" + resultados[0] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) pila.pop();
        fin = System.nanoTime();
        resultados[1] = (fin-inicio)/K;
        System.out.println("pop: \t\t" + resultados[1] + " ns");
    
        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) pila.peek();
        fin = System.nanoTime();
        resultados[2] = (fin-inicio)/K;
        System.out.println("peek (peor caso):\t" + resultados[2] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) pila.isEmpty();
        fin = System.nanoTime();
        resultados[3] = (fin-inicio)/K;
        System.out.println("isEmpty :\t" + resultados[3] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) pila.size();
        fin = System.nanoTime();
        resultados[4] = (fin-inicio)/K;
        System.out.println("size:\t" + resultados[4] + " ns");

        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) pila.delete(-1);
        fin = System.nanoTime();
        resultados[5] = (fin-inicio)/K;
        System.out.println("delete:\t" + resultados[5] + " ns");
        
        return resultados;
    }


    private static <N> long[] takeTimeCola(MyQueue<Integer> cola, int size) {
        Random random = new Random();
        long[] resultados = new long[6];
        int K = 100;
        //Llenar 
        for (int j = 0; j < size; j++) {
            if (j >= size - K) cola.enqueue(-1);
            else cola.enqueue(random.nextInt(Integer.MAX_VALUE - 1) + 1); 
        }
        System.gc();
        long inicio, fin;
        
        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) cola.enqueue(999);
        fin = System.nanoTime();
        resultados[0] = (fin-inicio)/K;
        System.out.println("enqueue: \t\t" + resultados[0] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) cola.dequeue();
        fin = System.nanoTime();
        resultados[1] = (fin-inicio)/K;
        System.out.println("dequeue: \t\t" + resultados[1] + " ns");
    
        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) cola.front();
        fin = System.nanoTime();
        resultados[2] = (fin-inicio)/K;
        System.out.println("front (peor caso):\t" + resultados[2] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) cola.isEmpty();
        fin = System.nanoTime();
        resultados[3] = (fin-inicio)/K;
        System.out.println("isEmpty :\t" + resultados[3] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) cola.size();
        fin = System.nanoTime();
        resultados[4] = (fin-inicio)/K;
        System.out.println("size:\t" + resultados[4] + " ns");

        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) cola.delete(-1);
        fin = System.nanoTime();
        resultados[5] = (fin-inicio)/K;
        System.out.println("delete:\t" + resultados[5] + " ns");
        
        return resultados;
    }

    public static void calentar() {
        
        MyStack<Integer> dummy = new Pila<>(100000);
        for (int i = 0; i < 100000; i++) dummy.push(i);
        for (int i = 0; i < 50000; i++) dummy.pop();  
        System.gc(); 
    }

}
