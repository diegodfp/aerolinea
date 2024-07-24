# Agencia Vuelos Globales

## Introducción

En el mundo actual, el desarrollo de software es fundamental para el funcionamiento eficiente y seguro de una aerolínea. La tecnología ha revolucionado la forma en que las aerolíneas operan, desde la gestión de vuelos y el mantenimiento de aeronaves hasta la experiencia del cliente. En una industria donde la puntualidad, la seguridad y la satisfacción del cliente son cruciales, el software se convierte en un aliado indispensable.

Vuelos Globales opera vuelos a nivel internacional y cuenta con una flota de aviones, tripulación variada, múltiples aerolíneas asociadas y una vasta red de aeropuertos y ciudades de destino. La empresa requiere una base de datos robusta para gestionar todos los aspectos de su operación, desde la reserva de vuelos hasta el mantenimiento de los aviones y la administración de la tripulación.

## Tabla de Contenidos
- [Características](#características)
- [Documentación](#documentación)
- [Tecnologías Usadas](#tecnologías-usadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Autores](#autores)

### Requisitos Previos
- Java JDK 17.0+
- MySQL 8.0+
- Maven 4.0.0

### Pasos para la Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/diegodfp/aerolinea.git
   ```
2. Crea y navega al directorio del proyecto:
    ```bash
    cd aerolinea
    ```

## Características
- Gestión de aviones y modelos
- Mantenimiento y revisiones de aeronaves
- Administración de tripulación
- Gestión de rutas y escalas
- Sistema de reservas y clientes
- Tarifas y tipos de documentos

## Documentación
Para más detalles, visita la [documentación](/docs/).

## Tecnologías Usadas
- **Lenguaje de Programación:** Java
- **Base de Datos:** MySQL
- **Control de Versiones:** Git
##Dependencias
###El proyecto utiliza las siguientes dependencias principales:

- **MySQL Connector/J (versión 8.4.0):** Conector JDBC para MySQL, que permite la conexión y operaciones con la base de datos MySQL.
- **JCalendar (versión 1.4):** Biblioteca que proporciona componentes de interfaz de usuario para la selección de fechas y manejo de calendarios.

## Estructura del Proyecto 
```css
📦aerolinea
 ┣ 📂airline
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜AirlineUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Airline.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜AirlineService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┗ 📜AirlineRegisterUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜AirlineRepository.java
 ┣ 📂airport
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜AirportUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Airport.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜AirportService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜AirportDeleteUi.java
 ┃ ┃ ┃ ┣ 📜AirportDetailsUi.java
 ┃ ┃ ┃ ┣ 📜AirportRegisterUi.java
 ┃ ┃ ┃ ┗ 📜AirportUpdateUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜AirportRepository.java
 ┣ 📂city
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜CityUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜City.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜CityService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜CityRepository.java
 ┣ 📂common
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┗ 📜DatabaseConfig.java
 ┃ ┃ ┗ 📂in
 ┃ ┃ ┃ ┣ 📜AdminUi.java
 ┃ ┃ ┃ ┣ 📜LoginUi.java
 ┃ ┃ ┃ ┣ 📜MainUi.java
 ┃ ┃ ┃ ┗ 📜SignInUi.java
 ┣ 📂country
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜CountryUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Country.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜CountryService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜CountryRepositoriy.java
 ┣ 📂customer
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜CustomerUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Customer.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜CustomerService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜CustomerDeleteUi.java
 ┃ ┃ ┃ ┣ 📜CustomerDetailsUi.java
 ┃ ┃ ┃ ┣ 📜CustomerRegisterUi.java
 ┃ ┃ ┃ ┗ 📜CustomerUpdateUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜CustomerRepository.java
 ┣ 📂documenttype
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜DocumenttypeUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Documenttype.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜DocumenttypeService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜DocumenttypeDeleteUi.java
 ┃ ┃ ┃ ┣ 📜DocumenttypeDetailsUi.java
 ┃ ┃ ┃ ┣ 📜DocumenttypeRegisterUi.java
 ┃ ┃ ┃ ┗ 📜DocumenttypeUpdateUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜DocumenttypeRepository.java
 ┣ 📂employee
 ┃ ┗ 📂domain
 ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┗ 📜Employee.java
 ┣ 📂flightConnections
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜FlightConnectionUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜FlightConnection.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜FlightConnectionService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜FlightConnectionDeleteUi.java
 ┃ ┃ ┃ ┣ 📜FlightConnectionDetailsUi.java
 ┃ ┃ ┃ ┗ 📜FlightConnectionUpdateUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜FlightConnectionRepository.java
 ┣ 📂flightfares
 ┃ ┗ 📂domain
 ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┗ 📜FlightFares.java
 ┣ 📂model
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜ModelUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Model.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜ModelService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜ModelRepository.java
 ┣ 📂plane
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜PlaneUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Plane.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜PlaneService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜PlaneDeleteUi.java
 ┃ ┃ ┃ ┣ 📜PlaneDetailsUi.java
 ┃ ┃ ┃ ┣ 📜PlaneRegisterUi.java
 ┃ ┃ ┃ ┗ 📜PlaneUpdateUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜PlaneRepository.java
 ┣ 📂status
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜StatusUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Status.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜StatusService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜StatusRepository.java
 ┣ 📂trip
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜TripUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜Trip.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜TripService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜TripDeleteUi.java
 ┃ ┃ ┃ ┣ 📜TripDetailsUi.java
 ┃ ┃ ┃ ┗ 📜TripUpdateUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜TripRepository.java
 ┣ 📂tripCrew
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜TripCrewUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┣ 📜TripConnectionInfo.java
 ┃ ┃ ┃ ┗ 📜TripCrew.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┗ 📜TripCrewService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┣ 📜AssignCrewToTripUi.java
 ┃ ┃ ┃ ┗ 📜DetailsCrewToTripUi.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┗ 📜TripCrewRepository.java
 ┣ 📂tripulationRole
 ┃ ┗ 📂domain
 ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┗ 📜TripulationRole.java
 ┣ 📂users
 ┃ ┣ 📂application
 ┃ ┃ ┗ 📜UserUseCase.java
 ┃ ┣ 📂domain
 ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┣ 📜UserAuthenticationPort.java
 ┃ ┃ ┃ ┣ 📜UserLoginUseCase.java
 ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┗ 📂infrastructure
 ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┣ 📜LoginUiAdapter.java
 ┃ ┃ ┃ ┣ 📜UserAuthenticationAdapter.java
 ┃ ┃ ┃ ┗ 📜UserRepository.java
 ┣ 📜Main.class
 ┗ 📜Main.java
```



## Autores 

- Diego Fernando Pérez *- Developer* .  <a href="http://github.com/diegodfp" target="_blank">Diegodfp</a>
- Elkin Gabriel Niño Sánchez  *- Developer* . <a href="https://github.com/Latinohablante" target="_blank">Latinohablante</a>
