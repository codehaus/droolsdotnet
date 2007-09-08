package org.drools.lang;

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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.drools.compiler.DrlParser;
import org.drools.lang.descr.AndDescr;
import org.drools.lang.descr.AttributeDescr;
import org.drools.lang.descr.BoundVariableDescr;
import org.drools.lang.descr.ColumnDescr;
import org.drools.lang.descr.EvalDescr;
import org.drools.lang.descr.ExistsDescr;
import org.drools.lang.descr.FieldBindingDescr;
import org.drools.lang.descr.FunctionDescr;
import org.drools.lang.descr.LiteralDescr;
import org.drools.lang.descr.NotDescr;
import org.drools.lang.descr.OrDescr;
import org.drools.lang.descr.PackageDescr;
import org.drools.lang.descr.PredicateDescr;
import org.drools.lang.descr.QueryDescr;
import org.drools.lang.descr.ReturnValueDescr;
import org.drools.lang.descr.RuleDescr;
import org.drools.lang.dsl.DefaultExpanderResolver;

public class RuleParserTest extends TestCase {

    private RuleParser parser;

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testPackage_OneSegment() throws Exception {
        final String packageName = parse( "package foo" ).package_statement();
        assertEquals( "foo",
                      packageName );
        assertFalse( this.parser.hasErrors() );
    }

    public void testPackage_MultipleSegments() throws Exception {
        final String packageName = parse( "package foo.bar.baz;" ).package_statement();
        assertEquals( "foo.bar.baz",
                      packageName );
        assertFalse( this.parser.hasErrors() );
    }

    public void testProlog() throws Exception {
        parse( "package foo; import com.foo.Bar; import com.foo.Baz;" ).prolog();
        assertEquals( "foo",
                      this.parser.getPackageDescr().getName() );
        assertEquals( 2,
                      this.parser.getPackageDescr().getImports().size() );
        assertEquals( "com.foo.Bar",
                      this.parser.getPackageDescr().getImports().get( 0 ) );
        assertEquals( "com.foo.Baz",
                      this.parser.getPackageDescr().getImports().get( 1 ) );
        assertFalse( this.parser.hasErrors() );
    }

    public void testEmptyRule() throws Exception {
        final RuleDescr rule = parseResource( "empty_rule.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "empty",
                      rule.getName() );
        assertNull( rule.getLhs() );
        assertNull( rule.getConsequence() );

        assertFalse( this.parser.hasErrors() );
    }

    public void FIXME_testKeywordCollisions() throws Exception {
        //MN: this really needs the multiphase parser for it to work properly
        final RuleParser parser = parseResource( "eol_funny_business.drl" );

        parser.compilation_unit();
        final PackageDescr pkg = parser.getPackageDescr();

        assertEquals( 1,
                      pkg.getRules().size() );

        assertFalse( parser.hasErrors() );

    }

    public void testLatinChars() throws Exception {
        final DrlParser parser = new DrlParser();
        final Reader drl = new InputStreamReader( this.getClass().getResourceAsStream( "latin-sample.drl" ) );
        final Reader dsl = new InputStreamReader( this.getClass().getResourceAsStream( "latin.dsl" ) );

        final PackageDescr pkg = parser.parse( drl,
                                         dsl );
        assertFalse( parser.hasErrors() );
        assertEquals( "br.com.auster.drools.sample",
                      pkg.getName() );
        assertEquals( 1,
                      pkg.getRules().size() );

    }

    public void testFunctionWithArrays() throws Exception {
        final DrlParser parser = new DrlParser();
        final Reader drl = new InputStreamReader( this.getClass().getResourceAsStream( "function_arrays.drl" ) );

        final PackageDescr pkg = parser.parse( drl );
        assertFalse( parser.hasErrors() );
        assertEquals( "foo",
                      pkg.getName() );
        assertEquals( 1,
                      pkg.getRules().size() );

        final RuleDescr rule = (RuleDescr) pkg.getRules().get( 0 );

        assertEqualsIgnoreWhitespace( "yourFunction(new String[3] {\"a\",\"b\",\"c\"});",
                                      rule.getConsequence() );
        final FunctionDescr func = (FunctionDescr) pkg.getFunctions().get( 0 );

        assertEquals( "String[]",
                      func.getReturnType() );
        assertEquals( "args[]",
                      func.getParameterNames().get( 0 ) );
        assertEquals( "String",
                      func.getParameterTypes().get( 0 ) );
    }

    public void testAlmostEmptyRule() throws Exception {
        final RuleDescr rule = parseResource( "almost_empty_rule.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "almost_empty",
                      rule.getName() );
        assertNotNull( rule.getLhs() );
        assertEquals( "",
                      rule.getConsequence().trim() );
        assertFalse( this.parser.hasErrors() );
    }

    public void testQuotedStringNameRule() throws Exception {
        final RuleDescr rule = parseResource( "quoted_string_name_rule.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "quoted string name",
                      rule.getName() );
        assertNotNull( rule.getLhs() );
        assertEquals( "",
                      rule.getConsequence().trim() );
        assertFalse( this.parser.hasErrors() );
    }

    public void testNoLoop() throws Exception {
        final RuleDescr rule = parseResource( "no-loop.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "rule1",
                      rule.getName() );
        final AttributeDescr att = (AttributeDescr) rule.getAttributes().get( 0 );
        assertEquals( "false",
                      att.getValue() );
        assertEquals( "no-loop",
                      att.getName() );
        assertFalse( this.parser.hasErrors() );

    }

