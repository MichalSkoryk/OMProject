#!/bin/bash
# Uruchomienie MSSQL w tle
/opt/mssql/bin/sqlservr &

# Poczekanie na uruchomienie MSSQL
sleep 30

# Wykonanie skryptu SQL
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "$SA_PASSWORD" -i /usr/src/app/setup.sql

# Utrzymanie kontenera w stanie aktywnym
tail -f /dev/null

echo "Done"