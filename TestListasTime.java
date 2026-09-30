import java.util.Random;

public class TestListasTime {

    
    public static void exeBenchmarck(){
        calentar();
        int[] tam = {1000,10000,100000,1000000,10000000};
        String[] names = {"Sencilla sin Cola", "Sencilla con Cola", "Doble sin Cola", "Doble con Cola"};
        String[] metodos = {"PushFront", "PushBack", "PopFront", "PopBack", "Find", "AddBefore", "AddAfter", "Erase"};

        long[][][] matrizTimes = new long[4][8][tam.length];
        
        for (int s = 0; s <tam.length; s++) {
            int size = tam[s];
            System.out.println("\n========================================");
            System.out.println(" SIZE: " + size);
            System.out.println("========================================");

            for (int tipo = 0; tipo < 4; tipo++) {
                System.out.println("\n--- " + names[tipo] + " ---");
                 try {
                    EstrucLista<Integer, ?> lista = instLista(tipo);
                    long[] times = takeTime(lista, size);
                    for (int m = 0; m<8;m++){
                        matrizTimes[tipo][m][s] = times[m];
                    }
                } catch (OutOfMemoryError e) {
                    System.out.println("OUT OF MEMORY, SIZE: " + size );
                    for (int m = 0; m < 8; m++) matrizTimes[tipo][m][s] = -1;
                }
            }
            System.gc(); 
        }
        JsonWriter.expJson(matrizTimes, tam, names, metodos, "resultados_List.json");
    }

    private static <N> long[] takeTime(EstrucLista<Integer, N> lista, int size) {
        Random random = new Random();
        long[] resultados = new long[8];

        //Llenar 
        for (int j = 0; j < size; j++) {
            if (j == 5) lista.pushFront(-1);
            else lista.pushFront(random.nextInt(Integer.MAX_VALUE - 1) + 1); //Fijar Objetivo peor caso
        }
        System.gc();
        long inicio, fin;
        int K = 100;

        // Metodos T simples
        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) lista.pushFront(999);
        fin = System.nanoTime();
        resultados[0] = (fin-inicio)/K;
        System.out.println("pushFront: \t\t" + resultados[0] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) lista.popFront();
        fin = System.nanoTime();
        resultados[2] = (fin-inicio)/K;
        System.out.println("popFront: \t\t" + resultados[2] + " ns");
        
        //No se limpiar por hipotesis de O(1)

        System.gc();
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) lista.pushBack(999);
        fin = System.nanoTime();
        resultados[1] = (fin-inicio)/K;
        System.out.println("pushBack (peor caso):\t" + resultados[1] + " ns");

        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) lista.popBack();
        fin = System.nanoTime();
        resultados[3] = (fin-inicio)/K;
        System.out.println("popBack (peor caso):\t" + resultados[3] + " ns");

        // Metodos N de nodo
        N nodoObjetivo = lista.find(-1);
        
        inicio = System.nanoTime();
        for (int i = 0; i<K; i++) lista.find(-1);
        fin = System.nanoTime();
        resultados[4] = (fin-inicio)/K;
        System.out.println("find (peor caso): \t" + resultados[4] + " ns");

        if (nodoObjetivo != null) {
            System.gc();
            inicio = System.nanoTime();
            for (int i = 0; i<K; i++) lista.addBefore(888, nodoObjetivo);
            fin = System.nanoTime();
            resultados[5] = (fin-inicio)/K;
            System.out.println("addBefore: \t\t" + resultados[5] + " ns");

            for (int i = 0; i<K; i++) lista.popFront(); //limpiar lista

            System.gc();
            inicio = System.nanoTime();
            for (int i = 0; i<K; i++) lista.addAfter(777, nodoObjetivo);
            fin = System.nanoTime();
            resultados[6] = (fin-inicio)/K;
            System.out.println("addAfter: \t\t" + resultados[6] + " ns");

            
            //No es necesario limpiar la lista 
            long TotalErase = 0;
            
            for (int i = 0; i < K; i++) {
                
                
                N nodotemp = null;
                if (nodoObjetivo instanceof Nodo) { //evitar error temp.next
                    nodotemp = (N) ((Nodo<?>) nodoObjetivo).next;
                    
                } else if (nodoObjetivo instanceof NodoDos) {
                    nodotemp = (N) ((NodoDos<?>) nodoObjetivo).next;
                
                }

                long t1 = System.nanoTime();
                lista.Erase(nodotemp);
                long t2 = System.nanoTime();
                
                TotalErase += (t2 - t1);
                
            }
            resultados[7] = TotalErase/K;
            System.out.println("Erase: \t\t\t" + resultados[7] + " ns");
        } else {
            System.out.println("ERROR: Nodo no encontrado");
        }
        return resultados;
    }


    private static EstrucLista<Integer, ?> instLista(int tipo) {
        switch (tipo) {
            case 0: return new Lista<>();
            case 1: return new ListaCola<>();
            case 2: return new ListaDob<>();
            case 3: return new ListaDobCola<>();
            default: return null;
        }
    }

    public static void calentar() {
        
        EstrucLista<Integer, ?> dummy = instLista(0);
        
        for (int i = 0; i < 100000; i++) {
            dummy.pushFront(i);
            dummy.pushBack(i);
        }
        for (int i = 0; i < 50000; i++) {
            dummy.popFront();
            dummy.popBack();
        }
        System.gc(); 
    }
}