package redhat.q5;

public class Conversion
{
    /**
     * Convert a base-10 integer to an 8-bit two's complement binary number, and return the binary number.
     */
    public String base10to2( String base10 ) throws NumberFormatException
    {
        int i = Integer.parseInt( base10 );
        String result = Integer.toBinaryString( i );
        
        return result;
    }

    /**
     * Return 2's complement negative of the given base10 number and return the binary number
     */
    public String negBase2( String base10 ) throws NumberFormatException
    {
        int i = Integer.parseInt( base10 );
        String result = Integer.toBinaryString( -i );
        
        return result;
    }
}