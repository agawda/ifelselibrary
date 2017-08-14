package com.gawdski;

import org.testng.annotations.Test;

import java.util.function.Consumer;

import static com.gawdski.Conditionals.doNothing;
import static com.gawdski.Conditionals.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Anna Gawda
 * @since 14.08.2017
 */
@Test
public class ConditionalsTest {

    @Test
    public void shouldReturnNewConditional() {
        assertNotNull(given("abc"));
    }

    @Test
    public void shouldReturnNewConditionalFromWhen() {
        assertNotNull(given("abc").when(true));
    }

    @Test
    public void shouldCallMethodIfTrue() {
        TestHelper mockedHelper = mock(TestHelper.class);
        String givenString = "abc";

        given(givenString)
                .when(TestHelper::somethingIsTrue)
                .then(mockedHelper::printFirstChar)
                .orElse(mockedHelper::printLastChar);

        verify(mockedHelper, times(1)).printFirstChar(givenString);
        verify(mockedHelper, times(0)).printLastChar(givenString);
    }

    @Test
    public void shouldCallMethodIfFalse() {
        TestHelper mockedHelper = mock(TestHelper.class);
        String givenString = "abc";

        given(givenString)
                .when(false)
                .then(mockedHelper::printFirstChar)
                .orElse(mockedHelper::printLastChar);

        verify(mockedHelper, times(0)).printFirstChar(givenString);
        verify(mockedHelper, times(1)).printLastChar(givenString);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionWithConstructor() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);
        String givenString = "abc";

        given(givenString)
                .when(false)
                .then(mockedHelper::printLastChar)
                .orElseThrowE(new RuntimeException());

        verify(mockedHelper, times(0)).printLastChar(givenString);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void shouldThrowExceptionWithSupplier() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);
        String givenString = "abc";

        given(givenString)
                .when(false)
                .then(mockedHelper::printLastChar)
                .orElseThrow(RuntimeException::new);

        verify(mockedHelper, times(0)).printLastChar(givenString);
    }

    @Test
    public void shouldNotThrowException() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);
        String givenString = "abc";

        given(givenString)
                .when(true)
                .then(mockedHelper::printLastChar)
                .orElseThrowE(new RuntimeException());

        verify(mockedHelper, times(1)).printLastChar(givenString);
    }

    @Test
    public void shouldNotThrowExceptionWithSupplier() throws Exception {
        TestHelper mockedHelper = mock(TestHelper.class);
        String givenString = "abc";

        given(givenString)
                .when(true)
                .then(mockedHelper::printLastChar)
                .orElseThrow(RuntimeException::new);

        verify(mockedHelper, times(1)).printLastChar(givenString);
    }

    @Test
    public void shouldHaveStringConsumerInGiven() {
        TestHelper mockedHelper = mock(TestHelper.class);

        given(TestHelper::getAString)
                .when(false)
                .then(mockedHelper::printFirstChar)
                .orElse(mockedHelper::printLastChar);

        verify(mockedHelper, times(0)).printFirstChar(TestHelper.getAString());
        verify(mockedHelper, times(1)).printLastChar(TestHelper.getAString());
    }

    @Test
    public void shouldDoNothing() {
        TestHelper mockedHelper = mock(TestHelper.class);

        given(TestHelper::getAString)
                .when(false)
                .then(mockedHelper::printFirstChar)
                .orElse(doNothing());

        verify(mockedHelper, times(0)).printFirstChar(TestHelper.getAString());
    }

    @Test
    public void shouldReturnValueWhenTrue() {
        int result = given(TestHelper::getAString)
                .when(true)
                .thenReturn(String::length)
                .orElse(String::hashCode);
        assertEquals(result, TestHelper.getAString().length());
    }
}