package com.codechum;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.lang.reflect.Field;
import org.assertj.swing.core.*;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;

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

    try {
      return finder.findByName(componentName, isChildIncluded);
    } catch(Exception e) {
      return null;
    }
  }

  private static String findMessageInContainer(ContainerOperator containerOperator) {
    Component[] components = containerOperator.getComponents();
    for (Component component : components) {
        if (component instanceof JLabel) {
            return ((JLabel) component).getText();
        } else if (component instanceof JTextComponent) {
            return ((JTextComponent) component).getText();
        } else if (component instanceof Container) {
            // If the component is a container, recursively search within it
            ContainerOperator nestedContainerOperator = new ContainerOperator((Container) component);
            String nestedMessage = findMessageInContainer(nestedContainerOperator);
            if (nestedMessage != null) {
                return nestedMessage;
            }
        }
    }
    return null; // Return null if no message component is found
  }

  public static String getJOptionPaneMessage() {
    boolean isFound = false;
  
    try {
        Window[] windows = Window.getWindows();

        for (Window window : windows) {
          if (window instanceof JDialog) {
              JDialog dialog = (JDialog) window;
              isFound = dialog.isShowing();
          }
        }
    } catch (Exception e) {
        System.out.print(e);
    }

    if (isFound) {
      ComponentChooser chooser = new ComponentChooser() {
        @Override
        public boolean checkComponent(Component comp) {
            if (comp instanceof JDialog) {
                JDialog dialog = (JDialog) comp;
                return dialog.isShowing();
            }
            return false;
        }
  
        @Override
        public String getDescription() {
          return "JDialog opened";
        }
      };
  
      JDialogOperator dialogOperator = new JDialogOperator(chooser);
      ContainerOperator containerOperator = new ContainerOperator(dialogOperator);

      return findMessageInContainer(containerOperator);
    } else {
      return null;
    }
  }

  public static void clickOKButtonInDialog() {
    ComponentChooser chooser = new ComponentChooser() {
      @Override
      public boolean checkComponent(Component comp) {
          if (comp instanceof JDialog) {
              JDialog dialog = (JDialog) comp;
              return "Message".equals(dialog.getTitle()) && dialog.isShowing();
          }
          return false;
      }

      @Override
      public String getDescription() {
          return "JOptionPane Dialog";
      }
    };

    JDialogOperator dialogOperator = new JDialogOperator(chooser);

    // Click on the "OK" button
    JButtonOperator okButtonOperator = new JButtonOperator(dialogOperator, "OK");
    okButtonOperator.push();
  }
}
