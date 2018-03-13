CC = "javac"
#DEP = $(*.class)


Run.class: Run.java Beer*.class *Panel.class
	$(CC) Run.java

%.class: %.java
	$(CC) $*.java

jar: *.class
	jar cvfm BierProbe.jar MANIFEST.MF *class 

clean:
	rm -rf *.class
	rm -rf *.jar