    public void testAutofocus() throws Exception {
        final RuleDescr rule = parseResource( "autofocus.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "rule1",
                      rule.getName() );
        final AttributeDescr att = (AttributeDescr) rule.getAttributes().get( 0 );
        assertEquals( "true",
                      att.getValue() );
        assertEquals( "auto-focus",
                      att.getName() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testConsequenceWithDeclaration() throws Exception {
        final RuleDescr rule = parseResource( "declaration-in-consequence.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "myrule",
                      rule.getName() );

        final String expected = "int i = 0; i = 1; i / 1; i == 1; i(i); i = 'i'; i.i.i; i\\i; i<i; i>i; i=\"i\";  ++i;" + "i++; --i; i--; i += i; i -= i; i *= i; i /= i;" + "int i = 5;" + "for(int j; j<i; ++j) {" + "System.out.println(j);}"
                          + "Object o = new String(\"Hello\");" + "String s = (String) o;";

        assertEqualsIgnoreWhitespace( expected,
                                      rule.getConsequence() );
        assertTrue( rule.getConsequence().indexOf( "++" ) > 0 );
        assertTrue( rule.getConsequence().indexOf( "--" ) > 0 );
        assertTrue( rule.getConsequence().indexOf( "+=" ) > 0 );
        assertTrue( rule.getConsequence().indexOf( "==" ) > 0 );

        //System.out.println(rule.getConsequence());
        //note, need to assert that "i++" is preserved as is, no extra spaces.

        assertFalse( this.parser.hasErrors() );
    }

    public void testLiteralBoolAndNegativeNumbersRule() throws Exception {
        final RuleParser parser = parseResource( "literal_bool_and_negative.drl" );
        final RuleDescr rule = parser.rule();
        assertFalse( parser.hasErrors() );

        assertNotNull( rule );

        assertEquals( "simple_rule",
                      rule.getName() );
        assertNotNull( rule.getLhs() );
        assertEqualsIgnoreWhitespace( "cons();",
                                      rule.getConsequence() );

        final AndDescr lhs = rule.getLhs();
        assertEquals( 3,
                      lhs.getDescrs().size() );

        ColumnDescr col = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( 1,
                      col.getDescrs().size() );
        LiteralDescr lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( "==",
                      lit.getEvaluator() );
        assertEquals( "false",
                      lit.getText() );
        assertEquals( "bar",
                      lit.getFieldName() );
        assertEquals( false,
                      lit.isStaticFieldValue() );

        col = (ColumnDescr) lhs.getDescrs().get( 1 );
        assertEquals( 1,
                      col.getDescrs().size() );
        lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( ">",
                      lit.getEvaluator() );
        assertEquals( "-42",
                      lit.getText() );
        assertEquals( "boo",
                      lit.getFieldName() );

        col = (ColumnDescr) lhs.getDescrs().get( 2 );
        assertEquals( 1,
                      col.getDescrs().size() );
        lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( ">",
                      lit.getEvaluator() );
        assertEquals( "-42.42",
                      lit.getText() );
        assertEquals( "boo",
                      lit.getFieldName() );

        assertFalse( parser.hasErrors() );
    }

    public void testChunkWithoutParens() throws Exception {
        final String chunk = parse( "foo" ).paren_chunk();

        assertEquals( "foo",
                      chunk );

        assertFalse( this.parser.hasErrors() );
    }

    public void testChunkWithParens() throws Exception {
        final String chunk = parse( "fnord()" ).paren_chunk();

        assertEqualsIgnoreWhitespace( "fnord()",
                                      chunk );

        assertFalse( this.parser.hasErrors() );
    }

    public void testChunkWithParensAndQuotedString() throws Exception {
        final String chunk = parse( "fnord(\"cheese\")" ).paren_chunk();

        assertEqualsIgnoreWhitespace( "fnord(\"cheese\")",
                                      chunk );

        assertFalse( this.parser.hasErrors() );
    }

    public void testChunkWithRandomCharac5ters() throws Exception {
        final String chunk = parse( "%*9dkj" ).paren_chunk();

        assertEqualsIgnoreWhitespace( "%*9dkj",
                                      chunk );

        assertFalse( this.parser.hasErrors() );
    }

    public void testEmptyColumn() throws Exception {
        parseResource( "test_EmptyColumn.drl" );
        this.parser.compilation_unit();
        final PackageDescr packageDescr = this.parser.getPackageDescr();
        assertEquals( 1,
                      packageDescr.getRules().size() );
        final RuleDescr ruleDescr = (RuleDescr) packageDescr.getRules().get( 0 );
        assertEquals( "simple rule",
                      ruleDescr.getName() );
        assertNotNull( ruleDescr.getLhs() );
        assertEquals( 1,
                      ruleDescr.getLhs().getDescrs().size() );
        final ColumnDescr columnDescr = (ColumnDescr) ruleDescr.getLhs().getDescrs().get( 0 );
        assertEquals( 0,
                      columnDescr.getDescrs().size() ); //this may be null, not sure as the test doesn't get this far...
        assertEquals( "Cheese",
                      columnDescr.getObjectType() );

    }

    public void testSimpleRule() throws Exception {
        final RuleDescr rule = parseResource( "simple_rule.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "simple_rule",
                      rule.getName() );

        assertEquals( 7,
                      rule.getConsequenceLine() );
        assertEquals( 2,
                      rule.getConsequenceColumn() );

        final AndDescr lhs = rule.getLhs();

        assertNotNull( lhs );

        assertEquals( 3,
                      lhs.getDescrs().size() );

        //System.err.println( lhs.getDescrs() );

        // Check first column
        final ColumnDescr first = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "foo3",
                      first.getIdentifier() );
        assertEquals( "Bar",
                      first.getObjectType() );

        assertEquals( 1,
                      first.getDescrs().size() );

        LiteralDescr constraint = (LiteralDescr) first.getDescrs().get( 0 );

        assertNotNull( constraint );

        assertEquals( "a",
                      constraint.getFieldName() );
        assertEquals( "==",
                      constraint.getEvaluator() );
        assertEquals( "3",
                      constraint.getText() );

        // Check second column
        final ColumnDescr second = (ColumnDescr) lhs.getDescrs().get( 1 );
        assertEquals( "foo4",
                      second.getIdentifier() );
        assertEquals( "Bar",
                      second.getObjectType() );

        assertEquals( 2,
                      second.getDescrs().size() );

        //System.err.println( second.getDescrs() );

        final FieldBindingDescr fieldBindingDescr = (FieldBindingDescr) second.getDescrs().get( 0 );
        assertEquals( "a",
                      fieldBindingDescr.getFieldName() );
        assertEquals( "a4",
                      fieldBindingDescr.getIdentifier() );

        constraint = (LiteralDescr) second.getDescrs().get( 1 );

        assertNotNull( constraint );

        assertEquals( "a",
                      constraint.getFieldName() );
        assertEquals( "==",
                      constraint.getEvaluator() );
        assertEquals( "4",
                      constraint.getText() );

        // Check third column
        final ColumnDescr third = (ColumnDescr) lhs.getDescrs().get( 2 );
        assertNull( third.getIdentifier() );
        assertEquals( "Baz",
                      third.getObjectType() );

        assertEqualsIgnoreWhitespace( "if ( a == b ) { " + "  assert( foo3 );" + "} else {" + "  retract( foo4 );" + "}" + "  System.out.println( a4 );",
                                      rule.getConsequence() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testLineNumberInAST() throws Exception {
        //also see testSimpleExpander to see how this works with an expander (should be the same). 

        final RuleDescr rule = parseResource( "simple_rule.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "simple_rule",
                      rule.getName() );

        assertEquals( 7,
                      rule.getConsequenceLine() );
        assertEquals( 2,
                      rule.getConsequenceColumn() );

        final AndDescr lhs = rule.getLhs();

        assertNotNull( lhs );

        assertEquals( 3,
                      lhs.getDescrs().size() );

        // Check first column
        final ColumnDescr first = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "foo3",
                      first.getIdentifier() );
        assertEquals( "Bar",
                      first.getObjectType() );
        assertEquals( 1,
                      first.getDescrs().size() );

        // Check second column
        final ColumnDescr second = (ColumnDescr) lhs.getDescrs().get( 1 );
        assertEquals( "foo4",
                      second.getIdentifier() );
        assertEquals( "Bar",
                      second.getObjectType() );

        final ColumnDescr third = (ColumnDescr) lhs.getDescrs().get( 2 );
        assertEquals( "Baz",
                      third.getObjectType() );

        assertEquals( 4,
                      first.getLine() );
        assertEquals( 5,
                      second.getLine() );
        assertEquals( 6,
                      third.getLine() );
        assertFalse( this.parser.hasErrors() );
    }

    public void testMultiBindings() throws Exception {
        final RuleDescr rule = parseResource( "multiple_bindings.drl" ).rule();
        assertNotNull( rule );
        assertEquals( "simple_rule",
                      rule.getName() );

        assertEquals( 2,
                      rule.getLhs().getDescrs().size() );
        assertEquals( "foo",
                      ((ColumnDescr) rule.getLhs().getDescrs().get( 0 )).getIdentifier() );
        assertEquals( "baz",
                      ((ColumnDescr) rule.getLhs().getDescrs().get( 1 )).getIdentifier() );

    }

    public void testMultiBindingsMore() throws Exception {
        final RuleDescr rule = parseResource( "multiple_bindings_more.drl" ).rule();
        assertNotNull( rule );
        assertEquals( "simple_rule",
                      rule.getName() );

        assertEquals( 3,
                      rule.getLhs().getDescrs().size() );
        assertEquals( "foo",
                      ((ColumnDescr) rule.getLhs().getDescrs().get( 0 )).getIdentifier() );
        assertEquals( "something foo",
                      ((EvalDescr) rule.getLhs().getDescrs().get( 1 )).getText() );
        assertEquals( "another foo",
                      ((EvalDescr) rule.getLhs().getDescrs().get( 2 )).getText() );

    }

    public void testLhsSemicolonDelim() throws Exception {
        final RuleDescr rule = parseResource( "lhs_semicolon_delim.drl" ).rule();

        assertNotNull( rule );

        assertEquals( "simple_rule",
                      rule.getName() );

        final AndDescr lhs = rule.getLhs();

        assertNotNull( lhs );

        assertEquals( 3,
                      lhs.getDescrs().size() );

        //System.err.println( lhs.getDescrs() );

        // Check first column
        final ColumnDescr first = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "foo3",
                      first.getIdentifier() );
        assertEquals( "Bar",
                      first.getObjectType() );

        assertEquals( 1,
                      first.getDescrs().size() );

        LiteralDescr constraint = (LiteralDescr) first.getDescrs().get( 0 );

        assertNotNull( constraint );

        assertEquals( "a",
                      constraint.getFieldName() );
        assertEquals( "==",
                      constraint.getEvaluator() );
        assertEquals( "3",
                      constraint.getText() );

        // Check second column
        final ColumnDescr second = (ColumnDescr) lhs.getDescrs().get( 1 );
        assertEquals( "foo4",
                      second.getIdentifier() );
        assertEquals( "Bar",
                      second.getObjectType() );

        assertEquals( 2,
                      second.getDescrs().size() );

        //System.err.println( second.getDescrs() );

        final FieldBindingDescr fieldBindingDescr = (FieldBindingDescr) second.getDescrs().get( 0 );
        assertEquals( "a",
                      fieldBindingDescr.getFieldName() );
        assertEquals( "a4",
                      fieldBindingDescr.getIdentifier() );

        constraint = (LiteralDescr) second.getDescrs().get( 1 );

        assertNotNull( constraint );

        assertEquals( "a",
                      constraint.getFieldName() );
        assertEquals( "==",
                      constraint.getEvaluator() );
        assertEquals( "4",
                      constraint.getText() );

        // Check third column
        final ColumnDescr third = (ColumnDescr) lhs.getDescrs().get( 2 );
        assertNull( third.getIdentifier() );
        assertEquals( "Baz",
                      third.getObjectType() );

        assertEqualsIgnoreWhitespace( "if ( a == b ) { " + "  assert( foo3 );" + "} else {" + "  retract( foo4 );" + "}" + "  System.out.println( a4 );",
                                      rule.getConsequence() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testNotNode() throws Exception {
        final RuleDescr rule = parseResource( "rule_not.drl" ).rule();

        assertNotNull( rule );
        assertEquals( "simple_rule",
                      rule.getName() );

        final AndDescr lhs = rule.getLhs();
        assertEquals( 1,
                      lhs.getDescrs().size() );
        final NotDescr not = (NotDescr) lhs.getDescrs().get( 0 );
        assertEquals( 1,
                      not.getDescrs().size() );
        final ColumnDescr col = (ColumnDescr) not.getDescrs().get( 0 );

        assertEquals( "Cheese",
                      col.getObjectType() );
        assertEquals( 1,
                      col.getDescrs().size() );
        final LiteralDescr lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( "==",
                      lit.getEvaluator() );
        assertEquals( "stilton",
                      lit.getText() );
        assertEquals( "type",
                      lit.getFieldName() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testNotExistWithBrackets() throws Exception {

        final RuleParser parser = parseResource( "not_exist_with_brackets.drl" );

        parser.compilation_unit();
        final PackageDescr pkg = parser.getPackageDescr();

        final RuleDescr rule = (RuleDescr) pkg.getRules().get( 0 );

        assertNotNull( rule );
        assertEquals( "simple_rule",
                      rule.getName() );

        final AndDescr lhs = rule.getLhs();
        assertEquals( 2,
                      lhs.getDescrs().size() );
        final NotDescr not = (NotDescr) lhs.getDescrs().get( 0 );
        assertEquals( 1,
                      not.getDescrs().size() );
        final ColumnDescr col = (ColumnDescr) not.getDescrs().get( 0 );

        assertEquals( "Cheese",
                      col.getObjectType() );

        final ExistsDescr ex = (ExistsDescr) lhs.getDescrs().get( 1 );
        assertEquals( 1,
                      ex.getDescrs().size() );
        final ColumnDescr exCol = (ColumnDescr) ex.getDescrs().get( 0 );
        assertEquals( "Foo",
                      exCol.getObjectType() );

        assertFalse( parser.hasErrors() );
    }

    public void testNotBindindShouldBarf() throws Exception {
        final RuleParser parser = parseResource( "not_with_binding_error.drl" );
        parser.compilation_unit();
        assertTrue( parser.hasErrors() );
    }

    public void testSimpleQuery() throws Exception {
        final QueryDescr query = parseResource( "simple_query.drl" ).query();

        assertNotNull( query );

        assertEquals( "simple_query",
                      query.getName() );

        final AndDescr lhs = query.getLhs();

        assertNotNull( lhs );

        assertEquals( 3,
                      lhs.getDescrs().size() );

        // Check first column
        final ColumnDescr first = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "foo3",
                      first.getIdentifier() );
        assertEquals( "Bar",
                      first.getObjectType() );

        assertEquals( 1,
                      first.getDescrs().size() );

        LiteralDescr constraint = (LiteralDescr) first.getDescrs().get( 0 );

        assertNotNull( constraint );

        assertEquals( "a",
                      constraint.getFieldName() );
        assertEquals( "==",
                      constraint.getEvaluator() );
        assertEquals( "3",
                      constraint.getText() );

        // Check second column
        final ColumnDescr second = (ColumnDescr) lhs.getDescrs().get( 1 );
        assertEquals( "foo4",
                      second.getIdentifier() );
        assertEquals( "Bar",
                      second.getObjectType() );

        assertEquals( 2,
                      second.getDescrs().size() );
        //check it has field bindings.
        final FieldBindingDescr fieldBindingDescr = (FieldBindingDescr) second.getDescrs().get( 0 );
        assertEquals( "a",
                      fieldBindingDescr.getFieldName() );
        assertEquals( "a4",
                      fieldBindingDescr.getIdentifier() );

        constraint = (LiteralDescr) second.getDescrs().get( 1 );

        assertNotNull( constraint );

        assertEquals( "a",
                      constraint.getFieldName() );
        assertEquals( "==",
                      constraint.getEvaluator() );
        assertEquals( "4",
                      constraint.getText() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testQueryRuleMixed() throws Exception {
        final RuleParser parser = parseResource( "query_and_rule.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 4,
                      pack.getRules().size() ); //as queries are rules
        RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( "bar",
                      rule.getName() );

        QueryDescr query = (QueryDescr) pack.getRules().get( 1 );
        assertEquals( "simple_query",
                      query.getName() );

        rule = (RuleDescr) pack.getRules().get( 2 );
        assertEquals( "bar2",
                      rule.getName() );

        query = (QueryDescr) pack.getRules().get( 3 );
        assertEquals( "simple_query2",
                      query.getName() );

        assertFalse( parser.hasErrors() );
    }

    public void testMultipleRules() throws Exception {
        final RuleParser parser = parseResource( "multiple_rules.drl" );
        parser.compilation_unit();

        final PackageDescr pkg = parser.getPackageDescr();
        final List rules = pkg.getRules();

        assertEquals( 2,
                      rules.size() );

        final RuleDescr rule0 = (RuleDescr) rules.get( 0 );
        assertEquals( "Like Stilton",
                      rule0.getName() );

        final RuleDescr rule1 = (RuleDescr) rules.get( 1 );
        assertEquals( "Like Cheddar",
                      rule1.getName() );

        //checkout the first rule
        AndDescr lhs = rule1.getLhs();
        assertNotNull( lhs );
        assertEquals( 1,
                      lhs.getDescrs().size() );
        assertEqualsIgnoreWhitespace( "System.out.println(\"I like \" + t);",
                                      rule0.getConsequence() );

        // Check first column
        ColumnDescr first = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "Cheese",
                      first.getObjectType() );

        //checkout the second rule
        lhs = rule1.getLhs();
        assertNotNull( lhs );
        assertEquals( 1,
                      lhs.getDescrs().size() );
        assertEqualsIgnoreWhitespace( "System.out.println(\"I like \" + t);",
                                      rule1.getConsequence() );

        // Check first column
        first = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "Cheese",
                      first.getObjectType() );

        assertFalse( parser.hasErrors() );
    }

    public void testSimpleExpander() throws Exception {
        final RuleParser parser = parseResource( "simple_expander.drl" );
        final MockExpanderResolver mockExpanderResolver = new MockExpanderResolver();
        parser.setExpanderResolver( mockExpanderResolver );
        parser.compilation_unit();
        final PackageDescr pack = parser.getPackageDescr();
        assertNotNull( pack );
        assertEquals( 1,
                      pack.getRules().size() );

        assertTrue( mockExpanderResolver.checkCalled( "foo.dsl" ) );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( "simple_rule",
                      rule.getName() );

        //now check out the LHS
        assertEquals( 4,
                      rule.getLhs().getDescrs().size() );

        //The rain in spain ... ----> foo : Bar(a==3) (via MockExpander)
        ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( "Bar",
                      col.getObjectType() );
        assertEquals( "foo1",
                      col.getIdentifier() );
        assertEquals( 1,
                      col.getDescrs().size() );
        assertEquals( 6,
                      col.getLine() );

        LiteralDescr lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( "==",
                      lit.getEvaluator() );
        assertEquals( "a",
                      lit.getFieldName() );
        assertEquals( "1",
                      lit.getText() );

        //>Baz() --> not expanded, as it has the magical escape character '>' !!
        col = (ColumnDescr) rule.getLhs().getDescrs().get( 1 );
        assertEquals( "Baz",
                      col.getObjectType() );
        assertEquals( 7,
                      col.getLine() );

        //The rain in spain ... ----> foo : Bar(a==3) (via MockExpander), again...
        col = (ColumnDescr) rule.getLhs().getDescrs().get( 2 );
        assertEquals( "Bar",
                      col.getObjectType() );
        assertEquals( "foo2",
                      col.getIdentifier() );
        assertEquals( 1,
                      col.getDescrs().size() );
        assertEquals( 8,
                      col.getLine() );
        lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( "==",
                      lit.getEvaluator() );
        assertEquals( "a",
                      lit.getFieldName() );
        assertEquals( "2",
                      lit.getText() );

        //>Bar() --> not expanded, as it has the magical escape character '>' !!
        col = (ColumnDescr) rule.getLhs().getDescrs().get( 3 );
        assertEquals( "Bar",
                      col.getObjectType() );
        assertEquals( 9,
                      col.getLine() );

        assertEqualsIgnoreWhitespace( "bar foo3 : Bar(a==3) baz foo4 : Bar(a==4)",
                                      rule.getConsequence() );
        assertTrue( mockExpanderResolver.checkExpanded( "when,The rain in spain falls mainly" ) );
        assertTrue( mockExpanderResolver.checkExpanded( "then,Something else" ) );
        assertTrue( mockExpanderResolver.checkExpanded( "then,Hey dude" ) );

        assertFalse( parser.hasErrors() );
    }

    public void testExpanderErrorsAfterExpansion() throws Exception {

        final ExpanderResolver res = new ExpanderResolver() {
            public Expander get(String name,
                                String config) {
                return new Expander() {
                    public String expand(String scope,
                                         String pattern) {
                        return pattern;
                    }
                };
            }
        };

        final RuleParser parser = parseResource( "expander_post_errors.drl" );
        parser.setExpanderResolver( res );
        parser.compilation_unit();
        assertTrue( parser.hasErrors() );
        final RecognitionException err = (RecognitionException) parser.getErrors().get( 0 );
        assertEquals( 5,
                      err.line );

    }

    public void testExpanderLineSpread() throws Exception {

        final RuleParser parser = parseResource( "expander_spread_lines.drl" );
        final DefaultExpanderResolver res = new DefaultExpanderResolver( new InputStreamReader( this.getClass().getResourceAsStream( "complex.dsl" ) ) );
        parser.setExpanderResolver( res );
        parser.setExpanderDebug( true );
        parser.compilation_unit();
        //        List errorMessages = parser.getErrorMessages();
        //        for ( Iterator iter = errorMessages.iterator(); iter.hasNext(); ) {
        //            String element = (String) iter.next();
        //            System.out.println(element);
        //            
        //        }        

        assertFalse( parser.hasErrors() );

        final PackageDescr pkg = parser.getPackageDescr();
        final RuleDescr rule = (RuleDescr) pkg.getRules().get( 0 );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        final OrDescr or = (OrDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( 2,
                      or.getDescrs().size() );
        assertNotNull( rule.getConsequence() );

    }

    public void testExpanderUnExpandableErrorLines() throws Exception {

        //stubb expander
        final ExpanderResolver res = new ExpanderResolver() {
            public Expander get(String name,
                                String config) {
                return new Expander() {
                    public String expand(String scope,
                                         String pattern) {
                        if ( pattern.startsWith( "Good" ) ) {
                            return pattern;
                        } else {
                            throw new IllegalArgumentException( "whoops" );
                        }

                    }
                };
            }
        };

        final RuleParser parser = parseResource( "expander_line_errors.drl" );
        parser.setExpanderResolver( res );
        parser.compilation_unit();
        assertTrue( parser.hasErrors() );

        final List messages = parser.getErrorMessages();
        assertEquals( messages.size(),
                      parser.getErrors().size() );

        assertEquals( 4,
                      parser.getErrors().size() );
        assertEquals( ExpanderException.class,
                      parser.getErrors().get( 0 ).getClass() );
        assertEquals( 8,
                      ((RecognitionException) parser.getErrors().get( 0 )).line );
        assertEquals( 10,
                      ((RecognitionException) parser.getErrors().get( 1 )).line );
        assertEquals( 12,
                      ((RecognitionException) parser.getErrors().get( 2 )).line );
        assertEquals( 13,
                      ((RecognitionException) parser.getErrors().get( 3 )).line );

        final PackageDescr pack = parser.getPackageDescr();
        assertNotNull( pack );

        final ExpanderException ex = (ExpanderException) parser.getErrors().get( 0 );
        assertTrue( ex.getMessage().indexOf( "whoops" ) > -1 );

    }

    public void testBasicBinding() throws Exception {
        final RuleParser parser = parseResource( "basic_binding.drl" );
        parser.compilation_unit();

        final PackageDescr pkg = parser.getPackageDescr();
        final RuleDescr ruleDescr = (RuleDescr) pkg.getRules().get( 0 );

        final AndDescr lhs = ruleDescr.getLhs();
        assertEquals( 1,
                      lhs.getDescrs().size() );
        final ColumnDescr cheese = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( 1,
                      cheese.getDescrs().size() );
        assertEquals( "Cheese",
                      cheese.getObjectType() );
        assertEquals( 1,
                      lhs.getDescrs().size() );
        final FieldBindingDescr fieldBinding = (FieldBindingDescr) cheese.getDescrs().get( 0 );
        assertEquals( "type",
                      fieldBinding.getFieldName() );

        assertFalse( parser.hasErrors() );
    }

    public void testBoundVariables() throws Exception {
        final RuleParser parser = parseResource( "bindings.drl" );
        parser.compilation_unit();

        final PackageDescr pkg = parser.getPackageDescr();
        final RuleDescr ruleDescr = (RuleDescr) pkg.getRules().get( 0 );

        final AndDescr lhs = ruleDescr.getLhs();
        assertEquals( 2,
                      lhs.getDescrs().size() );
        final ColumnDescr cheese = (ColumnDescr) lhs.getDescrs().get( 0 );
        assertEquals( "Cheese",
                      cheese.getObjectType() );
        assertEquals( 2,
                      lhs.getDescrs().size() );
        FieldBindingDescr fieldBinding = (FieldBindingDescr) cheese.getDescrs().get( 0 );
        assertEquals( "type",
                      fieldBinding.getFieldName() );
        LiteralDescr literalDescr = (LiteralDescr) cheese.getDescrs().get( 1 );
        assertEquals( "type",
                      literalDescr.getFieldName() );
        assertEquals( "==",
                      literalDescr.getEvaluator() );
        assertEquals( "stilton",
                      literalDescr.getText() );

        final ColumnDescr person = (ColumnDescr) lhs.getDescrs().get( 1 );
        fieldBinding = (FieldBindingDescr) person.getDescrs().get( 0 );
        assertEquals( "name",
                      fieldBinding.getFieldName() );
        literalDescr = (LiteralDescr) person.getDescrs().get( 1 );
        assertEquals( "name",
                      literalDescr.getFieldName() );
        assertEquals( "==",
                      literalDescr.getEvaluator() );
        assertEquals( "bob",
                      literalDescr.getText() );

        final BoundVariableDescr variableDescr = (BoundVariableDescr) person.getDescrs().get( 2 );
        assertEquals( "likes",
                      variableDescr.getFieldName() );
        assertEquals( "==",
                      variableDescr.getEvaluator() );
        assertEquals( "$type",
                      variableDescr.getIdentifier() );

        assertFalse( parser.hasErrors() );
    }
    
    public void testOrNesting() throws Exception {
        final RuleParser parser = parseResource( "or_nesting.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertNotNull( pack );
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( "simple_rule",
                      rule.getName() );

        assertEquals(1, rule.getLhs().getDescrs().size());
        
        OrDescr or = (OrDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals(2, or.getDescrs().size());
        
        ColumnDescr first = (ColumnDescr) or.getDescrs().get( 0 );
        assertEquals("Person", first.getObjectType());
        
        AndDescr and = (AndDescr) or.getDescrs().get( 1 );
        assertEquals(2, and.getDescrs().size());
        
        ColumnDescr left = (ColumnDescr) and.getDescrs().get( 0 );
        assertEquals("Person", left.getObjectType());
        
        ColumnDescr right = (ColumnDescr) and.getDescrs().get( 1 );
        assertEquals("Cheese", right.getObjectType());
    }

    /** Test that explicit "&&", "||" works as expected */
    public void testAndOrRules() throws Exception {
        final RuleParser parser = parseResource( "and_or_rule.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertNotNull( pack );
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( "simple_rule",
                      rule.getName() );

        //we will have 2 children under the main And node
        final AndDescr and = rule.getLhs();
        assertEquals( 2,
                      and.getDescrs().size() );

        //check the "&&" part
        final AndDescr join = (AndDescr) and.getDescrs().get( 0 );
        assertEquals( 2,
                      join.getDescrs().size() );

        ColumnDescr left = (ColumnDescr) join.getDescrs().get( 0 );
        ColumnDescr right = (ColumnDescr) join.getDescrs().get( 1 );
        assertEquals( "Person",
                      left.getObjectType() );
        assertEquals( "Cheese",
                      right.getObjectType() );

        assertEquals( 1,
                      left.getDescrs().size() );
        LiteralDescr literal = (LiteralDescr) left.getDescrs().get( 0 );
        assertEquals( "==",
                      literal.getEvaluator() );
        assertEquals( "name",
                      literal.getFieldName() );
        assertEquals( "mark",
                      literal.getText() );

        assertEquals( 1,
                      right.getDescrs().size() );
        literal = (LiteralDescr) right.getDescrs().get( 0 );
        assertEquals( "==",
                      literal.getEvaluator() );
        assertEquals( "type",
                      literal.getFieldName() );
        assertEquals( "stilton",
                      literal.getText() );

        //now the "||" part
        final OrDescr or = (OrDescr) and.getDescrs().get( 1 );
        assertEquals( 2,
                      or.getDescrs().size() );
        left = (ColumnDescr) or.getDescrs().get( 0 );
        right = (ColumnDescr) or.getDescrs().get( 1 );
        assertEquals( "Person",
                      left.getObjectType() );
        assertEquals( "Cheese",
                      right.getObjectType() );
        assertEquals( 1,
                      left.getDescrs().size() );
        literal = (LiteralDescr) left.getDescrs().get( 0 );
        assertEquals( "==",
                      literal.getEvaluator() );
        assertEquals( "name",
                      literal.getFieldName() );
        assertEquals( "mark",
                      literal.getText() );

        assertEquals( 1,
                      right.getDescrs().size() );
        literal = (LiteralDescr) right.getDescrs().get( 0 );
        assertEquals( "==",
                      literal.getEvaluator() );
        assertEquals( "type",
                      literal.getFieldName() );
        assertEquals( "stilton",
                      literal.getText() );

        assertEqualsIgnoreWhitespace( "System.out.println( \"Mark and Michael\" );",
                                      rule.getConsequence() );

        assertFalse( parser.hasErrors() );
    }

    /** test basic foo : Fact() || Fact() stuff */
    public void testOrWithBinding() throws Exception {
        final RuleParser parser = parseResource( "or_binding.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 2,
                      rule.getLhs().getDescrs().size() );

        final OrDescr or = (OrDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( 2,
                      or.getDescrs().size() );

        final ColumnDescr leftCol = (ColumnDescr) or.getDescrs().get( 0 );
        assertEquals( "Person",
                      leftCol.getObjectType() );
        assertEquals( "foo",
                      leftCol.getIdentifier() );
        
        final ColumnDescr rightCol = (ColumnDescr) or.getDescrs().get( 1 );
        assertEquals( "Person",
                      rightCol.getObjectType() );
        assertEquals( "foo",
                      rightCol.getIdentifier() );
        
        final ColumnDescr cheeseDescr = (ColumnDescr) rule.getLhs().getDescrs().get( 1 );
        assertEquals("Cheese", cheeseDescr.getObjectType());
        assertEquals(null, cheeseDescr.getIdentifier());


        assertEqualsIgnoreWhitespace( "System.out.println( \"Mark and Michael\" + bar );",
                                      rule.getConsequence() );

        assertFalse( parser.hasErrors() );
    }

    /** test basic foo : Fact() || Fact() stuff binding to an "or"*/
    public void testOrBindingComplex() throws Exception {
        final RuleParser parser = parseResource( "or_binding_complex.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        final OrDescr or = (OrDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( 2,
                      or.getDescrs().size() );

        //first fact
        final ColumnDescr firstFact = (ColumnDescr) or.getDescrs().get( 0 );
        assertEquals( "Person",
                      firstFact.getObjectType() );
        assertEquals( "foo",
                      firstFact.getIdentifier() );

        //second "option"
        final ColumnDescr secondFact = (ColumnDescr) or.getDescrs().get( 1 );
        assertEquals( "Person",
                      secondFact.getObjectType() );
        assertEquals(1, secondFact.getDescrs().size());
        assertEquals("foo", secondFact.getIdentifier());
        
        assertEqualsIgnoreWhitespace( "System.out.println( \"Mark and Michael\" + bar );",
                                      rule.getConsequence() );

        assertFalse( parser.hasErrors() );
    }

    public void testOrBindingWithBrackets() throws Exception {
        final RuleParser parser = parseResource( "or_binding_with_brackets.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        final OrDescr or = (OrDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( 2,
                      or.getDescrs().size() );

        //first fact
        final ColumnDescr firstFact = (ColumnDescr) or.getDescrs().get( 0 );
        assertEquals( "Person",
                      firstFact.getObjectType() );
        assertEquals( "foo",
                      firstFact.getIdentifier() );

        //second "option"
        final ColumnDescr secondFact = (ColumnDescr) or.getDescrs().get( 0 );
        assertEquals( "Person",
                      secondFact.getObjectType() );
        assertEquals( "foo",
                      secondFact.getIdentifier() );

        assertEqualsIgnoreWhitespace( "System.out.println( \"Mark and Michael\" + bar );",
                                      rule.getConsequence() );

        assertFalse( parser.hasErrors() );
    }

    /** */
    public void testBracketsPrecedence() throws Exception {
        final RuleParser parser = parseResource( "brackets_precedence.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );

        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        final AndDescr rootAnd = (AndDescr) rule.getLhs().getDescrs().get( 0 );

        assertEquals( 2,
                      rootAnd.getDescrs().size() );

        final OrDescr leftOr = (OrDescr) rootAnd.getDescrs().get( 0 );

        assertEquals( 2,
                      leftOr.getDescrs().size() );
        final NotDescr not = (NotDescr) leftOr.getDescrs().get( 0 );
        final ColumnDescr foo1 = (ColumnDescr) not.getDescrs().get( 0 );
        assertEquals( "Foo",
                      foo1.getObjectType() );
        final ColumnDescr foo2 = (ColumnDescr) leftOr.getDescrs().get( 1 );
        assertEquals( "Foo",
                      foo2.getObjectType() );

        final OrDescr rightOr = (OrDescr) rootAnd.getDescrs().get( 1 );

        assertEquals( 2,
                      rightOr.getDescrs().size() );
        final ColumnDescr shoes = (ColumnDescr) rightOr.getDescrs().get( 0 );
        assertEquals( "Shoes",
                      shoes.getObjectType() );
        final ColumnDescr butt = (ColumnDescr) rightOr.getDescrs().get( 1 );
        assertEquals( "Butt",
                      butt.getObjectType() );

        assertFalse( parser.hasErrors() );
    }

    public void testEvalMultiple() throws Exception {
        final RuleParser parser = parseResource( "eval_multiple.drl" );
        parser.compilation_unit();

        System.err.println( parser.getErrorMessages() );

        assertFalse( parser.hasErrors() );

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 4,
                      rule.getLhs().getDescrs().size() );

        final EvalDescr eval = (EvalDescr) rule.getLhs().getDescrs().get( 0 );
        assertEqualsIgnoreWhitespace( "abc(\"foo\") + 5",
                                      eval.getText() );

        final ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 1 );
        assertEquals( "Foo",
                      col.getObjectType() );

    }

    public void testWithEval() throws Exception {
        final RuleParser parser = parseResource( "with_eval.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );
        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 3,
                      rule.getLhs().getDescrs().size() );
        ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( "Foo",
                      col.getObjectType() );
        col = (ColumnDescr) rule.getLhs().getDescrs().get( 1 );
        assertEquals( "Bar",
                      col.getObjectType() );

        final EvalDescr eval = (EvalDescr) rule.getLhs().getDescrs().get( 2 );
        assertEqualsIgnoreWhitespace( "abc(\"foo\")",
                                      eval.getText() );
        assertEqualsIgnoreWhitespace( "Kapow",
                                      rule.getConsequence() );

        assertFalse( parser.hasErrors() );
    }

    public void testWithRetval() throws Exception {
        final RuleParser parser = parseResource( "with_retval.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );

        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );
        final ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( 1,
                      col.getDescrs().size() );
        assertEquals( "Foo",
                      col.getObjectType() );
        final ReturnValueDescr retval = (ReturnValueDescr) col.getDescrs().get( 0 );
        assertEquals( "a + b",
                      retval.getText() );
        assertEquals( "name",
                      retval.getFieldName() );
        assertEquals( "==",
                      retval.getEvaluator() );

        assertFalse( parser.hasErrors() );
    }

    public void testWithPredicate() throws Exception {
        final RuleParser parser = parseResource( "with_predicate.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );

        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );
        final ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( 1,
                      col.getDescrs().size() );

        final PredicateDescr pred = (PredicateDescr) col.getDescrs().get( 0 );
        assertEquals( "age",
                      pred.getFieldName() );
        assertEquals( "$age2",
                      pred.getDeclaration() );
        assertEqualsIgnoreWhitespace( "$age2 == $age1+2",
                                      pred.getText() );

        assertFalse( parser.hasErrors() );
    }

    public void testNotWithConstraint() throws Exception {
        final RuleParser parser = parseResource( "not_with_constraint.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );

        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 2,
                      rule.getLhs().getDescrs().size() );

        ColumnDescr column = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        final FieldBindingDescr fieldBinding = (FieldBindingDescr) column.getDescrs().get( 0 );
        assertEquals( "$likes",
                      fieldBinding.getIdentifier() );

        final NotDescr not = (NotDescr) rule.getLhs().getDescrs().get( 1 );
        column = (ColumnDescr) not.getDescrs().get( 0 );
        final BoundVariableDescr boundVariable = (BoundVariableDescr) column.getDescrs().get( 0 );
        assertEquals( "type",
                      boundVariable.getFieldName() );
        assertEquals( "==",
                      boundVariable.getEvaluator() );
        assertEquals( "$likes",
                      boundVariable.getIdentifier() );

        assertFalse( parser.hasErrors() );
    }

    public void testGlobal() throws Exception {
        final RuleParser parser = parseResource( "globals.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 1,
                      pack.getRules().size() );

        final RuleDescr rule = (RuleDescr) pack.getRules().get( 0 );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );

        assertEquals( 1,
                      pack.getImports().size() );
        assertEquals( 2,
                      pack.getGlobals().values().size() );

        assertEquals( "java.lang.String",
                      pack.getGlobals().get( "foo" ) );
        assertEquals( "java.lang.Integer",
                      pack.getGlobals().get( "bar" ) );

        assertFalse( parser.hasErrors() );
    }

    public void testFunctions() throws Exception {
        final RuleParser parser = parseResource( "functions.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertEquals( 2,
                      pack.getRules().size() );

        final List functions = pack.getFunctions();
        assertEquals( 2,
                      functions.size() );

        FunctionDescr func = (FunctionDescr) functions.get( 0 );
        assertEquals( "functionA",
                      func.getName() );
        assertEquals( "String",
                      func.getReturnType() );
        assertEquals( 2,
                      func.getParameterNames().size() );
        assertEquals( 2,
                      func.getParameterTypes().size() );

        assertEquals( "String",
                      func.getParameterTypes().get( 0 ) );
        assertEquals( "s",
                      func.getParameterNames().get( 0 ) );

        assertEquals( "Integer",
                      func.getParameterTypes().get( 1 ) );
        assertEquals( "i",
                      func.getParameterNames().get( 1 ) );

        assertEqualsIgnoreWhitespace( "foo();",
                                      func.getText() );

        func = (FunctionDescr) functions.get( 1 );
        assertEquals( "functionB",
                      func.getName() );
        assertEqualsIgnoreWhitespace( "bar();",
                                      func.getText() );

        assertFalse( parser.hasErrors() );
    }

    public void testComment() throws Exception {
        final RuleParser parser = parseResource( "comment.drl" );
        parser.compilation_unit();

        final PackageDescr pack = parser.getPackageDescr();
        assertNotNull( pack );

        assertEquals( "foo.bar",
                      pack.getName() );

        assertFalse( parser.hasErrors() );
    }

    public void testAttributes() throws Exception {
        final RuleDescr rule = parseResource( "rule_attributes.drl" ).rule();
        assertEquals( "simple_rule",
                      rule.getName() );
        assertEqualsIgnoreWhitespace( "bar();",
                                      rule.getConsequence() );

        final List attrs = rule.getAttributes();
        assertEquals( 5,
                      attrs.size() );

        AttributeDescr at = (AttributeDescr) attrs.get( 0 );
        assertEquals( "salience",
                      at.getName() );
        assertEquals( "42",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 1 );
        assertEquals( "agenda-group",
                      at.getName() );
        assertEquals( "my_group",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 2 );
        assertEquals( "no-loop",
                      at.getName() );
        assertEquals( "true",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 3 );
        assertEquals( "duration",
                      at.getName() );
        assertEquals( "42",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 4 );
        assertEquals( "activation-group",
                      at.getName() );
        assertEquals( "my_activation_group",
                      at.getValue() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testAttributes_alternateSyntax() throws Exception {
        final RuleDescr rule = parseResource( "rule_attributes_alt.drl" ).rule();
        assertEquals( "simple_rule",
                      rule.getName() );
        assertEqualsIgnoreWhitespace( "bar();",
                                      rule.getConsequence() );

        final List attrs = rule.getAttributes();
        assertEquals( 5,
                      attrs.size() );

        AttributeDescr at = (AttributeDescr) attrs.get( 0 );
        assertEquals( "salience",
                      at.getName() );
        assertEquals( "42",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 1 );
        assertEquals( "agenda-group",
                      at.getName() );
        assertEquals( "my_group",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 2 );
        assertEquals( "no-loop",
                      at.getName() );
        assertEquals( "true",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 3 );
        assertEquals( "duration",
                      at.getName() );
        assertEquals( "42",
                      at.getValue() );

        at = (AttributeDescr) attrs.get( 4 );
        assertEquals( "activation-group",
                      at.getName() );
        assertEquals( "my_activation_group",
                      at.getValue() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testEnumeration() throws Exception {
        final RuleDescr rule = parseResource( "enumeration.drl" ).rule();
        assertEquals( "simple_rule",
                      rule.getName() );
        assertEquals( 1,
                      rule.getLhs().getDescrs().size() );
        final ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals( "Foo",
                      col.getObjectType() );
        assertEquals( 1,
                      col.getDescrs().size() );
        final LiteralDescr lit = (LiteralDescr) col.getDescrs().get( 0 );
        assertEquals( "bar",
                      lit.getFieldName() );
        assertEquals( "==",
                      lit.getEvaluator() );
        assertEquals( "Foo.BAR",
                      lit.getText() );
        assertTrue( lit.isStaticFieldValue() );

        assertFalse( this.parser.hasErrors() );
    }

    public void testExpanderBad() throws Exception {
        final RuleParser parser = parseResource( "bad_expander.drl" );
        try {
            parser.compilation_unit();
            fail( "Should have thrown a wobbly." );
        } catch ( final IllegalArgumentException e ) {
            assertNotNull( e.getMessage() );
        }

        assertFalse( parser.hasErrors() );
    }

    public void testInvalidSyntax_Catches() throws Exception {
        parseResource( "invalid_syntax.drl" ).compilation_unit();
        assertTrue( this.parser.hasErrors() );
    }

    public void testMultipleErrors() throws Exception {
        parseResource( "multiple_errors.drl" ).compilation_unit();
        assertTrue( this.parser.hasErrors() );
        assertEquals( 2,
                      this.parser.getErrors().size() );
    }

    public void testExtraLhsNewline() throws Exception {
        parseResource( "extra_lhs_newline.drl" ).compilation_unit();
        assertFalse( this.parser.hasErrors() );
    }

    public void testStatementOrdering1() throws Exception {
        parseResource( "statement_ordering_1.drl" );
        final MockExpanderResolver mockExpanderResolver = new MockExpanderResolver();
        this.parser.setExpanderResolver( mockExpanderResolver );
        this.parser.compilation_unit();

        final PackageDescr pkg = this.parser.getPackageDescr();

        assertEquals( 2,
                      pkg.getRules().size() );

        assertEquals( "foo",
                      ((RuleDescr) pkg.getRules().get( 0 )).getName() );
        assertEquals( "bar",
                      ((RuleDescr) pkg.getRules().get( 1 )).getName() );

        assertEquals( 2,
                      pkg.getFunctions().size() );

        assertEquals( "cheeseIt",
                      ((FunctionDescr) pkg.getFunctions().get( 0 )).getName() );
        assertEquals( "uncheeseIt",
                      ((FunctionDescr) pkg.getFunctions().get( 1 )).getName() );

        assertEquals( 4,
                      pkg.getImports().size() );
        assertEquals( "im.one",
                      pkg.getImports().get( 0 ) );
        assertEquals( "im.two",
                      pkg.getImports().get( 1 ) );
        assertEquals( "im.three",
                      pkg.getImports().get( 2 ) );
        assertEquals( "im.four",
                      pkg.getImports().get( 3 ) );

        assertFalse( this.parser.hasErrors() );
    }

    public void testRuleNamesStartingWithNumbers() throws Exception {
        parseResource( "rule_names_number_prefix.drl" ).compilation_unit();

        assertFalse( this.parser.hasErrors() );

        final PackageDescr pkg = this.parser.getPackageDescr();

        assertEquals( 2,
                      pkg.getRules().size() );

        assertEquals( "1. Do Stuff!",
                      ((RuleDescr) pkg.getRules().get( 0 )).getName() );
        assertEquals( "2. Do More Stuff!",
                      ((RuleDescr) pkg.getRules().get( 1 )).getName() );
    }

    public void testEvalWithNewline() throws Exception {
        parseResource( "eval_with_newline.drl" ).compilation_unit();

        System.err.println( this.parser.getErrorMessages() );
        assertFalse( this.parser.hasErrors() );
    }

    public void testEvalWithSemicolon() throws Exception {
        parseResource( "eval_with_semicolon.drl" ).compilation_unit();

        assertTrue( this.parser.hasErrors() );
        assertEquals( 1,
                      this.parser.getErrorMessages().size() );
        System.err.println( this.parser.getErrorMessages().get( 0 ) );
        assertTrue( ((String) this.parser.getErrorMessages().get( 0 )).indexOf( "Trailing semi-colon not allowed" ) >= 0 );
    }
    
    public void testEndPosition() throws Exception {
        parseResource( "test_EndPosition.drl" ).compilation_unit();
        RuleDescr rule = (RuleDescr) parser.getPackageDescr().getRules().get( 0 );
        ColumnDescr col = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );
        assertEquals(6, col.getLine());

        
        assertEquals(8, col.getEndLine());
        
    }

    public void testQualifiedClassname() throws Exception {
        parseResource( "qualified_classname.drl" ).compilation_unit();

        assertFalse( this.parser.hasErrors() );

        final PackageDescr pkg = this.parser.getPackageDescr();
        final RuleDescr rule = (RuleDescr) pkg.getRules().get( 0 );

        final ColumnDescr c = (ColumnDescr) rule.getLhs().getDescrs().get( 0 );

        assertEquals( "com.cheeseco.Cheese",
                      c.getObjectType() );
    }

    private RuleParser parse(final String text) throws Exception {
        this.parser = newParser( newTokenStream( newLexer( newCharStream( text ) ) ) );
        return this.parser;
    }

    private RuleParser parse(final String source,
                             final String text) throws Exception {
        this.parser = newParser( newTokenStream( newLexer( newCharStream( text ) ) ) );
        this.parser.setSource( source );
        return this.parser;
    }

    private RuleParser parseResource(final String name) throws Exception {

        System.err.println( getClass().getResource( name ) );
        final InputStream in = getClass().getResourceAsStream( name );

        final InputStreamReader reader = new InputStreamReader( in );

        final StringBuffer text = new StringBuffer();

        final char[] buf = new char[1024];
        int len = 0;

        while ( (len = reader.read( buf )) >= 0 ) {
            text.append( buf,
                         0,
                         len );
        }

        return parse( name,
                      text.toString() );
    }

    private CharStream newCharStream(final String text) {
        return new ANTLRStringStream( text );
    }

    private RuleParserLexer newLexer(final CharStream charStream) {
        return new RuleParserLexer( charStream );
    }

    private TokenStream newTokenStream(final Lexer lexer) {
        return new CommonTokenStream( lexer );
    }

    private RuleParser newParser(final TokenStream tokenStream) {
        final RuleParser p = new RuleParser( tokenStream );
        //p.setParserDebug( true );
        return p;
    }

    private void assertEqualsIgnoreWhitespace(final String expected,
                                              final String actual) {
        final String cleanExpected = expected.replaceAll( "\\s+",
                                                    "" );
        final String cleanActual = actual.replaceAll( "\\s+",
                                                "" );

        assertEquals( cleanExpected,
                      cleanActual );
    }

}