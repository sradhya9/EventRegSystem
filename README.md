# Event Registration System

## Overview
The Event Registration System is a Java-based application designed for efficiently managing event registrations at a college. The system provides role-based access to different types of users—Admin, Staff, and Student—allowing each role to perform specific tasks such as registering for events, managing event details, and viewing event information. This application aims to streamline the event registration process, enhance accessibility to event details, and support effective event administration.

## Features
- **Role-Based Access Control**: Three distinct user roles with specific permissions—Admin, Staff, and Student.
- **Event Management**: Admin users can add, update, delete, and view event details.
- **Registration Functionality**: Students and staff can register for events and view event information.
- **Intuitive User Interface**: Built using Java Swing, featuring a clean and user-friendly design.
- **Database Integration**: Uses MySQL to securely store, retrieve, and update event data.

## Technologies Used
- **Java**: Core application logic and GUI.
- **Swing**: GUI development for a responsive and intuitive user experience.
- **MySQL**: Database management for storing and retrieving event information.
- **JDBC**: For Java-MySQL connectivity.
- **NetBeans**: IDE used for development (recommended version 23).

## Prerequisites
- **JDK 23.0.1**: Ensure you have the required version of JDK installed.
- **MySQL 8.0**: Set up MySQL and create the necessary database and tables.
- **JDBC 9.1**: Ensure the correct JDBC driver is available.
- **NetBeans IDE**: Recommended for development and testing.

## System Workflow

### Login Page
The login page is the entry point for all users, allowing them to log in with their credentials and select their role.

### Roles and Functionalities
Each role has unique permissions to ensure that users have appropriate access to the system:

#### Admin
- **Add Event**: Allows admins to create new events, specifying event name, date, and description.
- **Update Event**: Admins can modify details of existing events.
- **Delete Event**: Allows admins to remove events from the system.
- **View All Events**: Provides a comprehensive view of all registered events.

#### Staff
- **Register for Events**: Staff members can register for upcoming events.
- **View Event Details**: Staff members can view all event details, including dates, descriptions, and other relevant information.

#### Student
- **Register for Events**: Students can register for available events.
- **View Event Details**: Students have access to view event details, similar to staff, providing transparency on event schedules.

## Database Schema
The MySQL database for this application consists of the following key tables and fields:

### Tables and Fields

#### Events
- **event_id**: Unique identifier for each event.
- **event_name**: Name of the event.
- **event_date**: Date the event is scheduled.
- **description**: Description of the event.

#### Users
- **user_id**: Unique identifier for each user.
- **user_type**: Specifies the role (Admin, Staff, or Student).
- **username**: Name of the user.
- **password**: Encrypted password for authentication.

#### Registrations
- **registration_id**: Unique identifier for each registration. Primary key with auto-increment.
- **user_id**: Foreign key linking to the unique ID of the user registering for an event.
- **event_id**: Foreign key linking to the unique ID of the event being registered for.
- **username**: Username of the person registering for the event.
- **event_name**: Name of the event.
- **payment_status**: Enum type with values 'Paid' and 'Pending', indicating the status of payment.
- **event_date**: Date on which the event is scheduled.

The `registrations` table links users and events, enabling the tracking of event registrations along with payment status and scheduling information.

## User Interface
The UI is designed for ease of use with a minimalistic, light theme. Each role-based page includes clearly labeled buttons for accessing specific functionalities, such as registering for events or managing event details.

- **Color Scheme**: The application uses a clean, consistent color palette across all screens.
- **Navigation**: Footer navigation provides users with quick access to relevant sections.

## Future Improvements
- **Security Enhancements**: Add password encryption and potentially two-factor authentication.
- **Advanced Reporting**: Include additional reporting features for event attendance analytics.
- **Mobile Compatibility**: Develop a mobile-friendly version to enable remote access.

## Troubleshooting

### Database Connection Issues
- Ensure MySQL service is running.
- Verify the credentials in `DBConnection.java` are correct.

### Missing JDBC Driver
- Confirm that the JDBC driver is correctly included in the project library.

### UI Display Problems
- Make sure to use a compatible version of NetBeans or your preferred IDE for Swing components.
