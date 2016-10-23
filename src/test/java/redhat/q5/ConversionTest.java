package redhat.q5;

import org.junit.Assert;
import org.junit.Test;

public class ConversionTest
{
    @Test
    public void test1()
    {
        String input = "1";
        String result = new Conversion().base10to2( input );
        Assert.assertEquals( "1", result );
    }
    
    @Test
    public void test2()
    {
        String input = "1";
        String result = new Conversion().negBase2( input );
        Assert.assertEquals( "11111111111111111111111111111111", result );
    }
}
