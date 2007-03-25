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
using System.CodeDom;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Text;
using org.apache.commons.jci.compilers;
using System.Reflection;
using java.lang;
using org.apache.commons.jci.problems;
using org.apache.commons.jci.readers;
using org.apache.commons.jci.stores;
using System.IO;
using java.nio;
using java.nio.charset;
using System.CodeDom.Compiler;
using Microsoft.CSharp;
using org.drools.spi;
using org.drools.rule;
using System.Runtime.Remoting;
using org.drools.semantics.java;
using java.util;
using System.Reflection.Emit;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;

namespace org.drools.dotnet.semantics
{
    
    
    public class DotnetCSharpCompiler : AbstractJavaCompiler
    {
                
        #region JavaCompiler Members

        /*
        public CompilationResult compile(string[] strarr, org.apache.commons.jci.readers.ResourceReader rr, org.apache.commons.jci.stores.ResourceStore rs, java.lang.ClassLoader cl)
        {
            // call a compiler to produce an assembly.
            Assembly assembly = null;                        
            // call the AppDomain\            
            AppDomain domain = 
                ClassLoaderAppDomainResolver.instance.GetAppDomain(cl);
            domain.Load(assembly.FullName);

            return null;
        }
                  */

        public object[] GetLinePragmaInfo(string s, int offset)
        {
            int lineNumber = -1;
            string fileName = null;
            string[] sr = s.Split('\n');
            foreach (string line in sr)
            {
                if (line.StartsWith("/*LineStart:"))
                {
                    string[] subarr = line.Split(new char[]{':','/','*','\r'}, StringSplitOptions.RemoveEmptyEntries);
                    if (subarr.Length >= 2 && subarr[1] != "")
                    {
                        lineNumber = int.Parse(subarr[1]);
                        lineNumber -= offset;
                    }
                }
                if (line.StartsWith("/*FileName:"))
                {
                    string[] subarr = line.Split(new char[] {':','/','*','\r'}, StringSplitOptions.RemoveEmptyEntries);
                    if (subarr.Length >= 2 && subarr[1] != "")
                    {
                        fileName = subarr[1];
                    }
                }
            }
            if (fileName == null || lineNumber < 0) return null;
            return new object[] { fileName, lineNumber };
        }
        /**
         * Standard procedure to compile resources:
         * 1. Get an AppDomain correspondint to the Classloader.
         * 2. Create an assembly in the given appdomain
         * 3. Create a type for each resource, given the className (resourceName), contents 
              (pReader.getBytes(resourceName)), and the AppDomain.
         * 4.  Write the compiled types to the store.
         */
        public override CompilationResult compile(
            string[] pResourceNames,
            ResourceReader pReader,
            ResourceStore pStore,
            ClassLoader pClassLoader
            )
       {
            int OFFSETCONSTANT = 8;
            Type[] types = new Type[pResourceNames.Length];
            string[] contents = new string[pResourceNames.Length];
            CodeSnippetCompileUnit[] units = new CodeSnippetCompileUnit[pResourceNames.Length];
            for (int i = 0; i < types.Length; i++)
            {
                string resourceName = pResourceNames[i].Replace('.','/')+".java";
                byte[] byteArray = pReader.getBytes(resourceName);
                string fileContents = this.StringFromBytes(byteArray);                
                units[i] = new CodeSnippetCompileUnit(fileContents.Replace("cli.", ""));
                if (fileContents.Contains("public static void consequence"))
                {
                    object[] info = this.GetLinePragmaInfo(fileContents,OFFSETCONSTANT);
                    if(info != null)
                        units[i].LinePragma = new CodeLinePragma(info[0] as string, (int)info[1]);                    
                }
            }


            CodeDomProvider provider = GetProvider();
            CompilerParameters compilerParameters = new CompilerParameters();
            compilerParameters.GenerateInMemory = true;
            compilerParameters.IncludeDebugInformation = true;
            
//            compilerParameters.OutputAssembly = pResourceNames[i].Substring(pResourceNames[i].LastIndexOf('.') + 1);
            
            int count = 0;
            foreach (Assembly assembly in AppDomain.CurrentDomain.GetAssemblies())
            {

               if (assembly.FullName.StartsWith("CompiledRules"))
                {
                    try
                    {
                        File.Delete(assembly.Location);
                    }
                    catch (System.Exception e)
                    {
                        count++;
                    }
                }
                else
                {
                    compilerParameters.ReferencedAssemblies.Add(assembly.Location);
                }

            }

            compilerParameters.OutputAssembly = "CompiledRules" + count + ".dll";

            CompilerResults results = provider.CompileAssemblyFromDom
                //(compilerParameters, contents);
                (compilerParameters, units);
            
            Collection problems = new ArrayList();
            
            DotnetPackageCompilationData pcData = (DotnetPackageCompilationData)
                ((PackageStore)pStore).getPackageCompilationData();

            MemoryStream stream = new MemoryStream(1024);
            BinaryFormatter formatter = new BinaryFormatter();
            formatter.Serialize(stream, results.CompiledAssembly);
            
            for (int i = 0; i < types.Length; i++)
            {
                string resourceName = pResourceNames[i];
                pcData.write(resourceName, new object[]{results.CompiledAssembly, stream.GetBuffer()});

            }
            CompilationProblem[] result = new CompilationProblem[problems.size()];
            return new CompilationResult(result);
        }

        #endregion

        private static CodeDomProvider GetProvider()
        {
            string providerName = System.Configuration.ConfigurationManager.AppSettings["drools.dotnet.codedomprovider"];

            if (providerName != null && providerName.Trim().Length > 0)
            {
                Type providerType = Type.GetType(providerName);
                if (providerType == null)
                {
                    return null;
                    //throw new CodeGenerationException("Unable to find CodeDomProvider [" +
                      //  providerName + "]");
                }
                return (CodeDomProvider)Activator.CreateInstance(providerType);
            }
            else
            {
                return new CSharpCodeProvider();
            }

        }
        
        public string StringFromBytes(byte[] byteArray)
        {
            
            //char[] characters = (new UnicodeEncoding()).GetChars(byteArray);
            /*
            java.io.StringWriter writer = new java.io.StringWriter();
            writer.write(characters);
            
            StringBuffer javabuffer = new StringBuffer();
            javabuffer.append(characters);
            return javabuffer.toString();
            */
            ByteBuffer bb = ByteBuffer.wrap(byteArray);
            //CharBuffer cb = bb.asCharBuffer();
            StringBuffer javabuffer = new StringBuffer();
            char[] ca = Charset.defaultCharset().decode(bb).array();
            javabuffer.append(ca);
            string str0 = javabuffer.toString();
            return str0;

            /*
            javabuffer = new StringBuffer();
            CharBuffer cb = bb.asCharBuffer();
            ca = cb.array();
            javabuffer.append(ca);
            string str1 = javabuffer.toString();
            return str1;
             */
        }
        
    }
}
