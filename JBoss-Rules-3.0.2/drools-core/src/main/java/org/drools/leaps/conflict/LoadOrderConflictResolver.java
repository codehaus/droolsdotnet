package org.drools.leaps.conflict;

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

import java.io.Serializable;
import java.util.Comparator;

import org.drools.common.DefaultFactHandle;

/**
 * <code>LoadOrderConflictResolver</code> that uses the load order of rules to
 * resolve conflict.
 * 
 * @author Alexander Bagerman
 * 
 * @see org.drools.leaps.conflict.AbstractConflictResolver
 * @see org.drools.leaps.ConflictResolver
 * @see org.drools.spi.ConflictResolver
 */
public class LoadOrderConflictResolver
    implements
    Comparator,
    Serializable {

    /** Singleton instance. */
    private static final LoadOrderConflictResolver INSTANCE = new LoadOrderConflictResolver();

    /**
     * Retrieve the singleton instance.
     * 
     * @return The singleton instance.
     */
    public static Comparator getInstance() {
        return LoadOrderConflictResolver.INSTANCE;
    }

    /**
     * Construct.
     */
    private LoadOrderConflictResolver() {
        // intentionally left blank
    }

    /**
     * @see LeapsRuleConflictResolver
     */
    public int compare(final Object o1,
                       final Object o2) {
        return (-1) * AbstractConflictResolver.compare( ((DefaultFactHandle) o1).getRecency(),
                                                        ((DefaultFactHandle) o2).getRecency() );
    };
}