# Как запускать тесты
*__Шаги для запуска тестов:__*
1. -Запустить Docker Desktop_;
2. _Запустить файл docker-compose.yml_;
3. _Запустить файл согласно ОС (см. ниже)_.

## Windows
Для упрощения запуска, SUT следует запускать через .bat файл *__app-deadline.bat__*. В нём прописаны переменные окружения. 
Так же в нем прописана команда для автоматической очистки БД, т.к. для успешного запуска SUT записывает в БД демо-данные.

*__Окружение, необходимое для запуска тестов__*:
* _Windows 10_
* _[AdoptOpenJDK 11](https://adoptopenjdk.net/)_
* _[Docker Desktop](https://www.docker.com/products/docker-desktop)_
* _IntelliJ IDEA или аналогичная IDE для Java_


## Unix-системы(Linux/macOS)
Запуск SUT на Unix-системах осуществляется посредством файла *__app-deadline.sh__*. В нем *__аналогичные__* файлу для Windows параметры окружения.

*__Окружение, необходимое для запуска тестов на Linux__*:
* _Дистрибутив Linux (CentOS, Ubuntu, Fedora, AWS, Azure, Debian)_
* _[AdoptOpenJDK 11](https://adoptopenjdk.net/)_
* [Docker Engine for Linux](https://hub.docker.com/search?offering=community&operating_system=linux&q=&type=edition)
* _IntelliJ IDEA или аналогичная IDE для Java_
  
*__Окружение, необходимое для запуска тестов на macOS__*:
* _macOS on Intel Chip or Apple Silicon_
* _[AdoptOpenJDK 11](https://adoptopenjdk.net/)_
* _[Docker Desktop for macOS](https://www.docker.com/products/docker-desktop)_
* _IntelliJ IDEA или аналогичная IDE для Java_
