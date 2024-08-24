FROM mcr.microsoft.com/mssql/server:2022-latest

COPY ./setup.sql /usr/src/app/setup.sql
COPY ./entrypoint.sh /usr/src/app/entrypoint.sh
ENTRYPOINT ["/usr/src/app/entrypoint.sh"]
