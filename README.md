# 🧠 Simulador de Batallas Pokémon - Miniproyecto 3 (MVC)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

> **Universidad del Valle - Colombia 🇨🇴**  
> **Programación Orientada a Objetos | GUI Aplicada | Patrón MVC**

---

## 📚 Tabla de Contenidos

- [Descripción](#descripción)
- [Arquitectura MVC](#arquitectura-mvc)
- [Componentes del Modelo](#componentes-del-modelo)
- [Interfaz Gráfica (Vista)](#interfaz-gráfica-vista)
- [Controlador](#controlador)
- [Integrantes](#integrantes)
- [Instalación y Uso](#instalación-y-uso)


---

## 📖 Descripción

Este proyecto es una extensión del **Miniproyecto 2**, ahora adaptado al patrón de diseño **Modelo-Vista-Controlador (MVC)**.  
El objetivo es mejorar la organización del código separando claramente la **lógica del juego**, la **interfaz gráfica del usuario (GUI)** y el **controlador** que gestiona la interacción entre ambos.

---

## 🧱 Arquitectura MVC

- **Modelo**: Contiene toda la lógica del juego (Pokémon, ataques, combate).
- **Vista**: Interfaz visual construida con **Swing**, incluye pantallas de selección, combate y resultados.
- **Controlador**: Gestiona la comunicación entre la vista y el modelo, según la interacción del usuario.

---

## 🔧 Componentes del Modelo

- 🐲 **Pokémon**: Atributos como nombre, tipo, HP, ataque, defensa, ataque especial, defensa especial, velocidad, y una lista de hasta 4 ataques.
- ⚔️ **Ataque**: Definido por nombre, tipo (físico/especial, aunque el tipo no se usará) y potencia.
- 🧑‍🎓 **Entrenador**: Nombre y equipo de hasta 3 Pokémon. Capacidad de elegir cuál entra al combate.
- 🥊 **Batalla**: Gestiona el sistema de turnos, aplica daño y determina al ganador.

---

## 🎨 Interfaz Gráfica (Vista)

- Pantalla de ingreso de nombres de entrenadores.
- Visualización de los equipos Pokémon asignados aleatoriamente.
- Interfaz para seleccionar ataques y mostrar el estado del combate.
- *(Bonus)*: Posibilidad de mostrar imágenes de los Pokémon.
- *(Bonus)*: Uso de generadores automáticos de interfaz (por ejemplo, en NetBeans).

---

## 🎮 Controlador

- Se encarga de recibir eventos de la interfaz (clics, selecciones, etc.).
- Conecta la vista con el modelo, enviando instrucciones y actualizando los datos mostrados al usuario.
- Se recomienda utilizar la **segunda versión** del controlador vista en clase.

---

## 👥 Integrantes

- 👤 Paula Jimena Bohórquez Bermúdez  
- 👤 Manuela Delgado Aguirre  
- 👤 Juan Camilo Gil Agudelo  
- 👤 Gabriel Esteban Burbano Mora  

> ⚠️ **Importante**: Quien no aparezca aquí **no será calificado**.

---

## 🛠️ Instalación y Uso

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/SimuladorPokemonMVC.git
   ```
2. Abrir el proyecto en **NetBeans** o cualquier IDE de tu preferencia compatible con Java.
3. Ejecutar la clase principal que lanza la interfaz.
4. Seguir las instrucciones en pantalla.
