package org.drools.reteoo;

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

import java.util.ArrayList;
import java.util.List;

import org.drools.common.DefaultFactHandle;
import org.drools.spi.PropagationContext;

public class MockObjectSink
    implements
    ObjectSink {
    private final List asserted  = new ArrayList();
    private final List retracted = new ArrayList();
    private final List modified  = new ArrayList();

    public void assertObject(final DefaultFactHandle handle,
                             final PropagationContext context,
                             final ReteooWorkingMemory workingMemory) {
        this.asserted.add( new Object[]{handle, context, workingMemory} );
    }

    public void retractObject(final DefaultFactHandle handle,
                              final PropagationContext context,
                              final ReteooWorkingMemory workingMemory) {
        this.retracted.add( new Object[]{handle, context, workingMemory} );
    }

    public void modifyObject(final DefaultFactHandle handle,
                             final PropagationContext context,
                             final ReteooWorkingMemory workingMemory) {
        this.modified.add( new Object[]{handle, context, workingMemory} );

    }

    public List getAsserted() {
        return this.asserted;
    }

    public List getRetracted() {
        return this.retracted;
    }

    public List getModified() {
        return this.modified;
    }

}