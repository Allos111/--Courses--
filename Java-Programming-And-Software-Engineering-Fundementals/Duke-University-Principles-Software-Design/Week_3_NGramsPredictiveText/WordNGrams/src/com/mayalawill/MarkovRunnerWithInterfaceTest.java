package com.mayalawill;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MarkovRunnerWithInterfaceTest {
    private MarkovRunnerWithInterface mwf;

    @Before
    public void setUp() throws Exception {
        mwf = new MarkovRunnerWithInterface();
    }

    @After
    public void tearDown() throws Exception {
        mwf = null;
    }

    @Test
    public void runMarkovTest() {
        mwf.runMarkov();
    }

    @Test
    public void testHashMapTest() {
        mwf.testHashMap();
    }

    @Test
    public void compareMethodsTest() {
        mwf.compareMethods();
    }

    @Test
    public void testQuizTest() {
        mwf.testQuiz();
    }

}

