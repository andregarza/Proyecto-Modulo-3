# Proyecto-Modulo-3

Para introducir mi proyecto haré una breve descripción de la funcionalidad.

La aplicación tiene como propósito crear una base de datos de películas con su datos, con el propósito de actuar como una lista de películas vistas por el usuario, de modo que cada que el usuario agregue una película esta se agregue a la base datos con el nombre, año, sinopsis y la fecha en que se vio la película que se crea de forma automática esto siendo posible ya que la aplicación utiliza la API de una base de datos de películas llamada MovideDB, además de esto el usuario puede acceder a links de películas mediante un repositorio en línea, para que el usuario pueda ver estas películas siempre y cuando no se violen los derechos de autor de la obra, de misma forma si el usuario quiere conocer los datos de una película puede poner el nombre de cualquier película y se le regresará toda su información.

Ahora enlistare las características de la aplicación siguiendo la rúbrica para facilitar la evaluación.

1. Patrón MVC con Spring Boot: La aplicación desde la entrega anterior sigue este patrón ya que contiene estas capas en la que se separa el controlador de la entidad o modelo del servicio, por lo que estas capas están bien definidas así como la interacción entre ellas, además que cuenta con un CRUD completo para nuestra entidad que será accedido desde el Controlador

2. Spring Data Rest: Para esta entrega decidí prescindir de Spring Data Rest ya que al tener ya un controlador completamente definido además de customizado, la integración de los repositorios de SpringDataRest no suponen ninguna mejora o ventaja además de considerar el tiempo necesario para refactorizar teniendo en cuenta la pequeña ventana de tiempo para esta entrega

3. Spring MVC Validation: Para validar los datos entregados por el usuario se utilizaron el paquete de validation de SPRING BOOT, y se utilizaron estas validaciones en la clase llamada MovieDTO que es la clase que está interactuando con el usuario y el controlador, así mismo se colocaron las validaciones en el controlador mediante el uso de anotaciones y finalmente se creó un handler global de estas excepciones que se encarga de manejar cualquier error de validación y regresa información útil al usuario.

4. Manejo de Errores: Para este punto como ya se mencionó anteriormente se creó un manejador global de excepciones que cubre las excepciones más comunes que el usuario pueda encontrarse así como un manejador de excepciones para métodos HTTP como lo son el método 401 y 403 que manejan las excepciones para el contenido que no esté autorizado o no tenga permitido el uso de algún método.

5. Spring Security: Se agregó un proceso de autenticación mediante un login usando Spring Security, además que se agregaron perfiles con diferentes roles, los cuales definen el acceso que tienen a diferentes características y métodos dentro de la aplicación, debido a la ventana de tiempo tan reducida no se pudo integrar JWT a Spring Security.

6. Anotaciones y Pruebas Unitarias: Se realizaron pruebas unitarias al código utilizando JUnit, asegurando el correcto funcionamiento de el controlador y de la lógica de negocio, las pruebas realizadas fueron lo más generales con el propósito de reutilizar el código en otras pruebas similares, las pruebas cubren la mayor parte de los casos y aseguran que los diferentes métodos cumplan con su función, la integración con SELENIUM se prescinde ya que en los contados métodos en que podía ser utilizado resultaba redundante debido a  que previamente existía una prueba con JUnit que “Testeaba” lo mismo y de forma más rápida y ágil por lo que finalmente quedó descartada del proyecto, cabe mencionar que esto de ninguna manera significa que el proyecto no cuenta con las pruebas unitarias pertinentes para asegurar el correcto funcionamiento de los componentes.

7. Despliegue de la aplicación:  La aplicación se desplegó mediante la generación de un archivo Jar que finalmente se montó en un contenedor de Docker lo cual permite su despliegue y acceso mediante la plataforma de Docker
