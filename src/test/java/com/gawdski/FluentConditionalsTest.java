package com.gawdski;

import org.testng.annotations.Test;

import static com.gawdski.FluentConditionals.doNothing;
import static com.gawdski.FluentConditionals.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
@Test
public class FluentConditionalsTest {

    @Test
    public void shouldReturnFluentConditionalsForWhen() {
        assertTrue(when(true) != null);
    }

    @Test
    public void shouldReturnFluentConditionalsForThen() {
        assertTrue(when(true).then(new TestHelper()::printBar) != null);
    }

    @Test
    public void shouldPrintBar() {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper.somethingIsFalse()).then(mockedHelper::printFoo).orElse(mockedHelper::printBar);

        verify(mockedHelper, times(0)).printFoo();
        verify(mockedHelper, times(1)).printBar();
    }

    @Test
    public void shouldRunFoo() {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper.somethingIsTrue()).then(mockedHelper::printFoo).orElse(mockedHelper::printBar);

        verify(mockedHelper, times(1)).printFoo();
        verify(mockedHelper, times(0)).printBar();
    }

    @Test
    public void shouldRunFooWithSupplier() {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsTrue).then(mockedHelper::printFoo).orElse(mockedHelper::printBar);

        verify(mockedHelper, times(1)).printFoo();
        verify(mockedHelper, times(0)).printBar();
    }

    @Test
    public void shouldDoNoting() {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsFalse).then(mockedHelper::printFoo).orElse(doNothing);

        verify(mockedHelper, times(0)).printFoo();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowNewException() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsFalse)
                .then(mockedHelper::printFoo)
                .orElseThrowE(new RuntimeException());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionWithConstructorReference() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsFalse)
                .then(mockedHelper::printFoo)
                .orElseThrow(RuntimeException::new);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionWithMethod() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsFalse)
                .then(mockedHelper::printFoo)
                .orElseThrow(TestHelper::createException);

    }

    @Test
    public void shouldPrintFooWithExceptionMethod() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsTrue)
                .then(mockedHelper::printFoo)
                .orElseThrow(TestHelper::createException);

        verify(mockedHelper, times(1)).printFoo();
    }

    @Test
    public void shouldPrintFooWithExceptionConstructorReference() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsTrue)
                .then(mockedHelper::printFoo)
                .orElseThrow(RuntimeException::new);

        verify(mockedHelper, times(1)).printFoo();
    }

    @Test
    public void shouldPrintFooWithNewException() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);

        when(TestHelper::somethingIsTrue)
                .then(mockedHelper::printFoo)
                .orElseThrowE(new RuntimeException());

        verify(mockedHelper, times(1)).printFoo();
    }

    @Test
    public void shouldReturnResultForTrue() {
        Integer givenResult = when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);

        assertEquals(givenResult, Integer.valueOf(1000));
    }

    @Test
    public void shouldReturnResultForFalse() {
        int givenResult = when(TestHelper::somethingIsFalse)
                .thenReturn(TestHelper::getHighNumber)
                .orElse(TestHelper::getLowNumber);

        assertEquals(givenResult, 1);
    }

    @Test
    public void shouldReturnResultForFalseWithNumbers() {
        int givenResult = when(TestHelper::somethingIsFalse)
                .thenReturn(1000)
                .orElse(0);

        assertEquals(givenResult, 0);
    }

    @Test
    public void shouldReturnNumberFromOrElseSupplier() {
        int givenResult = when(TestHelper::somethingIsTrue)
                .thenReturn(1000)
                .orElse(0);

        assertEquals(givenResult, 1000);
    }

    @Test
    public void shouldReturnNumberWithNoException() throws Exception {
        int givenResult = when(TestHelper::somethingIsTrue)
                .thenReturn(TestHelper::getHighNumber)
                .orElseThrow(RuntimeException::new);

        assertEquals(givenResult, 1000);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionWithSupplier() throws Exception {
        int givenResult = when(TestHelper::somethingIsFalse)
                .thenReturn(1000)
                .orElseThrow(RuntimeException::new);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionWithConstructor() throws Exception {
        int givenResult = when(TestHelper::somethingIsFalse)
                .thenReturn(1000)
                .orElseThrowE(new RuntimeException());
    }

    @Test
    public void shouldReturnNumberWithExceptionConstructor() throws Exception {
        int givenResult = when(TestHelper::somethingIsTrue)
                .thenReturn(1000)
                .orElseThrowE(new RuntimeException());

        assertEquals(givenResult, 1000);
    }

    @Test
    public void shouldReturnString() {
        String givenResult = when(TestHelper::somethingIsFalse)
                .thenReturn("asdads")
                .orElse("abc");
        assertEquals(givenResult, "abc");
    }
}
