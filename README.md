<!-- PROJECT LOGO -->
<br />
<div align="center">
  
   <h1 align="center">Stadium Hunter</h1>
 
</div>


## Table of Contents
* [General Info](#general-information)
* [User Stories](#user-stories)
* [Setup](#setup)
* [Technologies Used](#technologies-used)
* [Models](#models)
* [Server Routes](#server-routes-table)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Resources](#resources)



## General Information
This projects serves as a backend or server side functionality to the Stadium Hunter WebApp, accepting HTTP requests.

## User Stories
* As a User I want to track all the stadiums I'have visited all around the world.
* As a User I want to leave review about the stadiums i have visited, so other users can benefit from my opinion.
* As a User I want to see other users reviews about stadium i want to visit.


## Setup

  Clone the Stadium Hunter API. In a terminal, run:
```bash
$ git clone https://github.com/Openbank-Java-Bootcamp/Pilar-Alvarez-Stadium-Hunter-Server
```

  Before running the API server, you should set the database config with yours or set your database config with my values.


## Technologies used

Java, SpringBoot, MySQL, Postman.
* [Java](https://www.java.com/es/)
* [SpringBoot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com/)
* [Postman](https://www.postman.com/)


## Models

![model-diagram](model-diagram.png)

There are four types of accounts:

-Saving 

-Checking

-Student checking  

-Credit

Accounts can only be created by an Admin.

Anyone can create a new account holder user and login, but only admins when create account vinculate an account with its owner (account holder).

Account Holders can make transfers from their account and check their balance. 

When a transfer is requested, the availability of funds (or the credit limit in the case of credit account) is checked before processing it. Once the transfer is made, if the balance falls below the minimum allowed a penalty fee is charged.

In addition, suspicions of fraud are detected by freezing the account and preventing the transfer. Only Admin can reactivate the account.

Every time an account holder access their balance, it is checked if it is necessary to add corresponding interests (in saving and credit account) or charge maintenance to the account (checking account).


## Server Routes Table
 
| Request Type  | Route              | Description                            | Authorization    | Requested Data      |
| ------------- | ---------------    | -------------------------------------- |----------------- |---------------------|
| `POST`        | /api/login         | Login  with username and password      | PUBLIC           |username & password|
| `POST`        | /api/checking      | Create a Checking Account              | ADMIN            |CheckingAccountDTO object  |
| `POST`        | /api/saving        | Create a Saving Account                | ADMIN            |SavingAccountDTO object|
| `POST`        | /api/credit        | Create a Credit Account                | ADMIN            |CreditAccountDTO object|
| `PATCH`       | /api/transfer      | Transfer Money                         | ACCOUNT HOLDER   |OwnerTransferDTO object|
| `GET`         | /api/balance       | Access account balance                 | ACCOUNT HOLDER   |accountId|
| `GET`         | /api/adminbalance  | Access any account balance             | ADMIN            |accountId|
| `PATCH`       | /api/setbalance    | Modify any account balance             | ADMIN            |accountId & newBalance|
| `DELETE`      | /api/account       | Delete an account                      | ADMIN            |accountId|
| `PATCH`       | /api/status        | Change account status                  | ADMIN            |accountId & status|
| `POST`        | /api/roles         | Create a new authority role            | ADMIN            |Role object|
| `POST`        | /api/roletouser    | Add role to a user                     | ADMIN            |RoleToUserDTO object|
| `POST`        | /api/thirdparties  | Create a Third Party                   | ADMIN            |ThirdParty object|
| `PATCH`       | /api/thirdtransfer | A third party transfer money to an account   | PUBLIC   |hashedKey & ThirdPartyTransferDTO object|
| `PATCH`       | /api/thirdtake     | A third party takes money from an account   | PUBLIC   |hashedKey & ThirdPartyTransferDTO object|
| `DELETE`      | /api/thirdparties  | Delete a Third Party                   | ADMIN         |thirdPartyId|
| `POST`        | /api/users         | Create an Account Holder               | PUBLIC         |AccountHolder object|
| `POST`        | /api/admin         | Create an Admin                        | ADMIN         |Admin object|
| `DELETE`      | /api/users         | Delete a user                          | ADMIN          |userId|

Objects must be provided in the body of the request as raw and JSON format, as examples shown below.

![controller-diagram](controller-diagram.png)


## Project Status
Project is: _in progress_ / _complete_ / _no longer being worked on_. If you are no longer working on it, provide reasons why.


## Room for Improvement
Include areas you believe need improvement / could be improved. Also add TODOs for future development.

Room for improvement:
- Improvement to be done 1
- Improvement to be done 2

To do:
- Feature to be added 1
- Feature to be added 2

## Resources

Instructors of Ironhack: Raymond & Shaun.

https://www.baeldung.com/

https://stackoverflow.com/
