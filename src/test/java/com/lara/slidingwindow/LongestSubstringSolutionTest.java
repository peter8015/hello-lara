package com.lara.slidingwindow;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LongestSubstringSolutionTest {

    private LongestSubstringSolution solution;

    @BeforeEach
    public void setUp() {
        solution = new LongestSubstringSolution();
    }

    @Test
    public void lengthOfLongestSubstring_NullString_ReturnsZero() {
        assertEquals(0, solution.lengthOfLongestSubstring(null));
    }

    @Test
    public void lengthOfLongestSubstring_EmptyString_ReturnsZero() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    public void lengthOfLongestSubstring_AllUniqueCharacters_ReturnsLength() {
        assertEquals(6, solution.lengthOfLongestSubstring("abcdef"));
    }

    @Test
    public void lengthOfLongestSubstring_WithRepeatingCharacters_ReturnsMaxLength() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabc"));
    }

    @Test
    public void lengthOfLongestSubstring_AllSameCharacters_ReturnsOne() {
        assertEquals(1, solution.lengthOfLongestSubstring("aaaa"));
    }

    @Test
    public void lengthOfLongestSubstring_MixedCharacters_ReturnsMaxLength() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }


    @Test
    public void lengthOfLongestSubstring_MixedCharacters_ReturnsMaxLength1() {
        assertEquals(3, solution.lengthOfLongestSubstring("dvdf"));
    }


}
