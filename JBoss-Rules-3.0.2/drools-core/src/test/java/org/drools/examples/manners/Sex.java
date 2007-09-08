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
package org.drools.examples.manners;

public final class Sex {
    public static final Sex      M       = new Sex( 0 );
    public static final Sex      F       = new Sex( 1 );

    public static final String   stringM = "m";
    public static final String   stringF = "f";
    public static final String[] sexList = new String[]{Sex.stringM, Sex.stringF};

    private final int            sex;

    private Sex(final int sex) {
        this.sex = sex;
    }

    public final String getSex() {
        return Sex.sexList[this.sex];
    }

    public final static Sex resolve(final String sex) {
        if ( Sex.stringM.equals( sex ) ) {
            return Sex.M;
        } else if ( Sex.stringF.equals( sex ) ) {
            return Sex.F;
        } else {
            throw new RuntimeException( "Sex '" + sex + "' does not exist for Sex Enum" );
        }
    }

    public final String toString() {
        return getSex();
    }

    public final boolean equals(final Object object) {
        return this == object;
    }

    public final int hashCode() {
        return this.sex;
    }

}