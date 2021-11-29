## CHALLENGE ALBO

Con el paso de los años la biblioteca albo de comics, necesita tener actualizado
todo el listado de escritores, editores y coloristas de cómics que han estado
involucrados en las historias de los siguientes integrantes de los Vengadores (Iron
Man, Captain America). Así como todos los demás héroes que a través de cada
cómic han interactuado con cada uno de ellos. Esto hay que actualizarlo diario, ya
que hay que pagarles regalías respectivas a los escritores, editores y coloristas

Esta API posee los siguientes servicios:

***/marvel/colaborators/{name}***
 - Obtiene los editores, escritores y coloristas que han estado involucrados en
   los cómics del personaje.

***/marvel/characters/{name}***
 - Obtiene los otros héroes con los cuales nuestro personaje ha interactuado
   en cada uno de los cómics.
   
## Decisiones técnicas
Para almacenar la información en la base de datos uso H2 Database (base de datos en memoria), la cual al iniciar la aplicación se encuentra vacía.
Para realizar la sincronización con la API de marvel, se configuró un Cron que se ejecuta todos los días a las 00:00:01 HS.		
Utiliza Swagger para documentar la API.
Se utilizaron aspects para logear los servicios.

***El proyecto incluye un par de scripts llamados:***
  - assemble.sh el cual hace la construcción del aplicativo que exponen los servicios.
  - avengers.sh el cual ejecuta el aplicativo que exponen los servicios en el puerto 80***