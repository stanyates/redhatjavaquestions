package redhat.q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RangeImplem implements Range
{
    final private ArrayList< RangeElement > rangeElements = new ArrayList<>();
    
    public RangeImplem()
    {
    }
    
    public RangeImplem( int from, int to )
    {
        if ( from > to )
        {
            throw new IllegalArgumentException( "from > to" );
        }
        
        rangeElements.add( new RangeElement( from, to ) );
    }

    @Override
    public Range newRange( int from, int to )
    {
        return new RangeImplem( from, to );
    }

    @Override
    public boolean isIn( int value )
    {
        int index = Collections.binarySearch( rangeElements, new RangeElement( value, value ), locator );
        return index >= 0;
    }

    @Override
    public int min()
    {
        if ( rangeElements.size() == 0 )
        {
            throw new IllegalStateException( "empty range" );
        }
        
        return rangeElements.get( 0 ).getFrom();
    }

    @Override
    public int max()
    {
        if ( rangeElements.size() == 0 )
        {
            throw new IllegalStateException( "empty range" );
        }
        
        return rangeElements.get( rangeElements.size() - 1 ).getTo();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Because only the min and max values are accessible through the Range interface,
     * adding a disjoint range adds a single range from min to max to this Range.
     */
    @Override
    public Range add( Range r )
    {
        if ( r.min() > r.max() )
        {
            throw new IllegalArgumentException( "min > max" );
        }
        
        // Find existing first and last matching ranges; if one doesn't fall in an existing range the
        // index is negative and one less than the insertion point.  See Collections.binarySearch for details.
        
        int startIndex = Collections.binarySearch( rangeElements, new RangeElement( r.min(), r.min() ), locator );
        int endIndex = Collections.binarySearch( rangeElements, new RangeElement( r.max(), r.max() ), locator );
        
        // Create the new RangeElement and see which existing entries it potentially combines with and replaces.
        
        int from = r.min();
        int to = r.max();

        if ( startIndex >= 0 )
        {
            RangeElement e = rangeElements.get( startIndex );
            from = Math.min( e.getFrom(), r.min() );
        }

        if ( endIndex >= 0 )
        {
            RangeElement e = rangeElements.get( endIndex );
            to = Math.max( e.getTo(), r.max() );
        }

        RangeElement newRange = new RangeElement( from, to );
        
        // Shortcut, initial RangeEntry.
        if ( rangeElements.size() == 0 )
            rangeElements.add( newRange );
        
        else
        {
            if ( startIndex < 0 )
                startIndex = -( startIndex + 1 );
            
            if ( endIndex < 0 )
                endIndex = -( endIndex + 1 );
            
            // See which existing entries we need to replace...delete them before adding the new entry.
            for ( int i = startIndex; i <= endIndex && i < rangeElements.size(); )
            {
                RangeElement e = rangeElements.get( i );
                
                if ( locator.compare( e, newRange ) == 0 )
                {
                    rangeElements.remove( i );
                    endIndex--; // bugfix, additional entries were searched unnecessarily before adding this
                }
                
                else
                    i++;
                
                // TODO - coalesce adjacent ranges
            }

            // Add the new entry.
            rangeElements.add( startIndex, newRange );
        }
        
        return this;
    }
    
    @Override
    public Range subtract( Range r )
    {
        // Bonus question.
        
        // Find existing first and last matching ranges; if one doesn't fall in an existing range the
        // index is negative and one less than the insertion point.  See Collections.binarySearch for details.
        
        int startIndex = Collections.binarySearch( rangeElements, new RangeElement( r.min(), r.min() ), locator );
        int endIndex = Collections.binarySearch( rangeElements, new RangeElement( r.max(), r.max() ), locator );
        
        boolean startMatched = startIndex >= 0;
        boolean endMatched = endIndex >= 0;
        
        if ( startIndex < 0 )
            startIndex = -( startIndex + 1 );

        if ( endIndex < 0 )
            endIndex = -( endIndex + 1 );
        
        // See which existing first and/or last entries need to be replaced instead of deleted.
        
        if ( startMatched )
        {
            RangeElement start = rangeElements.get( startIndex );
            
            // Deleting only part of the start range.
            if ( start.getFrom() < r.min() )
            {
                RangeElement replacement = new RangeElement( start.getFrom(), r.min() - 1 );
                rangeElements.add( startIndex, replacement );
                startIndex++;
                endIndex++;
            }
        }
        
        if ( endMatched )
        {
            RangeElement end = rangeElements.get( endIndex );
            
            // Deleting only part of the end range.
            if ( end.getTo() > r.max() )
            {
                RangeElement replacement = new RangeElement( r.max() + 1, end.getTo() );
                rangeElements.add( endIndex + 1, replacement );
            }
        }
        
        RangeElement target = new RangeElement( r.min(), r.max() );

        for ( int i = startIndex; i <= endIndex && i < rangeElements.size(); )
        {
            RangeElement e = rangeElements.get( i );

            if ( locator.compare( e, target ) == 0 )
            {
                rangeElements.remove( i );
                endIndex--;
            }

            else
                i++;
        }
        
        return this;
    }
    
    /**
     * Package visibility exclusively for unit testing.
     * @return internal data structure of ranges
     */
    ArrayList< RangeElement > getRangeElements()
    {
        return rangeElements;
    }

    /**
     * Internal range representation, package visibility exclusively for unit testing.
     */
    static class RangeElement
    {
        final private int from;
        final private int to;
        
        public RangeElement( int from, int to )
        {
            this.from = from;
            this.to = to;
        }
        
        public int getFrom()
        {
            return from;
        }
        
        public int getTo()
        {
            return to;
        }
        
        @Override
        public String toString()
        {
            return "[" + from + ", " + to + "]";
        }
        
        /**
         * Returns true if the from and to values in two entries match.
         * For unit testing only, to compare internal range entries against expected values.
         */
        @Override
        public boolean equals( Object o )
        {
            if ( o == null )
                return false;
            
            RangeElement it = ( RangeElement )o;
            
            return ( this.from == it.from ) && ( this.to == it.to );
        }
        
        /**
         * Because {@link #equals} is overridden hashCode is overridden to be consistent,
         * even though this class isn't hashed and isn't intended for public consumption.
         * See {@link java.lang.Object#hashCode()}.
         */
        @Override
        public int hashCode()
        {
            // Bloch Effective Java algorithm.
            int hash = 17;
            hash = 31 * from + hash;
            hash = 31 * to + hash;
            
            return hash;
        }
    }
    
    // Comparator returns zero if target range falls anywhere within an existing range,
    // negative if less than the from value, positive if greater than the to value.
    final private Comparator< RangeElement > locator = new Comparator< RangeElement >()
    {
        @Override
        public int compare( RangeElement o1, RangeElement o2 )
        {
            if ( o1.getTo() < o2.getFrom() )
            {
                return -1;
            }
            
            if ( o2.getTo() < o1.getFrom() )
            {
                return 1;
            }
            
            return 0;
        }
    };
}
