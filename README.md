# Kulutusseuranta

Tämä ohjelmointiprojekti on toteutettu Backend ohjelmointi-kurssin lopputyönä Haaga-Helia ammattikorkeakoulussa. Työn aiheena on kulutusseurantajärjestelmä, jossa käyttäjä
voi tarkastella omaa kulutustaan ja kulujen jakautumista eri kategorioihin.

## Toiminnallisuudet
- Käyttäjien hallinta ja todennus: rekisteröinti- ja kirjautumissivu.
- Peruskäyttäjä voi lisätä kuluja ja nähdä lisäämänsä kulut.
- Admin-käyttäjä voi lisätä, poistaa ja muokata kuluja sekä nähdä lisäämänsä kulut (CRUD).
- Käyttäjän tiedot tallennetaan ulkoiseen tietokantaan (PostgreSQL).
- Kulut on esitetty sekä taulukossa että ympyrädiagrammissa, josta käyttäjä voi tarkastella kulujen
  jakautumista kategorioittain.
- Jokainen käyttäjä voi nähdä vain omat tietonsa.

## Käytetyt teknologiat
- Spring Boot
- PostgreSQL
- Java
- HTML
- Thymeleaf

## Kuvia

![Rekisteröidy](images/rekisteroidy.PNG)
<p></p>
Käyttäjän tulee rekisteröityä voidakseen lisätä kuluja järjestelmään. Jokaisella käyttäjällä on oma uniikki käyttäjätunnus ja salasana. Käyttäjä saa
ilmoituksen, mikäli käyttäjätunnus on jo varattu.

![Rekisteröidy](images/kirjaudu.PNG)
<p></p>


![Rekisteröidy](images/home.PNG)
<p></p>


![Rekisteröidy](images/kuluttajakulut.PNG)
<p></p>


![Rekisteröidy](images/lisätään.PNG)
<p></p>


![Rekisteröidy](images/admininkulut.PNG)
<p></p>


![Rekisteröidy](images/muokkaa.PNG)
<p></p>





