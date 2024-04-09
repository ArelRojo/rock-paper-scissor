# Rock Paper Scissor 

This project is based on a Spring boot application. 

### Game rules

 - Rock beats Scissors
 - Scissors beats Paper
 - Paper beats Rock

# Technology used
- Programming language: JAVA 17
- Framework: Spring Boot
- ORM framework: JPA
- In-memory database: H2
- Build tool: Maven

# Modularization 
This project is organized as a multi-module project, where different parts of the functionality are divided into separate modules. 
This modular approach enhances code organization, maintainability, and scalability. Below is an overview of the project's module structure:

[rock-paper-scissor-boot]
This module hosts the Spring Boot Application, including delegates and configuration files (e.g., application.yml in resources).

[rock-paper-scissor-domain]
Here, you'll find domain objects and value objects, such as enums.

[rock-paper-scissor-endpoint]
In this module, you'll discover the OpenAPI contract.

[rock-paper-scissor-repository]
This module contains repositories responsible for the querying and persistence of domain objects.

[rock-paper-scissor-service-impl]
Here, you'll encounter the business logic implementation and various mappers.

[rock-paper-scissor-service]
This module contains various service interfaces.

 Each module contains its own POM file, which serves as the configuration and metadata for that particular module. 
 Additionally, there is a parent POM file that oversees the entire project and coordinates the build process across all modules.

# Comments in code

The codebase adheres to the principles of Clean Code, a set of guidelines and best practices aimed at producing readable, maintainable, and elegant code.
The code styling is heavily influenced by the principles outlined in Robert C. Martin's book "Clean Code." 
In line with that philosophy, the code is self-explanatory and does not require extensive comments to understand its purpose or functionality. 
While comments can sometimes be useful for providing context or explanations, 
is believed that well-named variables, functions, and classes, along with clear and concise code structure, should suffice to convey intent.

As a result, you will find minimal use of comments within our codebase. Instead, we focus on writing code that is inherently understandable and avoids the need for excessive commenting.
 
