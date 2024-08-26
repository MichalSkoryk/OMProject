FROM mcr.microsoft.com/mssql/server:2022-latest

WORKDIR /usr/src/app

COPY ./setup.sql setup.sql
COPY ./entrypoint.sh entrypoint.sh

RUN sed -i 's/\r$//' entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]