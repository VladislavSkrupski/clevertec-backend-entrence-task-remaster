package by.task.backendtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InputArgsForReceipt.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InputArgsForReceiptTest {
    @Autowired
    private InputArgsForReceipt inputArgsForReceipt;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link InputArgsForReceipt}
     *   <li>{@link InputArgsForReceipt#setDiscountCardId(int)}
     *   <li>{@link InputArgsForReceipt#getDiscountCardId()}
     *   <li>{@link InputArgsForReceipt#getProductAmountMap()}
     * </ul>
     */
    @Test
    void testConstructor() {
        InputArgsForReceipt actualInputArgsForReceipt = new InputArgsForReceipt();
        actualInputArgsForReceipt.setDiscountCardId(3);
        assertEquals(3, actualInputArgsForReceipt.getDiscountCardId());
        Map<Integer, Integer> expectedProductAmountMap = actualInputArgsForReceipt.productAmountMap;
        assertSame(expectedProductAmountMap, actualInputArgsForReceipt.getProductAmountMap());
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setDiscountCardId(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetDiscountCardId() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        //       at by.task.backendtest.InputArgsForReceipt.setDiscountCardId(InputArgsForReceipt.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        inputArgsForReceipt.setDiscountCardId("3");
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setDiscountCardId(String)}
     */
    @Test
    void testSetDiscountCardId2() {
        inputArgsForReceipt.setDiscountCardId("-3");
        assertEquals(3, inputArgsForReceipt.getDiscountCardId());
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setDiscountCardId(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetDiscountCardId3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NumberFormatException: For input string: "Discount Card Data"
        //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        //       at java.lang.Integer.parseInt(Integer.java:668)
        //       at java.lang.Integer.parseInt(Integer.java:786)
        //       at by.task.backendtest.InputArgsForReceipt.setDiscountCardId(InputArgsForReceipt.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        inputArgsForReceipt.setDiscountCardId("-Discount Card Data");
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(int, int)}
     */
    @Test
    void testSetProductAmountMap() {
        inputArgsForReceipt.setProductAmountMap(1, 1);
        assertEquals(1, inputArgsForReceipt.getProductAmountMap().size());
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(int, int)}
     */
    @Test
    void testSetProductAmountMap2() {
        inputArgsForReceipt.setProductAmountMap(1, 0);
        assertEquals(1, inputArgsForReceipt.getProductAmountMap().size());
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetProductAmountMap3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NumberFormatException: For input string: "Product Arg"
        //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        //       at java.lang.Integer.parseInt(Integer.java:668)
        //       at java.lang.Integer.parseInt(Integer.java:786)
        //       at by.task.backendtest.InputArgsForReceipt.setProductAmountMap(InputArgsForReceipt.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        inputArgsForReceipt.setProductAmountMap("Product Arg");
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetProductAmountMap4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
        //       at by.task.backendtest.InputArgsForReceipt.setProductAmountMap(InputArgsForReceipt.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        inputArgsForReceipt.setProductAmountMap("-");
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetProductAmountMap5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        //       at by.task.backendtest.InputArgsForReceipt.setProductAmountMap(InputArgsForReceipt.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        inputArgsForReceipt.setProductAmountMap("42");
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetProductAmountMap6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NumberFormatException: For input string: "Product Arg"
        //       at java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        //       at java.lang.Integer.parseInt(Integer.java:668)
        //       at java.lang.Integer.parseInt(Integer.java:786)
        //       at by.task.backendtest.InputArgsForReceipt.setProductAmountMap(InputArgsForReceipt.java:44)
        //   See https://diff.blue/R013 to resolve this issue.

        inputArgsForReceipt.setProductAmountMap("42-Product Arg");
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(String)}
     */
    @Test
    void testSetProductAmountMap7() {
        inputArgsForReceipt.setProductAmountMap("42-42");
        assertEquals(  1, inputArgsForReceipt.getProductAmountMap().size());
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(Map)}
     */
    @Test
    void testSetProductAmountMap8() {
        HashMap<Integer, Integer> integerIntegerMap = new HashMap<>();
        integerIntegerMap.put(1, 42);
        inputArgsForReceipt.setProductAmountMap(integerIntegerMap);
        assertEquals(1, inputArgsForReceipt.getProductAmountMap().size());
    }

    /**
     * Method under test: {@link InputArgsForReceipt#setProductAmountMap(Map)}
     */
    @Test
    void testSetProductAmountMap9() {
        HashMap<Integer, Integer> integerIntegerMap = new HashMap<>();
        integerIntegerMap.put(-1, 42);
        integerIntegerMap.put(1, 42);
        inputArgsForReceipt.setProductAmountMap(integerIntegerMap);
        assertEquals(2, inputArgsForReceipt.getProductAmountMap().size());
    }
}

