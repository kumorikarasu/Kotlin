
run:
	./gradlew run -t

initial:
	cqlsh -f src/main/resources/cql/initial.cql
