# Vietnam Sea Monorepo Main Backend  

## Overview  
This is the main backend service for the **Vietnam Sea (VNS)** monorepo project. It provides essential APIs and functionality to support the ecosystem of VNS, including services for user management, booking, payments, and more.  

## Contributors  
- [@AnataAria](https://github.com/AnataAria)  
- [@haiha0402](https://github.com/haiha0402)  

## Features  
- User authentication and authorization  
- Tour booking and payment management  
- Feedback and review handling  
- Integration with external services (e.g., Consul, MongoDB)  

## Requirements  
Before starting development, ensure you have the following installed and running:  
- [Consul](https://www.consul.io/) (Service discovery and configuration)  
- [MongoDB](https://www.mongodb.com/) (Database use for manage account)  
- [Postgres](https://www.postgresql.org) (Database use for others)
- [Java](https://www.oracle.com/java/technologies/downloads) (Runtime environment, Requite jdk 21 > x > 17)   
- [Email Server]()
## Development Setup  

### 1. Clone the Repository  
```bash  
git clone [https://github.com/vietnam-sea/sea-coffee-service.git]  
cd [sea-coffee-service]
```
### 2. Build The Application (Remember your dev device should have maven installed or using maven wrapper)
```bash
mvn clean install
```
### 3. Insert The Value From .env.examples into your environment
Steps
- Prepare all env values that defined in .env.examples
- Add this env to env of your IDE
### 4. Enjoy

