package redhat.q1;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class RangeTest
{
    @Test
    public void test1()
    {
        Range r = new RangeImplem( 100, 200 );
        Assert.assertTrue( r.isIn( 100 ) );
    }
    
    @Test
    public void test2()
    {
        Range r = new RangeImplem( 100, 200 );
        Assert.assertTrue( r.isIn( 200 ) );
    }
    
    @Test
    public void test3()
    {
        Range r = new RangeImplem( 100, 200 );
        Assert.assertTrue( r.isIn( 150 ) );
    }
    
    @Test
    public void test4()
    {
        Range r = new RangeImplem( 100, 200 );
        Assert.assertFalse( r.isIn( 99 ) );
    }
    
    @Test
    public void test5()
    {
        Range r = new RangeImplem( -200, -100 );
        Assert.assertFalse( r.isIn( 201 ) );
    }
    
    @Test
    public void test6()
    {
        Range r = new RangeImplem( -200, -100 );
        Assert.assertTrue( r.isIn( -100 ) );
    }
    
    @Test
    public void test7()
    {
        Range r = new RangeImplem( -200, -100 );
        Assert.assertTrue( r.isIn( -200 ) );
    }
    
    @Test
    public void test8()
    {
        Range r = new RangeImplem( -200, -100 );
        Assert.assertTrue( r.isIn( -150 ) );
    }
    
    @Test
    public void test9()
    {
        Range r = new RangeImplem( -200, -100 );
        Assert.assertFalse( r.isIn( -99 ) );
    }
    
    @Test
    public void test10()
    {
        Range r = new RangeImplem( -200, -100 );
        Assert.assertFalse( r.isIn( -201 ) );
    }
    
    @Test
    public void test11()
    {
        try
        {
            new RangeImplem( 1, 0 );
        }
        catch( IllegalArgumentException ex )
        {
            return;
        }
        
        Assert.fail( "no exception for invalid range" );
    }
    
    @Test
    public void test12()
    {
        int value = new RangeImplem( 100, 200 ).min();
        Assert.assertEquals( value, 100 );
    }
    
    @Test
    public void test13()
    {
        int value = new RangeImplem( 100, 200 ).max();
        Assert.assertEquals( value, 200 );
    }
    
    @Test
    public void test14()
    {
        ArrayList< RangeImplem.RangeElement > expected = new ArrayList<>();
        
        RangeImplem r = new RangeImplem( 10, 20 );
        
        expected.add( new RangeImplem.RangeElement( 10, 20 ) );
        Assert.assertEquals( expected, r.getRangeElements() );
        
        for ( int i = 0; i < 10; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 10; i < 21; i++ )
            Assert.assertTrue( r.isIn( i ) );

        for ( int i = 21; i < 100; i++ )
            Assert.assertFalse( r.isIn( i ) );

        r.add( new RangeImplem( 30, 40 ) );
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 10, 20 ) );
        expected.add( new RangeImplem.RangeElement( 30, 40 ) );
        Assert.assertEquals( expected, r.getRangeElements() );
        
        for ( int i = 0; i < 10; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 10; i < 21; i++ )
            Assert.assertTrue( r.isIn( i ) );

        for ( int i = 21; i < 30; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 30; i < 41; i++ )
            Assert.assertTrue( r.isIn( i ) );
        
        for ( int i = 41; i < 100; i++ )
            Assert.assertFalse( r.isIn( i ) );
        
        r.add( new RangeImplem( 24, 26 ) );
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 10, 20 ) );
        expected.add( new RangeImplem.RangeElement( 24, 26 ) );
        expected.add( new RangeImplem.RangeElement( 30, 40 ) );
        Assert.assertEquals( expected, r.getRangeElements() );
        
        for ( int i = 0; i < 10; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 10; i < 21; i++ )
            Assert.assertTrue( r.isIn( i ) );

        for ( int i = 21; i < 24; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 24; i < 27; i++ )
            Assert.assertTrue( r.isIn( i ) );
        
        for ( int i = 27; i < 30; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 30; i < 40; i++ )
            Assert.assertTrue( r.isIn( i ) );
        
        for ( int i = 41; i < 100; i++ )
            Assert.assertFalse( r.isIn( i ) );
        
        r.add( new RangeImplem( 22, 31 ) );
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 10, 20 ) );
        expected.add( new RangeImplem.RangeElement( 22, 40 ) );
        Assert.assertEquals( expected, r.getRangeElements() );

        for ( int i = 0; i < 10; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 10; i < 21; i++ )
            Assert.assertTrue( r.isIn( i ) );

        for ( int i = 21; i < 22; i++ )
            Assert.assertFalse( r.isIn( i ) );

        for ( int i = 22; i < 41; i++ )
            Assert.assertTrue( r.isIn( i ) );
        
        for ( int i = 41; i < 100; i++ )
            Assert.assertFalse( r.isIn( i ) );
        
        r.add( new RangeImplem( 0, 50 ) );
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 0, 50 ) );
        Assert.assertEquals( expected, r.getRangeElements() );
        
        for ( int i = -100; i < 0; i++ )
            Assert.assertFalse( r.isIn( i ) );
        
        for ( int i = 0; i < 51; i++ )
            Assert.assertTrue( r.isIn( i ) );
        
        for ( int i = 51; i < 100; i++ )
            Assert.assertFalse( r.isIn( i ) );
    }
    
    @Test
    public void test15()
    {
        // given in the coding spec
        RangeImplem r = new RangeImplem();
        Assert.assertFalse( r.newRange( 1, 5 ).add( r.newRange( 8,10 ) ).isIn( 6 ) );
    }
    
    @Test
    public void test16()
    {
        try
        {
            new RangeImplem().min();
        }
        catch( IllegalStateException ex )
        {
            return;
        }
        
        Assert.fail( "no exception on min for uninitialized range" );
    }
    
    @Test
    public void test17()
    {
        try
        {
            new RangeImplem().max();
        }
        catch( IllegalStateException ex )
        {
            return;
        }
        
        Assert.fail( "no exception on max for uninitialized range" );
    }
    
    @Test
    public void test18()
    {
        ArrayList< RangeImplem.RangeElement > expected = new ArrayList<>();
        
        RangeImplem r = new RangeImplem( 0, 100 );
        
        expected.add( new RangeImplem.RangeElement( 0, 100 ) );
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.subtract( new RangeImplem( 11, 89 ) ); // subtract from middle of single existing range
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 0, 10 ) );
        expected.add( new RangeImplem.RangeElement( 90, 100 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
       
        r.subtract( new RangeImplem( 40, 60 ) ); // noop, in between existing ranges
        Assert.assertEquals( expected, r.getRangeElements() ); // same range
        
        r.subtract( new RangeImplem( -2000, -1000 ) ); // noop, before existing ranges
        Assert.assertEquals( expected, r.getRangeElements() ); // same range
        
        r.subtract( new RangeImplem( 1000, 2000 ) ); // noop, after existing ranges
        Assert.assertEquals( expected, r.getRangeElements() ); // same range
        
        r.add( new RangeImplem( 40, 60 ) ); // add a middle range back
        
        r.subtract( new RangeImplem( 0, 5 ) ); // subtract first part of first range
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 10 ) );
        expected.add( new RangeImplem.RangeElement( 40, 60 ) );
        expected.add( new RangeImplem.RangeElement( 90, 100 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
       
        r.subtract( new RangeImplem( 90, 95 ) ); // subtract first part of last range
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 10 ) );
        expected.add( new RangeImplem.RangeElement( 40, 60 ) );
        expected.add( new RangeImplem.RangeElement( 96, 100 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.subtract( new RangeImplem( 10, 10 ) ); // subtract last part of first range
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 9 ) );
        expected.add( new RangeImplem.RangeElement( 40, 60 ) );
        expected.add( new RangeImplem.RangeElement( 96, 100 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
       
        r.subtract( new RangeImplem( 100, 100 ) ); // subtract last part of last range
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 9 ) );
        expected.add( new RangeImplem.RangeElement( 40, 60 ) );
        expected.add( new RangeImplem.RangeElement( 96, 99 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.subtract( new RangeImplem( 9, 49 ) ); // subtract part of first and middle ranges
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 8 ) );
        expected.add( new RangeImplem.RangeElement( 50, 60 ) );
        expected.add( new RangeImplem.RangeElement( 96, 99 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.subtract( new RangeImplem( 59, 96 ) ); // subtract part of middle and last ranges
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 8 ) );
        expected.add( new RangeImplem.RangeElement( 50, 58 ) );
        expected.add( new RangeImplem.RangeElement( 97, 99 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.subtract( new RangeImplem( 8, 97 ) ); // subtract across first middle and last ranges
        
        expected.clear();
        expected.add( new RangeImplem.RangeElement( 6, 7 ) );
        expected.add( new RangeImplem.RangeElement( 98, 99 ) );
        
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.subtract( new RangeImplem( -1000, 1000 ) ); // subtract everything
        
        expected.clear();
        
        Assert.assertEquals( expected, r.getRangeElements() );
        
        r.add(  new RangeImplem( -1000, -1 ) );
        r.add(  new RangeImplem( 0, 0 ) );
        r.add(  new RangeImplem( 1, 1000 ) );
        
        r.subtract( new RangeImplem( -1000, 1000 ) ); // subtract everything variation, exact match and three elements
        
        Assert.assertEquals( expected, r.getRangeElements() );
                
    }
}
