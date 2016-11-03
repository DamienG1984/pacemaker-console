### Assignment 1 - Agile Software Development

Name: Damien Griffin - 02028263

### Objective
Design and implement a simple Activity Tracker console (CLI) application.
The application is to manage activities for multiple user accounts, enabling
activities to be created, read, updated and deleted (CRUD). The application is to persist the user and activity data to a single file. Two file formats are to be supported: 

    > XML
    > JSON

The application has the following Features:
+ lu list-users ()
+ cu create-user (first name, last name, email, password)
+ lu list-user (email)
+ lius list-user (id)
+ la list-activities (userid, sortBy: type, location, distance, date, duration)
+ la list-activities (user id)   
+ du delete-user (id)
+ aa add-activity (user-id, type, location, distance, datetime, duration)
+ al add-location (activity-id, latitude, longitude)
+ cff change-file-format (file format: xml, json)
+ l load ()
+ s store ()

 

### Overview
Data persistence was achived using Serialization with the ability to store data in two different file formats (JSON,XML). The output of this project is displayed in the java console, results are displayed in tabular format using IASCIITableAware. The application uses the MAVEN build framework. JUINT was used to develop test cases for the following features 

+ Create User Test
+ Get All Users
+ Get User by ID
+ Get User by Email
+ Deleting Users
+ Add Activity
+ List Activity
+ Create Location
+ Get Location
+ Order by Test
+ Persistence Test
