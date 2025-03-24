# Rapport – innlevering 2
**Team:** *Nintenbros* - *Sivatharshan Kisokumar, Rami Al-Salman, Jonas Lilletvedt, Jørgen Haugland-Gjertsen*
* Lenkte til google dokumentet: https://docs.google.com/document/d/1TnUwjE-fSS2XODMOGqWLFbq1qdGESWM95zFIxp0f4Dk/edit?usp=sharing
* Lenke til GitLab/Trello/etc. https://git.app.uib.no/inf112/25v/proj/nintenbros, https://trello.com/b/kRWDLJFY

# DEL B
## Ukentlig progresjon og møtereferat
* Møte 1
    * Diskuterte hvilken rolle hver person skal ha
    * Ble enig om å ha en hybrid løsning hvor vi jobber to og to med ulike funksjoner av spillet; Jørgen og Sivatharshan jobber med å lage karakteren, mens Rami og Jonas bygger en map. Legger grunnlaget for resten av spillet her, før vi på iterativtvis legger til funksjoner i spillet.
    * Ideen var å bruke en tile basert løsning på mapen hvor vi lager en egen Grid klasse 
    * Jobbet i to ulike branches hvor klassen som laget karakteren og view klassen ble laget i en og grid klassen i den andre

* Møte 2 
    * Målet var å få etablert et solid fundament for spillet hvor det er en figur som beveger på seg både sidelengs og vertikalt. 
    * Vi var fornøyd med implementeringen av grid klassen, hvor vi benytter oss av en rutenett for å lage spillet. Startet med å heller bruke en annen metode hvor vi brukte lister for å lage mappen i spillet, men innså raskt at å lage et grid system var lettere og mer fleksibil til utviklingen av ulike aspekter i spillet. 
    * Grid ble ferdigstilt og MVP begynner å ta form og vi legger iterativt til ulike funksjoner
    * Merget de to bransjene og justerte på klasser som overlappet som feks view og model
    * Neste steg vil være å implementere kollisjoner og hindringer på mappen
    * Vi kommer også til å fokusere på hvordan bevegelsen av mappen løses. Foreløpig har vi planlagt å tegne nye maps og “render” mapen hver gang noe nytt blir vist på skjermen. Karakteren vil dermed stå stille mens bakgrunnen endrer seg

* Teamroller
    * Foreløpig har teamrollene vi tidligere bestemte funket bra. For å lage grunnlaget for spillet i starten av prosjektet, måtte vi se bort ifra teamrollene og kun fokusere på å designe strukturen på spillet og hvilken klasser vi skulle ha med. Hittil så har vi fått til et solid grunnlag av spillet med de fundamentale funksjonene. Fra og med nå, vil det da være naturlig å forholde oss til de ulike ansvarsområdene som ble delegert tidligere. Hittil har rollene dermed vært ganske flytende hvor alle jobbet med både utviklingen og rapportskrivningen.. Fra og med nå, vil gradvis vi fokusere på rollene som ble bestemt tidligere for å produsere et best mulig produkt. 


* Prosjektmetodikk
    * Hittil har de ulike valgene vi har tatt med tanke på prosjektmetodikk funket veldig bra. Tanken var å forholde oss til parprogrammering hvor to stykker fokuserer på mappen mens de to andre fokuserte på selve karakteren. Dette syntes vi funket bra siden vi allerede hadde laget en god slagplan tidligere på hvordan vi ville forme spillet. Noe vi kunne spesifisert tidligere var hvordan vi kombinerte mappen og karakteren mer effektivt. Siden begge parter jobbet i forskjellige branches ble det lite oppfølging gjennom en viktig del av programmeringen. Dette kunne vi unngått ved å kommunisere litt bedre før vi delte arbeidsoppgaver. Heldigvis, var det ikke vanskelig å slå sammen begge branches siden alle hadde kjennskap til gridcell oppsettet som vi har tidligere benyttet i andre fag. 

* Gruppedynamikk
    * Gruppedynamikken har vært meget bra. Det er god kommunikasjon gjennom flere kanaler (discord og snapchat) og arbeidsoppgaver er fordelt god tid i forveien. Vi har benyttet oss av Trello appen for å ha oversikt og status over de ulike arbeidsoppgavene. Hittil så har det vært lite uenigheter i arbeidsmetodikken og det kan forklares av god disponering av arbeidsoppgaver i Trello og tett oppfølgning av team lead som sørger for at vi opprettholder progresjon i prosjektet. I tillegg til å møtes ukentlig på onsdager, har vi ofte digitale møter for å oppklare aspekter ved prosjektet vi er usikre for å sørge for at alle er på samme side. Alle er også veldig lett tilgjengelig på de ulike kommunikasjonskanalene som gjør det enklere

* Forbredringer
    * Noe vi kan forbedre på er å holde Trello mer oppdatert for å få klarere status på prosjektet. Hittil har vi ofte glemt å oppdatere Trello siden kommunikasjonen går fortest ofte på Discord eller Snapchat. Allikevel, har vi merket at slike kommunikasjonskanaler ikke fører til strukturert arbeidsfordeling og vi mister da oversikten over arbeidsoppgavene iblant. Fremover vil vi fokusere på å holde Trello stadig oppdatert for å ha det som et referansepunkt for alle medlemmer. Dermed kan vi minimere kommunikasjon gjennom Discord og kan alltid henvende oss til Trello appen dersom vi trenger statusoppdatering på de ulike arbeidsoppgavene. Dette “problemet” har på ingen måter ført til svekkelse i prosjektstrukturen, men det er noe vi kan forbedre for å organisere prosjektet og få bedre flyt i kommunikasjonen

## Krav og spesifikasjon 
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

* Hittil har vi klart å lage grunnlaget for spillet, hvor vi har klart å vise spilleren på et grid på skjermen. Spilleren kan også bevege på seg horisontalt i tillegg til å hoppe med gravitasjon. Vi kan også laste ned mappen som csv filer som kan vises på skjermen. Ved å prioritere disse punktene har vi laget et godt grunnlag for å iterativtvis legge til nye funksjoner, feks. kollisjoner, hindringer og hoppe vertikalt på plattformer. 



### Bugs
* I punkt 3 (bevege spiller (piltaster for høyre og venstre)) opplever vi at når karakteren går utafor banen så kræsjer/avsluttes spillet. I denne delen av prosjektet er dette ikke et stort problem siden vi kun prøver å få til de viktige grunnleggende funksjonene på plass. Når vi begynner å se nærmere på kollisjoner vil vi se nærmere på hvordan vi kan fikse dette problemet. Trenger dynamisk grid og map størrelse. 
