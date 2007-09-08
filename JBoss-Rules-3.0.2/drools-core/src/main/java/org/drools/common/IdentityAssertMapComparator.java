package org.drools.common;
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

import org.drools.util.FastComparator;

public class IdentityAssertMapComparator extends FastComparator {
    /**
     * 
     */
    private static final long serialVersionUID = -4008762903660457691L;

    private final Class       factHandleClass;

    private final boolean     _rehash          = FastComparator.REHASH_SYSTEM_HASHCODE;

    public IdentityAssertMapComparator(final Class factHandleClass) {
        this.factHandleClass = factHandleClass;
    }

    public int hashCodeOf(final Object obj) {
        if (obj.getClass() == this.factHandleClass ) {
            return ( ( InternalFactHandle ) obj).getObjectHashCode();
        }
        return obj.hashCode();
    }

    /**
     * Special comparator that allows FactHandles to be keys, but always  checks
     * like for like.
     */
    public boolean areEqual(final Object o1,
                            final Object o2) {
        if ( o1.getClass() == this.factHandleClass ) {
            return  ( ( InternalFactHandle ) o1).getObject() == ( ( InternalFactHandle ) o2).getObject();
        }
        
        return o1 == ((InternalFactHandle) o2).getObject();
    }

    public int compare(final Object o1,
                       final Object o2) {
        return ((Comparable) o1).compareTo( o2 );
    }

    public String toString() {
        return "identity";
    }
}
