package org.drools.semantics.java;

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

import junit.framework.TestCase;

public class FunctionFixerTest extends TestCase {

    private static final FunctionFixer fixer = new FunctionFixer();

    public void testSimple() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "Func.func()",
                      fixer.fix( "func( )" ) );
    }

    public void testSimpleWithPArams() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "Func.func(yyy, iii)",
                      fixer.fix( "func(yyy, iii)" ) );
    }

    public void testMoreComplex() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "xxx Func.func(yyy, iii) yyy",
                      fixer.fix( "xxx func(yyy, iii) yyy" ) );
    }

    public void testLeaveAloneNew() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "new Integer (5)",
                      fixer.fix( "new Integer (5)" ) );
    }

    public void testLeaveAloneDrools() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "xxx drools.org(iii) Func.func(yyy, iii) yyy",
                      fixer.fix( "xxx drools.org(iii) func(yyy, iii) yyy" ) );
    }

    public void testWorkWithDotAll() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "\n\t\n\tAddFive.addFive(list) ;",
                      fixer.fix( "\n\t\n\taddFive ( list ) ;" ) );
    }

    public void testWithDollarSigns() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "\nFoo.foo($list);",
                      fixer.fix( "\nfoo($list);" ) );
    }

    public void testReservedWordsInJava() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "\nfor(int i=0; i < 2; i++) { /*do noithing*/ }",
                      fixer.fix( "\nfor(int i=0; i < 2; i++) { /*do noithing*/ }" ) );
    }

    public void testMultipleInABracket() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "if (Foo.foo(bar)) { Bar.bar(baz); }",
                      fixer.fix( "if (foo(bar)) { bar(baz); }" ) );
    }

    public void testInBrackets() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "if (Foo.foo(bar))",
                      fixer.fix( "if (foo(bar))" ) );
    }

    public void testAlreadyAdded() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "Foo.foo(bar)",
                      fixer.fix( "Foo.foo(bar)" ) );
    }

    public void testInString() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "\"if (foo(bar))\"",
                      fixer.fix( "\"if (foo(bar))\"" ) );
    }

    public void testComplexWithBrackets() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "System.out.println(\"foo(\" + Foo.foo(bar) + Bar.bar(baz)",
                      fixer.fix( "System.out.println(\"foo(\" + foo(bar) + bar(baz)" ) );
    }
    
    public void testXPath() {
        final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "foo.executeXpath(\"//node1/node2/text()\")",
                      fixer.fix("foo.executeXpath(\"//node1/node2/text()\")" ) );
      }
      
      public void testExpressionGrouping() {
       final FunctionFixer fixer = new FunctionFixer();
        assertEquals( "while((foo = bar.baz()) != null)",
                      fixer.fix( "while((foo = bar.baz()) != null)" ) );
      }     

}