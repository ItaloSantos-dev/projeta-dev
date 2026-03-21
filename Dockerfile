#Base da aplicação (java)
FROM eclipse-temurin:21

#Info sobre o fornecedor da imagem
LABEL authors="ItaloUser"

#Cria pasta de trabalho
WORKDIR /app

#Copia meu .jar local para pasta de trabalho criada
COPY target/projeta.dev-0.0.1-SNAPSHOT.jar /app/projeta.dev.jar

#Comando que vai executar quando der run
ENTRYPOINT ["java", "-jar", "projeta.dev.jar"]