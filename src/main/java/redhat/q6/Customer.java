package redhat.q6;

import java.util.List;

public interface Customer
{
    public String getName();
    public String getLastName();
    public List< Address > getAddresses();
}
