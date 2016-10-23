package redhat.q6;

import java.util.List;

/**
 * This defines the search API required for this exercise.
 */
public interface Customers
{
    /**
     * Returns a list of customers.  If {@code searchCriteria} is specified, only customers matching all
     * criteria are returned.  Each search criteria entry is a field name and corresponding value to be matched.
     * <p>
     * If {@code sortField} is specified, the returned customer list is sorted based on the value of the
     * specified field.  {@code isSortAscending} indicates whether returned customers are sorted in
     * ascending or descending order.
     * <p>
     * Results are paged to limit the number of entries returned by one request.  On the initial query, specify
     * the maximum number of records returned as {@code limit} and the value of {@code startAt} as zero.
     * Subsequent requests should increment {@code startAt} by the total number of records returned until
     * an empty customer list is returned.
     * @param searchCriteria return only customers matching the criteria, null for all customers
     * @param sortField returned entries are sorted based on the value of this field, null otherwise
     * @param isSortAscending sort in ascending order if true, descending order if false
     * @param startAt index of first matching record to return, beginning at zero
     * @param limit maximum number of records to return
     * @return customer list
     * @throws SearchException for any error
     */
    public List< Customer > searchCustomers( List< NameValue > searchCriteria, String sortField, boolean isSortAscending, int startAt, int limit )
        throws SearchException;
}
