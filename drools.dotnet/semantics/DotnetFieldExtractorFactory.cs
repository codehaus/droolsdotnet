/** 
* Copyright (c) 2007, Ritu Jain, Chinmay Nagarkar and Sahi Technologies Pvt Ltd
* All rights reserved.
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the Sahi Technologies Pvt. Ltd./Esahi.com  nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
using System;
using System.Collections.Generic;
using System.Text;
using java.lang;
using org.drools.@base;
using System.Reflection;
using Microsoft.CSharp;
using System.CodeDom.Compiler;
using System.CodeDom;

namespace org.drools.dotnet.semantics
{
    public class DotnetClassFieldExtractorFactory : org.drools.@base.ClassFieldExtractorFactory
    {
        

        public static readonly string BASE_PACKAGE = "org/drools/base";

        

        public static new BaseClassFieldExtractor  getClassFieldExtractor(Class clazz,
                                                                       string fieldName)
        {
                return new DotnetBaseClassFieldExtractor(clazz, fieldName);
        }


         /**
          * Return the classObjectType, allowing for the fact that it will be autoboxed if it is a primitive.
          */
        public static ClassObjectType getClassObjectType(Type fieldType)
        {
            Type returnClass = null;
            // autobox primitives
            if ( fieldType.IsPrimitive) {
                if ( fieldType == typeof(char) ) {
                    returnClass = typeof(System.Char);
                } else if ( fieldType == typeof(sbyte) ) {
                    returnClass = typeof(System.SByte);
                } else if ( fieldType == typeof(short) ) {
                    returnClass = typeof(System.Int16);
                } else if ( fieldType == typeof(int) ) {
                    returnClass = typeof(System.Int32);
                } else if ( fieldType == typeof(long) ) {
                    returnClass = typeof(System.Int64);
                } else if ( fieldType == typeof(float) ) {
                    returnClass = typeof(System.Single);
                } else if ( fieldType == typeof(double) ) {
                    returnClass = typeof(System.Double);
                } else if ( fieldType == typeof(bool) ) {
                    returnClass = typeof(System.Boolean);
                }
            } else {
                returnClass = fieldType;
            }

            return new DotnetClassObjectType( returnClass );
        }

        /*private static String getTypeName(final Class fieldType) {
            String ret = null;

            if ( fieldType.isPrimitive() ) {
                if ( fieldType == char.class ) {
                    ret = "java/lang/Character";
                } else if ( fieldType == byte.class ) {
                    ret = "java/lang/Byte";
                } else if ( fieldType == short.class ) {
                    ret = "java/lang/Short";
                } else if ( fieldType == int.class ) {
                    ret = "java/lang/Integer";
                } else if ( fieldType == long.class ) {
                    ret = "java/lang/Long";
                } else if ( fieldType == float.class ) {
                    ret = "java/lang/Float";
                } else if ( fieldType == double.class ) {
                    ret = "java/lang/Double";
                } else if ( fieldType == boolean.class ) {
                    ret = "java/lang/Boolean";
                }
            } else {
                ret = fieldType.getName().replace( '.',
                                                   '/' );
            }

            return ret;
        }

        private static String getPrimitiveTag(final Class fieldType) {
            String ret = null;
            if ( fieldType == char.class ) {
                ret = "C";
            } else if ( fieldType == byte.class ) {
                ret = "B";
            } else if ( fieldType == short.class ) {
                ret = "S";
            } else if ( fieldType == int.class ) {
                ret = "I";
            } else if ( fieldType == long.class ) {
                ret = "J";
            } else if ( fieldType == float.class ) {
                ret = "F";
            } else if ( fieldType == double.class ) {
                ret = "D";
            } else if ( fieldType == boolean.class ) {
                ret = "Z";
            }

            return ret;
        }*/

        /**
         * Simple classloader
         * @author Michael Neale
         */
        /*static class ByteArrayClassLoader extends ClassLoader {
            public ByteArrayClassLoader(final ClassLoader parent) {
                super( parent );
            }

            public Class defineClass(final String name,
                                     final byte[] bytes) {
                return defineClass( name,
                                    bytes,
                                    0,
                                    bytes.length );
            }
        }
        }*/
    }
}
