package redhat.q2;

public class Code
{
    public String addStringItems( String[] items, boolean forceUpperCase )
    {
        StringBuilder sb = new StringBuilder();
        
        for ( String item : items )
        {
            sb.append( item );
        }
        
        return forceUpperCase ? sb.toString().toUpperCase() : sb.toString();
    }
}
