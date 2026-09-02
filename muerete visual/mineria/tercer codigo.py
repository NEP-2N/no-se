import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
#%matplotlib inline
plt.style.use('fivethirtyeight')

from sklearn.datasets import make_blobs

import matplotlib.pyplot as plt

from os import system as sys
sys('cls')
# ==================================================================================================

fig, ax = plt.subplots(1, 2, figsize=(15, 5))
ax = ax.flatten()
for i in range(0,3):
    ax[0].scatter(
        x = [t[3] for t in statistics_global if t[2] == i],
        y = [t[0] for t in statistics_global if t[2] == i],
        c = plt.rcParams['axes.prop_cycle'].by_key()['color'][i],
        marker = 'o',
        edgecolor = 'black',
        label = f"Clase {i}"
    )

ax[0].set_title('Media del atributo 0')
ax[0].legend()

for i in range(0,3):
    ax[i].scatter(
        x = [t[3] for t in statistics_global if t[2] == i],
        y = [t[1] for t in statistics_global if t[2] == i],
        
    )

# ==================================================================================================

statistics_global = []
delta = 100
df_sample_n = pd.DataFrame()
df_pob = df_population.copy()
max = len(df_pob)

for i in range(delta, max, delta):
    df_top = df_pob.sample(n = delta)
    df_sample_n = pd.concat([df_sample_n, df_tmp])
    
    df_pob = df_pob.drop(df_tmp.index)
    df_sample_classes = []
    
    for j in np.unique(df_sample_n('class')):
        df_sample_classes.append(df_sample_n[df_sample_n['class'] == j])
        
    for k in range(len(df_sample_classes)):
        statistics_global.append([df_sample_classes[k][0].mean(), df_sample_classes[k][i].mean(), k, i])



for i in range(len(statistics_global)):
    print(statistics_global[i])