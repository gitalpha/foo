
package testOmega;


import ca.uqam.mgl7361.a2011.omega.framework.annotation.*;
import ca.uqam.mgl7361.a2011.omega.framework.core.Assertion;

//  Voir http://martinfowler.com/bliki/JunitNewInstance.html

public class CasDeTest_Exemple_Fowler {
    
    
    java.util.List list = new java.util.ArrayList();

    @Test
    public void testFirst() {
        list.add("one");

        // Assert égalité
        Assertion.AssertExpectedEqualsActual(1, list.size());
    }

    @Test
    public void testSecond() {

        // Assert égalité
        Assertion.AssertExpectedEqualsActual(0, list.size());
    }   
    
    
}
