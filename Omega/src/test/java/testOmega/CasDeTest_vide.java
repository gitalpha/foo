

package testOmega;

import ca.uqam.mgl7361.a2011.omega.framework.annotation.*;

public class CasDeTest_vide
{

        @Setup 
        protected void setUp() {
            System.out.println("avant casDeTestVide");
        }

        @Teardown
        protected void tearDown() {
            System.out.println("apres casDeTestVide");
            System.out.println("---------------------------------");
        } 
        
        
}
