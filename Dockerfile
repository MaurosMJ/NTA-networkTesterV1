From openjdk:8u181-alpine
WORKDIR /networkTester
COPY . .
CMD ["java", "-jar", "dist/networkTester.jar"]