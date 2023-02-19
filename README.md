### Clone the repository using the command:
```bash
git clone https://github.com/M1k3Dm/ds_vehicle-transfer-app.git
``` 
### Remove database data:
```bash
docker volume rm pgdata14
``` 
### Run a postgres database with default users using docker:
```bash
docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=transfer-db --net=host -v "$(pwd)"/assets/db:/docker-entrypoint-initdb.d -v pgdata14:/var/lib/postgresql/data -d postgres:14
``` 
### Connect to database using psql:
```bash
psql -h localhost -p 5432 -d transfer-db -U postgres -W
```

