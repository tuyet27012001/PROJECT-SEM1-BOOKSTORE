package vn.edu.vtc.bl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PresentationTest {
  @Test
  public void email1() {
    Presentation presentation = new Presentation();
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
    Presentation presentation = new Presentation();
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
    Presentation presentation = new Presentation();
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
    Presentation presentation = new Presentation();
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
    Presentation presentation = new Presentation();
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
    Presentation presentation = new Presentation();
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
    Presentation presentation = new Presentation();
    try {
      final boolean result = presentation.validPhone("034564565465483");
      final boolean expected = false;
      assertEquals(expected, result);
    } catch (final Exception e) {
      // TODO: handle exception
    }
  }
}