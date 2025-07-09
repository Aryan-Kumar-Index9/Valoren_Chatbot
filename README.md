# Valoren Chatbot

This project is a Spring Boot application for a WhatsApp chatbot. Configuration values are loaded from environment variables using the [dotenv-java](https://github.com/cdimascio/dotenv-java) library.

## Required environment variables

Set the following variables in your shell or in a `.env` file at the project root:

- `DB_USERNAME` – database username
- `DB_PASSWORD` – database password
- `BOT_NUMBER` – WhatsApp bot phone number
- `PEPIPOST_API_KEY` – API key for Netcore
- `PEPIPOST_BASE_URL` – base URL for sending messages
- `TEMPLATE_BASE_URL` – base URL for template operations

## Running the application

1. Ensure PostgreSQL is running and accessible via the URL configured in `src/main/resources/application.properties`.
2. Provide the environment variables above.
3. Start the application with Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will read values from the environment at startup.
