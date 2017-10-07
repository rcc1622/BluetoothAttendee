package com.csc_331_jagwares.bluetoothattendee.persistence;

import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by steven on 10/7/2017.
 */
public class AttendeeDatasourceTest {
    AttendeeDatasource datasource;

    @Before
    public void setUp() throws Exception {
        datasource = new AttendeeDatasource(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        datasource.close();
    }

    @Test
    public void studentExists() throws Exception {
        assertFalse(datasource.studentExists("J999999999"));
    }

}