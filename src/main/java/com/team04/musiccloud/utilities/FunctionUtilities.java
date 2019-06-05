package com.team04.musiccloud.utilities;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionUtilities {

  public static <T, U> void setOnCondition(T source, Predicate<T> condition,
      Function<T, U> getter, Consumer<U> setter) {
    if (condition.test(source)) {
      setter.accept(getter.apply(source));
    }
  }
}
