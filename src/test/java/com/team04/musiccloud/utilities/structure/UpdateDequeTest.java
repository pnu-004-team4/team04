package com.team04.musiccloud.utilities.structure;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UpdateDequeTest {

  private UpdateDeque<Integer> deque;

  @Before
  public void setUp() {
    deque = new UpdateDeque<>(3);
  }

  @Test
  public void addFirst() {
    deque.add(1);
    deque.add(2);
    deque.addFirst(3);

    assertEquals(3, deque.peekFirst().intValue());
    assertTrue(deque.isFull());

    deque.addFirst(4);

    assertEquals(4, deque.peekFirst().intValue());
    assertTrue(deque.isFull());
  }

  @Test
  public void addLast() {
    deque.add(1);
    deque.add(2);
    deque.addLast(3);

    assertEquals(3, deque.peekLast().intValue());
    assertTrue(deque.isFull());

    deque.addLast(4);

    assertEquals(4, deque.peekLast().intValue());
    assertTrue(deque.isFull());
  }

  @Test
  public void add() {
    deque.add(1);
    deque.add(2);
    deque.add(3);

    assertEquals(3, deque.peekLast().intValue());
    assertTrue(deque.isFull());

    deque.add(4);

    assertEquals(4, deque.peekLast().intValue());
    assertTrue(deque.isFull());
  }

  @Test
  public void offerFirst() {
    deque.offer(1);
    deque.offer(2);
    deque.offerFirst(3);

    assertEquals(3, deque.peekFirst().intValue());
    assertTrue(deque.isFull());

    deque.offerFirst(4);

    assertEquals(4, deque.peekFirst().intValue());
    assertTrue(deque.isFull());
  }

  @Test
  public void offerLast() {
    deque.offer(1);
    deque.offer(2);
    deque.offerLast(3);

    assertEquals(3, deque.peekLast().intValue());
    assertTrue(deque.isFull());

    deque.offerLast(4);

    assertEquals(4, deque.peekLast().intValue());
    assertTrue(deque.isFull());
  }

  @Test
  public void offer() {
    deque.offer(1);
    deque.offer(2);
    deque.offer(3);

    assertEquals(3, deque.peekLast().intValue());
    assertTrue(deque.isFull());

    deque.offer(4);

    assertEquals(4, deque.peekLast().intValue());
    assertTrue(deque.isFull());
  }

  @Test
  public void push() {
    deque.push(1);
    deque.push(2);
    deque.push(3);

    assertEquals(3, deque.peek().intValue());
    assertTrue(deque.isFull());

    deque.push(4);

    assertEquals(4, deque.peek().intValue());
    assertTrue(deque.isFull());
  }
}
