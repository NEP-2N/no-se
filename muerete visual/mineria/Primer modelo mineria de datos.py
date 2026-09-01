import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import accuracy_score

from sklearn.model_selection import GridSearchCV

import os
os.system("cls")

# Cargar el archivo CSV
data = pd.read_csv("c:/Users/conda/Escritorio/muerete visual/mineria/iris_v1.csv")

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

param_grid = {
    "hidden_layer_sizes": [(10,), (20,), (10,10), (20,20)],
    "activation": ["relu", "tanh"],
    "solver": ["adam", "sgd"],
    "alpha": [0.0001, 0.001, 0.01],
    "max_iter": [500, 1000]
}


# Instanciar el modelo base
rna = MLPClassifier(random_state=42)

# Aplicar GridSearchCV para encontrar la mejor combinacion de hiperparametros
grid_search = GridSearchCV(rna, param_grid, cv=5, scoring="accuracy", n_jobs=1)
grid_search.fit(X_train, y_train)

# Mejor configuracion encontrada
print(f"Mejores hiperparametros encontrados: {grid_search.best_params_}")
print(f"Precision en datos de prueba: {grid_search.best_estimator_.score(X_test, y_test)}")

# Mejores hiperparametros encontrados: {'activation': 'relu', 'alpha': 0.0001, 'hidden_layer_sizes': (20,), 'max_iter': 1000, 'solver': 'adam'}
# Precision en datos de prueba: 1.0



# Crear y entrenar un modelo de red neuronal artificial
# Ejemplo 1: se define una arquitectura y un número máximo de epcos
clf = MLPClassifier(hidden_layer_sizes=(4, 4), max_iter=500, random_state=42)
clf.fit(X_train, y_train)

# Evaluar el modelo
y_pred = clf.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)

print("-------------------------------------------------------------------------------------------------------------------------------------------------------")
print(f"Precisión del modelo: {accuracy:.2f}")