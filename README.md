# ⚔️ Simulador de Batallas Pokémon - Miniproyecto 4 (MVC + Estructuras + Persistencia + Excepciones + Barras de vida)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

> **Universidad del Valle – Colombia 🇨🇴**  
> **Programación Orientada a Objetos | Estructuras Avanzadas | MVC | Excepciones | Persistencia**

---

## 📚 Tabla de Contenidos

- [📖 Descripción](#📖-descripción)
- [🧱 Arquitectura MVC](#🧱-arquitectura-mvc)
- [🔁 Cambio Clave en el Diseño: BattleManager](#🔁-cambio-clave-en-el-diseño-battlemanager)
- [🧠 Estructuras de Datos Avanzadas](#🧠-estructuras-de-datos-avanzadas)
- [💾 Persistencia](#💾-persistencia)
- [🚨 Manejo de Excepciones](#🚨-manejo-de-excepciones)
- [🖼️ Interfaz Gráfica](#🖼️-interfaz-gráfica)
- [🎮 Controlador](#🎮-controlador)
- [👥 Integrantes](#👥-integrantes)
- [🛠️ Instalación y Uso](#🛠️-instalación-y-uso)

---

## 📖 Descripción

Este proyecto representa la cuarta fase del desarrollo de un **sistema de batallas Pokémon**, implementado bajo el patrón **Modelo-Vista-Controlador (MVC)**. A diferencia de versiones anteriores, esta incluye:

- Manejo de excepciones personalizadas.
- Uso de **estructuras de datos avanzadas**.
- Guardado y carga de partidas mediante archivos de texto.
- Refactorización de la lógica del combate para mejorar la mantenibilidad.

---

## 🧱 Arquitectura MVC

- **Modelo**: Lógica del juego (Pokémon, ataques, entrenadores, combate).
- **Vista**: GUI con **Swing**, permite la interacción visual.
- **Controlador**: Ahora más limpio, solo conecta la vista con la lógica interna (BattleManager).

---

## 🔁 Cambio Clave en el Diseño: `BattleManager`

Antes, la lógica del combate estaba incrustada en el `Controller`, lo que violaba el principio de separación de responsabilidades.

Ahora, todo el manejo de la batalla está encapsulado en la clase `BattleManager`:

- Gestiona entrenadores, Pokémon y ataques.
- Determina el orden de turnos (`LinkedList`).
- Lleva el historial de ataques (`Stack<String>`).
- Evalúa condiciones de victoria.
- Se encarga de **guardar y cargar** partidas (`Serializable`).
- Administra los nombres y estados de vida de Pokémon con colas (`Queue`).

El `Controller` solo se encarga de responder a eventos de la interfaz gráfica y delegar tareas a `BattleManager`.

✅ Esto mejora la escalabilidad, el orden del código y su reutilización en otras vistas (por ejemplo, consola).

---

## 🧠 Estructuras de Datos Avanzadas

Este proyecto hace uso de las siguientes estructuras:

- 🔁 **Pila (`Stack`)**  
  Almacena el historial de movimientos durante la batalla.  
  Permite mostrar los últimos ataques realizados.

- 🧬 **Lista Enlazada (`LinkedList`)**  
  Controla el **orden de turnos** de los Pokémon en combate, priorizando por velocidad.

- ⏳ **Cola (`Queue`)**  
  Administra el nombre y estado (vida) de los Pokémon disponibles para cada entrenador.

- 🧠 **Tabla Hash (`HashMap`)**  
  Usada en la clase `Trainer` para buscar Pokémon por nombre eficientemente.

---

## 💾 Persistencia

Gracias a la implementación de `Serializable`, el juego permite:

- **Guardar partida** en archivos `.dat`, con información completa del estado actual.
- **Cargar partida** desde archivos guardados, restaurando entrenadores, Pokémon, historial y turnos.

Esto permite continuar una batalla justo donde se dejó.

---

## 🚨 Manejo de Excepciones

Se implementó una excepción personalizada para mejorar la robustez del sistema frente a errores provocados por el usuario:

- ⚠️ `SeleccionInvalidaException`:  
  Se lanza cuando el usuario no ingresa los nombres de los entrenadores o no selecciona un Pokémon al avanzar entre paneles.
  Esta excepción evita que el programa continúe con datos incompletos y garantiza una interacción correcta con la interfaz gráfica.

---

## 🖼️ Interfaz Gráfica

La GUI fue desarrollada con **Swing** y permite:

- Ingreso de nombres de entrenadores.
- Selección visual de Pokémon.
- Botones para ejecutar ataques.
- Visualización del historial de combate.
- Botones para guardar/cargar partida.

---

## 🎮 Controlador

El `Controller` ahora tiene funciones mucho más específicas:

- Recibe eventos desde la interfaz.
- Llama a métodos de `BattleManager`.
- Controla el cambio entre paneles (`Panel1`, `Panel2`, `Panel3`).
- Actualiza la GUI según el estado de la batalla.

---

## 👥 Integrantes

- 👤 Paula Jimena Bohórquez Bermúdez - 2459409
- 👤 Manuela Delgado Aguirre - 2459640
- 👤 Juan Camilo Gil Agudelo - 2459531
- 👤 Gabriel Esteban Burbano Mora - 2459385

