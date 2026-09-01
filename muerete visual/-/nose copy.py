import matplotlib.pyplot as plt

# 1. Definir la fórmula en LaTeX
formula = r'$f(x) = \frac{-b \pm \sqrt{b^2 - 4ac}}{2a}$'

# 2. Colocar el texto en coordenadas (X, Y) dentro de la gráfica
plt.text(0.5, 0.5, formula, fontsize=24, ha='center', va='center')

# 3. Ocultar los ejes de la gráfica actual
plt.axis('off')

# 4. Mostrar o guardar el resultado
plt.show()
