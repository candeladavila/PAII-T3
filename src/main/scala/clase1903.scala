class clase1903
/*
Programación concurrente:
El uso del operador secuencial ";" índica un orden arbitrario
Cualquier permutación de las instrucciones da lugar a un programa correcto
En un programa secuencial el programador tiene que decidir el orden en el que se ejecutan las instrucciones

Lo que nos gustaría es tener en el lenguaje de programación un operador en el que fuera capaz de expresar que da igual el
orden de las operciones -> OPERADOR PARALELO

Operador paralelo: || (no es este símbolo pero es la misma idea)
Cuando compongo codigo con este operador da igual el orden en el que se ejecute el código
No es determinista, hay distintas ejecuciones secuenciales
Ejemplo: x = 0 || y = 0 || z = 0 = { x = 0 -> y = 0 -> z = 0, x = 0 -> z = 0 -> y = 0, ...}
El indeterminismo da errores para la deputación del programa (NUNCA) solo puedo detectar que hay un error,
si no lo detecta no puedo saber si está bien porque no puedo hacer una búsqueda exahaustiva

PROGRAMACIÓN CONCURRENTE
Imaginamos que tenemos tres procesos (en paralelo) si mi máquina tuviera 3 procesadores entonces podríamos asignar cada
código en un procesador -> sería más rápido

Se pueden solapar en el tiempo -> existen ejecuciones válidas de un código en el que dos códigos están simultáneamente ejecutándose

¿Cómo razonamos el paralelo si solo tengo un procesador?
Si tenemos que ejecutar dos códigos P,Q gruesos (involucran computación) como razono en como es la ejecución de P paralelo con Q si solo tengo un procesador
La idea es la semántica del interleaving -> la ejecución paralela de los códigos se ejecuta entrelazando los procesos de los códigos

Ejemplo:
P = p1 -> p2 -> ... -> pn
Q = q1 -> q2 -> ... -> pm
Mentalmente nos imaginamos que cada uno tiene un procesador -> tiene un contador por donde va cada proceso ejecutándose
Se inicializa con las primeras instrucciones de cada proceso
Una vez que tenemos eso, la máquina escoge de forma indeterminista cuál de los dos procesos ejecutar (se genera un árbol de los distintos caminos posibles -> nodos hoja = ejecuciones válidas)
Dependiendo de lo que se haya ejecutado movemos el contador del proceso que se haya hecho

NOTA: No suele ocurrir que un procesador tenga un solo proceso (normalmente son más grandes y en la práctica cada procesador tiene mucha capacidad)
NOTA: Cada camino del árbol se conoce como trama de ejecución
      Número de tramas de ejecución de cada uno de los programas crece de forma más que exponencial = (m+n)!/(m!*n!)

Al principio no sabes cuál de las ejecuciones del árbol son correctas -> cuando lleguemos a la hoja el resultado debería ser correcto para todas
Si hay un error en alguna no tenemos por qué saber donde está -> para saberlo ejecutamos varias veces

La ejecución secuencial es una de las posibles ejecuciones concurrentes

ORDEN PARCIAL
El orden del entrelazado no es arbitraria -> el orden de las instrucciones de cada uno de los procesos se mantiene
Componemos procesos secuenciales -> las instrucciones de P y Q se ejecutan en el orden en el que lo indiquemos, lo que no sabemos es el orden del entrelazado de instrucciones

Entrelazado arbitrario -> no podemos:
-hacer suposiciones sobre la velocidad relativa entre procesadores lógicos
-hacer suposiciones sobre la velocidad real de ejecución

 */