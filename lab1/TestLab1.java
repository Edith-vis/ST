package cn.tjucic.st;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestLab1 {

	Lab1 lab;
	
	@Before
	public void setUp(){
		lab = new Lab1();
	}
	
	//@Test
	//public void test1(){
	//	assertEquals(true, lab.Can(83));
	//}
	
	//@Test
	//public void test2(){
	//	assertEquals(false, lab.Can(-1));
	//}
	
	@Test
	public void test3(){
		assertEquals(false, lab.Can(1000));
	}
	
}