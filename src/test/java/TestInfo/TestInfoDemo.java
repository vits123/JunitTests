package TestInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("TestInfo demo")
public class TestInfoDemo {

  TestInfoDemo(TestInfo testInfo){
    assertEquals("TestInfo demo",testInfo.getDisplayName());
  }

  @BeforeEach
  void init(TestInfo testInfo){
    String displayName=testInfo.getDisplayName();
    assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
  }

  @Test
  @DisplayName("TEST 1")
  @Tag("mytag")
  void test1(TestInfo testInfo){
    assertEquals("TEST 1",testInfo.getDisplayName());
    assertTrue(testInfo.getTags().contains("mytag"));
  }


}
