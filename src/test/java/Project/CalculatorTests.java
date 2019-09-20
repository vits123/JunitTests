package Project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

  @Test
  @DisplayName("1 + 1 = 2")
  void addTwoNumbers() {
    Calculator cal = new Calculator();
    assertEquals(2, cal.add(1, 1), "1+1 should equal 2");
  }

  @ParameterizedTest(name="{0} + {1} = {2}")
  @CsvSource({
      "0,1,1",
      "1,2,3",
      "49,51,100",
      "1,100,101"
  })
  void add(int first, final int second, final int expectedResult){
    Calculator cal1=new Calculator();
    assertEquals(expectedResult,cal1.add(first,second),
        () -> first + " + " + second +" should equal" +expectedResult);
  }

}
