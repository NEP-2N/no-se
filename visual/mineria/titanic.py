# Instrucciones. YAYO
# A partir de la base de datos Titanic, se realizará una actividad en la cual:
#   1. Analizar si la muestra es suficiente o no para una predicción si una persona sobrevive o no.
#   2. Definir con claridad las métricas a usar.
#   3. Definir si se tiene que procesar toda la muestra o solo una parte de ella.
#     a. Técnica de muestreo a usar.
#     b. Técnica para tamaño de muestra.
#     c. Llegar a la dimensión requerida.
#     d. Mostrar JupiterNotebook/Pyton graficar.
#
# Entregables.
#   1. Subir archivo a plataforma.
#   2. Colocar un link al drive para acceso directo.



# Instrucciones. TEAMS
# A partir de la base de datos Titanic, se realizará una actividad en la cual:
#   1. Analizar si la muestra es suficiente o no para una predicción si una persona sobrevive o no.
#   2. ¿Se puede usar un número menor de instancias que contiene la muestra inicial sin perder representatividad?
#   3. ¿Qué tamaño de conjuntos train y test se debe de usar sin perder representatividad de cada partición? ¿Qué porcentaje de división se deberá emplear?
# Se deben de argumentar las respuestas
#
# Entregables.
#   1. Un archivo jupyter notebook dónde se desarrolle la actividad
#   2. Un link a su jupyter notebook en su cuenta de drive para su visualización rápida



import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import accuracy_score

from sklearn.model_selection import GridSearchCV

from os import system as sys
sys("cls")

# Cargar el archivo CSV
data = pd.read_csv("C:/Users/conda/Desktop/github/PC/visual/mineria/titanic_dataset.csv")

# Convertimos los valores de String a int para que el modelo se entrene correctamente
data['Sex'] = data['Sex'].astype('category').cat.codes # male = 1, female = 0
data['Embarked'] = data['Embarked'].astype('category').cat.codes # C = 0, Q = 1, S = 2

data = data.drop(columns=["PassengerId", "Name", "Ticket", "Cabin"])
# Cabin es util, pero tiene demasiados elementos vacios (529 incluso despues de quitar las demas columnas)
# Ademas, la mayoria de los elementos que SI tienen cabin, son de clase alta, pues los que no se registraron
# fueron en su inmensa mayoria, los de clase baja


data = data.dropna()

# Separar características (X) y etiquetas (y)
X = data.drop(columns=["Survived"])
y = data["Survived"]

# Pclass = clase economica  (1 alta, 2 media, 3 baja)
# SibSp = Hermanos / matrimonio a bordo
# Parch = Padres / hijos a bordo
# Ticket = numero de billete
# Fare = tarifa pagada por el billete
# Cabin = numero de camarote
# Embarked = puerto donde embarco el pasajero (c = cherburgo, q = queenstown, s = southampton

print(data)

print("-------------------------------------------------------------------------------------------------------------------------------------------------------")

# Dividir los datos en conjuntos de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.20, random_state=42)

# Normalizar los datos (opcional pero recomendable para RNA)
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

print("Datos preparados para ser usados en una RNA con scikit-learn.")
print("-------------------------------------------------------------------------------------------------------------------------------------------------------")

clf = MLPClassifier(hidden_layer_sizes=(32), max_iter=1000, alpha=0.0001, random_state=42)
clf.fit(X_train, y_train)

# Evaluar el modelo
y_pred = clf.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)

print("-------------------------------------------------------------------------------------------------------------------------------------------------------")
print(f"Precisión del modelo: {accuracy:.2f}")
