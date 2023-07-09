# Irrigation System Readme

This repository contains the code for an irrigation system, which provides APIs for managing plots and configuring irrigation processes. The system allows users to add, edit, and retrieve information about plots, as well as configure irrigation processes for specific plots.

## Technologies Used

- Java
- Spring Boot
- MySQL
- Hibernate
- Liquibase
- Maven

## Setup

1. Ensure that Java, MySQL, and Maven are installed on your system.
2. Clone this repository to your local machine.
3. Open the project in your preferred Java IDE.
4. Configure the MySQL database connection by modifying the `application.properties` file. Update the `spring.datasource.username` and `spring.datasource.password` properties with your MySQL credentials.
5. Run the application using the IDE's run or build command.

## Configuration

The application can be configured using the `application.properties` file located in the `src/main/resources` directory. The following properties are available for configuration:

- `server.port`: Specifies the port on which the application will run. Default value is `8082`.
- `server.error.include-stacktrace`: Controls whether stack traces should be included in error responses. Default value is `never`.
- `server.servlet.context-path`: Specifies the context path for the API. Default value is `/api`.
- `spring.application.name`: Defines the application name. Default value is `irrigation`.
- `spring.datasource.username`: MySQL database username.
- `spring.datasource.password`: MySQL database password.
- `spring.datasource.url`: JDBC URL for connecting to the MySQL database.
- `spring.jpa.show-sql`: Controls whether SQL queries should be logged. Default value is `true`.
- `spring.jpa.generate-ddl`: Specifies whether Hibernate should generate DDL statements. Default value is `true`.
- `spring.jpa.hibernate.ddl-auto`: Specifies the Hibernate DDL generation strategy. Default value is `validate`.
- `spring.jpa.hibernate.dialect`: Specifies the Hibernate dialect for MySQL. Default value is `org.hibernate.dialect.MySQL5InnoDBDialect`.
- `spring.jpa.properties.hibernate.format_sql`: Controls whether formatted SQL should be logged. Default value is `true`.
- `spring.liquibase.change-log`: Specifies the Liquibase changelog file path.

Additional configuration properties related to the irrigation system can be found in the `application.properties` file. These properties include `irrigation.speed` (speed of irrigation), and `max.sensor.retries` (maximum number of sensor connection retries).

## Usage

The irrigation system provides several APIs for managing plots and configuring irrigation processes. The APIs are accessible through the following endpoints:

### Plots API

- `GET /plots`: Retrieves a list of all plots.
- `GET /plots/{plotId}`: Retrieves information about a specific plot.
- `POST /plots`: Adds a new plot.
- `PUT /plots/{plotId}`: Updates an existing plot.
- `PATCH /plots/{plotId}`: Edits an existing plot.

### Configuration Process API

- `PUT /plots/{plotId}`: Configures an irrigation process for a specific plot.

### Sensor API

- `GET /sensor/state`: Retrieves the current state of the sensor.
- `PUT /sensor/state`: Changes the state of the sensor.

## Models

The application uses several models to represent the data:

- `Plot`: Represents a plot of land with properties such as name, crop type, and area.
- `TimeSlot`: Represents a time slot for irrigation, including start and end dates, irrigation water amount, and other related properties.
- `IrrigationProcess`: Represents an irrigation process with properties such as start time, end time, duration, status, and sensor retries.

## Services

The application includes the following services:

- `PlotService`: Provides methods for managing plots, including adding, editing, and retrieving plot information.
- `SensorService`: Manages the state of the sensor and provides methods for changing and retrieving its state.
- `IrrigationProcessService`: Handles the management of irrigation processes, including starting and ending irrigation based on scheduled time slots.

## Enums

The application includes several enums to represent different states and values:

- `TimeSlotState`: Represents the state of a time slot, including values such as SCHEDULED, IN_PROGRESS, DONE, and REJECTED.
- `SensorState`: Represents the state of the sensor, including values AVAILABLE and NOT_AVAILABLE.
- `AlarmState`: Represents the state of the alarm, including values RINGING and SILENT.

## Controllers

The application includes the following controllers:

- `PlotController`: Handles HTTP requests related to plots, including adding, editing, and retrieving plot information.
- `SensorController`: Handles HTTP requests related to the sensor, including retrieving and changing its state.

## Error Handling

The application includes error handling for various scenarios. When an error occurs, an appropriate error response is returned, including a message, status code, timestamp, and details.

## Contributions

Contributions to this project are welcome. If you find any issues or want to suggest improvements, please create a new issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to use, modify, and distribute this code for personal or commercial purposes.
