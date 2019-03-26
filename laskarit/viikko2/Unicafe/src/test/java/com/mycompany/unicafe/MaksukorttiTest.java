package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldoPalauttaaOikeanSaldon() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(2500);
        
        assertEquals("saldo: 35.0", kortti.toString());
    }
    
    @Test
    public void maksaminenVahentaaSaldoaOikein() {
        kortti.otaRahaa(750);
        
        assertEquals("saldo: 2.50", kortti.toString());
    }
    
    @Test
    public void maksaminenEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(2000);
        
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalautusarvoOikeinKunMaksaminenOnnistuu() {
        boolean tulos = kortti.otaRahaa(750);
        
        assertTrue(tulos);
    }
    
    @Test
    public void otaRahaaPalautusarvoOikeinKunMaksaminenEiOnnistu() {
        boolean tulos = kortti.otaRahaa(20000);
        
        assertFalse(tulos);
    }
    
    
}
