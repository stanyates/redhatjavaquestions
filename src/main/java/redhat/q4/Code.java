package redhat.q4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Code
{
    public List<String> combine( Iterator<String> itr1, Iterator<String> itr2 )
    {
        ArrayList< String > result = new ArrayList<>();
        
        String v1 = null;
        String v2 = null;
        
        while( true )
        {
            if ( v1 == null && itr1.hasNext() )
                v1 = itr1.next();
            
            if ( v2 == null && itr2.hasNext() )
                v2 = itr2.next();
            
            if ( v1 == null && v2 == null )
                break;
            
            if ( v1 != null && v2 == null )
            {
                result.add( v1 );
                v1 = null;
            }
            
            else if ( v1 == null && v2 != null )
            {
                result.add( v2 );
                v2 = null;
            }
              
            else
            {
                int cmp = v1.compareTo( v2 );

                if ( cmp <= 0 )
                {
                    result.add( v1 );
                    v1 = null;
                }

                else
                {
                    result.add( v2 );
                    v2 = null;
                }
            }
        }
        
        return result;
    }
}
