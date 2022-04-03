default:
	cat ./vv
dist: keystore
	./mvnw clean package

	java -jar target/dna-0.0.1-SNAPSHOT.jar