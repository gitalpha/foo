
package testOmega;

import ca.uqam.mgl7361.a2011.omega.framework.annotation.*;
import ca.uqam.mgl7361.a2011.omega.framework.core.Assertion;

/**
 * Corbertura ne peut pas fonctionner avec des "inner-classes"
 * @version 15 octobre 2011
 */
public class CasDeTest_complexe {

    @Setup
    protected void setUp()
    {
        System.out.println( "avant casDeTestComplexe" );
    }


    @Teardown
    protected void tearDown()
    {
        System.out.println( "apres casDeTestComplexe" );
        System.out.println( "---------------------------------" );
    }


    @Test
    public void test_un_egale_un_entier() throws Exception {
        
        int i = 1, j = 1;
        
        Assertion.AssertExpectedEqualsActual(i, j);
            
        System.out.println("test_un_egale_un_entier() ");
    }
    
    @Test
    public void test_un_egale_un_entier2() throws Exception
    {
        int i = 1, j = 1;
        Assertion.AssertExpectedEqualsActual(i, j);
        System.out.println("test_un_egale_un_entier2() ");

    }

    @Test
    public void test_un_egale_un_chaine() throws Exception
    {
        String i = "1";
        String j = "1";
        Assertion.AssertExpectedEqualsActual(i, j);
        System.out.println("test_un_egale_un_chaine() ");

    }

    @Test
    public void test_types_differents() throws Exception
    {
        Integer i = 1;
        String j = "1";
        //Assertion.AssertExpectedEqualsActual(i, j);
        //System.out.println("test_types_differents() ");

    }


}
