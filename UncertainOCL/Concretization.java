package UncertainOCL;

import java.util.List;
import java.util.LinkedList;

public class Concretization {
  
  public Concretization() {
    myAlt = new LinkedList<Alternative>();
  }

  public Concretization( Alternative alt ) {
    myAlt = new LinkedList<Alternative>();
    myAlt.add(alt);
  }

  public void append( String text )
  {
    for(Alternative a: myAlt) {
      a.append( text );
    }
  }

  public void prepend( String text )
  {
    for(Alternative a: myAlt) {
      a.prepend( text );
    } 	
  }

  public void addLabel( String label ) {
    myAlt.removeIf( a -> !a.isLabelCompatible(label) );
    for(Alternative a: myAlt) {
      a.addLabel( label );
    }
  }

  public void addAlternative(Alternative alt) {
    myAlt.add(alt);
  }

  // Add all alternative concretizations from another alternative branch
  public void addAlternatives(Concretization other) {
    myAlt.addAll( other.myAlt );
  }

  public boolean isEmpty() {
    return myAlt.isEmpty();
  }

  public Concretization combine(Concretization other) {
    return combine(other, " ");
  }

  // Build the cartesian product of all concretizations followed by all concretizations of other
  public Concretization combine(Concretization other, String separator) {
    Concretization result = new Concretization();
    for(Alternative a1: myAlt) {
      for(Alternative a2: other.myAlt) {
        if (a1.isCompatible(a2)) {
          result.myAlt.add( a1.combine(a2, separator) );
	}
      }
    }
    return result;	
  }

  // Convert the concretizations to a list of strings 
  public List<String> enumerateText()
  {
    List<String> list = new LinkedList<String>();
    for(Alternative a: myAlt) {
      list.add( a.getText() );
    }
    return list;
  }

  // Provide a list of labels used in each concretization 
  public List<String> enumerateLabels()
  {
    List<String> list = new LinkedList<String>();
    for(Alternative a: myAlt) {
      list.add( a.getLabels() );
    }
    return list;
  }

  private List<Alternative> myAlt;
}
