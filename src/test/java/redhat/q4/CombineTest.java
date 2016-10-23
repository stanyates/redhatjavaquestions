package redhat.q4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class CombineTest
{
    @Test
    public void test1()
    {
        TreeSet< String > s1 = new TreeSet<>();
        TreeSet< String > s2 = new TreeSet<>();
        
        s1.add( "aaaa" );
        s1.add( "bbbb" );
        s1.add( "dddd" );
        s1.add( "zzzz" );
        
        s2.add( "cccc" );
        s2.add( "dddd" );
        s2.add( "eeee" );
        s2.add( "yyyy" );
        
        Combine< String > combine = new Combine<>( s1.iterator(), s2.iterator() );
        
        ArrayList< String > result = new ArrayList<>();
        
        while ( combine.hasNext() )
        {
            result.add( combine.next() );
        }
        
        ArrayList< String > expected = new ArrayList<>();
        
        expected.addAll( s1 );
        expected.addAll( s2 );
        
        Collections.sort( expected );
        
        System.out.println( "expected: " + expected );
        System.out.println( "result: " + result );
        
        Assert.assertEquals( expected, result );
    }
}
