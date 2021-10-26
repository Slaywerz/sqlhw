DB_URL='jdbc:mysql://localhost:3306/app';
DB_USER='app';
DB_PASS='pass';
CMD_LINE="mysql -u ${DB_USER} $DB_USER --password=$DB_PASS";
docker exec -it mysqldb bash -c "$CMD_LINE" < ./data_clean.sql;
java -jar ./artifacts/app-deadline.jar