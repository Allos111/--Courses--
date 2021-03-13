package com.mayalawill;

/**
 * Generating Random Text
 * Assignment 1: MarkovZero and MarkovOne
 * Assignment 2: MarkovFour and MarkovModel
 *
 * @author Allo111
 * @version July, 2020
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesterTest {
    private Tester t;

    @Before
    public void setUp() throws Exception {
        t = new Tester();
    }

    @After
    public void tearDown() throws Exception {
        t = null;
    }

    @Test
    public void GetFollowstest() {
        t.testGetFollows();
    }

    @Test
    public void GetFollowsWithFileTest() {
        t.testGetFollowsWithFile();
    }
}

