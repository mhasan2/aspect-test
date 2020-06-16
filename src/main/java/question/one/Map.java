package question.one;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Map {

  private Map() {
  }

  //  Unsure what is expected in this question.
  //  Do you expect a deep copy of input list to ensure original list is unchanged?

  //  Alternative to streams is to manually create a new list. Then iterate over input list and
  //  apply function to each element, storing the result in new list

  /**
   * @param function {@link Function} to apply to each element of input list - should not modify
   *                 input values
   * @param input    {@link List} to apply function to
   * @return {@link List} result of function applied to input
   */
  public static <I, O> List<O> map(Function<I, ? extends O> function, List<? extends I> input) {
    return input.stream().map(function).collect(Collectors.toList());
  }
}
