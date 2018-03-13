CC = javac
PANELS = MainPanel.class AddPanel.class ListPanel.class NotePanel.class
BEERS = BeerData.class BeerList.class BeerPresenter.class BeerWindow.class

Run.class: Run.java $(PANELS) $(BEERS)
	$(CC) Run.java

%.class: %.java
	$(CC) $*.java

jar: Run.class $(PANELS) $(BEERS)
	jar cvfm BierProbe.jar MANIFEST.MF *class 

clean:
	rm -rf *.class
	rm -rf *.jar
