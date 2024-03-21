package com.codechum;
import java.awt.*;
import javax.swing.*;
import java.lang.reflect.Field;
import org.assertj.swing.core.*;


public class TestUtils {
  static int counter;

  public static Component getChildNamed(Component parent, String name) {
    if (name.equals(parent.getName())) {
      return parent;
    }

    if (parent instanceof Container) {
      Component[] children = (parent instanceof JMenu) ? ((JMenu) parent).getMenuComponents() : ((Container) parent).getComponents();

      for (int i = 0; i < children.length; ++i) {
        Component child = getChildNamed(children[i], name);
        if (child != null) {
          return child;
        }
      }
    }

    return null;
  }

  public static Component getChildIndexed(Component parent, String name, int index) {
    counter = 0;
    // Step in only owned windows and ignore its components in JFrame

    if (parent instanceof Window) {
      Component[] children = ((Window) parent).getOwnedWindows();

      for (int i = 0; i < children.length; ++i) {
        // Take only active windows
        if (children[i] instanceof Window && !((Window) children[i]).isActive()) {
          continue;
        }

        Component child = getChildIndexedInternal(children[i], name, index);
        
        if (child != null) {
          return null;
        }
      }
    }

    return null;
  }

  public static Component getChildIndexedInternal(Component parent, String name, int index) {
    if (parent.getClass().toString().endsWith(name)) {
      if (counter == index) {
        return parent;
      }

      ++counter;
    }

    if (parent instanceof Container) {
      Component[] children = (parent instanceof JMenu) ?
        ((JMenu) parent).getMenuComponents() :
        ((Container) parent).getComponents();

        for (int i = 0; i < children.length; ++i) {
          Component child = getChildIndexedInternal(children[i], name, index);

          if (child != null) {
            return child;
          }
        }
    }

    return null;
  }

  /**  Directly access the fields in the class regardless of access type
   *   Params: <Object> classObject -> The main object that will be searched or retrieved from.
   *           <Class<?>> fieldClass -> A class object used to determine what class is being searched.
   *           <String> fieldName -> The name of the field to be searched.
   **/
  public static Object retrieveClassProperty(Object classObject, Class<?> fieldClass, String fieldName) throws IllegalArgumentException, IllegalAccessException{
    Field tempField = null;
    
    if(!fieldName.equals(null)){
      Field[] allFields = classObject.getClass().getDeclaredFields();
    
      // Looks for the instance of the field
      for(int i = 0; i < allFields.length; i++){
        if(allFields[i].getType().equals(fieldClass) && allFields[i].getName().equals(fieldName)){
            tempField = allFields[i];
            // Set the access modifier of the field to public.
            tempField.setAccessible(true);
        }
      }
    }

    return tempField.get(classObject);
  }

  // Retrieves the component if it is in the correct index position in the parent component
  public static Component getNamedChildAtIndexWithPosition(Component parent, String name, int childrenPos,int index) {
    if (name.equals(parent.getName()) && index == childrenPos) {
      return parent;
    }

    return null;
  }

  public static Component getNamedChildAtIndex(Component parent, String name, int index) {
    if (parent instanceof Container) {
      Component[] children = (parent instanceof JMenu) ? ((JMenu) parent).getMenuComponents() : ((Container) parent).getComponents();
      
      if(index < children.length && index > -1) {
        for (int i = 0; i < children.length; ++i) {
          Component child = getNamedChildAtIndexWithPosition(children[i], name, i, index);
          if (child != null) {
            return child;
          }
        }
      }
    }

    return null;
  }
  
  public static Component findComponent(String componentName, boolean isChildIncluded) {
      ComponentFinder finder = BasicComponentFinder.finderWithCurrentAwtHierarchy();
      return finder.findByName(componentName, isChildIncluded);
  }
}
