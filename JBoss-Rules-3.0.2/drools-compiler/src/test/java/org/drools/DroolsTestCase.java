package org.drools;

/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

public abstract class DroolsTestCase extends TestCase {
    public DroolsTestCase() {
        super();
    }

    public DroolsTestCase(final String name) {
        super( name );
    }

    public void assertLength(final int len,
                             final Object[] array) {
        assertEquals( Arrays.asList( array ) + " does not have length of " + len,
                      len,
                      array.length );
    }

    public void assertLength(final int len,
                             final Collection collection) {
        assertEquals( collection + " does not have length of " + len,
                      len,
                      collection.size() );
    }

    public void assertContains(final Object obj,
                               final Object[] array) {
        for ( int i = 0; i < array.length; ++i ) {
            if ( array[i] == obj ) {
                return;
            }
        }

        fail( Arrays.asList( array ) + " does not contain " + obj );
    }

    public void assertContains(final Object obj,
                               final Collection collection) {
        assertTrue( collection + " does not contain " + obj,
                    collection.contains( obj ) );
    }

}