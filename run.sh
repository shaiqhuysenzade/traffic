default:
	cat ./run.sh
dist: keystore
	./mvnw clean package

	java -jar target/dna-0.0.1-SNAPSHOT.jar