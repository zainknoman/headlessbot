# HeadlessBot

Java 21 + Spring Boot + Playwright browser automation service.

## Stack
- Java 21
- Spring Boot
- Maven
- Microsoft Playwright for Java
- Chromium

## Build
```powershell
mvn clean package
```

Install Playwright browsers if required:
```powershell
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

## Configuration
Edit `src/main/resources/application.yml` and set the authorized target URL and timeout. Update the selectors in `FormSubmissionService` to match the target form.

## API
`POST /api/forms/submit`

Example PowerShell request:
```powershell
Invoke-RestMethod `
  -Uri "http://localhost:8080/api/forms/submit" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"name":"John Doe","email":"john@example.com","category":"general"}'
```

## Authentication and CAPTCHA
Do not hardcode passwords or commit authentication state. Use an authorized session strategy and secure configuration.

CAPTCHA remains human-in-the-loop. This project does not bypass CAPTCHA or other anti-bot controls.

## Docker
```powershell
mvn clean package
docker build -t headlessbot .
docker run --rm -p 8080:8080 headlessbot
```
