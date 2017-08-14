package com.gawdski;

public class TestHelper {
    static boolean somethingIsTrue() {
        return true;
    }

    static boolean somethingIsFalse() { return false; }

    static int getHighNumber() {
        return 1000;
    }

    static int getLowNumber() {
        return 1;
    }

    static String getAString() {
        return "a string";
    }

    void printFoo() {
        System.out.println("Foo");
    }

    void printBar() {
        System.out.println("Bar");
    }

    static RuntimeException createException() {
        return new RuntimeException();
    }

    void printFirstChar(String string) {
        System.out.println(string.charAt(0));
    }

    void printLastChar(String string) {
        System.out.println(string.charAt(string.length()-1));
    }

    static AnotherClass extractMessageForHighNumber(SomeClass someClass) {
        return new AnotherClass(someClass.getMessageForHighNumber());
    }

    static AnotherClass extractMessageForLowNumber(SomeClass someClass) {
        return new AnotherClass(someClass.getMessageForLowNumber());
    }
}

class SomeClass {
    String getMessageForHighNumber() {
        return "I'm so high";
    }

    String getMessageForLowNumber() {
        return "I'm so low";
    }
}

class AnotherClass {
    final String message;

    AnotherClass(String message) {
        this.message = message;
    }
}
