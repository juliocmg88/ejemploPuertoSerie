# Puerto Serie Application

This application interacts with the RS232 port of a computer to control electronic gates of a hydroelectric plant. You can download the executable `.jar` file and run it on your machine.

## Features

- Validate authorized users to login
- Toggle status of gates
- Visualize historic changes in gates' status

## Download

You can download the latest version of the application [here](https://github.com/juliocmg88/ejemploPuertoSerie/tree/main/dist/ejemploPuertoSerie.jar).
The application requires a specific MySQL database model to work correctly.

## How to Set Up the Database

1. **Install MySQL:**
   - Ensure you have MySQL installed on your machine. You can download it from [here](https://dev.mysql.com/downloads/mysql/).

2. **Create the Database:**
   - Open your MySQL command line or a MySQL client (e.g., MySQL Workbench).
   - Create a new database:

     ```sql
     CREATE DATABASE serial_port_events;
     ```

3. **Import the Database Schema and Initial Data:**
   - Download .sql files that refers to the database sructure [here] (https://github.com/juliocmg88/ejemploPuertoSerie/tree/main/database)
   - Navigate to the directory where you have the `.sql` files (located in the `database` folder).
   - Run the following command to import the schema:

     ```sh
     mysql -u your_username -p serial_port_events < path/to/file_1.sql
     mysql -u your_username -p serial_port_events < path/to/file_2.sql
     mysql -u your_username -p serial_port_events < path/to/file_N.sql
     ```

4. **Update the Application Configuration:**
   - Ensure that your application is configured to connect to the MySQL database. You may need to update a configuration file or environment variables with your database credentials.



## How to Run

1. Ensure you have Java installed on your machine. You can download it from [here](https://www.java.com/en/download/).
2. Download the `.jar` file using the link above.
3. Open a terminal or command prompt and navigate to the directory where you downloaded the `.jar` file.
4. Run the following command:

   ```sh
   java -jar ejemploPuertoSerie.jar
   ```

## Application Work Screenshots
Whether you manage to work this application in your local environment or not, you can view its pages [here] (https://github.com/juliocmg88/ejemploPuertoSerie/tree/main/screenshots)