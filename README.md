# Employee Reimbursement System

## Project Description

A website for a company that implements a reimbursement system. Employees can view and add reimbursement requests. Managers can then view all the filtered reimbursements and approve/deny/delete the reimbursements.

## Technologies Used

* Java8
* Gradle
* JUnit/Mockito
* Logback
* GCP Compute Engine
* GCP Cloud SQL
* Firebase
* HTML/CSS/JavaScript

## Features

List of features:
* Login Feature:
  * Login as either employee or manager
* Employee Features:
  * Login and view current/resolved reimbursements
  * Add a new reimbursement
* Manager Features:
  * View all reimbursements
  * Filter reimbursements by status(Pending, Approved, Denied)
  * Approve/Deny/Delete any pending reimbursement

## Getting Started
  * git clone 
  * gradle build
* Required Environmental Variables:
  * Database
    * db_username
    * db_password
    * db_url
* Frontend :
  * Run a server using live-server --port = 8081



## Usage
* Login page
![login-page](https://user-images.githubusercontent.com/64660004/162838216-a0c84267-f299-49b0-ade6-4e719def4595.PNG)
* Employee views reimbursements
![employee-view](https://user-images.githubusercontent.com/64660004/162838723-76cffcb9-2cdd-4306-8031-d28064e6434b.PNG)

* Employee adds a reimbursement
![employee-add](https://user-images.githubusercontent.com/64660004/162838544-ccd064ca-9087-42f1-9193-aaee858e6437.PNG)
* Manager views reimbursements (filtered)
![manager-view](https://user-images.githubusercontent.com/64660004/162838805-9c7b0e6a-49fc-4c9c-ae9e-9e81f0c7736c.PNG)
* Managers can approve or deny pending reimbursements
![manager-approve-deny](https://user-images.githubusercontent.com/64660004/162839047-45c7ed8b-d51b-44dd-818e-325c5bff9c19.PNG)
* Managers can delete employee reimbursements
![manager-delete](https://user-images.githubusercontent.com/64660004/162839216-b6b6c6fc-061c-45ed-88ca-5fd83dede17f.PNG)


## License
* This project uses the following license: MIT
