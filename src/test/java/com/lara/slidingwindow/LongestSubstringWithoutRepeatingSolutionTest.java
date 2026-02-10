package com.lara.slidingwindow;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LongestSubstringWithoutRepeatingSolutionTest {

    private LongestSubstringWithoutRepeatingSolution solution;

    @BeforeEach
    public void setUp() {
        solution = new LongestSubstringWithoutRepeatingSolution();
    }

    @Test
    public void longestSubstring_NullString_WithoutRepeating_ReturnsZero() {
        assertEquals(0, solution.longestSubstringWithoutRepeating(null));
    }

    @Test
    public void longestSubstring_EmptyString_WithoutRepeating_ReturnsZero() {
        assertEquals(0, solution.longestSubstringWithoutRepeating(""));
    }

    @Test
    public void lengthOfLongestSubstring_AllUniqueCharacters_ReturnsLengthWithoutRepeatingWithoutRepeating() {
        assertEquals(6, solution.longestSubstringWithoutRepeating("abcdef"));
    }

    @Test
    public void lengthOfLongestSubstring_WithRepeatingCharacters_ReturnsMaxLengthWithoutRepeatingWithoutRepeating() {
        assertEquals(3, solution.longestSubstringWithoutRepeating("abcabc"));
    }

    @Test
    public void longestSubstring_AllSameCharacters_ReturnsOneWithoutRepeating() {
        assertEquals(1, solution.longestSubstringWithoutRepeating("aaaa"));
    }

    @Test
    public void lengthOfLongestSubstring_MixedCharacters_ReturnsMaxLengthWithoutRepeatingWithoutRepeating() {
        assertEquals(3, solution.longestSubstringWithoutRepeating("abcabcbb"));
    }


    @Test
    public void lengthOfLongestSubstring_MixedCharacters_ReturnsMaxLength1WithoutRepeatingWithoutRepeating() {
        assertEquals(3, solution.longestSubstringWithoutRepeating("dvdf"));
    }


}
