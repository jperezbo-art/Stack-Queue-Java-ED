import json
import os
import matplotlib.pyplot as plt


archivos = ["resultados_List.json", "resultados_Arr.json"]

for archivo in archivos:
    
    if not os.path.exists(archivo):
        print(f"Saltando {archivo}: El archivo no existe en esta carpeta.")
        continue

    with open(archivo, 'r') as f:
        datos = json.load(f)

    marcadores = ['o', 's', '^', 'D', 'v', 'p', '*', 'X']


    for nombre_lista, metodos in datos.items():
        plt.figure(figsize=(10, 6))
        
        
        for i, (nombre_metodo, coordenadas) in enumerate(metodos.items()): 
            
            eje_x = [punto[0] for punto in coordenadas]
            eje_y = [punto[1] for punto in coordenadas]
            
            plt.plot(eje_x, eje_y, marker=marcadores[i], label=nombre_metodo, linewidth=2, markersize=6)

    
        plt.title(f"Rendimiento - {nombre_lista}", fontsize=14, fontweight='bold')
        plt.xlabel("Tamaño de la lista (N)", fontsize=12)
        plt.ylabel("Tiempo Promedio (ns)", fontsize=12)
        
        # Ajuste Logaritmico
        plt.yscale('log')
        plt.xscale('log') 
        
        plt.grid(True, which="both", ls="--", alpha=0.5)
        plt.legend(bbox_to_anchor=(1.02, 1), loc='upper left', borderaxespad=0.)
        plt.tight_layout()

        
        nombre_archivo = f"grafica_{nombre_lista.replace(' ', '_')}.png"
        plt.savefig(nombre_archivo, dpi=300)
        plt.close()
        
        print(f"Gráfica generada: {nombre_archivo}")
        