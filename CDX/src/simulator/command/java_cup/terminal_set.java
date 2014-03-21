
package command.java_cup;

import javax.lang.model.element.Element;

import javolution.util.FastBitSet;

/** A set of terminals implemented as a FastBitSet. 
 * @version last updated: 11/25/95
 * @author  Scott Hudson
 */
public class terminal_set {

  /*-----------------------------------------------------------*/
  /*--- Constructor(s) ----------------------------------------*/
  /*-----------------------------------------------------------*/

  /** Constructor for an empty set. */
  public terminal_set() 
    { 
      /* allocate the FastBitSet at what is probably the right size */
      _elements = new FastBitSet();
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Constructor for cloning from another set. 
   * @param other the set we are cloning from.
   */
  public terminal_set(terminal_set other) 
    throws internal_error
    {
      not_null(other);
      _elements = new FastBitSet();
      _elements.addAll(other._elements);
    }

  /*-----------------------------------------------------------*/
  /*--- (Access to) Static (Class) Variables ------------------*/
  /*-----------------------------------------------------------*/

  /** Constant for the empty set. */
  public static final terminal_set EMPTY = new terminal_set();

  /*-----------------------------------------------------------*/
  /*--- (Access to) Instance Variables ------------------------*/
  /*-----------------------------------------------------------*/

  /** FastBitSet to implement the actual set. */
  protected FastBitSet _elements;

  /*-----------------------------------------------------------*/
  /*--- General Methods ----------------------------------------*/
  /*-----------------------------------------------------------*/

  /** Helper function to test for a null object and throw an exception if
   *  one is found. 
   * @param obj the object we are testing.
   */
  protected void not_null(Object obj) throws internal_error
    {
      if (obj == null) 
	throw new internal_error("Null object used in set operation");
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Determine if the set is empty. */
  public boolean empty()
    {
      return equals(EMPTY);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Determine if the set contains a particular terminal. 
   * @param sym the terminal symbol we are looking for.
   */
  public boolean contains(terminal sym) 
    throws internal_error
    {
      not_null(sym); 
      return _elements.get(sym.index());
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Given its index determine if the set contains a particular terminal. 
   * @param indx the index of the terminal in question.
   */
  public boolean contains(int indx) 
    {
      return _elements.get(indx);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Determine if this set is an (improper) subset of another.
   * @param other the set we are testing against.
   */
  public boolean is_subset_of(terminal_set other)
    throws internal_error
    {
      not_null(other);

      /* make a copy of the other set */
      FastBitSet copy_other = new FastBitSet();
      copy_other.addAll(other._elements);

      /* and or in */
      copy_other.or(_elements);

      /* if it hasn't changed, we were a subset */
      return copy_other.equals(other._elements);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Determine if this set is an (improper) superset of another.
   * @param other the set we are testing against.
   */
  public boolean is_superset_of(terminal_set other)
    throws internal_error
    {
      not_null(other);
      return other.is_subset_of(this);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Add a single terminal to the set.  
   * @param sym the terminal being added.
   * @return true if this changes the set.
   */
  public boolean add(terminal sym) 
    throws internal_error
    {
      boolean result;

      not_null(sym); 

      /* see if we already have this */ 
      result = _elements.get(sym.index());

      /* if not we add it */
      if (!result)
	_elements.set(sym.index());

      return result;
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Remove a terminal if it is in the set.
   * @param sym the terminal being removed.
   */
  public void remove(terminal sym) 
    throws internal_error
    {
      not_null(sym); 
      _elements.clear(sym.index());
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Add (union) in a complete set.  
   * @param other the set being added.
   * @return true if this changes the set.
   */
  public boolean add(terminal_set other)
    throws internal_error
    {
      not_null(other);

      /* make a copy */
      FastBitSet copy = new FastBitSet();
      copy.addAll(_elements);

      /* or in the other set */
      _elements.or(other._elements);

      /* changed if we are not the same as the copy */
      return !_elements.equals(copy);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Determine if this set intersects another.
   * @param other the other set in question.
   */
   public boolean intersects(terminal_set other)
     throws internal_error
     {
       not_null(other);

       /* make a copy of the other set */
       FastBitSet copy = new FastBitSet();
       copy.addAll(other._elements);

       /* xor out our values */
       copy.xor(this._elements);

       /* see if its different */
       return !copy.equals(other._elements);
     }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Equality comparison. */
  public boolean equals(terminal_set other)
    {
      if (other == null) 
	return false;
      else
	return _elements.equals(other._elements);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Generic equality comparison. */
  //Override
public boolean equals(Object other)
    {
      if (!(other instanceof terminal_set))
	return false;
      else
	return equals((terminal_set)other);
    }

  /*. . . . . . . . . . . . . . . . . . . . . . . . . . . . . .*/

  /** Convert to string. */
  //Override
public String toString()
    {
      String result;
      boolean comma_flag;
      
      result = "{";
      comma_flag = false;
      for (int t = 0; t < terminal.number(); t++)
	{
	  if (_elements.get(t))
	    {
	      if (comma_flag)
	        result += ", ";
	      else
	        comma_flag = true;

	      result += terminal.find(t).name();
	    }
	}
      result += "}";

      return result;
    }

  /*-----------------------------------------------------------*/

}

