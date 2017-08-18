package testOne;

import static org.junit.Assert.*;

import one.Analysis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnalysisTest {

	private static Analysis analysis = new Analysis();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testIsOperator() {
		assertEquals(0, analysis.isOperator("-+"));
		assertEquals(21, analysis.isOperator("+"));
	}
	@Test
	public void testIsDelimeter() {
		assertEquals(0, analysis.isDelimeter("?"));
		assertEquals(42, analysis.isDelimeter(";"));
	}
	@Test
	public void testIsKeyword() {
		assertEquals(0, analysis.isKeyword("hello"));
		assertEquals(1, analysis.isKeyword("int"));
	}
	@Test
	public void testIsIdent() {// 判断标识符是否合法
		assertEquals(false, analysis.isIdent("?"));
		assertEquals(true, analysis.isIdent("hello"));
	}
}
