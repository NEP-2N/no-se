import numpy as np
import matplotlib.pyplot as plt
from os import system as sys
sys("cls")


amplitud = [0.5, 0.75, 1.2]
frecuencia = [1, 1000000, 3300]
colors = ["red", "green", "blue"]

desviaciones = [0, 0.5, 1]

longitudes_de_onda = [
    r"$\lambda_1(t)=\frac{3*10^8\frac{m}{s}}{1hz}=300,000,000m$",
    r"$\lambda_2(t)=\frac{3*10^8\frac{m}{s}}{1,000,000hz}=300m$",
    r"$\lambda_3(t)=\frac{3*10^8\frac{m}{s}}{3,300hz}=90,909.0909m$"
]

filas = len(amplitud)
columnas = len(desviaciones)+1

plt.figure(figsize=(12, 5))

for i in range (filas):
    rango_x_1 = 0
    rango_x_2 = (4*np.pi)/frecuencia[i]
    
    for j in range(columnas-1):
            
        x = np.linspace(rango_x_1, rango_x_2, 90)
        y1 = amplitud[i]*np.sin((frecuencia[i]*x) + (desviaciones[j]*np.pi))
        
        plt.subplot(filas, columnas, (columnas*i)+j+1)
        plt.plot(x, y1, color=colors[i], linewidth=2)
        plt.title(f'{amplitud[i]}sin({frecuencia[i]}x + {desviaciones[j]}π)')
        plt.xlim(rango_x_1, rango_x_2)
        plt.ylim(-1.5,1.5)
        plt.axhline(0, color='black', linestyle=':')
        plt.axvline(0, color='black', linestyle=':')
        plt.grid(True, alpha=0.5)
        
    plt.subplot(filas, columnas, (columnas*i)+1+filas)
    plt.text(0.3, 0.5, longitudes_de_onda[i], fontsize=12, ha='center', va='center')
    plt.axis('off')
    

plt.subplots_adjust(hspace=0.7, wspace=0.8)
plt.show()