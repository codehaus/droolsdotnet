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
using org.drools.rule;
using java.lang;
using System.Reflection;
using System.Runtime.Remoting;
using org.drools.spi;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

namespace org.drools.dotnet.semantics
{
    [Serializable]
    public class DotnetPackageCompilationData :PackageCompilationData
    {
        //private Assembly assem;

        public DotnetPackageCompilationData()
            : base(null)
        { }

        public DotnetPackageCompilationData(ClassLoader parentClassLoader) : base(parentClassLoader)
        {}

        /*public void write(string resourceName, Assembly compiledAssembly)
        {
            
            this.assem = compiledAssembly;
            wire(resourceName);
        }*/

        public override void loadAssemblies()
        {
            java.util.Set set = this.assemblySerializationInfoStore.keySet();
            object[] objs = set.toArray();
            
            BinaryFormatter formatter = new BinaryFormatter();
            
            foreach(object obj in objs)
            {
                Assembly assem = (Assembly)formatter.Deserialize(new MemoryStream((byte[])
                                    (this.assemblySerializationInfoStore.get(obj))));
                this.store.put(obj, assem);
            }
            
        }
        public override void wire(string className,
                     object invoker) 
        {
            if (invoker == null)
                return;

            Assembly assem = (Assembly)this.store.get(className.Replace('.',
                                                   '/') + ".class");
            
            object obj = assem.CreateInstance(className);
            //ObjectHandle handle =AppDomain.CurrentDomain.CreateInstance(this.assem.FullName, className);
            //ObjectHandle handle = ClassLoaderAppDomainResolver.instance.GetAppDomain(this.getClassLoader()).CreateInstanceFrom(this.assem.Location, className);
            if ( invoker.GetType()==typeof(ReturnValueConstraint) ) {
                ((ReturnValueConstraint)invoker).setReturnValueExpression((ReturnValueExpression)obj) ;//handle.Unwrap());
            } else if ( invoker.GetType() == typeof(PredicateConstraint) ) {
                ((PredicateConstraint)invoker).setPredicateExpression((PredicateExpression)obj) ;//handle.Unwrap());
            } else if ( invoker.GetType() == typeof(EvalCondition) ) {
                ((EvalCondition)invoker).setEvalExpression((EvalExpression)obj);//handle.Unwrap());
            } else if ( invoker.GetType() == typeof(Rule) ) {
                ((Rule)invoker).setConsequence((Consequence)obj);//handle.Unwrap());
            }
        }
    }
}
