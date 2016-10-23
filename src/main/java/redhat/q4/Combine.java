package redhat.q4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Bonus variation of combine function for exercise 4, which doesn't add the overhead of
 * putting values into a return list, and which has the additional benefit of working for
 * any Comparable not just String.
 */
public class Combine< T extends Comparable< T > > implements Iterator< T >
{
    final private Iterator< T > itr1;
    final private Iterator< T > itr2;
    
    private T v1;
    private T v2;
    
    public Combine( Iterator< T > itr1, Iterator< T > itr2 )
    {
        this.itr1 = itr1;
        this.itr2 = itr2;
        
        v1 = itr1.hasNext() ? itr1.next() : null;
        v2 = itr1.hasNext() ? itr2.next() : null;
    }
    
    @Override
    public boolean hasNext()
    {
        return ( v1 != null ) || ( v2 != null );
    }

    @Override
    public T next()
    {
        T next;
        
        if ( v1 == null && v2 == null )
            throw new NoSuchElementException();
        
        if ( v1 != null && v2 == null )
        {
            next = v1;
            
            if ( itr1.hasNext() )
                v1 = itr1.next();
            else
                v1 = null;
        }
        
        else if ( v1 == null && v2 != null )
        {
            next = v2;
            
            if ( itr2.hasNext() )
                v2 = itr2.next();
            else
                v2 = null;
        }
          
        else
        {
            int cmp = v1.compareTo( v2 );

            if ( cmp <= 0 )
            {
                next = v1;
                
                if ( itr1.hasNext() )
                    v1 = itr1.next();
                else
                    v1 = null;
            }

            else
            {
                next = v2;
                
                if ( itr2.hasNext() )
                    v2 = itr2.next();
                else
                    v2 = null;
            }
        }
        
        return next;
    }

    /**
     * Not implemented.
     * @throws UnsupporedOperationException
     */
    @Override
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}
