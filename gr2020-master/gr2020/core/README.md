# Gruppe 20: Kjernemodulen

Dette prosjektet inneholder domenelaget og persistenslaget for [Moodtracker](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2020/gr2020/) laget av Gruppe 20.

# Domenelaget
Domenelaget inneholder alle klasser og logikk knyttet til dataene som applikasjonen handler om og håndterer.
Applikasjonen vår handler om å loggføre registreringene som brukeren gjør hver dag. 
Brukeren legger igjen en rate på hvordan dagen har vært og kan gå tilbake for å se på loggen senere.
Domenelaget finnes i [core-mappen](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2020/gr2020/-/tree/master/gr2020%2Fcore%2Fsrc%2Fmain%2Fjava%2Fmoodtracker%2Fcore)

# Persistenslaget
Dette laget inneholder alle klasser og logikk for lagring av dataene i domenelaget. Laget implementerer fillagring med JSON syntaks.
Persistenslaget finnes i [json-mappen](https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2020/gr2020/-/tree/master/gr2020%2Fcore%2Fsrc%2Fmain%2Fjava%2Fmoodtracker%2Fjson)

I applikasjonen vår lagres dataene til brukeren hver gang de lagrer en ny registrering. I tillegg skal også applikasjonen utvides til at brukeren skal kunne registrere humøret sitt
og redigere/slette enkelte registreringer. Dokumentmetafor med filorienterte aksjoner (open, save, save as, og delete) passer best for applikasjonen slik den er nå, i tillegg til at det vil være en bærekrafting lagringsmetode for eventuelle utvidelser. 
I motsetning til dokumentmetafor er implisitt lagring med en autosave-funksjon ikke nødvendig.
