# Tratamiento de datos
# =============================================================================
import numpy as np
import pandas as pd

# Graficos
# =============================================================================
import matplotlib.pyplot as plt
#%matplotlib inline
plt.style.use('fivethirtyeight')

from sklearn.datasets import make_blobs

# Se genera una poblacion con tres clases desbalanceadas y una distribucion
# no normal en los datos
import matplotlib.pyplot as plt
# generacion de la clase 0
samples = [80,100,50,70,40,40,50,90]
centroids = [(-3.5,5),(-1.5,3.5),(0,2.5),(1.2,3),(2.5,3.5),(3.5,3.5),(4,2.8),(5,1)]
std = [0.6,0.7,0.5,0.4,0.2,0.2,0.4,0.6]
population_c1, population_c1_labels = make_blobs(n_samples=samples, cluster_std=std,centers=centroids,random_state=42)
population_c1_labels = np.full_like(population_c1_labels,0)

# generacion de la clase 1
samples = [30,25,35,25,30,40,15,10]
centroids = [(-5.0,-4.7),(-3.0,-2.5),(-1.5,-2.5),(0.7,-3.0),(2.8,-2.5),(4.6,-2.2),(6.2,-1.7),(7.5,-1.0)]
std = [1.3,1.4,1.5,0.9,0.8,0.45,0.35,0.3]
population_c2, population_c2_labels = make_blobs(n_samples=samples, cluster_std=std,centers=centroids,random_state=42)
population_c2_labels = np.full_like(population_c2_labels,1)

# generacion de la clase 2
samples = [1000,1400,1300,1000,1200,1400,1300,1600]
centroids = [(4.0,10.0),(7.5,7),(11,5),(12.0,1.0),(13,-2),(11.5,-6.2),(8,-7.5),(4.5,-8.5)]
std = [1.2,1.1,1.3,1.2,1.5,1.2,1.6,1.3]
population_c3, population_c3_labels = make_blobs(n_samples=samples, cluster_std=std,centers=centroids,random_state=42)
population_c3_labels = np.full_like(population_c3_labels,2)

population = population_c1
population_labels = population_c1_labels
population = np.append(population,population_c2,axis=0)
population_labels = np.append(population_labels,population_c2_labels,axis=0)
population = np.append(population,population_c3,axis=0)
population_labels = np.append(population_labels,population_c3_labels,axis=0)


fig,ax = plt.subplots(1,1,figsize=(8,5))
for i in np.unique(population_labels):
    ax.scatter(
        x = population[population_labels == i,0],
        y = population[population_labels == i,1],
        c = plt.rcParams['axes.prop_cycle'].by_key()['color'][i],
        marker = 'o',
        edgecolor = 'black',
        label =f"Clase {i}"
    )

    title = 'Poblacion (#instancias): ' + str(len(population))
    ax.set_title(title)
    ax.legend()
    
plt.show()





