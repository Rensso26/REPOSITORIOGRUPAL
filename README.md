Colaboradores:
Rensso Nicolay Parra Vasquez
,Joel Joshua Luna Grijalva 
,Freddy Xavier Tapia Rea

# Proyecto grupal Galaga 

## Descripción

Este proyecto consta de dos componentes principales: un juego inspirado en Galaga y un sistema de persistencia que guarda los estados del juego en una base de datos MySQL. El juego de Galaga está desarrollado en Java utilizando Swing para la interfaz gráfica. El sistema de persistencia se encarga de almacenar y recuperar los estados del juego, permitiendo a los jugadores continuar desde donde lo dejaron.


## Estructura del Proyecto

El proyecto está dividido en dos módulos principales:

1. **Galaga Game**: Contiene la lógica y la interfaz del juego.
2. **Persistencia**: Maneja la persistencia de datos utilizando una base de datos MySQL.


## Requisitos

- JDK 21
- MySQL (creado el scheme)

## Configuración de la Base de Datos

1. **Crear el esquema de la base de datos**:
   
Ejecuta el siguiente comando en tu cliente de MySQL para crear la base de datos.

   Create database galaga_db;

2. **Configurar la conexión a la base de datos**:

Asegúrate de configurar las credenciales de acceso a la base de datos en tu archivo de configuración de Spring (application.properties):

   spring.application.name=persistence
	
   # Configuración de la base de datos
   spring.datasource.url=jdbc:mysql://localhost:3306/galaga_db
   spring.datasource.username= <Coloca tu usuario>
   spring.datasource.password= <Coloca tu contraseña>
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

   # Configuración de JPA/Hibernate
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true


## Estructura del Código

1. **Galaga Game

models: Contiene las clases del modelo, como Hero, Enemy, SuperEnemy, Bullets, etc.
controllers: Contiene la clase Container que maneja la lógica del juego.
actions: Contiene interfaces como Drawable y MovableX, etc.
state: Maneja el estado del juego y la persistencia, con clases como GameState, GameManager y GameStateService.

2. **Persistencia

GameManager: Clase principal que maneja la lógica de guardado y carga de los estados del juego.
GameState: Clase que representa el estado del juego que se guarda en la base de datos.
GameStateService: Interfaz de Spring Data JPA para acceder a la base de datos.


## Diseño del Juego

1. **Models

Hero: Representa al jugador. Maneja sus propiedades como vida, puntaje y coordenadas.
Enemy: Representa a los enemigos en el juego.
SuperEnemy: Representa al  enemigo más fuerte que aparece en el ultimo nivel.
Bullets: Representa las balas disparadas por el héroe y los enemigos.
Score: Maneja el puntaje del héroe.
Life: Representa la vida del héroe.
LifeSuperEnemy: Representa la vida de lo SuperEnemy.

2. **Controllers
Container: Clase principal que maneja la lógica del juego, incluyendo el movimiento del héroe, enemigos, y balas, así como el control de niveles y el manejo del estado del juego.

3. **Persistencia
GameManager: Clase principal que maneja la lógica de guardado y carga de los estados del juego.
GameState: Clase que representa el estado del juego que se guarda en la base de datos.
GameStateService: Interfaz de Spring Data JPA para acceder a la base de datos.


## Guía de Instalación

1. **Clonar el repositorio:

git clone https://github.com/Rensso26/REPOSITORIOGRUPAL.git

2. **Configurar el entorno:

Asegúrate de tener JDK y Maven instalados en tu sistema. Configura las variables de entorno JAVA_HOME y MAVEN_HOME correctamente.


## Ejecución del Proyecto

1. **Interacción con el juego:

Inicio de sesión: Al iniciar el juego, se te pedirá que ingreses un nombre de usuario para identificar tu sesión de juego.

Jugar: Usa las teclas 'A' y 'D' para mover al héroe de izquierda a derecha y la barra espaciadora para disparar.

Guardar y cargar estado: El juego guarda automáticamente el estado al finalizar un nivel o al salir. Puedes continuar tu partida desde el último estado guardado.


## Contacto

Para cualquier consulta o sugerencia, puedes contactarnos en [rnparra@uce.edu.ec].
