# # Use a imagem oficial do OpenJDK como base
# FROM eclipse-temurin:17-jdk
#
# # Cria um diretório dentro do container para o app
# WORKDIR /app
#
# # Copia os arquivos do projeto para dentro do container
# COPY build/libs/vehicle-service-*.jar app.jar
#
# # Expõe a porta que a aplicação usa
# EXPOSE 8081
#
# # Comando para rodar o jar
# ENTRYPOINT ["java", "-jar", "app.jar"]


# Etapa 1: Builder - build da aplicação usando Gradle
FROM gradle:8.3-jdk17 AS build

# Copia os arquivos necessários para o build
COPY --chown=gradle:gradle . /home/gradle/project

WORKDIR /home/gradle/project

# Build da aplicação (gera o .jar na pasta build/libs)
RUN gradle clean build -x test --no-daemon

# Etapa 2: Runner - imagem enxuta para rodar a aplicação
FROM eclipse-temurin:17-jdk

# Cria diretório da aplicação dentro do container
WORKDIR /app

# Copia o .jar gerado da etapa de build para a imagem final
COPY --from=build /home/gradle/project/build/libs/vehicle-service-*.jar app.jar

# Expõe a porta que a aplicação utiliza
EXPOSE 8081

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
