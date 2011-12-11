

package testOmega;

import ca.uqam.mgl7361.a2011.omega.framework.annotation.*;
import ca.uqam.mgl7361.a2011.omega.framework.core.Assertion;


public class CasDeTest_simple {
    
    
    @Setup
    public void setUp() {
        System.out.println("avant casDeTestSimple");
    }

    @Teardown
    public void tearDown() {
        System.out.println("apres casDeTestSimple");
        System.out.println("---------------------------------");
    }

    
    @Test
    public void test_un_egale_un_entier() throws Exception {
        
        int i = 1, j = 1;
        
        Assertion.AssertExpectedEqualsActual(i, j);
            
        System.out.println("test_un_egale_un_entier() ");
    }
    
}
