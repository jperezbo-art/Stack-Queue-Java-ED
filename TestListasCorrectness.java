public class TestListasCorrectness{
        
    public static void testearCorrectnessListas(){
        testCorrectness(new Lista<Integer>(), "Lista Sencilla");
        testCorrectness(new ListaCola<Integer>(), "Lista con Cola");
        testCorrectness(new ListaDob<Integer>(), "Lista Doble");
        testCorrectness(new ListaDobCola<Integer>(), "Lista Doble con Cola");
    }

    public static <N> void testCorrectness(EstrucLista<Integer, N> lista, String nombre) {
    System.out.println("--- Probando: " + nombre + " ---");
        boolean exitoTotal = true;

        // Insertar elemento unico
        lista.pushFront(10);
        if (lista.getSize() != 1) {
            System.out.println("  ERROR: 1"); exitoTotal = false;
        }

        // Extraer elemento Unico
        int unico = lista.popBack();
        if (unico != 10 || !lista.isEmpty() || lista.getSize() != 0) {
            System.out.println("  ERROR: 2"); exitoTotal = false;
        }

        // Insercion
        lista.pushBack(20);
        lista.pushBack(30);
        lista.pushFront(10);
    
        // Find
        if (lista.find(20) == null) {
            System.out.println("  ERROR: 3"); exitoTotal = false;
        }
        if (lista.find(999) != null) {
            System.out.println("  ERROR: 4"); exitoTotal = false;
        }

        // addAfter y addBefore
        N nodoVeinte = lista.find(20);
        lista.addAfter(25, nodoVeinte);
        lista.addBefore(15, nodoVeinte);
        
        if (lista.getSize() != 5) {
             System.out.println("  ERROR: 5"); exitoTotal = false;
        }

        // Erase de extremos y no extremos
        N nodoDiez = lista.find(10); 
        lista.Erase(nodoDiez);
        lista.printAs();
        // Esperado: [15, 20, 25, 30]

        N nodoTreinta = lista.find(30); 
        lista.Erase(nodoTreinta);
        lista.printAs();
        // Esperado: [15, 20, 25]

        N nodoVeinteBorrar = lista.find(20); 
        lista.Erase(nodoVeinteBorrar);
        lista.printAs();
        // Esperado: [15, 25]

        // Verificacion final
        Integer v1 = lista.popFront();
        Integer v2 = lista.popFront();
        lista.printAs();
        
        if (v1 == null || v2 == null || v1 != 15 || v2 != 25) {
            System.out.println("  ERROR: 6"); 
            exitoTotal = false;
        }

        // Limpieza
        if (!lista.isEmpty()) {
            System.out.println("  ERROR: 7"); exitoTotal = false;
        }

        if (exitoTotal) {
            System.out.println("  EXITO");
        }
        System.out.println();
    }
}


