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

import org.drools.FactHandle;

/**
 * Implementation of <code>FactHandle</code>.
 * @author <a href="mailto:mark.proctor@jboss.com">Mark Proctor</a>
 * @author <a href="mailto:bob@werken.com">bob mcwhirter </a>
 */
public class DefaultFactHandle
    implements
    InternalFactHandle {
    // ----------------------------------------------------------------------
    // Instance members
    // ----------------------------------------------------------------------

    /**
     * 
     */
    private static final long serialVersionUID = 1035305202846367106L;
    /** Handle id. */
    private long        id;
    private long        recency;
    private Object      obj;
    private EqualityKey key;
    private int         objectHashCode;
    
    // ----------------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------------

    public DefaultFactHandle() {

    }

    public DefaultFactHandle(final long id,
                             final Object obj) {
        this.id = id;
        this.recency = id;
        this.obj = obj;
        this.objectHashCode = obj.hashCode();
    }

    /**
     * Construct.
     * 
     * @param id
     *            Handle id.
     */
    public DefaultFactHandle(final long id,
                             final Object obj,
                             final long recency) {
        this.id = id;
        this.recency = recency;
        this.obj = obj;
        this.objectHashCode = obj.hashCode();
    }

    // ----------------------------------------------------------------------
    // Instance members
    // ----------------------------------------------------------------------

    /**
     * @see Object
     */
    public boolean equals(final Object obj) {
        if ( this == obj ) {
            return true;
        }

        if ( obj == null || !(obj instanceof DefaultFactHandle) ) {
            return false;
        }

        return this.id == ((DefaultFactHandle) obj).id;
    }
    
    public  int getObjectHashCode()  {
        return this.objectHashCode;
    }

    /**
     * @see Object
     */
    public int hashCode() {
        return (int)  this.id;
    }

    /**
     * @see FactHandle
     */
    public String toExternalForm() {
        return "[fid:" + this.id + ":" + this.recency + "]";
    }

    /**
     * @see Object
     */
    public String toString() {
        return toExternalForm();
    }

    public long getRecency() {
        return this.recency;
    }

    public void setRecency(final long recency) {
        this.recency = recency;
    }

    public long getId() {
        return this.id;
    }

    public void invalidate() {
        this.id = -1;
        this.obj = null;
    }

    public Object getObject() {
        return this.obj;
    }

    public void setObject(final Object obj) {
        this.obj = obj;
    }

    /**
     * @return the key
     */
    public EqualityKey getEqualityKey() {
        return this.key;
    }

    /**
     * @param key the key to set
     */
    public void setEqualityKey(final EqualityKey key) {
        this.key = key;
    }
}
