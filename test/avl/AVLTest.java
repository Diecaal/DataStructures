package avl;

import static org.junit.Assert.*;
import org.junit.Test;

public class AVLTest {

	@Test
	public void test_A() {

		AVLTree<Integer> t1 = new AVLTree<Integer>();

		t1.add(3);
		System.out.println(t1.toString());
		assertEquals("3(0)--", t1.toString());

		t1.add(2);
		System.out.println(t1.toString());
		assertEquals("3(-1)2(0)---", t1.toString());

		t1.add(1);
		System.out.println(t1.toString());
		assertEquals("2(0)1(0)--3(0)--", t1.toString());

		t1.add(0);
		System.out.println(t1.toString());
		assertEquals("2(-1)1(-1)0(0)---3(0)--", t1.toString());

		t1.add(-1);
		System.out.println(t1.toString());
		assertEquals("2(-1)0(0)-1(0)--1(0)--3(0)--", t1.toString());

		t1.add(6);
		System.out.println(t1.toString());
		assertEquals("2(0)0(0)-1(0)--1(0)--3(1)-6(0)--", t1.toString());
		assertEquals("2(0)0(0)-1(0)--1(0)--3(1)-6(0)--", t1.toString());

		t1.add(7);
		System.out.println(t1.toString());
		assertEquals("2(0)0(0)-1(0)--1(0)--6(0)3(0)--7(0)--", t1.toString());

		t1.add(8);
		t1.add(9);
		System.out.println(t1.toString());
		assertEquals("2(1)0(0)-1(0)--1(0)--6(1)3(0)--8(0)7(0)--9(0)--", t1.toString());

		t1.add(10);
		System.out.println(t1.toString());
		assertEquals("2(1)0(0)-1(0)--1(0)--8(0)6(0)3(0)--7(0)--9(1)-10(0)--", t1.toString());

		t1.add(11);
		System.out.println(t1.toString());
		assertEquals("2(1)0(0)-1(0)--1(0)--8(0)6(0)3(0)--7(0)--10(0)9(0)--11(0)--", t1.toString());

		t1.add(12);
		System.out.println(t1.toString());
		assertEquals("8(0)2(0)0(0)-1(0)--1(0)--6(0)3(0)--7(0)--10(1)9(0)--11(1)-12(0)--", t1.toString());
	}

	@Test
	public void test_B() {

		AVLTree<Character> tc = new AVLTree<Character>();

		tc.add('a');
		System.out.println(tc.toString());
		assertEquals("a(0)--", tc.toString());

		tc.add('b');
		System.out.println(tc.toString());
		assertEquals("a(1)-b(0)--", tc.toString());

		tc.add('c');
		System.out.println(tc.toString());
		assertEquals("b(0)a(0)--c(0)--", tc.toString());
	}


