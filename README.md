# AppTiendaBedu

Este proyecto aplica los conocimientos adquiridos en el curso de Kotlin Intermedio de cada una de las sesiones; dicho proyecto se denominado **TIENDA BEDU V2** el cual tiene los siguientes objetivos especificos:

* Inicio de sesión.

* Registro de productos e inventario.

* Detalles de usuario.


## Instrucciones para clonar en un equipo local

Para ello seguir las siguientes instruciones:

* Clonar el repositorio: git clone https://github.com/SanLnAlan/AppTiendaBedu.git
* Abrir el proyecto con el Android Studio.
* Dirigirse al archivo principal (MainActivity) de proyecto y ejecutarlo.


## Menú de aplicación

Al compilar el el MainActivity.kt se observará la siguiente pantalla:

![](https://github.com/SanLnAlan/AppTiendaBedu/blob/main/Im%C3%A1genes_TiendaBedu/login_TiendaBedu.jpeg)

*Imagen 1: Pantalla de inicio de sesión*


Para poder ingresar a la aplicación usa el user de pruebas del demo:

Email: admin@bedu.com

Cotraseña: password


## Acerca de

La aplicación se trata de una tienda en línea, en la que se puede escoger entre varios productos y el número deseado de los mismos. Se accede al catálogo de productos y a las demás funciones una vez que se inicie sesión. Una vez iniciada la sesión, la pantalla se verá de la siguiente manera:

![](https://github.com/SanLnAlan/AppTiendaBedu/blob/main/Im%C3%A1genes_TiendaBedu/catalogo_TiendaBedu.jpeg)

*Imagen 2: Catálogo de productos*


## Instrucciones para conexión con servidor

1. Descomprimir la carpeta https://drive.google.com/file/d/1bH2ywJ66-4tS60l1TNNiN0f5KuHMRYPd/view?usp=drive_link
2. Desde terminal ubicarse dentro de la carpeta descomprimida y a su vez dentro de la carpeta tienda-backend
3. con un ls comprobar que se encuentren los archivos docker-compose
4. Ejecutar los siguientes comandos:
   a. docker-compose build
   b. docker-compose up
5. Obtener la dirección Ip local y asignarla en el archivo MainActivity.kt en la constante llamada DIRECCION_IP_LOCAL 

[Video](https://drive.google.com/file/d/1yVP39S240DmWAEwA1B0MtOlGkm7yITrZ/view?usp=drive_link) detalle de la aplicación.



## Información de contacto

Cualquier aclaración dirigirse con:

* Alan Sandoval León (sandoval.leon.alan@gmail.com)
* Luis Fernando Pedraza Estañol (luisfedranol@gmail.com)
* Marco Antonio Mojica Martinez (mojicamarc@gmail.com)
* Carlos Armando Morales Bautista (carloamoraleb@gmail.com)
