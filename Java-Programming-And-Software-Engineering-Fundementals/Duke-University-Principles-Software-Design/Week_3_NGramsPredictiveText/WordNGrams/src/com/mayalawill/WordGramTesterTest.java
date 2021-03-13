package com.mayalawill;

/**
 * WordGram Class
 * Assignment 1: Complete WordGram
 *
 * @author Allos111
 * @version June, 2020
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordGramTesterTest
{
    private WordGramTester wgt;

    @Before
    public void setUp() throws Exception
    {
        wgt = new WordGramTester();
    }

    @After
    public void tearDown() throws Exception
    {
        wgt = null;
    }

    @Test
    public void testWordGramTest()
    {
        wgt.testWordGram();
    }

    @Test
    public void testWordGramEqualsTest()
    {
        wgt.testWordGramEquals();
    }

    @Test
    public void testshiftAddTest()
    {
        wgt.testshiftAdd();
    }
}

