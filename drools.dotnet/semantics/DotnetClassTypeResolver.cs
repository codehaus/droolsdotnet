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
using System.Collections;

using org.drools.spi;
using org.drools.semantics.java;
using System.Reflection;
using java.util;
using java.lang;


namespace org.drools.dotnet.semantics
{
    public class DotnetClassTypeResolver : ClassTypeResolver
    {


        private readonly List imports;

        private readonly AppDomain appDomain;

        private System.Collections.Hashtable cachedImports = new System.Collections.Hashtable();

        /*public DotnetClassTypeResolver(List imports)
            : base(null)
        {
            new DotnetClassTypeResolver(imports,
                  null);
        }*/

        public DotnetClassTypeResolver(List imports, ClassLoader appDomain)
            : base(null, null)
        {
            this.imports = imports;

            if (this.appDomain == null)
            {
                this.appDomain = AppDomain.CurrentDomain;
            }
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.drools.semantics.base.Importer#getImports( Class clazz )
         */
        /* (non-Javadoc)
         * @see org.drools.semantics.java.TypeResolver#getImports()
         */
        public override List getImports()
        {
            return this.imports;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.drools.semantics.base.Importer#addImports(org.drools.spi.ImportEntry)
         */
        /* (non-Javadoc)
         * @see org.drools.semantics.java.TypeResolver#addImport(java.lang.String)
         */
        public override void addImport(string importEntry)
        {
            if (!this.imports.contains(importEntry))
            {
                this.imports.add(importEntry);
            }
        }

        public Type lookupFromCache(string className)
        {
            object objs = this.cachedImports[className];
            if (objs != null)
                return objs.GetType();
            else
                return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.drools.semantics.base.Importer#importClass(java.lang.ClassLoader,
         *      java.lang.String)
         */
        /* (non-Javadoc)
         * @see org.drools.semantics.java.TypeResolver#resolveType(java.lang.String)
         */
        public override Class resolveType(string className)
        {
            Type type = resolveDotnetType(className);
            try
            {
                return Class.forName(type.AssemblyQualifiedName);
            }
            catch (System.Exception e)
            {
                return Class.forName("cli." + type.FullName);
            }
            
                
        }

        public Type resolveDotnetType(string className)
        {
            Type clazz = null;

            // Now try the package object type cache         
            clazz = lookupFromCache(className);

            // try loading className
            if (clazz == null)
            {
                try
                {
                    clazz = Type.GetType(className);

                }
                catch (System.Exception e)
                {
                    clazz = null;
                }
            }
            
            if (clazz == null)
            {

                System.Collections.ArrayList validTypes = new System.Collections.ArrayList();
                //check is the type is in any referenced assemblies
                foreach (Assembly assembly in appDomain.GetAssemblies())
                {
                    clazz = assembly.GetType(className);
                    if (clazz != null) validTypes.Add(clazz);
                }
                if (clazz == null)
                {
                    //check with imports
                    Iterator i = imports.iterator();
                    while (i.hasNext())
                    {
                        string testTypeName = (string)i.next() + "." + className;
                        foreach (Assembly assembly in appDomain.GetAssemblies())
                        {
                            clazz = assembly.GetType(testTypeName);
                            if (clazz != null) validTypes.Add(clazz);
                        }
                    }
                }

                if (validTypes.Count > 1)
                {
                    throw new System.Exception("Ambiguous class reference : " + className);
                }
                else if (validTypes.Count == 1)
                {
                    clazz = (Type)validTypes[0];
                }
                else
                {
                    clazz = null;
                }
            }

            return clazz;
        }

        public bool isEmpty()
        {
            return (this.imports.size() == 0);
        }

        
    }

}
