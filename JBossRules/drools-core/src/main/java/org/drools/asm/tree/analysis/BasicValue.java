/***
 * ASM: a very small and fast Java bytecode manipulation framework
 * Copyright (c) 2000-2005 INRIA, France Telecom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.drools.asm.tree.analysis;

import org.drools.asm.Type;

/**
 * A {@link Value} that is represented by its type in a seven types type sytem.
 * This type system distinguishes the UNINITIALZED, INT, FLOAT, LONG, DOUBLE,
 * REFERENCE and RETURNADDRESS types.
 * 
 * @author Eric Bruneton
 */
public class BasicValue
    implements
    Value {

    public final static Value UNINITIALIZED_VALUE = new BasicValue( null );

    public final static Value INT_VALUE           = new BasicValue( Type.INT_TYPE );

    public final static Value FLOAT_VALUE         = new BasicValue( Type.FLOAT_TYPE );

    public final static Value LONG_VALUE          = new BasicValue( Type.LONG_TYPE );

    public final static Value DOUBLE_VALUE        = new BasicValue( Type.DOUBLE_TYPE );

    public final static Value REFERENCE_VALUE     = new BasicValue( Type.getType( "Ljava/lang/Object;" ) );

    public final static Value RETURNADDRESS_VALUE = new BasicValue( null );

    private Type              type;

    public BasicValue(final Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public int getSize() {
        return this.type == Type.LONG_TYPE || this.type == Type.DOUBLE_TYPE ? 2 : 1;
    }

    public boolean isReference() {
        return this.type != null && (this.type.getSort() == Type.OBJECT || this.type.getSort() == Type.ARRAY);
    }

    public boolean equals(final Object value) {
        if ( value == this ) {
            return true;
        } else if ( value instanceof BasicValue ) {
            if ( this.type == null ) {
                return ((BasicValue) value).type == null;
            } else {
                return this.type.equals( ((BasicValue) value).type );
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.type == null ? 0 : this.type.hashCode();
    }

    public String toString() {
        if ( this == BasicValue.UNINITIALIZED_VALUE ) {
            return ".";
        } else if ( this == BasicValue.RETURNADDRESS_VALUE ) {
            return "A";
        } else if ( this == BasicValue.REFERENCE_VALUE ) {
            return "R";
        } else {
            return this.type.getDescriptor();
        }
    }
}