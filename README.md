## PicPay Backend Challenge
PicPay Simplified is a simplified payment platform. Here you can deposit and transfer money between users. We have 2 types of users, ordinary users and merchants, both of whom have a wallet with money and make transfers between them. This is a Restful API, as requested in the challenge objective.

## Used Technologies
[![Used Technologies](https://skillicons.dev/icons?i=java,spring,maven,docker,postgres,rabbitmq,postman)](https://skillicons.dev) 

Some specific technologies that also were used:
> Spring Data JPA <br>
> Flyway Migrations <br>
> Docker Compose <br>
> PostgreSQL (Docker Container) <br>
> Hibernate Validator <br>
> RestTemplate <br>
> RabbitMQ <br>

## Features
> Registering COMMON and MERCHANT Wallets <br>
> Carry out transactions from a COMMON Wallet to a MERCHANT Wallet <br>
> Sending notifications when carrying out transactions using an PicPay external service <br>

## Project Structure
````css
simplified-picpay-challenge
│ README.md
│ pom.xml
| docker-compose.yaml
| .gitignore
└───src
│    └───main
│          └───java
│                └───br
│                    └───com
│                         └───simplifiedpicpay
│                                     │ SimplifiedpicpayApplication.java
│                                     └───config
│                                     └───controllers
│                                     └───domains
│                                            └───transaction
│                                            └───user
│                                     └───dtos
│                                     └───enums
│                                     └───infra
│                                           └───exceptions
│                                           └───security
│                                     └───repository
│                                     └───services
````

### Requirements

- JDK 17+
- Maven
- Docker

### Project Configuration

1. Clone the repository:
   ```bash
   git clone https://github.com/gb-alves03/simplified-picpay-challenge.git
   cd simplified-picpay-challenge

2. Initialize the database in the Docker Container:
   ````bash
   docker-compose up
   ````
   
3. Compile the project:
   ````bash
   mvn clean install

4. Execute the application:
   ````bash
   mvn spring-boot:run


## Project Architecture
![image](https://github.com/gb-alves03/simplified-picpay-challenge/assets/100585280/5d4555db-d09f-4dbb-b85a-a973cb16519f)

