import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import accuracy_score

try:
    from IPython.display import display
except ImportError:
    def display(obj):
        print(obj)

from sklearn.model_selection import GridSearchCV

import os
os.system("cls")

# Cargar el archivo CSV
data = pd.read_csv("C:/Users/conda/Escritorio/github/latitude/visual de latitude/mineria/preprocesamiento_200,000.csv")
data = data.dropna()

data = data.rename(columns = {"ingresos_mensuales": "salario"})

display(data)


columnas = ["region", "canal_preferido", "estado_civil", "tipo_membresia", "segmento_cliente"]
for elemento in columnas:    
    col_cat = data[elemento].astype('category')
    mapeo = dict(zip(col_cat.cat.categories, range(len(col_cat.cat.categories))))
    print(f"{elemento}: {mapeo}")
    data[elemento] = data[elemento].astype('category').cat.codes




# Separar características (X) y etiquetas (y)
X = data.drop(columns=["target"])
y = data["target"]

# Dividir los datos en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalizar los datos (opcional pero recomendable para RNA)
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

print("Datos preparados para ser usados en una RNA con scikit-learn.")
print("-------------------------------------------------------------------------------------------------------------------------------------------------------")



# Crear y entrenar un modelo de red neuronal artificial
# Ejemplo 1: se define una arquitectura y un número máximo de epcos
clf = MLPClassifier(hidden_layer_sizes=(4, 4), max_iter=500, random_state=42)
clf.fit(X_train, y_train)

# Evaluar el modelo
y_pred = clf.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)

print("-------------------------------------------------------------------------------------------------------------------------------------------------------")
print(f"Precisión del modelo: {accuracy:.2f}")