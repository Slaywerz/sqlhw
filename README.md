#Как запускать тесты

##Windows
Для упрощения запуска, SUT следует запускать через .bat файл *__app-deadline.bat__*. В нём прописаны переменные окружения. 
Так же в нем прописана команда для автоматической очистки БД, т.к. для успешного запуска SUT записывает в БД демо-данные.

*__Окружение, необходимое для запуска тестов__*:
* _Windows 10_
* _[AdoptOpenJDK 11](https://adoptopenjdk.net/)_
* _[Docker Desktop](https://www.docker.com/products/docker-desktop)_
* _IntelliJ IDEA или аналогичная IDE для Java_


##Unix-системы(Linux/macOS)
Запуск SUT на Unix-системах осуществляется посредством файла *_app-deadline.sh_*.

*__Окружение, необходимое для запуска тестов__*:
* _Дистрибутив Linux или macOS_
* _[AdoptOpenJDK 11](https://adoptopenjdk.net/)_
* _[Docker Desktop for macOS](https://www.docker.com/products/docker-desktop)_
* [Docker Engine for Linux](https://hub.docker.com/search?offering=community&operating_system=linux&q=&type=edition)
* _IntelliJ IDEA или аналогичная IDE для Java_