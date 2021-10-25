set DB_URL=jdbc:mysql://localhost:3306/app
set DB_USER=app
set DB_PASS=pass
docker exec -i mysqldb bash -c "mysql -u %DB_USER% %DB_USER% --password=%DB_PASS%" < data_clean.sql
java -jar ./artifacts/app-deadline.jar