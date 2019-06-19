package com.aaroncoplan.todoist;

import org.junit.Assert;
import org.junit.Test;

public class TodoistTest {
    @Test public void testSomeLibraryMethod() {
        Todoist todoist = new Todoist("TOKEN");
        todoist.doTest();
        Assert.assertFalse(true);
    }
}
