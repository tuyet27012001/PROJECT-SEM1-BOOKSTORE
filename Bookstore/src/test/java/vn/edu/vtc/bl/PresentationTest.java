package vn.edu.vtc.bl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PresentationTest {
  Presentation presentation = new Presentation();
  @Test
  public void email1() {
    
    try {
      final boolean result = presentation.validEmail("hghh@");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void email2() {
    try {
      final boolean result = presentation.validEmail("mlqaqqgz@gmail.com");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test

  public void email3() {
    try {
      final boolean result = presentation.validEmail("mlqaqq2gmail.com");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void email4() {
    try {
      final boolean result = presentation.validEmail("mlqaqq2@gmail");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void phone1() {
    try {
      final boolean result = presentation.validPhone("0966996201");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void phone2() {
    try {
      final boolean result = presentation.validPhone("03483");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void phone3() {
    try {
      final boolean result = presentation.validPhone("034564565465483");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void dateBirth1() {
    try {
      final String result = presentation.dateBirth("12-12-2019");
      final String expected = "2019-12-12";
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void dateBirth2() {
    try {
      final String result = presentation.dateBirth("10-3-2000");
      final String expected = "2000-3-10";
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void dateBirth3() {
    try {
      final String result = presentation.dateBirth("9-11-1999");
      final String expected = "1999-11-9";
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void dateBirth4() {
    try {
      final String result = presentation.dateBirth("9-9-1999");
      final String expected = "1999-9-9";
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
 
  @Test
  public void validNameTest1() {
    try {
      final boolean result = presentation.validName("Nguyen Thi Tuyet");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validNameTest2() {
    try {
      final boolean result = presentation.validName("tuyet23");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validNameTest3() {
    try {
      final boolean result = presentation.validName("Nguyen");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validPasswordTest1() {
    try {
      final boolean result = presentation.validPassword("text");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest2() {
    try {
      final boolean result = presentation.validPassword("Text1234");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest3() {
    try {
      final boolean result = presentation.validPassword("Text@123");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest4() {
    try {
      final boolean result = presentation.validPassword("text1234");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest5() {
    try {
      final boolean result = presentation.validPassword("text@123");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest6() {
    try {
      final boolean result = presentation.validPassword("text@");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest7() {
    try {
      final boolean result = presentation.validPassword("TuyetTuyet123#");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest8() {
    try {
      final boolean result = presentation.validPassword("@123");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest9() {
    try {
      final boolean result = presentation.validPassword("@#.");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  @Test
  public void validPasswordTest10() {
    try {
      final boolean result = presentation.validPassword("Text@");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validDateTest1() {
    try {
      final boolean result = presentation.validDate("01-02-2001");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validDateTest2() {
    try {
      final boolean result = presentation.validDate("01-02-2020");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validDateTest3() {
    try {
      final boolean result = presentation.validDate("1-2-2001");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
  
  @Test
  public void validDateTest4() {
    try {
      final boolean result = presentation.validDate("22-02-2001");
      final boolean expected = true;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validDateTest5() {
    try {
      final boolean result = presentation.validDate("123-02-2001");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

  @Test
  public void validDateTest6() {
    try {
      final boolean result = presentation.validDate("012-12-2001");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }

}