	@Test
	public void test_C() {

		AVLTree<Integer> ti = new AVLTree<Integer>();

		ti.add(7);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("7(0)--", ti.toString());

		ti.add(6);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("7(-1)6(0)---", ti.toString());

		ti.add(5);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("6(0)5(0)--7(0)--", ti.toString());

		ti.add(4);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("6(-1)5(-1)4(0)---7(0)--", ti.toString());

		ti.add(3);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("6(-1)4(0)3(0)--5(0)--7(0)--", ti.toString());

		ti.add(2);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("4(0)3(-1)2(0)---6(0)5(0)--7(0)--", ti.toString());

		ti.add(1);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--6(0)5(0)--7(0)--", ti.toString());

		ti.add(8);
		System.out.println(ti.toString());
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--6(1)5(0)--7(1)-8(0)--", ti.toString());

		ti.add(9);
		System.out.println("9 -> " + ti.toString() + ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--6(1)5(0)--8(0)7(0)--9(0)--", ti.toString());

		ti.add(10);
		System.out.println("ADD 10 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--8(0)6(0)5(0)--7(0)--9(1)-10(0)--", ti.toString());

		ti.remove(10);
		System.out.println("DELETE 10 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--8(-1)6(0)5(0)--7(0)--9(0)--", ti.toString());

		ti.remove(8);
		System.out.println("DELETE 8 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--7(-1)6(-1)5(0)---9(0)--", ti.toString());

		ti.remove(9);
		System.out.println("DELETE 9 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--6(0)5(0)--7(0)--", ti.toString());

		ti.remove(6);
		System.out.println("DELETE 6 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--5(1)-7(0)--", ti.toString());

		ti.remove(5);
		System.out.println("DELETE 5 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(-1)2(0)1(0)--3(0)--7(0)--", ti.toString());

		ti.remove(7);
		System.out.println("DELETE 7 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("2(1)1(0)--4(-1)3(0)---", ti.toString());
	}

	
	@Test
	public void test_AVL_2() {

		AVLTree<Integer> ti = new AVLTree<Integer>();

		ti.add(1);
		System.out.println(ti.toString());
		assertEquals("1(0)--", ti.toString());

		ti.add(2);
		System.out.println(ti.toString());
		assertEquals("1(1)-2(0)--", ti.toString());

		ti.add(3);
		System.out.println(ti.toString());
		assertEquals("2(0)1(0)--3(0)--", ti.toString());

		ti.add(4);
		System.out.println(ti.toString());
		assertEquals("2(1)1(0)--3(1)-4(0)--", ti.toString());

		ti.add(5);
		System.out.println(ti.toString());
		assertEquals("2(1)1(0)--4(0)3(0)--5(0)--", ti.toString());

		ti.add(6);
		System.out.println(ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--5(1)-6(0)--", ti.toString());

		ti.add(10);
		System.out.println(ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--6(0)5(0)--10(0)--", ti.toString());

		ti.add(11);
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--6(1)5(0)--10(1)-11(0)--", ti.toString());

		ti.add(8);
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--6(1)5(0)--10(0)8(0)--11(0)--", ti.toString());

		ti.add(7);
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--8(0)6(0)5(0)--7(0)--10(1)-11(0)--", ti.toString());

	}

	@Test
	public void test_AVL_3() {

		AVLTree<Integer> ti = new AVLTree<Integer>();

		ti.add(5);
		System.out.println(ti.toString());
		assertEquals("5(0)--", ti.toString());

		ti.add(2);
		System.out.println(ti.toString());
		assertEquals("5(-1)2(0)---", ti.toString());

		ti.add(10);
		System.out.println(ti.toString());
		assertEquals("5(0)2(0)--10(0)--", ti.toString());

		ti.add(15);
		System.out.println(ti.toString());
		assertEquals("5(1)2(0)--10(1)-15(0)--", ti.toString());

		ti.add(12);
		System.out.println(ti.toString());
		assertEquals("5(1)2(0)--12(0)10(0)--15(0)--", ti.toString());

		ti.add(9);
		System.out.println(ti.toString());
		assertEquals("10(0)5(0)2(0)--9(0)--12(1)-15(0)--", ti.toString());

		ti.add(7);
		System.out.println(ti.toString());
		assertEquals("10(-1)5(1)2(0)--9(-1)7(0)---12(1)-15(0)--", ti.toString());

		ti.add(8);
		System.out.println(ti.toString());
		assertEquals("10(-1)5(1)2(0)--8(0)7(0)--9(0)--12(1)-15(0)--", ti.toString());

		ti.add(6);
		System.out.println(ti.toString());
		assertEquals("10(-1)7(0)5(0)2(0)--6(0)--8(1)-9(0)--12(1)-15(0)--", ti.toString());
	}

	@Test
	public void test_AVL_4() {

		AVLTree<Integer> ti = new AVLTree<Integer>();

		ti.add(1);
		System.out.println(ti.toString());
		assertEquals("1(0)--", ti.toString());

		ti.add(2);
		System.out.println(ti.toString());
		assertEquals("1(1)-2(0)--", ti.toString());

		ti.add(3);
		System.out.println(ti.toString());
		assertEquals("2(0)1(0)--3(0)--", ti.toString());

		ti.add(4);
		System.out.println(ti.toString());
		assertEquals("2(1)1(0)--3(1)-4(0)--", ti.toString());

		ti.add(5);
		System.out.println(ti.toString());
		assertEquals("2(1)1(0)--4(0)3(0)--5(0)--", ti.toString());

		ti.add(6);
		System.out.println(ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--5(1)-6(0)--", ti.toString());

		ti.add(10);
		System.out.println(ti.toString());
		assertEquals("4(0)2(0)1(0)--3(0)--6(0)5(0)--10(0)--", ti.toString());

		ti.add(11);
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--6(1)5(0)--10(1)-11(0)--", ti.toString());

		ti.add(8);
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--6(1)5(0)--10(0)8(0)--11(0)--", ti.toString());

		ti.add(7);
		System.out.println(ti.toString());
		assertEquals("4(1)2(0)1(0)--3(0)--8(0)6(0)5(0)--7(0)--10(1)-11(0)--", ti.toString());

		ti.remove(1);
		System.out.println("DELETE 1 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("4(1)2(1)-3(0)--8(0)6(0)5(0)--7(0)--10(1)-11(0)--", ti.toString());

		ti.remove(3);
		System.out.println("DELETE 3 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("8(-1)4(1)2(0)--6(0)5(0)--7(0)--10(1)-11(0)--", ti.toString());

		ti.remove(4);
		System.out.println("DELETE 4 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("8(-1)6(-1)2(1)-5(0)--7(0)--10(1)-11(0)--", ti.toString());

		ti.remove(7);
		System.out.println("DELETE 7 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("8(0)5(0)2(0)--6(0)--10(1)-11(0)--", ti.toString());

		ti.remove(11);
		System.out.println("DELETE 11 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("8(-1)5(0)2(0)--6(0)--10(0)--", ti.toString());

		ti.remove(10);
		System.out.println("DELETE 10 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("5(1)2(0)--8(-1)6(0)---", ti.toString());
	}

	@Test
	public void test_AVL_5() {

		AVLTree<Integer> ti = new AVLTree<Integer>();

		ti.add(10);
		ti.add(6);
		ti.add(15);
		ti.add(3);
		ti.add(9);
		ti.add(14);
		ti.add(20);
		ti.add(2);
		ti.add(4);
		ti.add(7);
		ti.add(12);
		ti.add(1);

		System.out.println(ti.toString());
		assertEquals("10(-1)6(-1)3(-1)2(-1)1(0)---4(0)--9(-1)7(0)---15(-1)14(-1)12(0)---20(0)--", ti.toString());
//	    assertEquals (5, ti.getHeight());


		ti.remove(20);
		System.out.println("DELETE 20 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("6(0)3(-1)2(-1)1(0)---4(0)--10(0)9(-1)7(0)---14(0)12(0)--15(0)--", ti.toString());
//	    assertEquals (4, ti.getHeight());

		ti.remove(4);
		System.out.println("DELETE 4 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("6(1)2(0)1(0)--3(0)--10(0)9(-1)7(0)---14(0)12(0)--15(0)--", ti.toString());
//	    assertEquals (4, ti.getHeight());

		ti.remove(10);
		System.out.println("DELETE 10 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("6(1)2(0)1(0)--3(0)--9(1)7(0)--14(0)12(0)--15(0)--", ti.toString());
//	    assertEquals (4, ti.geteight());

		ti.remove(9);
		System.out.println("DELETE 9 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("6(1)2(0)1(0)--3(0)--14(-1)7(1)-12(0)--15(0)--", ti.toString());
//	    assertEquals (4, ti.getHeight());

		ti.remove(6);
		System.out.println("DELETE 6 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("3(1)2(-1)1(0)---14(-1)7(1)-12(0)--15(0)--", ti.toString());
//	    assertEquals (4, ti.getHeight());

		ti.remove(3);
		System.out.println("DELETE 3 -> " + ti.toString() + " *** " + ti.toString());
		assertEquals("7(0)2(-1)1(0)---14(0)12(0)--15(0)--", ti.toString());
//	    assertEquals (3, ti.getHeight());

	}


	@Test
	public void test_L7() {

		AVLTree<Character> a = new AVLTree<Character>();

		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		assertEquals(true, a.search('a'));
		assertEquals(false, a.search('f'));

		a = new AVLTree<Character>();

		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		assertEquals('d', (char) a.getMax(a.getRoot()));

		a = new AVLTree<Character>();

		a.add('b');
		a.add('a');
		a.add('d');
		a.add('c');
		assertEquals("b(1)a(0)--d(-1)c(0)---", a.toString());

		a.remove('d');
		assertEquals("b(0)a(0)--c(0)--", a.toString());
		a.remove('b');
		assertEquals("a(1)-c(0)--", a.toString());

		a = new AVLTree<Character>();

		a.add('b');
		assertEquals("b(0)--", a.toString());
		a.add('a');
		assertEquals("b(-1)a(0)---", a.toString());
		a.add('d');
		assertEquals("b(0)a(0)--d(0)--", a.toString());
		a.add('c');
		assertEquals("b(1)a(0)--d(-1)c(0)---", a.toString());

	}

}
