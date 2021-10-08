# SQL

## Настройки для запуска

Сначала запускаем контейнер:
```
docker-compose -f .\docker-compose.yml up
```
Потом запускаем SUT:
```
java -jar ./artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3308/app
```