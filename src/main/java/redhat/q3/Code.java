package redhat.q3;

import java.util.ArrayList;

public class Code
{
    public String addStringItems( String[] items, boolean forceUpperCase )
    {
        // 1. add each array item to a Collection implementation
        
        ArrayList< String > list = new ArrayList<>( items.length );
        
        for ( String item : items )
        {
            list.add( item );
        }
        
        // 2. iterate over the Collection and return all values as a concatenated
        
        StringBuilder sb = new StringBuilder();
        
        for ( String item : list )
        {
            sb.append( item );
        }
        
        // 3. if forceUpperCase is true, returned value must be all upper case

        return forceUpperCase ? sb.toString().toUpperCase() : sb.toString();
        
        // Tell me why you chose the container implementation that you did.
        
        //>>>>ArrayList performs well for known-size insertion and sequential access
        
        // Is the optimized function thread safe?
        
        //>>>>Yes, no shared resources updated.
        
        // Is the unoptimized function thread safe?
        
        //>>>>Yes, no shared resources updated.
        
        // If not, how can you make it thread safe?
        
        //>>>>It is thread safe.
    }
}
