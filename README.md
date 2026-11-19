# El Despertar del Héroe

Aventura RPG por turnos desarrollada en **Java** con persistencia de puntuaciones en **XML** (validado mediante **DTD**) y generación automática de clasificación web mediante **XSLT/XPath**, **HTML5** y **CSS3**.

Proyecto intermodular de 1.º de Desarrollo de Aplicaciones Web (DAW) para los módulos de **Programación**, **Entornos de Desarrollo** y **Lenguaje de Marcas**.

---

## Características principales

* **Selección de héroe:** Posibilidad de jugar con **Mario** (mayor velocidad, elemento fuego) o **Luigi** (mayor defensa, elemento eléctrica).
* **Campaña en 3 capítulos:** Combates por turnos con orden basado en velocidad frente a **Goomba**, **Boo** (inmune a ataques físicos) y **Bowser** (con pinchos dorsales y ataque elemental).
* **Sistema de combate e inventario:** Ataques de Salto, Martillo (con minijuego de golpe crítico) y Elemental, además de un inventario fijo de 3 pociones curativas (+25 HP).
* **Ranking Top 10 persistente:** Ordenación automática de mayor a menor puntuación (con desempate por antigüedad) almacenada en `ranking.xml` y transformada en cada partida a `ranking.html`.

### Sistema de puntuación
* **+100 pts** por cada enemigo derrotado.
* **+1 pt** por cada punto de vida (HP) restante al completar la aventura.
* **+25 pts** por cada poción no consumida al finalizar con victoria.

---

## Estructura del proyecto

```text
├── Main.java                 # Flujo principal, menús, narrativa, combate y lógica de ranking
├── Personaje.java            # Superclase base con atributos comunes y cálculo de críticos
├── Bros.java                 # Subclase jugable (Mario/Luigi), ataques especiales e inventario
├── Goomba.java               # Enemigo del Capítulo 1
├── Boo.java                  # Enemigo espectral del Capítulo 2
├── Bowser.java               # Jefe final del Capítulo 3
├── IataqueElemental.java     # Interfaz para personajes con habilidades elementales
├── IconPinchos.java          # Interfaz de marcado para enemigos con pinchos
├── Ifantasma.java            # Interfaz de marcado para enemigos espectrales
├── RankingXML.java           # Lectura (importar) y escritura DOM + XSLT (exportar)
├── ranking.xml               # Base de datos XML con el historial de puntuaciones
├── ranking.dtd               # Definición de tipo de documento para validar ranking.xml
├── ranking.xsl               # Plantilla XSLT con rutas XPath para generar el HTML
├── ranking.html              # Vista web del ranking actualizada automáticamente
└── styles.css                # Hoja de estilos CSS para ranking.html

```

---

## Requisitos y ejecución

### Requisitos previos

* **Java Development Kit (JDK):** Versión 17 o superior (compatible con JDK 11+).
* Navegador web moderno para visualizar `ranking.html`.

### Pasos para compilar y ejecutar

1. Clona este repositorio o descarga el código fuente:
```bash
git clone https://github.com/DanpGamer/ElDespertarDelHeroe
cd ElDespertarDelHeroe

```


2. *(Opcional en Windows)* Activa la codificación UTF-8 en la consola para visualizar correctamente tildes y barras de vida:
```bash
chcp 65001

```


3. Compila todos los archivos fuente Java desde la carpeta raíz donde se encuentran `ranking.xml` y `ranking.xsl`:
```bash
javac -encoding UTF-8 *.java

```


4. Ejecuta el programa principal:
```bash
java -Dfile.encoding=UTF-8 Main

```


5. Tras finalizar una partida (por victoria, derrota o abandono), abre el archivo `ranking.html` en cualquier navegador para consultar la tabla actualizada.

---

## Autor

* **Reinaldo Andrés García Villamediana** — 1.º DAW (XTART Formación Profesional, 2026)
