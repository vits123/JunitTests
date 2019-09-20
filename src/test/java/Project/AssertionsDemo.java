package Project;

import java.time.Duration;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.
import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertionsDemo {

  private final Calculator calculator = new Calculator();
  private final Person person = new Person("Jane", "Doe");

  @Test
  void standardAssertions() {
    assertEquals(2, calculator.add(1, 1));
    assertEquals(4, calculator.multiply(2, 2),
        "The optional failure message is now the last parameter");
    assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated --"
        + "to avoid constructing complex messages unnecessarily ");
  }

  @Test
  void groupedAssertions() {
    // In a grouped assertions all assertions are executed
    assertAll("person",
        () -> assertEquals("Jane", person.getFirstName()),
        () -> assertEquals("Doe", person.getLastName())
    );
  }

  @Test
  void dependentAssertions(){
    // within a code block,if an assertion fails the
    // subsequent code in same block will be skipped

    assertAll("properties",
        () -> {
           String firstName=person.getFirstName();
           assertNotNull(firstName);

           // executed only if previous assertion is valid
           assertAll("first name",
               () -> assertTrue(firstName.startsWith("J")),
               () -> assertTrue(firstName.endsWith("e")));


        },
        () -> {
           String lastName=person.getLastName();
           assertNotNull(lastName);

          // executed only if previous assertion is valid
           assertAll("last name",
               () -> assertTrue(lastName.startsWith("D")),
               () -> assertTrue(lastName.endsWith("e"))
           );
          }
        );
  }

  @Test
  void exceptionTesting(){
    Exception exception=assertThrows(ArithmeticException.class,
        ()-> calculator.divide(1,0));
    assertEquals("/ by zero",exception.getMessage());
  }

  @Test
  void timeoutNotExceeded(){
    assertTimeout(Duration.ofMinutes(2),()->
    {
      // perform task that takes less than 2 minutes
    } );
  }

  @Test
  void timeoutNotExceededWithResult(){
    String actualResult=assertTimeout(Duration.ofMinutes(2),
        ()-> {
          return "a result";
        });
    assertEquals("a result",actualResult);
  }

  @Test
  void timeoutNotExceededWithMethod(){
    String actualgreeting=assertTimeout(Duration.ofMinutes(2),AssertionsDemo::greeting);
    assertEquals("Hello World",actualgreeting);
  }

  @Test
  void timeoutExceeded(){
    assertTimeout(Duration.ofMillis(10),()->{
      Thread.sleep(5);
    });
  }

  @Test
  void timeoutExceededWithPreemptiveTermination(){
    assertTimeoutPreemptively(Duration.ofMillis(10),()->{
      Thread.sleep(5);
    });
  }
  private static String greeting(){
    return "Hello World";
  }

}
