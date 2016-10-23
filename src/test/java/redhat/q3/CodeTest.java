package redhat.q3;

import org.junit.Assert;
import org.junit.Test;

public class CodeTest
{
    @Test
    public void test1()
    {
        String[] items = { "aaaa", "bbbb", "cccc", "dddd", "eeee" };
        
        Assert.assertEquals( "aaaabbbbccccddddeeee", new Code().addStringItems( items, false ) );
    }
    
    @Test
    public void test2()
    {
        String[] items = { "aaaa", "bbbb", "cccc", "dddd", "eeee" };
        
        Assert.assertEquals( "AAAABBBBCCCCDDDDEEEE", new Code().addStringItems( items, true ) );
    }
}
