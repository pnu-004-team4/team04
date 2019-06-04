package com.team04.musiccloud.utilities.structure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FixedDequeTest {

  private FixedDeque<Integer> deque;

  @Before
  public void setUp() {
    deque = new FixedDeque<>(3);
  }

  @Test
  public void equalityTest() {
    FixedDeque deque1 = new FixedDeque(3);
    FixedDeque deque2 = new FixedDeque(3);
    FakeDeque fakeDeque = new FakeDeque();

    assertEquals(deque1, deque1);
    assertEquals(deque1, deque2);
    assertNotEquals(null, deque2);
    assertNotEquals(deque1, fakeDeque);

    assertEquals(deque1.hashCode(), deque2.hashCode());
  }

  @Test
  public void getCapacity() {
    assertEquals(3, deque.getCapacity());
  }

  @Test
  public void isFull() {
    assertFalse(deque.isFull());

    deque.add(1);
    deque.add(2);
    deque.add(3);

    assertTrue(deque.isFull());
  }

  @Test(expected = OverSizeLimitException.class)
  public void addFirst() {
    deque.add(1);
    deque.add(2);
    deque.addFirst(3);

    assertEquals(3, deque.peekFirst().intValue());
    assertTrue(deque.isFull());

    deque.addFirst(4);
  }

  @Test(expected = OverSizeLimitException.class)
  public void addLast() {
    deque.add(1);
    deque.add(2);
    deque.addLast(3);

    assertEquals(3, deque.peekLast().intValue());
    assertTrue(deque.isFull());

    deque.addLast(4);
  }

  @Test
  public void offerFirst() {
    deque.offer(1);
    deque.offer(2);
    deque.offerFirst(3);

    assertEquals(3, deque.peekFirst().intValue());
    assertTrue(deque.isFull());
    assertFalse(deque.offerFirst(4));
  }

  @Test
  public void offerLast() {
    deque.offer(1);
    deque.offer(2);
    deque.offerLast(3);

    assertEquals(3, deque.peekLast().intValue());
    assertTrue(deque.isFull());
    assertFalse(deque.offerLast(4));
  }

  @Test(expected = OverSizeLimitException.class)
  public void push() {
    deque.push(1);
    deque.push(2);
    deque.push(3);

    assertEquals(3, deque.peek().intValue());
    assertTrue(deque.isFull());

    deque.push(4);
  }

  private class FakeDeque {

  }
}
