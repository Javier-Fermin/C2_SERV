/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author javie
 */
@Singleton
@Startup
public class SymmetricCryptographyInitialier {
    
   @PostConstruct
   public void onStartup() {
        SymmetricCryptography symCryp = new SymmetricCryptography();
        symCryp.cifrarTexto("uwu", "javierferminardila64@gmail.com/hfvr uohc niql smvm");
   }
}
