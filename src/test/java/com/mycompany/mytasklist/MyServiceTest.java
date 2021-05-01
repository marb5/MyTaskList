package com.mycompany.mytasklist;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
/**
 *
 * @author marcin
 */
public class MyServiceTest {
    //SUT - system under test
    private MyService SUT = new MyService();
    
    @Test
    public void testDemo() throws Exception {
        assertTrue(true);
    }
    
    @Test
    public void testGreeting_null_returnsDefaultValue() throws Exception {
        //given
        String name = null;
        
        //when
        String result = SUT.greeting(name);
        
        //then
        assertEquals("Hello world!", result);
    }
    
    @Test
    public void testGreeting_name_returnsName() throws Exception {
        //given
        String name = "TwojeImie";
        
        //when
        String result = SUT.greeting(name);
        
        //then
        assertEquals("Hello " + name + "!", result);
    }
}
