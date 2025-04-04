# INF112 libGDX + Maven template 
Simple skeleton with [libGDX](https://libgdx.com/). 

**Important:** Replace this README with info about *your* project!


# Maven Setup
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File → Import → Maven → Existing Maven Projects* (or *Check out Maven Projects from SCM* to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:
* `src/main/java` – Java source files go here (as usual for Maven) – **IMPORTANT!!** only `.java` files, no data files / assets
* `src/main/resources` – data files go here, for example in an `assets` sub-folder – **IMPORTANT!** put data files here, or they won't get included in the jar file
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files

**TODO:** You should probably edit the `pom.xml` and fill in details such as the project `name` and `artifactId`:


```xml

	< !-- FIXME - set group id -->
	<groupId>inf112.skeleton.app</groupId>
	< !-- FIXME - set artifact name -->
	<artifactId>gdx-app</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	< !-- FIXME - set app name -->
	<name>mvn-app</name>
	< !-- FIXME change it to the project's website -->
	<url>http://www.example.com</url>
```

## Running
You can run the project with Maven using `mvn exec:java`. Change the main class by modifying the `main.class` setting in `pom.xml`:

```
		<main.class>inf112.skeleton.app.Main</main.class>
```

Running the program should open a window with the text “Hello, world!” and an alligator in the lower left corner.  Clicking inside the window should play a *blip* sound. Exit by pressing *Escape* or closing the window.

You may have to compile first, with `mvn compile` – or in a single step, `mvn compile exec:java`.

## Testing
Run unit tests with `mvn test` – unit test files should have `Test` in the file name, e.g., `ExampleTest.java`. This will also generate a [JaCoCo](https://www.jacoco.org/jacoco) code coverage report, which you can find in [target/site/jacoco/index.html](target/site/jacoco/index.html).

Use `mvn verify` to run integration tests, if you have any. This will do everything up to and including `mvn package`, and then run all the tests with `IT` in the name, e.g., `ExampleIT.java`.

## Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

* `target/NAME-VERSION.jar` – your compiled project, packaged in a JAR file
* `target/NAME-VERSION-fat.jar` – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/NAME-VERSION-fat.jar`.


If you have test failures, and *really* need to build a jar anyway, you can skip testing with `mvn -Dmaven.test.skip=true package`.

## Git Setup
If you look at *Settings → Repository* in GitLab, you can protect branches – for example, forbid pushing to the `main` branch so everyone have to use merge requests.

### Template example files
* `src/main/resources/obligator.png` – Ingrid Næss Johansen
* `src/main/resources/blipp.ogg`– Dr. Richard Boulanger et al (CC-BY-3.0)

(You should probably delete these if you don't need them!)

# INF112 Project – *On The Run - Freedom*

* Team: *Nintenbros* (Gruppe 6): *Sivatharshan Kisokumar, Rami Al-Salman, Jonas Lilletvedt, Jørgen Haugland-Gjertsen*
* Lenke til GitLab/Trello/etc. https://git.app.uib.no/inf112/25v/proj/nintenbros, https://trello.com/b/kRWDLJFY

## Om spillet
*X-mannen er på rømmen! Han må komme seg til Oslo for å unngå å bli fengslet. Hjelp X-mannen med å ikke bli tatt av purken. Hopp over hindringene som du møter på vei til frihet!

## Kjøring
* Kompileres med `mvn package`.
* Kjøres med `java -jar target/kurt-mario-1.0-SNAPSHOT-fat.jar`
* Krever Java 21 eller senere

## Kjente feil
TBD 

## Credits
TBD

