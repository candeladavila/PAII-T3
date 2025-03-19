object  clase1903 extends App {
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
/*
  import scala.util.Random

  def intercambio(lista: Array[Int]): Unit = {
    for (i <- lista.indices) {
      for (j <- i + 1 until lista.length) {
        if (lista(i) > lista(j)) {
          val aux = lista(i)
          lista(i) = lista(j)
          lista(j) = aux
        }
      }
    }
  }

  def mezclarIt(l1: Array[Int], l2: Array[Int], lista: Array[Int]) = {
    var i = 0
    var j = 0
    var k = 0
    while (i < l1.length && j < l2.length) {
      if (l1(i) <= l2(j)) {
        lista(k) = l1(i);
        i += 1
      } else {
        lista(k) = l2(j);
        j += 1
      }
      k += 1
    }
    while (i < l1.length) {
      lista(k) = l1(i);
      i += 1;
      k += 1
    }
    while (j < l2.length) {
      lista(k) = l2(j);
      j += 1;
      k += 1
    }
  }

  //OPCIÓN 1: caso secuencial
  val a = new Array[Int](20)
  for (i <- 0 until a.length) {
    a(i) = Random.nextInt(100)
  }
  println(s"Array inicial: ${a.mkString(" ")}")
  val (a1, a2) = a.splitAt(a.length / 2)
  intercambio(a1)
  intercambio(a2)
  mezclarIt(a1, a2, a)
  println(s"Array ordenado: ${a.mkString(" ")}")
  //fin opcion 1

  class Hebra(a3: Array[Int]) extends Thread {
    override def run = intercambio(a3)
  }

  val a3 = new Array[Int](20)
  for (i <- 0 until a3.length) {
    a3(i) = Random.nextInt(100)
  }
  println(s"Array inicial concurrente: ${a3.mkString(" ")}")
  val (a31, a32) = a3.splitAt(a3.length / 2)
  val h1 = new Hebra(a31)
  val h2 = new Hebra(a32)
  h1.start();
  h2.start() //empiezan a entrelazarse las hebras
  h1.join();
  h2.join() //empiezan a competir por el procesador -> función de sincronización
  mezclarIt(a31, a32, a3) //mezclamos los resultados
  println(s"Array ordenado concurrente: ${a3.mkString(" ")}")
*/
  /*
Los procesos de un programa concurrente habitualmente se comunican y sincronizan sus acciones
Se pueden comunicar a través de memoria compartida o paso de mensajes (memoria distribuida)

PROBLEMAS DE LA PROGRAMACIÓN CONCURRENTE

 */

  import scala.util.Random

  // Algoritmo de ordenación por intercambio (Bubble Sort)
  def intercambio(lista: Array[Int]): Unit = {
    val n = lista.length
    println(s"Iniciando ordenación con ${lista.mkString(" ")}") // Depuración

    for (i <- 0 until n - 1) {
      for (j <- 0 until n - 1 - i) {
        if (lista(j) > lista(j + 1)) {
          val aux = lista(j)
          lista(j) = lista(j + 1)
          lista(j + 1) = aux
          println(s"Iteración $i-$j: ${lista.mkString(" ")}") // Ver cambios
        }
      }
    }

    println(s"Ordenación finalizada: ${lista.mkString(" ")}")
  }

  // Algoritmo de mezcla ordenada
  def mezclarIt(l1: Array[Int], l2: Array[Int], lista: Array[Int]): Unit = {
    var i, j, k = 0
    while (i < l1.length && j < l2.length) {
      if (l1(i) <= l2(j)) {
        lista(k) = l1(i);
        i += 1
      } else {
        lista(k) = l2(j);
        j += 1
      }
      k += 1
    }
    while (i < l1.length) {
      lista(k) = l1(i);
      i += 1;
      k += 1
    }
    while (j < l2.length) {
      lista(k) = l2(j);
      j += 1;
      k += 1
    }
  }

  // Opción 1: Ordenación secuencial
  val a = Array.fill(20)(Random.nextInt(100))
  println(s"Array inicial: ${a.mkString(" ")}")

  val (a1, a2) = a.splitAt(a.length / 2)
  intercambio(a1)
  intercambio(a2)
  mezclarIt(a1, a2, a)

  println(s"Array ordenado secuencialmente: ${a.mkString(" ")}")

  // Opción 2: Ordenación concurrente con hilos
  // Clase para ejecutar la ordenación en un hilo
  class Hebra(private val lista: Array[Int]) extends Thread {
    override def run(): Unit = {
      println(s"🟢 Inicio de hebra para: ${lista.mkString(" ")}")
      intercambio(lista) // Ahora usa sortInPlace() para evitar bloqueos
      println(s"✅ Hebra finalizada para: ${lista.mkString(" ")}")
    }
  }

  // Creación del array y su división
  val a3 = Array.fill(20)(Random.nextInt(100))
  println(s"Array inicial concurrente: ${a3.mkString(" ")}")

  val (a31, a32) = a3.splitAt(a3.length / 2)

  // Crear y ejecutar hilos
  val h1 = new Hebra(a31)
  val h2 = new Hebra(a32)

  h1.start()
  h2.start()

  // Verificar que los hilos inician
  println("⏳ Esperando a que los hilos terminen...")

  h1.join() // Si no termina, el hilo se bloqueó
  println("✔ Hebra 1 completada")

  h2.join() // Si no termina, el hilo se bloqueó
  println("✔ Hebra 2 completada")

  // Mezclar resultados
  mezclarIt(a31, a32, a3)
  println(s"Array ordenado concurrentemente: ${a3.mkString(" ")}")
}