# Irrigation System 

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

configuration properties related to the irrigation system can be found in the `application.properties` file. These properties include `irrigation.speed` (speed of irrigation), and `max.sensor.retries` (maximum number of sensor connection retries).

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
## DB Design 
![Alt Text](https://github.com/ahmedatef00/irrigation/blob/master/src/main/resources/static/img.png)

## Database Seeder

The application includes a DatabaseSeeder class located in the com.example.irrigation.utils package. This class is responsible for seeding initial data into the database. During the application startup, the run method of the DatabaseSeeder class is executed, which in turn calls the seedData method to create and save sample data.

To customize the seeded data, you can modify the seedData method in the DatabaseSeeder class. The current implementation creates a plot, a time slot, and an irrigation process associated with the plot. Additionally, the startSensor method is called to set the sensor's status.
## PostMan Collection 
The application includes a Postman Collection on src/main/resources/static/Irrigation-System.postman_collection.json
