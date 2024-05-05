FROM liquibase/liquibase:4.18

ENV URL="jdbc:postgresql://localhost:5435/postgres"
ENV USERNAME="postgres"
ENV PASSWORD="postgres"
ENV COMMAND="update"
ENV SCHEMA="public"
ENV LOG_LEVEL="info"
WORKDIR /changelog

COPY migration /liquibase/changelog

CMD ["sh", "-c", "docker-entrypoint.sh --url=${URL} --liquibase-schema-name=${SCHEMA} --log-level=${LOG_LEVEL} --username=${USERNAME} --password=${PASSWORD} --classpath=/liquibase/changelog --changeLogFile=changelog.json ${COMMAND}"]