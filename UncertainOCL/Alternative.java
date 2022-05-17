package UncertainOCL;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;

public class Alternative 
{
  public Alternative( String text )
  {
    myLabels = new HashSet<String>();
    myConflicts = new HashSet<String>();
    myText = text;
  }

  public Alternative(String label, String text)
  {
    myLabels = new HashSet<String>();
    myLabels.add( label );
    myConflicts = new HashSet<String>();
    myText = text;
  }
 
  // Combine two alternatives
  private Alternative(Alternative a1, Alternative a2, String separator)
  {
    myLabels = new HashSet<String>();
    myConflicts = new HashSet<String>();
    // We receive the labels and conflicts of previous alternatives
    myLabels.addAll(a1.myLabels);
    myLabels.addAll(a2.myLabels);
    myConflicts.addAll(a1.myConflicts);
    myConflicts.addAll(a2.myConflicts);
    // We combine the text of this alternative with the other in sequence
    // Add one separator between the two texts
    myText = a1.myText + separator + a2.myText;
  }

  public boolean hasLabel()
  {
    return myLabels.size() == 0;
  } 

  public boolean isLabelCompatible(String label)
  {
    return !myConflicts.contains(label);
  }

  public String getText()
  {
    return myText;
  }

  public String getLabels()
  {
    if (myLabels.size() == 0) {
      return "none";
    }
    return myLabels.toString();
  }

  public void addLabel(String label)
  {
    if (!label.isEmpty()) {  
      assert(!myConflicts.contains(label));
      myLabels.add( label );
    }
  }

  public void addConflict( String label )
  {
    if (!label.isEmpty()) {
      assert(!myLabels.contains(label));
      myConflicts.add( label );
    }
  }

  public void append( String text )
  {
    myText = myText + text;
  }

  public void prepend( String text )
  {
    myText = text + myText;
  }
 
  public boolean isCompatible( Alternative other )
  {
    // Is any of my labels in the list of conflicts of the other alternative?
    for(String s: myLabels) {
      if (other.myConflicts.contains(s)) {
        return false;
      }
    }

    // Is any of my conflicts in the list of labels of the other alternative?
    for(String s: myConflicts) {
      if (other.myLabels.contains(s)) {
        return false;
      }
    }

    return true;
  }

  public Alternative combine(Alternative other) {
    assert(isCompatible(other));
    return new Alternative(this, other, " ");
  }

  public Alternative combine(Alternative other, String separator) {
    assert(isCompatible(other));
    return new Alternative(this, other, separator);
  }
  
  private String myText;
  private Set<String>  myLabels;
  private Set<String>  myConflicts;
}

