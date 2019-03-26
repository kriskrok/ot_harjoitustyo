package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(10000);
    }
    
    @Test
    public void luotuKassapaateOlemassa() {
        assertTrue(kassapaate != null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void uudenKassapaatteenMyytyjenEdullistenLounaidenMaaraOikein() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void uudenKassapaatteenMyytyjenMaukkaidenLounaidenMaaraOikein() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    /*Edullinen käteistestit*/ //edullisesti 240
    @Test
    public void edullisenKateisostoPalauttaaVaihtorahanOikeinKunMaksuRiittava() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
    }
    
    @Test
    public void edullisenKateisostoKasvattaaKassanRahamaaraaOikein() {
        kassapaate.syoEdullisesti(250);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenKateisostoKasvattaaMyytyjenEdullistenLounaidenMaaraaOikein() {
        kassapaate.syoEdullisesti(250);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenKateisostoPalauttaaVaihtorahanOikeinKunMaksuEiRiita() {
        assertEquals(150, kassapaate.syoEdullisesti(150));
    }
    
    @Test
    public void edullisenKateisostoEiKasvataKassanRahamaaraaKunMaksuEiRiita() {
        kassapaate.syoEdullisesti(150);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenKateisostoEiKasvataMyytyjenMaukkaudenLounaidenMaaraaKunMaksuEiRiita() {
        kassapaate.syoEdullisesti(150);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    /*Maukas käteistestit*/
    @Test
    public void maukkaanKateisostoPalauttaaVaihtorahanOikeinKunMaksuRiittava() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void maukkaanKateisostoKasvattaaKassanRahamaaraaOikein() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanKateisostoKasvattaaMyytyjenMaukkaidenLounaidenMaaraaOikein() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanKateisostoPalauttaaVaihtorahanOikeinKunMaksuEiRiita() {
        assertEquals(250, kassapaate.syoMaukkaasti(250));
    }
    
    @Test
    public void maukkaanKateisostoEiKasvataKassanRahamaaraaKunMaksuEiRiita() {
        kassapaate.syoMaukkaasti(250);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanKateisostoEiKasvataMyytyjenMaukkaudenLounaidenMaaraaKunMaksuEiRiita() {
        kassapaate.syoMaukkaasti(250);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    /*Edullinen korttitestit*/
    @Test
    public void edullisenKorttiostoEiKasvataKassanRahamaaraa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenKorttiostoKasvattaaMyytyjenEdullistenLounaidenMaaraaOikein() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenKorttiostoPalautusArvoOikeinKunMaksuOnnistuu() {
        boolean arvo = kassapaate.syoEdullisesti(kortti);
        assertTrue(arvo);
    }
    
    @Test
    public void edullisenKorttiostoEiKasvataMyytyjenEdullistenLounaidenMaaraaKunMaksuEiRiita() {
        kortti.otaRahaa(9900);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenKorttiostoPalautusarvoOikeinKunMaksuEiOnnistu() {
        kortti.otaRahaa(9900);
        boolean arvo = kassapaate.syoEdullisesti(kortti);
        assertFalse(arvo);
    }
    /*Maukas korttitestit*/
    
    @Test
    public void maukkaanKorttiostoEiKasvataKassanRahamaaraa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanKorttiostoKasvattaaMyytyjenMaukkaidenLounaidenMaaraaOikein() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanKorttiostoPalautusArvoOikeinKunMaksuOnnistuu() {
        boolean arvo = kassapaate.syoMaukkaasti(kortti);
        assertTrue(arvo);
    }
    
    @Test
    public void maukkaanKorttiostoEiKasvataMyytyjenmaukkaanLounaidenMaaraaKunMaksuEiRiita() {
        kortti.otaRahaa(9700);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanKorttiostoPalautusarvoOikeinKunMaksuEiOnnistu() {
        kortti.otaRahaa(9700);
        boolean arvo = kassapaate.syoMaukkaasti(kortti);
        assertFalse(arvo);
    }
    
    @Test
    public void rahanLataaminenKortilleKasvattaaKassanSaldoaOikein() {
        kassapaate.lataaRahaaKortille(kortti, 25000);
        assertEquals(100000 + 25000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void rahanLataaminenKortilleEikasvataSaldoaKunLataaminenEiOnnistu() {
        kassapaate.lataaRahaaKortille(kortti, -25000);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

}
