package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsDemo {

  private final Calculator calculator=new Calculator();

  @Test
  void testOnlyOnCiServer(){
    assumeTrue("CI".equals(System.getenv("ENV")));
  }

  @Test
  void testOnlyOnDeveloperWorkstation(){
    assumeTrue("DEV".equals(System.getenv("ENV")),
        ()->"Aborting test: not on developer workstation");
  }

  @Test
  void testInAllEnvironments(){
    assumingThat("CI".equals(System.getenv("ENV")),
        ()->{
           assertEquals(2,calculator.divide(4,2));
        });
    assertEquals(42,calculator.multiply(6,7));
  }

}
