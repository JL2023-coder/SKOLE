# Rapport – innlevering 1
**Team:** *Nintenbros* - *Sivatharshan Kisokumar, Rami Al-Salman, Jonas Lilletvedt, Jørgen Haugland-Gjertsen*

* Lenkte til google dokumentet: https://docs.google.com/document/d/1TnUwjE-fSS2XODMOGqWLFbq1qdGESWM95zFIxp0f4Dk/edit?usp=sharing

## A1
### INF112 Project – *On the Run - Freedom*

* Team: *Nintenbros* (Gruppe 6): *Sivatharshan Kisokumar, Rami Al-Salman, Jonas Lilletvedt, Jørgen Haugland-Gjertsen*
* Lenke til GitLab/Trello/etc. https://git.app.uib.no/inf112/25v/proj/nintenbros, https://trello.com/b/kRWDLJFY

#### Om spillet
* X-mannen er på rømmen! Han må komme seg til Oslo for å unngå å bli fengslet. Hjelp X-mannen med å ikke bli tatt av purken. Hopp over hindringene som du møter på vei til frihet!

#### Kjøring
* Kompileres med `mvn package`.
* Kjøres med TBD
* Krever Java 21 eller senere

#### Kjente feil
* TBD

#### Credits
* TBD

## Prosjektstillinger
* Team Lead (Rami) - Overordende ansvar for prosjektet og sørger for at alle frister er møtt
* Kundekontakt (Jørgen) - Har fokus på brukeropplevelsen og design av spillet
* Rapportansvarlig (Sivatharshan) - Ansvarlig for kontinuerlig oppdatering av rapporten
* Tekniskansvarlig (Jonas)  -  Har ansvar for å opprettholde god kodestil og struktur på prosjektet


## A2
### Platformspill
#### Spillfigur som kan styres – gå til høyre/venstre, hoppe oppover
* Skrollende effekt (spilleren forblir på samme sted på skjermen og resten av verdenen beveger seg)
* Spilleren kan være i stand til å dobbel hoppe
#### Todimensjonal verden:
* Fixed map som beveger når spilleren flytter på seg
* Grid med tiles
#### Kollisjon
* Tile kollisjon: Spilleren er ikke i stand til å gå gjennom vegger, gulvet eller å hoppe gjennom taket.
* kollisjon med andre objekter -> fiender, bevegende plattformer (hører ikke til grid)
#### Andre aspekter
* Spilleren faller dersom den ikke står på noe (gravitasjon)
* Hindringer som faller fra himmelen, hull i banen, spikes
* Checkpoints hvis man faller ut av banen
* Spilleren kan samle poeng ved å plukke opp ting
* Utfordringen i spillet er gjerne en eller flere av: må prøve å ikke treffe hindringer, unngå møte på purken og hoppe over hull i banen.

## A3
### Prosjektmetodikk
* Testing og parprogrammering

### Metoder og Prosess:
* Møter og hyppighet av dem
    * Møter minst en gang i uken (onsdag 10-12), avtaler onsdag om man trenger et møte før onsdag uken etter. 
* Kommunikasjon mellom møter
    * gjennom discord og snapchat
* Arbeidsfordeling
    * vi fordeler ut forskjellige deler av programmet som må enten utvikles, testes eller fikses
    * Når noen er ferdige med en arbeidsoppgave, bør en annen i gruppen se gjennom endringene for å dobbeltsjekke at det fungerer som ønsket. 
* Oppfølging av arbeid
    * Trello: https://trello.com/b/kRWDLJFY 
* Deling og oppbevaring av felles dokumenter, diagram og kodebase
    * Bruker felles Google Docs dokument for å oppbevare alt av deskriptiv informasjon, potensielt lage en “change log”
    * Bruker Draw.io for å lage klassediagrammer
* Debugging
* *Dersom man møter på noen feil i koden, vil en annen fra gruppen bidra til debuggingen av koden. Unngå å bruke mye tid på debugging av egen kode

### Organisering av Prosjektet
* Vi kommer til å møtes hver onsdag for å løse problemer som fremkommer og fordele oppgaver. Om det er behov for flere møter vil disse bli avtalt onsdag eller via snapchat/discord. 




### Forventet produkt:
* Overordnede målet for applikasjonen - et velfungerende plattformspill som har en viss grad av kompleksitet gjennom hindringer og forskjellige maps.


### MVP
1. vise banen (tiles i ett grid)
2. vise spiller
3. bevege spiller (piltaster for høyre og venstre)
4. kollisjon med tiles
5. spilleren faller når den ikke står på tiles som er bakke
6. spilleren kan hoppe
7. spilleren dør om den faller inni hullet i banen
8. dersom spilleren når slutten av banen blir man tatt til neste bane
9. vise fiender
10. fiende beveger seg og interagerer med verdenen (terreng og spiller)
11. spiller kan dø av fiender
12. start/restart skjerm

### Brukerhistorie
* Som spiller vil jeg kunne skille mellom hva som er banen og "Player1".
* Som spiller vil jeg kunne bevege player for fullføre banen
* Som spiller vil jeg at player karakteren skal interagere med banen på en logisk måte (gravitasjon og kollisjon)
* Som spiller vil jeg være i stand til å hoppe slik at jeg kan komme meg over hindringer.
* Som spiller vil jeg at karakteren dør dersom den faller utenfor banen slik at jeg må navigere banen forsiktig
* Som spiller vil jeg se fiender slik at jeg kan unngå dem
* Som spiller vil jeg at fiender beveger seg og interagere med verdenen slik at de oppfører seg “realistisk” (f.eks: ikke faller gjennom bakken)
* Som spiller vil jeg at karakteren kan dø av fiendene slik at de blir en utfordring for å fullføre banene
* Som spiller vil jeg vite hvilken taster som funker og hva de gjør. (En help menu)
* Som spiller vil jeg ha en konfirmasjon på om jeg har nådd en checkpoint. 
* Som spiller vil jeg vite om jeg har fullført et nivå. 
* Som spiller vil jeg kunne ta meg videre til neste nivå dersom banen er ferdig
* Som spiller vil jeg vite hvor mange ganger en kan dø før game over? 
* Hva er min “final” score. 
* Identifisere gjennom lyden at det er en hindring på banen
* Som spiller vil jeg kunne ta skade av fiender 


## A4
* Testet noen av funksjonene i HelloWorld.java, lærte hvordan man roterer bilder i libgdx. Testet å lage rektangler. Testet input fra mus og tastatur. Testet hvordan man kun tegner en del av et bilde slik at vi kan få til animasjon gjennom spritesheets. Ble kjent med libgdx og mappestruktur. 

## A5
### Suksesser:
* Vi samarbeider godt, alle kan si sin mening, vi har laget et system slik at man ikke pusher kode og “ødelegger” for andre. Diskutert at vi skal bruke trello, snap og discord som måter å kommunisere på. Vi har laget en plan om hvordan sluttproduktet skal være.  

### Utfordringer:
* Kompilere koden for Mac PCer
* mer kontinuerlig jobbing

### Tiltak for forbedre utfordringene:
* Vi fikset problemet med å kompilere koden på mac ved å gjøre en liten endring i Main funksjonen
* Kontinuerlig jobbing kan bli lettere å organisere når man faktisk begynner på prosjektet i tillegg til at vi nå har opprettet og satt oss inn i systemene vi ønsker å bruke til å organisere arbeidet.










