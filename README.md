# Ohjelmistotekniikka, harjoitustyö :atom_symbol:
## Tehtävät

## Dokumentaatio
* [tuntikirjanpito](https://github.com/kriskrok/ot_harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)
* [Vaatimusmäärittely](https://github.com/kriskrok/ot_harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Arkkitehtuurikuvaus](https://github.com/kriskrok/ot_harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Itemexchange-1.0-SNAPSHOT.jar_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/kriskrok/ot_harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

![Image of Yaktocat](https://octodex.github.com/images/yaktocat.png)

> I thought a thought that i thought i had thought but the thought that i had thought wasnt the thought that i had thought i had thought so maybe if i had thought the thought that i thought i thought i wouldn't of thought so much.
